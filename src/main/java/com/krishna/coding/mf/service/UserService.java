package com.krishna.coding.mf.service;

import com.krishna.coding.mf.dto.TokenResponse;
import com.krishna.coding.mf.dto.AuthRequest;
import com.krishna.coding.mf.entity.UserInfo;
import com.krishna.coding.mf.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoDetailsService userInfoDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponse createUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo user = repository.save(userInfo);
        var token = jwtService.generateToken(userInfoDetailsService.loadUserByUsername(user.getUsername()));
        log.info("token: {}", token);
        return TokenResponse.builder().token(token).build();

    }

    public String authenticate(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        if(authentication.isAuthenticated()){
            return "Authentication is successful!.";
        }else{
            throw new UsernameNotFoundException("Invalid credentials!.");
        }
    }
}
