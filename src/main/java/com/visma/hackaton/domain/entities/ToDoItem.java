package com.visma.hackaton.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "todo_item")
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_item_id")
    public long id;

    @Column(name = "name", nullable = false, length = 50)
    public String name;

    @Column(name = "description", length = 500)
    public String description;

    @Column(name = "is_done", columnDefinition = "boolean default false")
    public boolean isDone;

    @ManyToOne
    @JoinColumn(name = "todo_list_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    public ToDoList list;
}
