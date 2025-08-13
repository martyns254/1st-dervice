package com.example.my_first_springboot_app;

import com.example.my_first_springboot_app.model.User;
import com.example.my_first_springboot_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        User user = new User("Test User", "test@example.com", 25);
        User created = userService.createUser(user);

        assertNotNull(created.getId());
        assertEquals("Test User", created.getName());
        assertEquals("test@example.com", created.getEmail());
        assertEquals(25, created.getAge());
        assertTrue(created.isActive());
    }

    @Test
    void testGetUserStats() {
        long totalUsers = userService.getUserCount();
        long activeUsers = userService.getActiveUserCount();
        double avgAge = userService.getAverageAge();

        assertTrue(totalUsers >= 3); // We initialize with 3 users
        assertTrue(activeUsers >= 3);
        assertTrue(avgAge > 0);
    }
}