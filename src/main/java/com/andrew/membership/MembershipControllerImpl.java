package com.andrew.membership;

public class MembershipControllerImpl implements MembershipController {

    MembershipService membershipService;

    public MembershipControllerImpl() {
        membershipService = new MembershipServiceImpl();
    }

    @Override
    public void displayMembershipOptions() {
        String[] options = {
                "1: See all memberships",
                "2: See all active memberships",
                "3: Find all memberships of a member",
                "4: in"
        };
    }

    @Override
    public void executeMembershipOption(int option) {

    }


    @Override
    public void displayAllMemberships() {

    }

    @Override
    public void displayAllActiveMemberships() {

    }

    @Override
    public void displayFormAndFindMembershipsOfOneMember() {

    }

    @Override
    public void displayFormAndFindOneMembership() {

    }

    @Override
    public void displayFormAndInsertOneMembership() {

    }

    @Override
    public void displayFormAndUpdateOneMembership() {

    }

    @Override
    public void displayFormAndDeleteOneMembership() {

    }
}
