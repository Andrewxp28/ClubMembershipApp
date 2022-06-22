package com.andrew.membership;

import com.andrew.member.Member;

import java.util.List;

public interface MembershipDAO {
    public Membership findOneMembership(int membershipId);
    public Membership findOneMembership(Membership membership);
    public List<Membership> findAllMembershipsOfOneMember(String memberEmail);
    public List<Membership> findAllMemberships();
    public boolean insertOneMembership(Membership membership);
    public boolean updateOneMembership(Membership membership);
    public boolean deleteOneMembership(Membership membership);
    public boolean deleteAllMemberships();
}
