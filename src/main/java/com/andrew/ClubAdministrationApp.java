package com.andrew;

import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.*;
import com.andrew.membership.Membership;
import com.andrew.membership.MembershipService;
import com.andrew.membership.MembershipServiceImpl;
import com.andrew.membership.MembershipType;

import java.time.LocalDate;
import java.util.List;

public class ClubAdministrationApp {

    public static void main(String[] args) {
        System.out.println("Hello Andrew");
        // setup db
        SQLDB db = SqliteSQLDBImpl.getInstance();
        db.initialiseDb("ClubDb6.db");
        // create instances of services
        MemberService memberService = new MemberServiceImpl();
        MembershipService membershipService = new MembershipServiceImpl();

        Member member1 = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        Member member2 = new Member("andy.fam@gmail.com", "Andy", "fam", "1234567890");
        Member member3 = new Member("Em.chen@gmail.com", "Em", "Chen", "12934857492");

        Membership membership1 = new Membership(
                LocalDate.parse("2021-04-20"),
                LocalDate.parse("2022-04-21"),
                "a.p@gmail.com",
                MembershipType.REGULAR
                );

        Membership membership2 = new Membership(
                LocalDate.parse("2022-05-25"),
                LocalDate.parse("2023-05-26"),
                "a.p@gmail.com",
                MembershipType.VIP
        );

        Membership membership3 = new Membership(
                LocalDate.parse("2020-03-10"),
                LocalDate.parse("2021-03-11"),
                "andy.fam@gmail.com",
                MembershipType.REGULAR
        );

        Membership membership4 = new Membership(
                LocalDate.parse("2019-09-09"),
                LocalDate.parse("2020-09-10"),
                "Em.chen@gmail.com",
                MembershipType.REGULAR
        );
        // inserting members first
        memberService.insertOneMember(member1);
        memberService.insertOneMember(member2);
        memberService.insertOneMember(member3);

        // inserting memberships
        membershipService.insertOneMembership(membership1);
        membershipService.insertOneMembership(membership2);
        membershipService.insertOneMembership(membership3);
        membershipService.insertOneMembership(membership4);


        // trying to delete a member
        //memberService.deleteOneMember(member3.getEmail());
        // printing results
        List<Member> members = memberService.findAllMembers();
        List<Membership> memberships = membershipService.findAllMemberships();

        System.out.println(members);
        System.out.println(memberships);



        /*
        MemberDAO memberDAO = new MemberDAOImpl();
        Member member1 = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        Member member2 = new Member("andy.fam@gmail.com", "Andy", "fam", "1234567890");
        Member member3 = new Member("Em.chen@gmail.com", "Em", "Chen", "12934857492");
        memberDAO.insertOneMember(member1);
        memberDAO.insertOneMember(member2);
        memberDAO.insertOneMember(member3);




        System.out.println("Printing list of all members:");
        List<Member> members = memberDAO.findAllMembers();
        System.out.println(members);
        System.out.println("Printing queries: ");
        System.out.println(memberDAO.findOneMember(member1.getEmail()));
        System.out.println(memberDAO.findOneMember(member2.getEmail()));
        System.out.println(memberDAO.findOneMember(member3.getEmail()));

         */

        db.closeConnection();
    }
}
