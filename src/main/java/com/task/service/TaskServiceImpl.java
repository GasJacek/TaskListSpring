package com.task.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.dao.TaskDao;
import com.task.dto.TaskDto;
import com.task.model.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;

	@Override
	public List<TaskDto> selectAllTask() {
		Iterable<Task> tasks = taskDao.findAll();
		List<TaskDto> taskDtos = new ArrayList<TaskDto>();
		if(tasks != null) {
			Iterator<Task> iterator = tasks.iterator();
			while(iterator.hasNext()) {
				Task task = iterator.next();
				TaskDto taskDto = new TaskDto(task.getTaskNumber(), task.getTaskName(), task.getTaskDescription(), task.isTaskStatus());
				taskDto.setId(task.getId());
				taskDtos.add(taskDto);
			}
		}
		return taskDtos;
	}

	@Override
	public String addTask(TaskDto taskDto) {
		Task task = taskDao.findByTaskNumber(taskDto.getTaskNumber());
		if(task != null)
			return "Task Number should be unique. Please provide some unique value.";
		task = new Task(taskDto.getTaskName(), taskDto.getTaskDescription(), taskDto.getTaskNumber(), taskDto.isTaskStatus());
		taskDao.save(task);
		return "Task with task Id=" + task.getId() + " and task Number=" + task.getTaskNumber() + " has been saved";
	}


	@Override
	public String deleteTask(Integer taskId) {
		Optional<Task> taskOptional = taskDao.findById(taskId);
		try {
			taskOptional.get();
		} catch(Exception e) {
			return "Task doest not exist with provided id:" + taskId;
		}
		taskDao.deleteById(taskId);
		return "Deleted successfully";
	}

	@Override
	public String updateTask(TaskDto taskDto) {
		Optional<Task> taskOptional = taskDao.findById(taskDto.getId());
		try {
			Task task = taskOptional.get();
			if(taskDto.getTaskDescription()!= null) {
				task.setTaskDescription(taskDto.getTaskDescription());
			}
			if(taskDto.getTaskName()!= null) {
				task.setTaskName(taskDto.getTaskName());
			}
			if(taskDto.getTaskNumber()!= null) {
				task.setTaskNumber(taskDto.getTaskNumber());
			}
			if(taskDto.isTaskStatus()) {
				task.setTaskStatus(true);
			} else {
				task.setTaskStatus(false);
			}
			taskDao.save(task);
			return "Task with task Id=" + task.getId() + " and task Number=" + task.getTaskNumber() + " has been saved :";
		} catch(Exception e) {
			return "Task number already exists. Error updating task with Task Id:" + taskDto.getId();
		}
	}

	@Override
	public TaskDto selectTask(Integer taskNumber) {
		Task task = taskDao.findByTaskNumber(taskNumber);
		if(task == null)
			return null;
		TaskDto taskDto = new TaskDto(task.getTaskNumber(), task.getTaskName(), task.getTaskDescription(), task.isTaskStatus());
		taskDto.setId(task.getId());
		return taskDto;
	}
}