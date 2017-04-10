package com.eretailservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.eretailservice.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}