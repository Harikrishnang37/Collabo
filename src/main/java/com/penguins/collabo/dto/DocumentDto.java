package com.penguins.collabo.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DocumentDto {
    private int id;
    private int userid;
    private String title;
    private String content;
    private String username;

    private List<Integer> EditAccess;
    private List<Integer> ViewAccess;

    @Override
    public String toString() {
        return "DocumentDto [id=" + id + ", userid=" + userid + ", title=" + title + ", content=" + content + ", username=" + username + "]";
    }
}
