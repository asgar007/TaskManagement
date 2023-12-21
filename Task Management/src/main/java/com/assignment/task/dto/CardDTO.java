package com.assignment.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
    private String id;
    private String title;
    private String description;
    @Size(max = 2, message = "A card can only be assigned to up to 2 users.")
    private List<String> assignedUserIds;

    private String taskListId;//to move easily to different taskLists
    private String BoardId; //to restrict the movement of cards to same board
}
