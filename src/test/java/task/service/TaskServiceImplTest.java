package task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.task.exception.BusinessException;
import com.task.exception.NotFoundException;
import com.task.service.TaskServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.task.dao.TaskDao;
import com.task.dto.TaskDto;
import com.task.model.Task;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
	 public void addTaskTest() throws BusinessException {
		 MockitoAnnotations.initMocks(this);
		 TaskDto taskDto = new TaskDto(123, "TestTask", "TaskDescription", 2, false);
		 Mockito.when(taskDao.findByTaskNumber(Mockito.anyInt())).thenReturn(null);
		 Task task = new Task(1, 123, "TestTask", "TaskDescription", false);
		 Mockito.spy(task);
		 Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(task);
		 taskDto =  serviceImpl.addTask(taskDto);
		 Assert.assertEquals(taskDto.getId(), task.getId());
	 }
	 
	 @Test(expected = BusinessException.class)
	 public void addTaskTestExist() throws BusinessException{
		 MockitoAnnotations.initMocks(this);
		 Task task = new Task("TestTask", "TaskDescription", 123, false);
		 task.setId(1);
		 TaskDto taskDto = new TaskDto(123, "TestTask", "TaskDescription", 3, false);
		 Mockito.when(taskDao.findByTaskNumber(Mockito.anyInt())).thenReturn(task);
		 Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(task);
		 serviceImpl.addTask(taskDto);
	 }
	 
	 @Test
	 public void updateTaskTest() throws NotFoundException {
		 MockitoAnnotations.initMocks(this);
		 TaskDto taskDto = new TaskDto(123, "TestTask", "TaskDescription",1, true);
		 Task task = new Task(1, 123, "TestTask", "TaskDescription", false);
		 Mockito.spy(task);
		 Mockito.when(taskDao.findById(taskDto.getId())).thenReturn(Optional.of(task));
		 Mockito.when(taskDao.save(Mockito.any(Task.class))).thenReturn(task);
		 taskDto =  serviceImpl.updateTask(taskDto);
		 Assert.assertEquals(task.getId(), taskDto.getId());
	 }
	 
	 @Test(expected = NotFoundException.class)
	 public void selectTaskByTaskNumberNullTest() throws NotFoundException {
		 MockitoAnnotations.initMocks(this);
		 Mockito.when(taskDao.findByTaskNumber(Mockito.anyInt())).thenReturn(null);
		 TaskDto output = serviceImpl.selectTask(123);
		 Assert.assertNull(output);
	 }
}