package com.andrew.membership;


import com.andrew.member.Member;

import java.time.LocalDate;

public class Membership {
    private LocalDate startDate;
    private LocalDate endDate;
    private String memberEmail;
    private MembershipType type;

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
        return (memberEmail.equals(membership.getMemberEmail()) &&
                type.equals(membership.getType()) &&
                startDate.equals(membership.getStartDate()) &&
                endDate.equals(membership.getEndDate()));
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
}
