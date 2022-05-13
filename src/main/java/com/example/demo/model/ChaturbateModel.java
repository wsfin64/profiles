package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChaturbateModel {

    public ChaturbateModel(){
    }

    public ChaturbateModel(String broadcaster_username, Integer age, String room_status){
        this.broadcaster_username = broadcaster_username;
        this.age = age;
        this.room_status = room_status;
    }

    private String broadcaster_username;
    private Integer age;
    private String room_status;

    public String getUrl(){
        return "https://chaturbate.com/" + this.broadcaster_username;
    }

}
