package com.example.database.repository.mapper;

import com.example.database.domain.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(rs.getInt("id"), rs.getString("name"), rs.getFloat("cgpa"));
    }

}
