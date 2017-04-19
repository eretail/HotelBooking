package com.eretailservice.auth.service;

import com.eretailservice.auth.model.User;

public interface UserService {
    void save(User user);

    public User findByUsername(String username);
}
