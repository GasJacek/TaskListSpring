package com.task.command;

import java.util.concurrent.Callable;

import com.task.exception.NotFoundException;
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
    public String call() {
        if (taskId != null && taskId > 0) {
            try {
                return taskService.deleteTask(taskId);
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        }
        return "Please provide task id to delete task";
    }
}
