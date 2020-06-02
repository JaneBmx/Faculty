package com.vlasova.command.impl.page;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.vlasova.command.RequestParams.*;

public class ProfilePageCommand implements Command {
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String role = (String) request.getSession().getAttribute("role");
        if (role != null && !role.equalsIgnoreCase(GUEST)) {
            return request.getSession().getAttribute(ROLE).toString().equalsIgnoreCase(ADMIN)
                    ? new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT)
                    : new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
        }
        return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
    }
}
