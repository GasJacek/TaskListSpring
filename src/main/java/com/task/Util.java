package com.task;

import com.task.dto.TaskDto;
import com.task.exception.BusinessException;

public class Util {

    public static boolean isValidObject(TaskDto taskDto) throws BusinessException {
        if (taskDto.getTaskDescription() != null && taskDto.getTaskDescription().length() > 255) {
            throw new BusinessException("Task Description limit is 255 characters");
        }
        if (taskDto.getTaskName() != null && taskDto.getTaskName().length() > 255) {
            throw new BusinessException("Task Name limit is 255 characters");
        }
        if (taskDto.getTaskNumber() != null && taskDto.getTaskNumber() < 1) {
            throw new BusinessException("Task Number should be greater than zero.");
        }
        return true;
    }
}
