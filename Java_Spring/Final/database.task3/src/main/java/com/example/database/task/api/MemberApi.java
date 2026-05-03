package com.example.database.task.api;

import com.example.database.task.domain.Member;
import com.example.database.task.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberApi {

    private final MemberService memberService;

    public MemberApi(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public void save(@RequestBody Member member) {
        memberService.save(member);
    }

    @PutMapping
    public void update(@RequestBody Member member) {
        memberService.update(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        memberService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Member> findOne(@PathVariable int id) {
        return memberService.findById(id);
    }

    @GetMapping("/{page}/{size}")
    public List<Member> findAll(@PathVariable int page, @PathVariable int size) {
        return memberService.findAll(page, size);
    }
}