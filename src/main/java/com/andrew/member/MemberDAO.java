package com.andrew.member;

import java.util.List;

public interface MemberDAO {

    public Member findOneMember(String email);
    public List<Member> findAllMembers();
    public boolean deleteOneMember(String email);
    public boolean deleteAllMembers();
    public boolean updateOneMember(Member member);
    public boolean insertOneMember(Member member);
}
