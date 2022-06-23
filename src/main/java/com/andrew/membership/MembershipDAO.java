package com.andrew.membership;

import com.andrew.member.Member;

import java.util.List;

public interface MembershipDAO {
    Membership findOneMembership(int membershipId);
    Membership findOneMembership(Membership membership);
    List<Membership> findAllMembershipsOfOneMember(String memberEmail);
    List<Membership> findAllMemberships();
    boolean insertOneMembership(Membership membership);
    //public boolean updateOneMembership(Membership membership);
    boolean deleteOneMembership(Membership membership);
    boolean deleteOneMembership(int membershipId);
    boolean deleteAllMemberships();
}
