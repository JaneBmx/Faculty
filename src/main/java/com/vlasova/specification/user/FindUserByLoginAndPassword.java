package com.vlasova.specification.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/*
 *
 */
public class FindUserByLoginAndPassword extends AbstractUserSpecification implements UserSpecification {
    private static final String FIND =
            "SELECT user_id, user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege " +
                    "FROM users WHERE login = ? AND  password = ?";
    private String userLogin;
    private String userPassword;

    public FindUserByLoginAndPassword(String userLogin, String userPassword) {
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    @Override
    public Set<User> query() throws QueryException {
        users = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, userLogin);
                preparedStatement.setString(2, userPassword);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    users.add(createUser());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        return users;
    }
}
