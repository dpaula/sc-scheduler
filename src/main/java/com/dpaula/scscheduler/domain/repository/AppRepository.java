package com.dpaula.scscheduler.domain.repository;

import com.dpaula.scscheduler.domain.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppRepository extends JpaRepository<App, UUID> {
}
