package com.andrew.member;

public interface MemberController {
    // displaying options for members
    void displayMemberOptions();
    // interpreting Member option selected
    void executeMemberOption(int option);

    // all the options for members
    void displayAllMembers();
    void displayFormAndFindOneMember();
    void displayFormAndInsertOneMember();
    void displayFormAndUpdateOneMember();
    void displayFormAndDeleteOneMember();
    void displayAndDeleteAllMembers();
    // finding active members
    void displayAllActiveMembers();
}
