package org.daypilot.demo.angular2scheduler.repository;

import org.daypilot.demo.angular2scheduler.domain.Resource;
import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}