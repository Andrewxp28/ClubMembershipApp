package com.andrew.member;

import java.util.List;

public interface MemberService {
    Member findOneMember(String email);
    List<Member> findAllMembers();
    boolean deleteOneMember(String email);
    boolean deleteAllMembers();
    boolean updateOneMember(String memberEmail, Member newMemberDetails);
    boolean insertOneMember(Member member);
}
