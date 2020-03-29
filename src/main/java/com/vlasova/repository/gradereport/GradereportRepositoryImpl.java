package com.vlasova.repository.gradereport;

import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.gradereport.GradeReportSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GradeReportRepositoryImpl implements GradeReportRepository {
    private static final String INSERT_GRADE_REPORT = "INSERT INTO  grade_reports(user_id, faculity_id) VALUES (?, ?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO grade_repotr2subject (user_id, subject_id, mark) VALUES(?,?,?)";
    private static final String DELETE = "DELETE FROM grade_reports WHERE user_id = ?";
    private static final String DELETE_MARKS = "DELETE FROM grade_report2subject WHERE user_id = ?";
    private static final String UPDATE = "UPDATE grade_reports SET certificate = ?, isAccepted = ?, isFree = ? WHERE user_id = ?";

    private static class GradeReportRepositoryHolder {
        private static final GradeReportRepositoryImpl INSTANCE = new GradeReportRepositoryImpl();
    }

    public static GradeReportRepositoryImpl getInstance() {
        return GradeReportRepositoryHolder.INSTANCE;
    }

    private GradeReportRepositoryImpl() {
    }

    @Override
    public void add(User user) throws RepositoryException {
        if (user != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE_REPORT)) {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setInt(2, user.getGradeReport().getFaculty().getId());
                    preparedStatement.executeUpdate();
                    addSubjectsMarks(user, connection);
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    private void addSubjectsMarks(User user, ProxyConnection connection) throws SQLException {
        if (user.getGradeReport() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECTS)) {
                if (preparedStatement != null) {
                    for (Map.Entry<Subject, Integer> entry : user.getGradeReport().getMarks().entrySet()) {
                        preparedStatement.setInt(1, user.getId());
                        preparedStatement.setInt(2, entry.getKey().getId());
                        preparedStatement.setInt(3, entry.getValue());
                        preparedStatement.executeUpdate();
                    }
                }
            }
        }
    }

    @Override
    public void remove(User user) throws RepositoryException {
        if (user != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS)) {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.executeQuery();
                    preparedStatement1.setInt(1, user.getId());
                    preparedStatement1.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    @Override
    public void update(User user, GradeReport gradeReport) throws RepositoryException {
        if (user != null && gradeReport != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                if (preparedStatement != null) {
                    preparedStatement.setDouble(1, user.getGradeReport().getCertificate());
                    preparedStatement.setBoolean(2, user.getGradeReport().isAccepted());
                    preparedStatement.setBoolean(3, user.getGradeReport().isFree());
                    preparedStatement.setInt(4, user.getId());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    @Override
    public Set<GradeReport> query(GradeReportSpecification specification) throws RepositoryException {
        if (specification != null) {
            try {
                return specification.query();
            } catch (QueryException e) {
                throw new RepositoryException(e);
            }
        }
        return new HashSet<>();
    }
}