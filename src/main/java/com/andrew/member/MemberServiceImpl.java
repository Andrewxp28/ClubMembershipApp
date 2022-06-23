package com.andrew.member;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    MemberDAO memberDao;

    public MemberServiceImpl() {
        memberDao = new MemberDAOImpl();
    }

    @Override
    public Member findOneMember(String email) {
        return memberDao.findOneMember(email);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberDao.findAllMembers();
    }

    @Override
    public boolean deleteOneMember(String email) {
        return memberDao.deleteOneMember(email);
    }

    @Override
    public boolean deleteAllMembers() {
        return memberDao.deleteAllMembers();
    }

    @Override
    public boolean updateOneMember(String memberEmail, Member newMemberDetails) {
        return memberDao.updateOneMember(memberEmail, newMemberDetails);
    }

    @Override
    public boolean insertOneMember(Member member) {
        // need to check if this email already exists
        Member rsMember = memberDao.findOneMember(member.getEmail());
        // member with this email already exists
        if (rsMember != null) return false;
        // member does not exist so we can insert safely.
        return memberDao.insertOneMember(member);
    }
}
