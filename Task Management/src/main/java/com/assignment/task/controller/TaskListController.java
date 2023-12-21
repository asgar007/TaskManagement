package com.assignment.task.controller;

import com.assignment.task.dto.CardDTO;
import com.assignment.task.dto.TaskListDTO;
import com.assignment.task.response.SuccessResponse;
import com.assignment.task.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/taskLists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService taskListService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createTaskList(@RequestBody TaskListDTO taskListDTO){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.CREATED.toString())
                        .message("TaskList is created successfully")
                        .data(taskListService.createTaskList(taskListDTO)).build());
    }
}
