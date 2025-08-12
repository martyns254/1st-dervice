package com.example.my_first_springboot_app.service;

import com.example.my_first_springboot_app.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService() {
        // Initialize with sample data
        createUser(new User("Alice Johnson", "alice@example.com", 28));
        createUser(new User("Bob Smith", "bob@example.com", 35));
        createUser(new User("Carol Davis", "carol@example.com", 42));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<User> getActiveUsers() {
        return users.values().stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public List<User> getUsersByAgeRange(int minAge, int maxAge) {
        return users.values().stream()
                .filter(user -> user.getAge() >= minAge && user.getAge() <= maxAge)
                .collect(Collectors.toList());
    }

    public User createUser(User user) {
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        Long id = idGenerator.getAndIncrement();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = users.get(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }

        // Check if email is being changed and if new email already exists
        if (!existingUser.getEmail().equals(updatedUser.getEmail()) &&
                getUserByEmail(updatedUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email " + updatedUser.getEmail() + " is already in use");
        }

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());

        return existingUser;
    }

    public boolean deleteUser(Long id) {
        return users.remove(id) != null;
    }

    public boolean deactivateUser(Long id) {
        User user = users.get(id);
        if (user != null) {
            user.deactivate();
            return true;
        }
        return false;
    }

    public boolean activateUser(Long id) {
        User user = users.get(id);
        if (user != null) {
            user.activate();
            return true;
        }
        return false;
    }

    public long getUserCount() {
        return users.size();
    }

    public long getActiveUserCount() {
        return users.values().stream()
                .filter(User::isActive)
                .count();
    }

    public double getAverageAge() {
        return users.values().stream()
                .filter(User::isActive)
                .mapToInt(User::getAge)
                .average()
                .orElse(0.0);
    }

    public List<User> searchUsers(String searchTerm) {
        String term = searchTerm.toLowerCase();
        return users.values().stream()
                .filter(user ->
                        user.getName().toLowerCase().contains(term) ||
                                user.getEmail().toLowerCase().contains(term))
                .collect(Collectors.toList());
    }
}