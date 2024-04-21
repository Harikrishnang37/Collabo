package com.penguins.collabo.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDto {
    private int id;
    private int userid;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "DocumentDto [id=" + id + ", userid=" + userid + ", title=" + title;
    }
}
