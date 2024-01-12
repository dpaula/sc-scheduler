package com.dpaula.scscheduler.api.model;

import com.dpaula.scscheduler.domain.entity.valueobject.HealthCheckConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheckConfigModel {
    @NotNull
    private HealthCheckConfig.Type type;
    @NotNull
    private Integer timeout;
}
