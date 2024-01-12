package com.dpaula.scscheduler.api.model;

import com.dpaula.scscheduler.api.validators.ValidInterval;
import jakarta.validation.Valid;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Data
public class ScheduleCreateRequest {

    @ValidInterval
    private Integer runInterval;
    @NotNull
    @Valid
    private HealthCheckConfigModel checkConfig;
    @NotNull
    private UUID appId;
}
