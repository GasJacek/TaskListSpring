package com.task.command;

import java.util.concurrent.Callable;

import com.task.Util;
import com.task.dto.TaskDto;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "add" , header = "add a task")
public class AddTask implements Callable<String> {
	
	@Option(required = true, names = "name", description = "Task Name")
    private String taskName;
	@Option(required = false, names = "desc", description = "Task Description")
    private String taskDescription;
	@Option(required = true, names = "num", description = "Task Number")
    private Integer taskNumber;
	
	@Autowired
	private TaskService taskService;
	
	
	@Override
	public String call() throws Exception {
		TaskDto taskDto = new TaskDto(taskNumber, taskName, taskDescription, false);
		if(Util.isValidObject(taskDto)) {
			String output = taskService.addTask(taskDto);
			System.out.println(output);
			return output;
		}
		System.out.println("Invalid data. Name and description max length 255 chars. Unsigned number only.");
		return "Invalid data. Name and description max length 255 chars. Unsigned number only.";
	}
}
