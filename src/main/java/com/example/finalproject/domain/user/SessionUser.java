package com.example.finalproject.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String myName;
    private String email;
    private Boolean blueChecked;

    @Builder
    public SessionUser(Integer id, String myName, String email, Boolean blueChecked) {
        this.id = id;
        this.myName = myName;
        this.email = email;
        this.blueChecked = blueChecked;
    }


    public SessionUser(User user) {
        this.id = user.getId();
        this.myName = getMyName();
        this.email = getEmail();
        this.blueChecked = getBlueChecked();
    }
}
