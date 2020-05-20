package com.task.command;

import java.util.concurrent.Callable;

import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "delete", header = "delete a task")
public class DeleteTask implements Callable<String> {
	
	@Option(required = true, names = "id", description = "Task Id")
    private Integer taskId;


	@Autowired
	private TaskService taskService;
	
	
	@Override
	public String call() throws Exception {
		if(taskId != null && taskId > 0) {
			return taskService.deleteTask(taskId);
		}
		return "Please provide task id to delete task";
	}
}
