package com.andrew.membership;

import java.util.List;

public interface MembershipService {
    Membership findOneMembership(int membershipId);
    Membership findOneMembership(Membership membership);
    List<Membership> findAllMembershipsOfOneMember(String memberEmail);
    List<Membership> findAllMemberships();
    boolean insertOneMembership(Membership membership);
    boolean deleteOneMembership(int membershipId);
    boolean deleteOneMembership(Membership membership);
    boolean deleteAllMemberships();


}
