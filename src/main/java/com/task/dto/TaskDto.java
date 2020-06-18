package com.task.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TaskDto {

    private Integer taskNumber;
    private String taskName;
    private String taskDescription;
    private Integer id;
    private boolean taskStatus;

    @Override
    public String toString() {
        return "Task Id: " + this.id + " , Task Name: " + this.taskName + " , Task Number: " + this.taskNumber + " , Task Description: " + this.taskDescription + ", Task Completed: " + this.taskStatus;
    }
}