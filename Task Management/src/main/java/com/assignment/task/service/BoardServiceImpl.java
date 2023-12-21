package com.assignment.task.service;

import com.assignment.task.dto.BoardDTO;
import com.assignment.task.exception.BoardNotFoundException;
import com.assignment.task.model.Board;
import com.assignment.task.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final TaskListService taskListService;

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        return modelMapper.map(boardRepository.save(board), BoardDTO.class);
    }

    @Override
    public String deleteBoardWithAllTaskAssociated(String boardId) {

        Optional<BoardDTO> optionalBoardDTO = findByBoardId(boardId);
        if (optionalBoardDTO.isPresent()){
            BoardDTO boardDTO = optionalBoardDTO.get();
            //find All tasks associated and delete
            log.info("taskList IDs : {}", boardDTO.getTaskListIds());
            boardDTO.getTaskListIds().forEach(taskListService::deleteTaskList);
            boardRepository.deleteById(boardId);
            return "Board with associated taskList deleted with Id"+boardId;
        }
        else {
            throw new BoardNotFoundException("Board not found with id: " + boardId);
        }


    }

    private Optional<BoardDTO> findByBoardId(String id){
        return Optional.ofNullable(modelMapper.map(boardRepository.findById(id), BoardDTO.class));
    }
}
