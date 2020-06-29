package com.vlasova.controller.command;

import com.vlasova.controller.command.impl.ajax.GetAllFacultiesAJAX;
import com.vlasova.controller.command.impl.ajax.GetAllGradeReportsAJAX;
import com.vlasova.controller.command.impl.faculty.AddFacultyCommand;
import com.vlasova.controller.command.impl.faculty.DeleteFacultyCommand;
import com.vlasova.controller.command.impl.gradereport.DeleteGradeReportCommand;
import com.vlasova.controller.command.impl.gradereport.EnrollGraderReportsCommand;
import com.vlasova.controller.command.impl.gradereport.EditGradeReportCommand;
import com.vlasova.controller.command.impl.ajax.GetAllUserAJAX;
import com.vlasova.controller.command.impl.gradereport.UnEnrollCommand;
import com.vlasova.controller.command.impl.language.SwitchLanguageCommand;
import com.vlasova.controller.command.impl.page.ProfilePageCommand;
import com.vlasova.controller.command.impl.user.*;

public enum CommandType {
    /*user commands*/
    SIGN_UP                   (new SignUpCommand()),
    LOGIN                     (new SignInCommand()),
    EDIT_REQUEST              (new EditGradeReportCommand()),
    EDIT_USER                 (new EditUserCommand()),
    LOG_OUT                   (new LogOutCommand()),

    /*Footer links*/
    PROFILE                   (new ProfilePageCommand()),

    /*ADMIN*/
    ADD_FACULTY               (new AddFacultyCommand()),
    DELETE_FACULTY            (new DeleteFacultyCommand()),
    ADD_USER                  (new AddUserCommand()),
    DELETE_USER               (new DeleteUserCommand()),
    GET_ALL_USERS             (new GetAllUsersCommand()),
    DELETE_GRADE_REPORT       (new DeleteGradeReportCommand()),

    /*json data*/
    GET_ALL_USERS_AJAX        (new GetAllUserAJAX()),
    GET_ALL_FACULTIES_AJAX    (new GetAllFacultiesAJAX()),
    GET_ALL_GRADE_REPORTS_AJAX(new GetAllGradeReportsAJAX()),

    /*buttons*/
    ACCEPT                    (new EnrollGraderReportsCommand()),
    NULLIFY                   (new UnEnrollCommand()),
    SWITCH_LANG               (new SwitchLanguageCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command getCommandByString(String string){
        for (CommandType ct : values()){
            if ( ct.toString().equalsIgnoreCase(string)){
                return ct.getCommand();
            }
        }
        return null;
    }

    public static CommandType getCommandTypeByString(String string){
        for (CommandType ct : values()){
            if(ct.toString().equalsIgnoreCase(string)){
                return ct;
            }
        }
        return null;
    }

    public Command getCommand() {
        return command;
    }
}