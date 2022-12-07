package com.fk.apiDashboard.controllers;

import com.fk.apiDashboard.exception.ResourceNotFoundException;
import com.fk.apiDashboard.models.User;
import com.fk.apiDashboard.repository.UserRepository;
import com.fk.apiDashboard.security.CurrentUser;
import com.fk.apiDashboard.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
