package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.impl.page.ProfilePageCommand;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.GradeReportService;
import com.vlasova.service.UserService;

import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements UserCommand {
    private final UserService userService = UserService.getInstance();
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASS);

        if (login != null && password != null) {
            try {
                User user = userService.logIn(login, password);
                if (user != null) {
                    request.getSession().setAttribute(USER, user);


                    request.getSession().setAttribute(ROLE, user.getRole() == Role.ADMIN ? ADMIN : USER);



                    GradeReport gradeReport = gradeReportService.getGradeReportByUserId(user.getId());
                    if (gradeReport != null) {
                        request.getSession().setAttribute(GRADE_REPORT, gradeReport);
                    }
                    return user.getRole() == Role.ADMIN
                            ? new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT)
                            : new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
                }
                request.setAttribute(MSG, MSG_ERR_WRONG_PAS_OR_LOG);
            } catch (ServiceException e) {
                request.setAttribute(MSG, MSG_SERV_ERR);
            }
        }
        return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
    }
}
