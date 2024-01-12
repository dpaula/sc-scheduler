package com.dpaula.scscheduler.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

@Entity
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String address;

    protected App() {
        //For JPA/Hibernate
    }

    public App(UUID id, String name, String address) {
        Validate.notNull(name);
        Validate.notNull(address);
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void update(App app) {
        Validate.notNull(app);
        this.name = app.getName();
        this.address = app.getAddress();
    }
}
