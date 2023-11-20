# SpringMVC_CRUD
### Description
JavaRush module 5 first project.

The application uses MySQL, Hibernate, Spring, Docker.

Task: You need to make a task list (todo-list) with the ability to view the list of tasks, add new tasks, edit and delete existing tasks.

## To run the app

1. Rebuild the app
```
mvn clean install
```
2. Build docker images and run the containers
```
docker-compose up
```
3. Apply [tasks.sql](./tasks.sql) script to MySQL.
4. Open http://localhost:7777/tasks/  
