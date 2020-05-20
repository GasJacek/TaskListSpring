package com.task.service;

import java.util.List;

import com.task.dto.TaskDto;

public interface TaskService {

	public String addTask(TaskDto task);

	public String deleteTask(Integer taskNumber);
	
	public String updateTask(TaskDto task);
	
	public TaskDto selectTask(Integer taskNumber);
	
	public List<TaskDto> selectAllTask();
}