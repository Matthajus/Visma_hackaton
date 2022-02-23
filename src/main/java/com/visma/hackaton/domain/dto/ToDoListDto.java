package com.visma.hackaton.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.visma.hackaton.domain.entities.ToDoItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ToDoListDto extends ToDoListBaseDto {

    @JsonProperty("items")
    public List<ToDoItem> items;

}
