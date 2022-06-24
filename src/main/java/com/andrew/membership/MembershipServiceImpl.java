package com.andrew.membership;

import java.util.List;

/**
 * A concrete implementation of MembershipService.
 */
public class MembershipServiceImpl implements MembershipService {

    MembershipDAO membershipDAO;

    public MembershipServiceImpl() {
        membershipDAO = new MembershipDAOImpl();
    }

    @Override
    public Membership findOneMembership(int membershipId) {
        return membershipDAO.findOneMembership(membershipId);
    }

    @Override
    public Membership findOneMembership(Membership membership) {
        return membershipDAO.findOneMembership(membership);
    }

    @Override
    public List<Membership> findAllMembershipsOfOneMember(String memberEmail) {
        return membershipDAO.findAllMembershipsOfOneMember(memberEmail);
    }

    @Override
    public List<Membership> findAllMemberships() {
        return membershipDAO.findAllMemberships();
    }

    @Override
    public List<Membership> findAllActiveMemberships() {
        return membershipDAO.findAllActiveMemberships();
    }

    @Override
    public boolean insertOneMembership(Membership membership) {
        // need to check for duplicates.
        List<Membership> memberships = membershipDAO.findAllMembershipsOfOneMember(membership.getMemberEmail());
        // if there are no memberships at all
        if (memberships == null) {
            return membershipDAO.insertOneMembership(membership);
        }
        // found a list of membership owned by one member.
        // checking if this specific membership already exist.
        boolean exists = memberships.contains(membership);
        if (exists) return false;
        // this membership does not exist, so we can safely insert.
        return membershipDAO.insertOneMembership(membership);
    }

    @Override
    public boolean deleteOneMembership(int membershipId) {
        return membershipDAO.deleteOneMembership(membershipId);
    }

    @Override
    public boolean deleteOneMembership(Membership membership) {
        return membershipDAO.deleteOneMembership(membership);
    }

    @Override
    public boolean deleteAllMemberships() {
        return membershipDAO.deleteAllMemberships();
    }
}
