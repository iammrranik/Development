package com.example.database.repository;

import com.example.database.domain.Student;
import com.example.database.repository.mapper.StudentMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;

@Repository
public class StudentRepository implements IStudentRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public StudentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Student save(Student student) {
        String sql = """
                INSERT INTO students (name, cgpa)
                VALUES (:name, :cgpa)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", student.getName());
        params.addValue("cgpa", student.getCgpa());
        namedParameterJdbcTemplate.update(sql, params);
        return student;
    }

    @Override
    public Optional<Student> findById(Integer id) {
        String sql = """
                SELECT id, name, cgpa
                FROM students
                WHERE id = :id
                """;
        Map<String, Object> params = Map.of("id", id);
        List<Student> students = namedParameterJdbcTemplate.query(sql, params, new StudentMapper());
        if(!students.isEmpty()) {
            return students.stream().findFirst();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll(int page, int size) {
        int offset;
        if(page<=1){
            offset = 0;
        }else{
            offset = (page-1)*size;
        }

        String sql = """
                SELECT id, name, cgpa
                FROM students
                ORDER BY id
                LIMIT :limit OFFSET :offset
                """;
        Map<String, Object> params = Map.of("offset", offset, "limit", size);
        List<Student> students = namedParameterJdbcTemplate.query(sql, params, new StudentMapper());
        if(!students.isEmpty()) {
            return students;
        }
        return  Collections.emptyList();
    }

    @Override
    public int count() {
        String sql = """
                SELECT count(*)
                FROM students
                """;
        return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    @Override
    public int update(Student student) {
        String sql = """
                UPDATE students
                SET name=:name, cgpa=:cgpa
                WHERE id=:id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", student.getName());
        params.addValue("cgpa", student.getCgpa());
        params.addValue("id", student.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = """
                DELETE FROM students
                WHERE id=:id
                """;
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
}
