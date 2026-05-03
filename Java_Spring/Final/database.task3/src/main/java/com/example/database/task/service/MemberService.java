package com.example.database.task.service;

import com.example.database.task.domain.Member;
import com.example.database.task.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements IMemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member save(Member member) {
        memberRepository.save(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Integer id) {
        return memberRepository.findById(id);
    }

    @Override
    public List<Member> findAll(int page, int size) {
        return memberRepository.findAll(page, size);
    }

    @Override
    public int count() {
        return memberRepository.count();
    }

    @Override
    public int update(Member member) {
        return memberRepository.update(member);
    }

    @Override
    public int delete(int id) {
        return memberRepository.delete(id);
    }
}