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
@Table(name = "AccessReq")
public class AccessRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int fromUserId;
    private int toUserId;
    private int docId;
    private String docTitle;
    private String fromUserName;
    private String type;

    public AccessRequest(int fromUserId, int toUserId, int docId, String docTitle, String fromUserName, String type) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.docId = docId;
        this.docTitle = docTitle;
        this.fromUserName = fromUserName;
        this.type = type;
    }
}