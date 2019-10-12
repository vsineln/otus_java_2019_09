package ru.otus.l05.auth;

import java.util.Optional;

/*
 *
 * Проверить логику аутентификации:
 * 1) Если логин/пароль введен верно - возвращается true
 * 2) Если пользователя не существует или логин/пароль введен неверно - возвращается false
 *
 * */

public class AuthenticationService {

    private final UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean authenticate(String name, String password) {
        return Optional.ofNullable(userDao.findByName(name))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
