package com.assignment.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskListDTO {
    private String id;
    private String title;
    private String boardId;// to restrict movement to different Board
}
