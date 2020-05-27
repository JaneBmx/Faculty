package com.vlasova.command;

import com.vlasova.command.impl.faculty.AddFacultyCommand;
import com.vlasova.command.impl.gradereport.EditGradeReportCommand;
import com.vlasova.command.impl.page.LoginPage;
import com.vlasova.command.impl.user.*;

public enum CommandType {
    /*user commands*/
    LOGIN(new SignInCommand()),
    EDIT_USER(new EditUserCommand()),
    SIGN_UP(new SignUpCommand()),
    EDIT_REQUEST(new EditGradeReportCommand()),
    LOG_OUT(new LogOutCommand()),

    /*Footer links*/
    LOGIN_PAGE(new LoginPage()),

    /*faculty*/
    ADD_FACULTY(new AddFacultyCommand()),

    GET_ALL_USERS(new GetAllUsersCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
