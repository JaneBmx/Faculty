package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindFacultyById extends AbstractFacultySpecification implements FacultySpecification {
    private static final String FIND = "SELECT * FROM faculties WHERE faculty_id = ?";
    private int facultyId;

    public FindFacultyById(int id) {
        this.facultyId = id;
    }

    @Override
    public Set<Faculty> query() throws QueryException {
        faculties = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND);) {
            if (statement != null) {
                statement.setInt(1, facultyId);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    faculties.add(createFaculty());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        return faculties;
    }
}