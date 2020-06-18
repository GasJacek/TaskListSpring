package com.task.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Task;

@Repository
public interface TaskDao extends CrudRepository<Task, Integer> {

	public Task findByTaskNumber(Integer taskNumber);
}
