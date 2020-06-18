package com.task.command;

import java.util.concurrent.Callable;

import com.task.Util;
import com.task.dto.TaskDto;
import com.task.exception.BusinessException;
import com.task.exception.NotFoundException;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "update", header = "update a task")
public class UpdateTask implements Callable<String> {

    @Option(names = "name", description = "Task Name")
    private String taskName;
    @Option(names = "desc", description = "Task Description")
    private String taskDescription;
    @Option(names = "num", description = "Task Number")
    private Integer taskNumber;
    @Option(names = "status", description = "Task Status")
    private String taskStatus;
    @Option(required = true, names = "id", description = "Task Id")
    private Integer taskId;

    @Autowired
    private TaskService taskService;


    @Override
    public String call() {
        boolean taskStatusValue = false;
        if (taskStatus == null || taskStatus.equalsIgnoreCase("Y")) {
            taskStatusValue = true;
        }
        if (taskDescription == null && taskName == null && taskNumber == null && taskStatus == null) {
            System.out.println("Provide values to update");
            return "Provide values to update";
        }
        TaskDto taskDto = new TaskDto(taskNumber, taskName, taskDescription, taskId, taskStatusValue);
        try {
            if (Util.isValidObject(taskDto)) {
                taskDto = taskService.updateTask(taskDto);
                System.out.println(taskDto);
                return taskDto.toString();
            }
        } catch (BusinessException | NotFoundException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return null;
    }
}
