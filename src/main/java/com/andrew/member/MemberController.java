package com.andrew.member;

/**
 * A controller interface to control and execute Member commands within the application.
 */
public interface MemberController {

    /**
     * Displays the options for managing members.
     */
    void displayMemberOptions();

    /**
     * Takes an option and executes the corresponding operation.
     * @param option - The number of the operation to be executed
     */
    void executeMemberOption(int option);

    /**
     * Displays all members.
     */
    void displayAllMembers();

    /**
     * Displays a form to find one member and displays the results.
     */
    void displayFormAndFindOneMember();

    /**
     * Displays a form to insert a member into the database, and displays the status of the operation.
     */
    void displayFormAndInsertOneMember();

    /**
     * Displays a form to update one member and displays the status of the operation.
     */
    void displayFormAndUpdateOneMember();

    /**
     * Display a form to delete one member and displays the status of the operation.
     */
    void displayFormAndDeleteOneMember();

    /**
     * Displays status of operation and deletes all Members.
     */
    void displayAndDeleteAllMembers();

    /**
     * Displays all members with active memberships.
     */
    void displayAllActiveMembers();
}
