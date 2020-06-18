package com.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_number", unique = true, nullable = false)
    private Integer taskNumber;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "task_status", nullable = false)
    private boolean taskStatus;

    public Task(String taskName, String taskDescription, Integer taskNumber, boolean taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskNumber = taskNumber;
        this.taskStatus = taskStatus;
    }
}