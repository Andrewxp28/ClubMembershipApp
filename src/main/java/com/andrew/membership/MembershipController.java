package com.andrew.membership;

public interface MembershipController {
    // displaying options for memberships
    void displayMembershipOptions();
    // interpreting membership option selected
    void executeMembershipOption(int option);

    void displayAllMemberships();
    void displayAllActiveMemberships();
    void displayFormAndFindMembershipsOfOneMember();
    void displayFormAndFindOneMembership();
    void displayFormAndInsertOneMembership();
    void displayFormAndDeleteOneMembership();
    void displayAndDeleteAllMemberships();
}
