package com.dpaula.scscheduler.domain.service;

import com.dpaula.scscheduler.domain.entity.Schedule;
import com.dpaula.scscheduler.domain.repository.ScheduleRepository;
import com.dpaula.scscheduler.domain.service.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleManagementService {

    private final ScheduleRepository schedules;

    @Transactional
    public Schedule create(Schedule schedule) {
        log.info("Criando schedule: " + schedule.getApp().getName());
        Validate.notNull(schedule);
        Validate.notNull(schedule.getApp());
        return schedules.saveAndFlush(schedule);
    }

    @Transactional
    public Schedule update(Schedule updatedSchedule) {
        Validate.notNull(updatedSchedule);
        Validate.notNull(updatedSchedule.getApp());

        Schedule schedule = schedules.findById(updatedSchedule.getId()).orElseThrow(ScheduleNotFoundException::new);

        schedule.update(updatedSchedule);

        return schedules.saveAndFlush(schedule);
    }

    @Transactional
    public void delete(UUID scheduleId) {
        Schedule schedule = schedules.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
        schedules.delete(schedule);
    }
}
