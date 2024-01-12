package com.dpaula.scscheduler.domain.entity;

import com.dpaula.scscheduler.domain.entity.valueobject.HealthCheckConfig;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

@Entity
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private HealthCheckConfig checkConfig;

    private Integer runInterval;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_id", columnDefinition = "char(36)")
    private App app;

    protected Schedule() {
        //For JPA/Hibernate
    }

    public Schedule(UUID id, HealthCheckConfig checkConfig, Integer runInterval, App app) {
        Validate.notNull(checkConfig);
        Validate.notNull(runInterval);
        Validate.notNull(app);
        this.id = id;
        this.checkConfig = checkConfig;
        this.runInterval = runInterval;
        this.app = app;
    }

    public void update(Schedule schedule) {
        Validate.notNull(schedule);
        this.checkConfig = schedule.getCheckConfig();
        this.runInterval = schedule.getRunInterval();
    }
}
