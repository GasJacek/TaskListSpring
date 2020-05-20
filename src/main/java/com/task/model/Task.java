package com.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Task {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "task_number", unique = true, nullable = false)
	private Integer taskNumber;
	
	@Column(name = "task_name", nullable = false, length = 255)
	private String taskName;
	
	@Column(name = "task_description", length = 255)
	private String taskDescription;
	
	@Column(name = "task_status", nullable = false)
	private boolean taskStatus;

	public Task(){}
	
	public Task(String taskName, String taskDescription, Integer taskNumber, boolean taskStatus) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskNumber = taskNumber;
		this.taskStatus = taskStatus;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Integer getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public boolean isTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
}