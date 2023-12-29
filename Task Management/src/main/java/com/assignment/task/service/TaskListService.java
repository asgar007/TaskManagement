package com.assignment.task.service;

import com.assignment.task.dto.TaskListDTO;

public interface TaskListService {
    TaskListDTO createTaskList(TaskListDTO taskListDTO);
    void deleteTaskList(String taskListId);

    TaskListDTO getTaskListById(String taskListId);

    void deleteCardFromTaskListByCardId(String fromTaskListId, String cardId);

    String moveCardWithinTaskListInBoard(String cardId, String fromTaskListId, String toTaskListId);
}
