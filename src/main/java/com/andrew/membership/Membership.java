package com.andrew.membership;


import com.andrew.member.Member;

import java.time.LocalDate;

public class Membership {
    private LocalDate startDate;
    private LocalDate endDate;
    private Member member;
    private MembershipType type;

    public Membership(LocalDate startDate, LocalDate endDate, Member member, MembershipType type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.type = type;
    }
}
