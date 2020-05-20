package task.service;

import java.util.ArrayList;
import java.util.List;

import com.task.service.TaskServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.task.dao.TaskDao;
import com.task.dto.TaskDto;
import com.task.model.Task;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TaskServiceImpl.class, TaskDao.class})
@PowerMockIgnore("javax.management.*")
public class TaskServiceImplTest {
	
	 @InjectMocks
	 private TaskServiceImpl serviceImpl;

	 @Mock
	 private TaskDao taskDao;
	
	 @Test
	 public void selectAllTaskTest() {
		 MockitoAnnotations.initMocks(this);
		 List<Task> tasks = new ArrayList<Task>();
		 tasks.add(new Task("TestTask", "TaskDescription",124, false));
		 Mockito.when(taskDao.findAll()).thenReturn(tasks);
		 List<TaskDto> taskDtos =  serviceImpl.selectAllTask();
		 Assert.assertEquals(tasks.size(), taskDtos.size());
		 Assert.assertEquals(tasks.get(0).getTaskNumber(), taskDtos.get(0).getTaskNumber());
	 }
	 
	 @Test
	 public void addTaskTest() {
		 MockitoAnnotations.initMocks(this);
		 TaskDto taskDto = new TaskDto(123, "TestTask", "TaskDescription", false);
		 Mockito.when(taskDao.findByTaskNumber(Mockito.anyInt())).thenReturn(null);
		 Task task = new Task("TestTask", "TaskDescription", 123, false);
		 task.setId(1);
		 Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(task);
		 String output =  serviceImpl.addTask(taskDto);
		 Assert.assertEquals(output, "Task with task Id=null and task Number=123 has been saved");
	 }
	 
	 @Test
	 public void addTaskTestExist() {
		 MockitoAnnotations.initMocks(this);
		 Task task = new Task("TestTask", "TaskDescription", 123, false);
		 task.setId(1);
		 TaskDto taskDto = new TaskDto(123, "TestTask", "TaskDescription", true);
		 Mockito.when(taskDao.findByTaskNumber(Mockito.anyInt())).thenReturn(task);
		 Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(task);
		 String output =  serviceImpl.addTask(taskDto);
		 Assert.assertEquals(output, "Task Number should be unique. Please provide some unique value.");
	 }
	 
	 @Test
	 public void updateTaskTest() {
		 MockitoAnnotations.initMocks(this);
		 List<Task> tasks = new ArrayList<Task>();
		 tasks.add(new Task("TestTask", "TaskDescription", 123, false));
		 Mockito.when(taskDao.findAll()).thenReturn(tasks);
		 List<TaskDto> taskDtos =  serviceImpl.selectAllTask();
		 Assert.assertEquals(tasks.size(), taskDtos.size());
	 }
	 
	 @Test
	 public void selectTaskByTaskNumberNullTest() {
		 MockitoAnnotations.initMocks(this);
		 Mockito.when(taskDao.findByTaskNumber(Mockito.anyInt())).thenReturn(null);
		 TaskDto output = serviceImpl.selectTask(123);
		 Assert.assertNull(output);
	 }

}