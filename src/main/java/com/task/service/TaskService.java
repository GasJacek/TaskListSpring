package com.task.service;

import java.util.List;

import com.task.dto.TaskDto;
import com.task.exception.BusinessException;
import com.task.exception.NotFoundException;

public interface TaskService {

    TaskDto addTask(TaskDto task) throws BusinessException;

    String deleteTask(Integer taskNumber) throws NotFoundException;

    TaskDto updateTask(TaskDto task) throws NotFoundException;

    TaskDto selectTask(Integer taskNumber) throws NotFoundException;

    List<TaskDto> selectAllTask();
}