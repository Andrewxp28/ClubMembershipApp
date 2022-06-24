package com.andrew.membership;


import java.time.LocalDate;

/** An entity used to represent a Membership. This class is used as an intermediary
 * object to handle interactions between the system and the database.
 *  A membership is defined by having a startDate, endDate, memberEmail, type, and membershipId.
 */

public class Membership {
    private LocalDate startDate;
    private LocalDate endDate;
    private String memberEmail;
    private MembershipType type;
    private int membershipId;
    // returning constructor when retrieving from db
    public Membership(LocalDate startDate, LocalDate endDate, String memberEmail, MembershipType type, int membershipId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberEmail = memberEmail;
        this.type = type;
        this.membershipId = membershipId;
    }
    // initial constructor. (we don't manually assign an membershipId, db does this for us.)
    public Membership(LocalDate startDate, LocalDate endDate, String memberEmail, MembershipType type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberEmail = memberEmail;
        this.type = type;
    }

    @Override
    public final boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof Membership)) return false;
        Membership membership = (Membership) obj;
        return (membershipId == membership.getMembershipId() ||
                    (this.getMemberEmail().equals(membership.getMemberEmail()) &&
                     this.getStartDate().equals(membership.getStartDate()) &&
                     this.getEndDate().equals(membership.getEndDate()) &&
                     this.getType().equals(membership.getType()))
                );
    }

    @Override
    public String toString() {
        return "{" +
                "membership_id = " + this.membershipId +
                ", start_date: " + this.getStartDate() +
                ", end_date: " + this.getEndDate() +
                ", member_email: " + this.getMemberEmail() +
                ", type: " + this.getType() +
                "}";
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }
}
