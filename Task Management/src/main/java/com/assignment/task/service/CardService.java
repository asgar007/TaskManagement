package com.assignment.task.service;

import com.assignment.task.dto.CardDTO;

public interface CardService{
    CardDTO createCard(CardDTO cardDTO);
    CardDTO assignUserToCard(String cardId, String userId);
    String moveCardWithinTaskListInBoard(String cardId, String taskListId);

    void deleteCard(String cardId);
}
