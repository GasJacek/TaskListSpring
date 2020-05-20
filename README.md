# TaskListSpring
A simple Spring-based task list with H2 database. 

Installation:

1. Clone project to IDE.

2. Package jar with Maven.

3. Navigate to target folder and adjust the application.properties file if required.

4. Open command line and execute: java -jar task-1.0.0.jar <br> 
   (if application.properties file has been changed please <br> add --spring.config.location=file:/pathTofile)
	
Available commands:

help - display help msg <br>
add - add task <br>
get - display tasks <br>
update - update task <br>
delete - delete task <br>
exit - exit the program <br>
