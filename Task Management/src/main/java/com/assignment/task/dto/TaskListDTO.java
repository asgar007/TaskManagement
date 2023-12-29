package com.assignment.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskListDTO {
    private String id;
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Card list cannot be null")
    private List<String> cardIds;
}
