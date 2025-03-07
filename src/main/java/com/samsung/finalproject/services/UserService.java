package com.samsung.finalproject.services;

import com.samsung.finalproject.models.repositories.UserRepository;
import com.samsung.finalproject.models.viewmodels.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean registerUser(Users user, String confirmPassword) {
        Users existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.isPresent()) {
            return false;
        }

        if (!user.getPassword().equals(confirmPassword)) {
            return false;
        }
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return true;
    }
}
