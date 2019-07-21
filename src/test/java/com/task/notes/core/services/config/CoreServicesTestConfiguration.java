package com.task.notes.core.services.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.context.annotation.ComponentScan;

@AutoConfigureDataJpa
@EntityScan(basePackages = "com.task.notes.data.entities")
@ComponentScan("com.task.notes")
public class CoreServicesTestConfiguration {
}
