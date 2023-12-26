package com.assignment.task.controller;

import com.assignment.task.dto.CardDTO;
import com.assignment.task.dto.TaskListDTO;
import com.assignment.task.helper.MoveCardRequest;
import com.assignment.task.response.SuccessResponse;
import com.assignment.task.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping
    public ResponseEntity<SuccessResponse> moveCardWithinTaskListInBoard(@RequestBody MoveCardRequest moveCardRequest){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.OK.toString())
                        .message("Card is updated successfully")
                        .data(taskListService.moveCardWithinTaskListInBoard(moveCardRequest.getCardId(), moveCardRequest.getFromTaskListId(), moveCardRequest.getToTaskListId())).build());
    }
}
