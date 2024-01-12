package com.dpaula.scscheduler.domain.repository;

import com.dpaula.scscheduler.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
    List<Schedule> findAllByRunInterval(Integer runInterval);
}
