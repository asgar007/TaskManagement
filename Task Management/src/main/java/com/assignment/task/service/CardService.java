package com.assignment.task.service;

import com.assignment.task.dto.CardDTO;

public interface CardService{
    CardDTO createCard(CardDTO cardDTO);
    CardDTO assignUserToCard(String cardId, String userId);

    void deleteCard(String cardId);

    CardDTO getCardById(String cardId);
}
