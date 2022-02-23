package com.visma.hackaton.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.visma.hackaton.domain.dto.validation.ValidationGroups;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class ToDoItemDto {

    @JsonProperty("id")
    @Null(groups = ValidationGroups.OnCreate.class, message = "An id must be null")
    @NotNull(groups = ValidationGroups.OnCreate.class, message = "An id cant be null")
    public long id;

    @JsonProperty("name")
    @NotNull(groups = ValidationGroups.OnCreate.class, message = "The name must be defined")
    public String name;

    @JsonProperty("description")
    public String description;

    @JsonProperty("isDone")
    @NotNull(groups = ValidationGroups.OnCreate.class, message = "The isDone must be defined")
    public boolean isDone;

}
