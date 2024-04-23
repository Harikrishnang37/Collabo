package com.penguins.collabo.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userid;
    private String username;
    private String title;
    private String content;

    @ElementCollection
    private List<Integer> ViewAccess = new ArrayList<>();
    @ElementCollection
    private List<Integer> EditAccess = new ArrayList<>();

}
