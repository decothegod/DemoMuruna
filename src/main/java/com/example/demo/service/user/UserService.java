package com.example.demo.service.user;


import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO register(UserRequest request);

    UserDTO login(LoginRequest request);

    List<UserDTO> getAllUsers();

    UserDTO getUserByUUID(String uuid);
}
