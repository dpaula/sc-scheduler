package com.dpaula.scscheduler.api.controller;

import com.dpaula.scscheduler.api.model.HealthCheckConfigModel;
import com.dpaula.scscheduler.api.model.ScheduleCreateRequest;
import com.dpaula.scscheduler.api.model.ScheduleModel;
import com.dpaula.scscheduler.api.model.ScheduleUpdateRequest;
import com.dpaula.scscheduler.core.Mapper;
import com.dpaula.scscheduler.domain.entity.App;
import com.dpaula.scscheduler.domain.entity.Schedule;
import com.dpaula.scscheduler.domain.entity.valueobject.HealthCheckConfig;
import com.dpaula.scscheduler.domain.repository.AppRepository;
import com.dpaula.scscheduler.domain.repository.ScheduleRepository;
import com.dpaula.scscheduler.domain.service.ScheduleManagementService;
import com.dpaula.scscheduler.domain.service.exception.AppNotFoundException;
import com.dpaula.scscheduler.domain.service.exception.ScheduleNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleManagementService scheduleManagementService;
    private final ScheduleRepository schedules;
    private final AppRepository apps;
    private final Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleModel create(@RequestBody @Valid ScheduleCreateRequest request) {
        Schedule schedule = scheduleManagementService.create(toDomain(request));
        return mapper.map(schedule, ScheduleModel.class);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleModel update(@PathVariable UUID scheduleId,
                            @RequestBody @Valid ScheduleUpdateRequest request) {
        Schedule schedule = scheduleManagementService.update(toDomain(scheduleId, request));
        return mapper.map(schedule, ScheduleModel.class);
    }

    @DeleteMapping("/{scheduleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID scheduleId) {
        scheduleManagementService.delete(scheduleId);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleModel getById(@PathVariable UUID scheduleId) {
        Schedule schedule = schedules.findById(scheduleId)
                .orElseThrow(ScheduleNotFoundException::new);

        return mapper.map(schedule, ScheduleModel.class);
    }

    @GetMapping
    public List<ScheduleModel> getAll() {
        return schedules.findAll().stream()
                .map(schedule -> mapper.map(schedule, ScheduleModel.class))
                .toList();
    }

    private Schedule toDomain(ScheduleCreateRequest request) {
        HealthCheckConfigModel checkConfig = request.getCheckConfig();
        return Schedule.builder()
                .runInterval(request.getRunInterval())
                .app(findAppById(request.getAppId()))
                .checkConfig(HealthCheckConfig.builder()
                        .type(checkConfig.getType())
                        .timeout(checkConfig.getTimeout())
                        .build())
                .build();
    }

    private Schedule toDomain(UUID scheduleId, ScheduleUpdateRequest request) {
        HealthCheckConfigModel checkConfig = request.getCheckConfig();
        return Schedule.builder()
                .id(scheduleId)
                .app(findAppById(request.getAppId()))
                .runInterval(request.getRunInterval())
                .checkConfig(HealthCheckConfig.builder()
                        .type(checkConfig.getType())
                        .timeout(checkConfig.getTimeout())
                        .build())
                .build();
    }

    private App findAppById(UUID appId) {
        return apps.findById(appId)
                .orElseThrow(AppNotFoundException::new);
    }

}

