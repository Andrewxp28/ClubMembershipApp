package com.andrew.membership;

/**
 * A controller interface to control and execute Member commands within the application.
 */
public interface MembershipController {
    /**
     * Displays the options for managing memberships
     */
    void displayMembershipOptions();

    /**
     * Takes an option and executes the corresponding operation.
     * @param option - The number of the operation to be executed
     */
    void executeMembershipOption(int option);

    /**
     * Displays all memberships.
     */
    void displayAllMemberships();

    /**
     * Displays all active memberships.
     */
    void displayAllActiveMemberships();

    /**
     * Displays a form and finds the memberships belonging to a member.
     */
    void displayFormAndFindMembershipsOfOneMember();

    /**
     * Displays a form to find one membership.
     */
    void displayFormAndFindOneMembership();

    /**
     * Displays a form to insert one membership.
     */
    void displayFormAndInsertOneMembership();

    /**
     * Displays a form to delete one membership.
     */
    void displayFormAndDeleteOneMembership();

    /**
     * Displays status of operation and deletes all memberships.
     */
    void displayAndDeleteAllMemberships();
}
