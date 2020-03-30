package com.vlasova.service.impl;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.repository.user.UserRepository;
import com.vlasova.repository.user.UserRepositoryImpl;
import com.vlasova.service.UserService;
import com.vlasova.specification.user.FindUserById;
import com.vlasova.specification.user.FindUserByLoginAndPassword;

import java.util.Set;

public class UserServiceImpl implements UserService {
    //TODO validator
    private final UserRepository userRepository;
    private final UserService userService;

    private static class Holder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImpl.Holder.INSTANCE;
    }

    private UserServiceImpl() {
        userService = UserServiceImpl.getInstance();
        userRepository = UserRepositoryImpl.getInstance();
    }

    public void deleteUser(User user) throws ServiceException {
        if (user != null) {
            try {
                userRepository.remove(user.getId());
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public User logIn(String login, String password) throws ServiceException {
        try {
            Set<User> users = userRepository.query(new FindUserByLoginAndPassword(login, password));
            if (users.iterator().hasNext()) {
                return users.iterator().next();
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    public User registration(String name, String surname, String email, String login, String password) throws ServiceException {
        User user = new User();
        try {
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(Role.ENROLLEE);
            userRepository.add(user);
            return user;
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public User getUser(int id) throws ServiceException {
        try {
            if (userRepository.query(new FindUserById(id)).iterator().hasNext()) {
                return userRepository.query(new FindUserById(id)).iterator().next();
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return null;
    }




}