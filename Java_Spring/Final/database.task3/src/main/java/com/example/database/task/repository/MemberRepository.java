package com.example.database.task.repository;

import com.example.database.task.domain.Member;
import com.example.database.task.repository.Mapper.MemberMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository implements IMemberRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MemberRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Member member) {
        String sql = "INSERT INTO member (name, email, phone) VALUES (:name, :email, :phone)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", member.getName())
                .addValue("email", member.getEmail())
                .addValue("phone", member.getPhone());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Member> findById(Integer id) {
        String sql = "SELECT id, name, email, phone FROM member WHERE id = :id";
        Map<String, Object> params = Map.of("id", id);
        List<Member> members = namedParameterJdbcTemplate.query(sql, params, new MemberMapper());
        return members.stream().findFirst();
    }

    @Override
    public List<Member> findAll(int page, int size) {
        int offset = (page <= 1) ? 0 : (page - 1) * size;
        String sql = "SELECT id, name, email, phone FROM member ORDER BY id LIMIT :limit OFFSET :offset";
        Map<String, Object> params = Map.of("limit", size, "offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, new MemberMapper());
    }

    @Override
    public int count() {
        String sql = "SELECT count(*) FROM member";
        return namedParameterJdbcTemplate.queryForObject(sql, new HashMap<>(), Integer.class);
    }

    @Override
    public int update(Member member) {
        String sql = "UPDATE member SET name = :name, email = :email, phone = :phone WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", member.getName())
                .addValue("email", member.getEmail())
                .addValue("phone", member.getPhone())
                .addValue("id", member.getId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM member WHERE id = :id";
        return namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }
}