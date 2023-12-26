package com.assignment.task.service;

import com.assignment.task.dto.BoardDTO;
import com.assignment.task.dto.TaskListDTO;
import com.assignment.task.exception.BoardNotFoundException;
import com.assignment.task.model.Board;
import com.assignment.task.model.TaskList;
import com.assignment.task.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
        Board board = new Board();
        BeanUtils.copyProperties(boardDTO,board);
        Board finalBoard = board;
        boardDTO.getTaskListIds().forEach(taskListId->{
            TaskListDTO taskListDTO = taskListService.getTaskListById(taskListId);
            TaskList taskList = new TaskList();
            BeanUtils.copyProperties(taskListDTO, taskList);
            finalBoard.getTaskLists().add(taskList);
        });
        board = boardRepository.save(finalBoard);
        BeanUtils.copyProperties(board, boardDTO);
        return boardDTO;
    }

    @Override
    public String deleteBoardWithAllTaskAssociated(String boardId) {

        Board board = boardRepository.findById(boardId).orElseGet(Board::new);
        if(board.getId() != null){
            // delete tasks associated
            board.getTaskLists().forEach(taskList -> {
                taskListService.deleteTaskList(taskList.getId());
            });
            boardRepository.deleteById(boardId);
            return "Board with associated taskList deleted with Id"+boardId;
        }else {
            throw new BoardNotFoundException("Board not found with id: " + boardId);
        }
    }

    private BoardDTO findByBoardId(String id){
        Board board = boardRepository.findById(id).orElseGet(Board::new);
        BoardDTO boardDTO = new BoardDTO();
        BeanUtils.copyProperties(board, boardDTO);
        return boardDTO;
    }
}
