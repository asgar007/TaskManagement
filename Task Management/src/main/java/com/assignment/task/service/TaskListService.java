package com.assignment.task.service;

import com.assignment.task.dto.TaskListDTO;

public interface TaskListService {
    TaskListDTO createTaskList(TaskListDTO taskListDTO);
    void deleteTaskList(String taskListId);
}
