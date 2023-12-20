package com.assignment.task.service;

import com.assignment.task.dto.CardDTO;
import com.assignment.task.exception.CardNotFoundException;
import com.assignment.task.exception.MaxAssignmentsReachedException;
import com.assignment.task.model.Card;
import com.assignment.task.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;
    @Override
    public CardDTO createCard(CardDTO cardDTO) {
//        Card card = modelMapper.map(cardDTO, Card.class);
        Card card=new Card();
        BeanUtils.copyProperties(cardDTO,card);
        log.info("card : {}",card);
        return modelMapper.map(cardRepository.save(card), CardDTO.class);
    }

    @Override
    public CardDTO assignUserToCard(String cardId, String userId) {
        Optional<CardDTO> cardDTOOptional = findByCardId(cardId);

        if (cardDTOOptional.isPresent()) {
            CardDTO cardDTO = cardDTOOptional.get();

            // Check if the user is already assigned to the maximum allowed number of cards (2 in this case)
            if (cardDTO.getAssignedUserIds().size() >= 2) {
                log.error("User {} is already assigned to the maximum number of cards.", userId);
                throw new MaxAssignmentsReachedException("User is already assigned to the maximum number of cards.");
            }

            // Add the userId to the assignedUserIds list
            cardDTO.getAssignedUserIds().add(userId);
            Card card=new Card();
            BeanUtils.copyProperties(cardDTO,card);
            // Save the updated card
//            modelMapper.map(cardDTO, Card.class)
            Card updatedCard = cardRepository.save(card);
            log.error("card {}", updatedCard);
            // Map the updated card to CardDTO and return
            return modelMapper.map(updatedCard, CardDTO.class);
        }
        else {
            throw new CardNotFoundException("Card not found with id: " + cardId);
        }
    }

    @Override
    public String moveCardWithinTaskListInBoard(String cardId, String taskListId) {
        Optional<CardDTO> cardDTOOptional = findByCardId(cardId);

        if (cardDTOOptional.isPresent()) {
            CardDTO cardDTO = cardDTOOptional.get();
            String prevState = cardDTO.getTaskListId();

            // Add/replace the taskId
            cardDTO.setTaskListId(taskListId);
            Card card=new Card();
            BeanUtils.copyProperties(cardDTO,card);
//            // Save the updated card
            modelMapper.map(cardDTO, Card.class);
            Card updatedCard = cardRepository.save(card);
            log.error("card {}", updatedCard);
//            // Map the updated card to CardDTO and return
            return "Card moved from "+prevState + "to"+ taskListId;
        }
        else {
            throw new CardNotFoundException("Card not found with id: " + cardId);
        }
    }

    @Override
    public void deleteCard(String cardId) {
        log.info("Deleting card with Id: {}", cardId);

        try {
            cardRepository.deleteById(cardId);
            log.info("Card deleted successfully.");
        } catch (Exception e) {
            log.error("Error deleting card with Id: {}", cardId, e);
        }
    }


    private Optional<CardDTO> findByCardId(String id){
        return Optional.ofNullable(modelMapper.map(cardRepository.findById(id), CardDTO.class));
    }


}
