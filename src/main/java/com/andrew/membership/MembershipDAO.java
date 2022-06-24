package com.andrew.membership;

import java.util.List;

/** A Data Access Object (DAO) interface that interacts with the database. MembershipDAO handles all
 * membership database-related
 * operations.
 */

public interface MembershipDAO {
    /**
     * Takes a membership ID and searches for the membership in the database. Returns a Membership instance if found and
     * null otherwise.
     * @param membershipId membership ID of membership that is searched for in the database.
     * @return Membership
     */
    Membership findOneMembership(int membershipId);

    /**
     * Takes a membership instance and searches the database for a membership with matching details. Returns a
     * Membership instance if found, otherwise returns null.
     * @param membership Membership instance containing details of the desired membership.
     * @return Membership
     */
    Membership findOneMembership(Membership membership);

    /**
     * Finds all membership related to memberEmail. Returns java.util.List of Member instances if found and returns
     * null otherwise.
     * @param memberEmail Member's email that owns the memberships.
     * @return java.util.List of Memberships
     */
    List<Membership> findAllMembershipsOfOneMember(String memberEmail);

    /**
     * Finds all memberships in the database and returns them as a java.util.List if there
     * exists memberships. Otherwise returns null;
     * @return java.util.List of Memberships
     */
    List<Membership> findAllMemberships();

    /**
     * Finds all memberships that are active and returns them as a java.util.List if there exists active
     * memberships. Returns null otherwise.
     * @return
     */
    List<Membership> findAllActiveMemberships();

    /**
     * Takes a membership instance and inserts their details into the database. returns true if successful and false
     * otherwise.
     * @param membership membership to be added to database
     * @return boolean
     */
    boolean insertOneMembership(Membership membership);

    /**
     * takes a membership instance and deletes the corresponding membership within the database. Returns true if
     * successful and false otherwise.
     * @param membership membership to be deleted.
     * @return boolean
     */
    boolean deleteOneMembership(Membership membership);

    /**
     * Takes a membership id and deletes the corresponding membership from the database. Returns true if
     * successful and false otherwise.
     * @param membershipId membership id of membership to be deleted.
     * @return boolean
     */
    boolean deleteOneMembership(int membershipId);

    /**
     * Deletes all Memberships within the database. Returns true if successful and false otherwise.
     * @return boolean
     */
    boolean deleteAllMemberships();
}
