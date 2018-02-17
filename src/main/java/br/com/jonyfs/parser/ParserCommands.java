package br.com.jonyfs.parser;

import br.com.jonyfs.parser.log.LogRepository;
import br.com.jonyfs.parser.log.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@ShellComponent
public class ParserCommands {

    @Resource
    LogService logService;

    @Resource
    LogRepository logRepository;

    @ShellMethod("Parse the log file and search data. Example: parse path-to-log-file start-date(in yyyy-MM-dd.HH.mm.ss format) duration(hourly or daily) threshold(greather than 0)")
    public List parse(@ShellOption() String accesslog, @ShellOption() String startDate, @ShellOption() Duration duration, @ShellOption() Long threshold) {
        load(accesslog);

        return query(startDate, duration, threshold);
    }

    @ShellMethod("Load data file. Example: load path-to-log-file")
    public String load(@ShellOption() String accesslog) {
        logService.load(accesslog);
        return "Loaded";
    }

    @ShellMethod("count data rows on database")
    public Long count() {
        return logRepository.count();
    }

    @ShellMethod("clear all data rows on database")
    public String clean() {
        LOGGER.info("cleanning all data rows...");
        logRepository.deleteAll();
        LOGGER.info("OK");
        return "OK!";
    }

    @ShellMethod("Search data from database. Example: query start-date(in yyyy-MM-dd.HH.mm.ss format) duration(hourly or daily) threshold(greather than 0)")
    public List query(@ShellOption() String startDate, @ShellOption() Duration duration, @ShellOption() Long threshold) {
        LOGGER.info("Searching with startDate: {}, duration: {} and threshold: {}...", startDate, duration, threshold);
        return logService.search(startDate, duration, threshold);
    }
}
