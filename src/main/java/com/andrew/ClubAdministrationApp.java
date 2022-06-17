package com.andrew;

import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.Member;
import com.andrew.member.MemberDAO;
import com.andrew.member.MemberDAOImpl;

import java.util.List;

public class ClubAdministrationApp {

    public static void main(String[] args) {
        System.out.println("Hello Andrew");

        SQLDB db = SqliteSQLDBImpl.getInstance();
        // set path first
        db.setPath("ClubDb3.db");
        db.getConnection();
        db.createTable("members", "email TEXT PRIMARY KEY", "firstName TEXT", "lastName TEXT", "phone TEXT");
        MemberDAO memberDAO = new MemberDAOImpl();
        Member member1 = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        Member member2 = new Member("andy.fam@gmail.com", "Andy", "fam", "1234567890");
        Member member3 = new Member("Em.chen@gmail.com", "Em", "Chen", "12934857492");
        /*
        memberDAO.insertOne(member1);
        memberDAO.insertOne(member2);
        memberDAO.insertOne(member3);

         */
        System.out.println("Printing list of all members:");
        List<Member> members = memberDAO.findAll();
        System.out.println(members);
        System.out.println("Printing queries: ");
        System.out.println(memberDAO.findOne(member1.getEmail()));
        System.out.println(memberDAO.findOne(member2.getEmail()));
        System.out.println(memberDAO.findOne(member3.getEmail()));

        db.closeConnection();
    }
}
