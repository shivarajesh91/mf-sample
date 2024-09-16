package com.krishna.coding.mf.controller;

import com.krishna.coding.mf.dto.TokenResponse;
import com.krishna.coding.mf.dto.AuthRequest;
import com.krishna.coding.mf.entity.UserInfo;
import com.krishna.coding.mf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mf/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> createUser(@RequestBody UserInfo userInfo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(userInfo));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
