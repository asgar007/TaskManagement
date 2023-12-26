package com.assignment.task.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveCardRequest {
    private String cardId;
    private String fromTaskListId;
    private String toTaskListId;
}
