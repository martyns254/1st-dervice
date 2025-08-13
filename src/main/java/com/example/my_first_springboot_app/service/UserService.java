package com.example.my_first_springboot_app.service;

import com.example.my_first_springboot_app.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService() {
        // Initialize with sample data
        createSampleUser("Alice Johnson", "alice@example.com", 28);
        createSampleUser("Bob Smith", "bob@example.com", 35);
        createSampleUser("Carol Davis", "carol@example.com", 42);
    }

    private void createSampleUser(String name, String email, int age) {
        User user = new User(name, email, age);
        Long id = idGenerator.getAndIncrement();
        user.setId(id);
        users.put(id, user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Optional<User> getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
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

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAge(updatedUser.getAge());

        return existingUser;
    }

    public boolean deleteUser(Long id) {
        return users.remove(id) != null;
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
                .collect(java.util.stream.Collectors.toList());
    }
}