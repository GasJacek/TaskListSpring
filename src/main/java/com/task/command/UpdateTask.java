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
@Command(name = "update", header = "update a task" )
public class UpdateTask implements Callable<String> {
	
	@Option(required = false, names = "name", description = "Task Name")
    private String taskName;
	@Option(required = false, names = "desc", description = "Task Description")
    private String taskDescription;
	@Option(required = false, names = "num", description = "Task Number")
    private Integer taskNumber;
	@Option(required = false, names = "stat", description = "Task Number")
    private String taskStatus;
	@Option(required = true, names = "id", description = "Task Id")
    private Integer taskId;
	
	@Autowired
	private TaskService taskService;
	
	
	@Override
	public String call() throws Exception {

		boolean taskStatusValue = false;
		if(taskStatus == null || taskStatus.equalsIgnoreCase("Y")) { taskStatusValue = true; }


		TaskDto taskDto = new TaskDto(taskNumber, taskName, taskDescription, taskStatusValue);
		taskDto.setId(taskId);

		if(taskDescription == null && taskName == null && taskNumber == null && taskStatus == null) {
			System.out.println("Provide values to update");
			return "Provide values to update";
		}
		if(Util.isValidObject(taskDto)) {
			String output = taskService.updateTask(taskDto);
			System.out.println(output);
			return output;
		}
		System.out.println("Invalid data. Name and description max length 255 chars. Unsigned number only.");
		return "Invalid data. Name and description max length 255 chars. Unsigned number only.";
	}
}
