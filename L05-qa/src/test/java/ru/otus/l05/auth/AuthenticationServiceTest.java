package ru.otus.l05.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest {

    @Test
    void authenticateSuccess() {
        UserDao userDao = mock(UserDao.class);
        String name = "John";
        String password = "qwerty";
        when(userDao.findByName(name)).thenReturn(new User(name, password));
        AuthenticationService authenticationService = new AuthenticationService(userDao);

        assertTrue(authenticationService.authenticate(name, password));
    }

    @Test
    void authenticateFail() {
        UserDao userDao = mock(UserDao.class);
        String name = "John";
        String password = "qwerty";
        when(userDao.findByName(name)).thenReturn(new User(name, "123456"));
        AuthenticationService authenticationService = new AuthenticationService(userDao);
        assertFalse(authenticationService.authenticate(name, password));
    }

}
