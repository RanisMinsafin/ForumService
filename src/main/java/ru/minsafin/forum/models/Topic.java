package ru.minsafin.forum.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be from 3 to 30 symbols")
    private String name;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE)
    private List<Message> messages;
}
