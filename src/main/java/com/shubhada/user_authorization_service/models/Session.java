package com.shubhada.user_authorization_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends  BaseModel{
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String token;
}
