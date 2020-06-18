package com.task.command;

import java.util.concurrent.Callable;

import com.task.Util;
import com.task.dto.TaskDto;
import com.task.exception.BusinessException;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "add", header = "add a task")
public class AddTask implements Callable<String> {

    @Option(required = true, names = "name", description = "Task Name")
    private String taskName;
    @Option(names = "desc", description = "Task Description")
    private String taskDescription;
    @Option(required = true, names = "num", description = "Task Number")
    private Integer taskNumber;

    @Autowired
    private TaskService taskService;


    @Override
    public String call() {
        TaskDto taskDto = new TaskDto(taskNumber, taskName, taskDescription, 5, false );
        try {
            if (Util.isValidObject(taskDto)) {
                taskDto = taskService.addTask(taskDto);
                System.out.println(taskDto);
                return taskDto.toString();
            }
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return null;
    }
}
