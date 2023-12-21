package com.assignment.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "boards")
public class Board {
    @Id
    private String id;
    private String title;

    @Indexed
    private List<String> taskListIds;
}
