package br.com.jonyfs.parser.log;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@QueryEntity
@ToString
@Entity
public class Log implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    @Setter
    private LocalDateTime date;

    @Column
    @Setter
    @NotEmpty
    private String ip;

    @Column
    @Setter
    @NotEmpty
    private String request;

    @Column
    @Setter
    @NotNull
    @Min(0)
    private Long status;

    @Column
    @Setter
    @NotEmpty
    private String userAgent;

    @Nullable
    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
