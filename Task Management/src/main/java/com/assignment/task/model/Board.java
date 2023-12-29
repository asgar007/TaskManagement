package com.assignment.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
@Data
@Document(collection = "boards")
public class Board {
    @Id
    private String id;
    private String title;
    @DBRef
    @Field("taskLists")
    private List<TaskList> taskLists = new ArrayList<>();
}
