package com.assignment.task.service;

import com.assignment.task.dto.BoardDTO;

public interface BoardService {

    BoardDTO createBoard(BoardDTO boardDTO);

    String deleteBoardWithAllTaskAssociated(String boardId);
}
