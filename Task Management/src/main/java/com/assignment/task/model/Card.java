package com.assignment.task.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document(collection = "cards")
public class Card {
    @Id
    private String id;
    private String title;
    private String description;

    @Field("assignedUserIds")
    private List<String> assignedUserIds;

    @Indexed
    private String taskListId;
    @Indexed
    private String BoardId;
}
