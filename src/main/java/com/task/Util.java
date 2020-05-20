package com.task;

import com.task.dto.TaskDto;

public class Util {

	public static boolean isValidObject(TaskDto taskDto) {
		if (taskDto.getTaskDescription() != null && taskDto.getTaskDescription().length() > 255) {
			return false;
		}
		if (taskDto.getTaskName() != null && taskDto.getTaskName().length() > 255) {
			return false;
		}
		if (taskDto.getTaskNumber() != null && taskDto.getTaskNumber() < 0) {
			return false;
		}
		return true;
	}
}
