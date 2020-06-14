package com.vlasova.command.mapper;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

public class FacultyMapper {
    private static final String FAC_NAME = "faculty_name";
    private static final String PAID = "paid";
    private static final String FREE = "free";
    private static final String SUB_1 = "sub_1_id";
    private static final String SUB_2 = "sub_2_id";
    private static final String SUB_3 = "sub_3_id";

    public Faculty map(HttpServletRequest request) {
        Faculty faculty = new Faculty();
        faculty.setName(request.getParameter(FAC_NAME));
        faculty.setFreeAcceptPlan(Integer.parseInt(request.getParameter(FREE)));
        faculty.setPaidAcceptPlan(Integer.parseInt(request.getParameter(PAID)));
        faculty.setSubjects(mapSubjects(request));
        return faculty;
    }

    private Set<Subject> mapSubjects(HttpServletRequest request) {
        Set<Subject> subjects = new HashSet<>();
        subjects.add(Subject.getSubjectById(Integer.parseInt(request.getParameter(SUB_1))));
        subjects.add(Subject.getSubjectById(Integer.parseInt(request.getParameter(SUB_2))));
        subjects.add(Subject.getSubjectById(Integer.parseInt(request.getParameter(SUB_3))));
        return subjects;
    }
}