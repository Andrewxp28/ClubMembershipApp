package com.andrew.member;

import java.util.List;

/**
 * A service interface to interact with member entities from the database.
 */
public interface MemberService {
    /**
     * Takes an email and searches the database for the corresponding member. returns an instance of Member if found
     * and null otherwise.
     * @param email member's email
     * @return Member
     */
    Member findOneMember(String email);

    /**
     * Searches the database for all Members and returns them as a java.util.List of Member instances. If there are
     * no members within the database, null will be returned.
     * @return java.util.List of Member
     */
    List<Member> findAllMembers();

    /**
     * Takes an email and deletes the corresponding member from the database. Returns true if successfully and false
     * otherwise.
     * @param email member's email
     * @return boolean
     */
    boolean deleteOneMember(String email);

    /**
     * Deletes all members from the database. Return true if successful and false otherwise.
     * @return boolean
     */
    boolean deleteAllMembers();

    /**
     * Takes an email and finds the corresponding member and update the member with the new details. Returns true if
     * updated successfully and false otherwise.
     * @param memberEmail email of member to have their details updated.
     * @param newMemberDetails new details to update the member with.
     * @return boolean
     */
    boolean updateOneMember(String memberEmail, Member newMemberDetails);

    /**
     * Takes a Member instance and inserts their details into the database. Returns true if inserted successfully and
     * false otherwise.
     * @param member Member instance of the member to be added to the database.
     * @return boolean
     */
    boolean insertOneMember(Member member);
}
