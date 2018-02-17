package br.com.jonyfs.parser.log;

import br.com.jonyfs.parser.Duration;
import br.com.jonyfs.parser.ParserConstants;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
public class LogService {

    @Resource
    LogRepository logRepository;

    @Resource
    EntityManagerFactory entityManagerFactory;

    public void load(String accesslog) {
        LOGGER.info("Reading file {}...", accesslog);
        try (Stream<String> stream = Files.lines(Paths.get(accesslog))) {

            stream.forEach(this::parse);

        } catch (IOException e) {
            throw new RuntimeException("Fail to open file ", e);
        }
        LOGGER.info("OK!");
    }

    private void parse(String line) {
        int i = 0;
        String[] data = line.split("\\|");
        Log log = new Log();
        log.setDate(LocalDateTime.parse(data[i++], ParserConstants.FORMATTER_LOG));
        log.setIp(data[i++]);
        log.setRequest(data[i++]);
        log.setStatus(Long.valueOf(data[i++]));
        log.setUserAgent(data[i++]);
        logRepository.save(log);
    }

    public List search(String startDate,
                       Duration duration,
                       Long threshold) {
        QLog log = QLog.log;
        LocalDateTime initialDate;
        LocalDateTime finalDate;
        initialDate = LocalDateTime.parse(startDate, ParserConstants.FORMATTER_QUERY);
        if (Duration.hourly.equals(duration)) {
            finalDate = initialDate.plusHours(1);
        } else {
            finalDate = initialDate.plusDays(1);
        }

        JPAQuery query = new JPAQueryFactory(entityManagerFactory.createEntityManager())
                .select(log.ip, log.count())
                .from(log)
                .where(log.date.between(initialDate, finalDate))
                .groupBy(log.ip)
                .having(log.count().goe(threshold))
                .orderBy(log.count().desc());
        return query.fetch();
    }
}
