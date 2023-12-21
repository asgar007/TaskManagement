package com.assignment.task.service;

import com.assignment.task.dto.TaskListDTO;
import com.assignment.task.model.TaskList;
import com.assignment.task.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskListServiceImpl implements TaskListService{

    private final TaskListRepository taskListRepository;
    private final ModelMapper modelMapper;
    @Override
    public TaskListDTO createTaskList(TaskListDTO taskListDTO) {
        return modelMapper.map(taskListRepository.save(modelMapper.map(taskListDTO, TaskList.class)), TaskListDTO.class);
    }
    @Override
    public void deleteTaskList(String taskListId){
        taskListRepository.deleteById(taskListId);
    }
}
