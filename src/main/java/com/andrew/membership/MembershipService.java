package com.andrew.membership;

import java.util.List;

/**
 * A service interface to interact with member entities from the database.
 */
public interface MembershipService {
    /**
     * Takes a membership id and finds the corresponding membership within the database. Returns Membership instance
     * if found and null otherwise.
     * @param membershipId membership id of membership to be looked for.
     * @return Membership
     */
    Membership findOneMembership(int membershipId);

    /**
     * Takes a membership instance and finds a membership in the database with corresponding details. Returns
     * the membership if found and null otherwise.
     * @param membership membership details used to search database
     * @return Membership
     */
    Membership findOneMembership(Membership membership);

    /**
     * Takes a member email and finds all memberships owned by that member. Returns a java.util.List of Memberships
     * if found or null otherwise.
     * @param memberEmail member email that owns the memberships
     * @return java.util.List of Membership
     */
    List<Membership> findAllMembershipsOfOneMember(String memberEmail);

    /**
     * Finds all memberships in the database and returns them as a java.util.List if there are memberships in
     * the database. Returns
     * null otherwise.
     * @return java.util.List of Membership
     */
    List<Membership> findAllMemberships();

    /**
     * Finds all active memberships in the database and returns them as a java.util.List if there are active
     * memberships in the database. Returns null otherwise.
     * @return java.util.List of Membership
     */
    List<Membership> findAllActiveMemberships();

    /**
     * takes a membership instance and inserts its details into the database as a membership. Returns true if
     * inserted successfully or return false otherwise.
     * @param membership membership to be inserted
     * @return boolean
     */
    boolean insertOneMembership(Membership membership);

    /**
     * takes a membership id and deletes the corresponding membership from the database. Returns true if deleted
     * successfully or returns false otherwise.
     * @param membershipId membership id of membership to be deleted
     * @return boolean
     */
    boolean deleteOneMembership(int membershipId);

    /**
     * Takes a membership instance and deletes a membership in the database with matching details. Returns true if
     * deleted successfully or returns false otherwise.
     * @param membership
     * @return boolean
     */
    boolean deleteOneMembership(Membership membership);

    /**
     * Deletes all memberships from the database. Returns true deletion was successful or returns false otherwise.
     * @return boolean
     */
    boolean deleteAllMemberships();


}
