package com.assignment.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "lists")
public class TaskList {
    @Id
    private String id;
    private String title;
    @Indexed
    private String boardId;
}