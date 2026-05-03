package com.example.database.task.service;

import com.example.database.task.domain.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberService {
    Member save(Member member);
    Optional<Member> findById(Integer id);
    List<Member> findAll(int page, int size);
    int count();
    int update(Member member);
    int delete(int id);
}