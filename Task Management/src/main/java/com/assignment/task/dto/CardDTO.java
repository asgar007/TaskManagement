package com.assignment.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private String id;
    private String title;
    private String description;

    private List<String> assignedUserIds;

    private String taskListId;//to move easily to different taskLists
}
