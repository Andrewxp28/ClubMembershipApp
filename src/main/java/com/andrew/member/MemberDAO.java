package com.andrew.member;

import java.util.List;

public interface MemberDAO {

    Member findOneMember(String email);
    List<Member> findAllMembers();
    boolean deleteOneMember(String email);
    boolean deleteAllMembers();
    //public boolean updateOneMember(Member member);
    boolean updateOneMember(String memberEmail, Member newMemberDetails);
    boolean insertOneMember(Member member);
}
