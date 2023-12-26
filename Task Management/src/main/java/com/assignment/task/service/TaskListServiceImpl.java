package com.assignment.task.service;

import com.assignment.task.dto.CardDTO;
import com.assignment.task.dto.TaskListDTO;
import com.assignment.task.model.Card;
import com.assignment.task.model.TaskList;
import com.assignment.task.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskListServiceImpl implements TaskListService{

    private final TaskListRepository taskListRepository;
    private final ModelMapper modelMapper;
    private final CardService cardService;
    @Override
    public TaskListDTO createTaskList(TaskListDTO taskListDTO) {
        TaskList taskList = new TaskList();
        BeanUtils.copyProperties(taskListDTO,taskList);
        TaskList finalTaskList = taskList;
        taskListDTO.getCardIds().forEach(cardId ->{
            CardDTO cardDTO = cardService.getCardById(cardId);
            Card card = new Card();
            BeanUtils.copyProperties(cardDTO,card);
            log.info("card {}", card);
            finalTaskList.getCardList().add(card);
        });
        log.info("finalTaskList {}", finalTaskList);
        taskList = taskListRepository.save(finalTaskList);
        BeanUtils.copyProperties(taskList, taskListDTO);
        return taskListDTO;
    }
    @Override
    public void deleteTaskList(String taskListId){
        taskListRepository.deleteById(taskListId);
    }

    @Override
    public TaskListDTO getTaskListById(String taskListId) {
        TaskList taskList = taskListRepository.findById(taskListId).orElseGet(TaskList::new);
        TaskListDTO taskListDTO = new TaskListDTO();
        BeanUtils.copyProperties(taskList, taskListDTO);
        return taskListDTO;
    }

    @Override
    public void deleteCardFromTaskListByCardId(String fromTaskListId, String cardId) {
        TaskList taskList = taskListRepository.findById(fromTaskListId).orElseGet(TaskList::new);
        List<Card> cardList = taskList.getCardList().stream().filter(card -> !card.getId().equals(cardId)).collect(Collectors.toList());
        taskList.setCardList(cardList);
        taskListRepository.save(taskList);
    }

    @Override
    public String moveCardWithinTaskListInBoard(String cardId, String fromTaskListId, String toTaskListId) {
        deleteCardFromTaskListByCardId(fromTaskListId, cardId);
        TaskList taskList = taskListRepository.findById(toTaskListId).orElseGet(TaskList::new);
        CardDTO cardDTO = cardService.getCardById(cardId);
        Card card = new Card();
        BeanUtils.copyProperties(cardDTO, card);
        taskList.getCardList().add(card);
        taskListRepository.save(taskList);
        return "card moved";
    }
}
