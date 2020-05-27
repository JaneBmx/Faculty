package com.vlasova.command.impl.gradereport;

import com.vlasova.command.Answer;
import com.vlasova.command.mapper.GradeReportMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.GradeReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditGradeReportCommand implements GradeReportCommand {
    private static final Logger LOGGER = LogManager.getLogger(EditGradeReportCommand.class);
    private final GradeReportMapper gradeReportMapper = new GradeReportMapper();
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        int userID = ((User) request.getSession().getAttribute(USER)).getId();
        GradeReport gradeReport;
        try {
            gradeReport = gradeReportService.getGradeReportByUserId(userID);
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(MSG, MSG_SERV_ERR);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }

        if (gradeReport == null) {
            gradeReport = gradeReportMapper.map(request);
            try {
                gradeReportService.addGradeReport(gradeReport);
                request.getSession().setAttribute(GRADE_REPORT, gradeReport);
                return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                request.setAttribute(MSG, MSG_SERV_ERR);
                return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
            }
        }

        gradeReport = gradeReportMapper.map(request);
        try {
            gradeReportService.updateGradeReport(gradeReport);
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(MSG, MSG_SERV_ERR);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }
        request.getSession().setAttribute(GRADE_REPORT, gradeReport);
        return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
    }
}
