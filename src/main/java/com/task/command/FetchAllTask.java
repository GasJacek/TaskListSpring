package com.task.command;

import java.util.List;
import java.util.concurrent.Callable;

import com.task.exception.NotFoundException;
import com.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.task.dto.TaskDto;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Component
@Command(name = "get", header = "get tasks")
public class FetchAllTask implements Callable<String> {

    @Option(names = "taskNumber", description = "Task Number")
    private Integer taskNumber;

    @Autowired
    private TaskService taskService;

    @Override
    public String call() {
        if (taskNumber != null && taskNumber > 0) {
            try {
                TaskDto taskDto = taskService.selectTask(taskNumber);
                System.out.println(taskDto == null ? "Task does not exist" : taskDto);
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            List<TaskDto> taskDtos = taskService.selectAllTask();
            for (TaskDto taskDto : taskDtos) {
                System.out.println(taskDto);
            }
        }
        return "";
    }
}