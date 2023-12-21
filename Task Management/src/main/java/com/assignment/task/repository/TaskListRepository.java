package com.assignment.task.repository;

import com.assignment.task.model.TaskList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskListRepository extends MongoRepository<TaskList, String> {
}
