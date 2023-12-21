package com.assignment.task.controller;

import com.assignment.task.dto.CardDTO;
import com.assignment.task.helper.AssignUserRequest;
import com.assignment.task.helper.MoveCardRequest;
import com.assignment.task.response.SuccessResponse;
import com.assignment.task.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    @PostMapping
    public ResponseEntity<SuccessResponse> createCard(@RequestBody @Valid CardDTO cardDTO){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.CREATED.toString())
                        .message("Card is created successfully")
                        .data(cardService.createCard(cardDTO)).build());
    }

    @PutMapping
    public ResponseEntity<SuccessResponse> assignUserToCard(@RequestBody AssignUserRequest assignUserRequest){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.OK.toString())
                        .message("Card is updated successfully")
                        .data(cardService.assignUserToCard(assignUserRequest.getCardId(), assignUserRequest.getUserId())).build());
    }

    @PutMapping("taskList")
    public ResponseEntity<SuccessResponse> moveCardWithinTaskListInBoard(@RequestBody MoveCardRequest moveCardRequest){
        return  ResponseEntity.ok()
                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.OK.toString())
                        .message("Card is updated successfully")
                        .data(cardService.moveCardWithinTaskListInBoard(moveCardRequest.getCardId(), moveCardRequest.getTaskListId())).build());
    }

    @DeleteMapping("{cardId}")
    public void deleteCard(@PathVariable String cardId){
        cardService.deleteCard(cardId);
//                .body(SuccessResponse.builder().error(false).statusCode(HttpStatus.OK.toString())
//                        .message("Card is deleted successfully")
//                        .data().build());
    }
}
