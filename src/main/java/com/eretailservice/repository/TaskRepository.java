package com.eretailservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.eretailservice.model.Task;


public interface TaskRepository extends CrudRepository<Task, Integer>{

}
