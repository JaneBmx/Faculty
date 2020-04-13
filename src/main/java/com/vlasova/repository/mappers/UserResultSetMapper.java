package com.vlasova.repository.mappers;

import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.specification.user.AbstractUserSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapper implements ResultSetMapper<User> {

    private static Logger logger = LogManager.getLogger(AbstractUserSpecification.class);
    protected static final String ID = "user_id";
    protected static final String ROLE = "user_role";
    protected static final String NAME = "user_name";
    protected static final String SURNAME = "user_surname";
    protected static final String EMAIL = "user_email";
    protected static final String LOGIN = "user_login";
    protected static final String PASSWORD = "user_password";
    protected static final String PRIVILEGE = "user_privilege";

    @Override
    public User map(ResultSet resultSet) {
        User user = new User();
        try {
            resultSet.next();
            user.setId(resultSet.getInt(ID));
            user.setRole(Role.getRole(resultSet.getInt(ROLE)));
            user.setName(resultSet.getString(NAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setPrivilege(Privilege.getPrivilegeById(resultSet.getInt(PRIVILEGE)));
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

}
