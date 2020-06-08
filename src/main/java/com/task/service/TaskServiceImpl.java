package com.task.service;

import java.util.ArrayList;
import java.util.List;

import com.task.exception.BusinessException;
import com.task.exception.NotFoundException;
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
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto(task.getTaskNumber(), task.getTaskName(), task.getTaskDescription(), task.getId(), task.isTaskStatus());
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    @Override
    public TaskDto addTask(TaskDto taskDto) throws BusinessException {
        Task task = taskDao.findByTaskNumber(taskDto.getTaskNumber());
        if (task != null)
            throw new BusinessException("Task Number should be unique. Please provide some unique value.");
        task = new Task(taskDto.getTaskName(), taskDto.getTaskDescription(), taskDto.getTaskNumber(), taskDto.isTaskStatus());
        task = taskDao.save(task);
        taskDto.setId(task.getId());
        return taskDto;
    }

    @Override
    public String deleteTask(Integer taskId) throws NotFoundException {
        Task task = taskDao.findById(taskId).orElseThrow(() -> new NotFoundException("Task doest not exist with provided id:" + taskId));
        taskDao.delete(task);
        return "Deleted successfully";
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) throws NotFoundException {
        Task task = taskDao.findById(taskDto.getId()).orElseThrow(() -> new NotFoundException("Task number already exists. Error updating task with Task Id:" + taskDto.getId()));
        if (taskDto.getTaskDescription() != null) {
            task.setTaskDescription(taskDto.getTaskDescription());
        }
        if (taskDto.getTaskName() != null) {
            task.setTaskName(taskDto.getTaskName());
        }
        if (taskDto.getTaskNumber() != null && taskDao.findByTaskNumber(task.getTaskNumber()) == null) {
            task.setTaskNumber(taskDto.getTaskNumber());
        }
        if (taskDto.isTaskStatus()) {
            task.setTaskStatus(false);
        } else {
            task.setTaskStatus(true);
        }
        taskDao.save(task);
        return taskDto;
    }

    @Override
    public TaskDto selectTask(Integer taskNumber) throws NotFoundException {
        Task task = taskDao.findByTaskNumber(taskNumber);
        if (task == null)
            throw new NotFoundException("Task number already exists. Error updating task with Task Number :" + taskNumber);
        return new TaskDto(task.getTaskNumber(), task.getTaskName(), task.getTaskDescription(), task.getId(), task.isTaskStatus());
    }
}