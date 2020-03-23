package com.vlasova.repository;

import com.vlasova.entity.user.GradeReport;
import com.vlasova.specification.GradereportSpecification;

import java.util.Set;

public interface GradereportRepository {
    void add(GradeReport user);

    void remove(int id);

    void update(GradeReport user);

    Set<GradeReport> query(GradereportSpecification specification);
}
