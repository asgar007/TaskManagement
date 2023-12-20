package com.assignment.task.controller;

import com.assignment.task.dto.BoardDTO;
import com.assignment.task.dto.CardDTO;
import com.assignment.task.response.SuccessResponse;
import com.assignment.task.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createBoard(@RequestBody BoardDTO boardDTO){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.CREATED.toString())
                        .message("Board is created successfully")
                        .data(boardService.createBoard(boardDTO)).build());
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity<SuccessResponse> deleteBoardWithAllTaskAssociated(@PathVariable String boardId){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.OK.toString())
                        .message("Board is deleted successfully")
                        .data(boardService.deleteBoardWithAllTaskAssociated(boardId)).build());
    }
}
