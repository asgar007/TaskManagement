package com.assignment.task.service;

import com.assignment.task.dto.CardDTO;
import com.assignment.task.dto.UserDTO;
import com.assignment.task.exception.CardNotFoundException;
import com.assignment.task.exception.MaxAssignmentsReachedException;
import com.assignment.task.model.Card;
import com.assignment.task.model.User;
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
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Override
    public CardDTO createCard(CardDTO cardDTO) {
        log.info("cardDTO: {}", cardDTO);
        Card card = new Card();
        BeanUtils.copyProperties(cardDTO, card);
        Card finalCard = card;
        cardDTO.getAssignedUserIds().forEach(userId -> {
            UserDTO userDTO = userService.getUserById(userId);
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            finalCard.getAssignedUsers().add(user);
        });
        card = cardRepository.save(finalCard);
        BeanUtils.copyProperties(card, cardDTO);
        return cardDTO;
    }

    @Override
    public CardDTO assignUserToCard(String cardId, String userId) {
        Card card = cardRepository.findById(cardId).orElseGet(Card::new);
        CardDTO cardDTO = new CardDTO();
        if (card.getId() != null) {
            if (card.getAssignedUsers().size() >= 2) {
                log.error("User {} is already assigned to the maximum number of cards.", userId);
                throw new MaxAssignmentsReachedException("User is already assigned to the maximum number of cards.");
            }
            UserDTO userDTO = userService.getUserById(userId);
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            card.getAssignedUsers().add(user);
            card = cardRepository.save(card);
            BeanUtils.copyProperties(card, cardDTO);
            return cardDTO;
        } else {
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

    @Override
    public CardDTO getCardById(String cardId) {
        Card card = cardRepository.findById(cardId).orElseGet(Card::new);
        CardDTO cardDTO = new CardDTO();
        BeanUtils.copyProperties(card, cardDTO);
        return cardDTO;
    }


    private Optional<CardDTO> findByCardId(String id) {
        return Optional.ofNullable(modelMapper.map(cardRepository.findById(id), CardDTO.class));
    }

}
