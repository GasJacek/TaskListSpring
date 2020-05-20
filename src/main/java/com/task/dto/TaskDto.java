package com.task.dto;

import lombok.Data;

@Data
public class TaskDto {
	
	private Integer taskNumber;
	private String taskName;
	private String taskDescription;
	private Integer id;
	private boolean taskStatus;
	
	public TaskDto(Integer taskNumber, String taskName, String taskDescription, boolean taskStatus) {
		this.taskNumber = taskNumber;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.setTaskStatus(taskStatus);
	}

	public Integer getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Task Id: " + this.id + " , Task Name: " + this.taskName + " , Task Number: " + this.taskNumber +" , Task Description: " + this.taskDescription +", Task Completed: "+ this.taskStatus;
	}
	public boolean isTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
}