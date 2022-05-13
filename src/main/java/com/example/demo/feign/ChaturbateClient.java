package com.example.demo.feign;

import com.example.demo.model.ChaturbateModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://chaturbate.com/api/chatvideocontext/", name = "Chaturbate")
public interface ChaturbateClient {

    @GetMapping("/{name}")
    public ResponseEntity<ChaturbateModel> getChaturbateModel(@PathVariable String name);

}
