package com.dpaula.scscheduler.domain.entity.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

@Getter
@Builder
@Embeddable
public class HealthCheckConfig {
    private Type type;
    private Integer timeout;

    protected HealthCheckConfig() {
        //JPA Hibernate
    }

    public HealthCheckConfig(Type type, Integer timeout) {
        Validate.notNull(type);
        Validate.notNull(timeout);
        this.type = type;
        this.timeout = timeout;
    }

    public enum Type {
        HTTP,
        PING;
    }
}
