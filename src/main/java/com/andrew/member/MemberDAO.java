package com.andrew.member;

import java.util.List;

/** A Data Access Object (DAO) interface that interacts with the database. MemberDAO handles all
 * Member database-related
 * operations.
 */
public interface MemberDAO {
    /**
     * Takes an email and searches within the database for the corresponding member. returns a Member instance
     * if found, otherwise returns null.
     * @param email A member's email
     * @return Member
     */
    Member findOneMember(String email);

    /**
     * Searches the entire database for all members and returns member instances within a java.util.List. If no members exists,
     * null is returned.
     * @return java.util.List of Members.
     */
    List<Member> findAllMembers();

    /**
     * Takes an email and deletes the corresponding member from the database. Returns true if successful and
     * false if an error occurred.
     * @param email a member's email
     * @return boolean
     */
    boolean deleteOneMember(String email);

    /**
     * Deletes all members from the database. Returns true if successful, false otherwise.
     * @return boolean
     */
    boolean deleteAllMembers();

    /**
     * Takes a member's email and new member details to update a member's details within the database.
     * Returns true if update was successful, false otherwise.
     * @param memberEmail Member's email who will have their details updated.
     * @param newMemberDetails Member's new details encapsulated in a Member object.
     * @return boolean
     */
    boolean updateOneMember(String memberEmail, Member newMemberDetails);

    /**
     * Takes a member instance and insert its details into the database. Returns true if inserted successfully, false
     * otherwise.
     * @param member Member instance holding all the details of the member.
     * @return boolean
     */
    boolean insertOneMember(Member member);
}
