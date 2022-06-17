package com.andrew.member;

import java.util.List;

public interface MemberDAO {

    public Member findOne(String email);
    public List<Member> findAll();
    public boolean deleteOne(String email);
    public boolean deleteAll();
    public boolean updateOne(Member member);
    public boolean insertOne(Member member);
}
