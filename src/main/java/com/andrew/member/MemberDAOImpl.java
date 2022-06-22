package com.andrew.member;

import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    private final SQLDB db;

    public MemberDAOImpl() {
        db = SqliteSQLDBImpl.getInstance();
    }

    @Override
    public Member findOneMember(String email) {
        StringBuilder stmt = new StringBuilder("SELECT * FROM members WHERE ");
        stmt.append("email = \"").append(email).append("\";");
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt.toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // extracting data from the result set
                String rsEmail = rs.getString(1);
                String rsFirstName = rs.getString(2);
                String rsLastName = rs.getString(3);
                String rsPhone = rs.getString(4);
                // making a member and returning found member.
                return new Member(rsEmail, rsFirstName, rsLastName, rsPhone);
            } else {
                return null;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Member> findAllMembers() {

        StringBuilder stmt = new StringBuilder("SELECT * FROM members;");
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt.toString());
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Member> members = new ArrayList<>();
            while (rs.next()) {
                // extracting data from the result set
                String rsEmail = rs.getString(1);
                String rsFirstName = rs.getString(2);
                String rsLastName = rs.getString(3);
                String rsPhone = rs.getString(4);
                // making a member and returning found member.
                members.add(new Member(rsEmail, rsFirstName, rsLastName, rsPhone));
            }
            return members.size() == 0? null: members;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteOneMember(String email) {
        StringBuilder stmt = new StringBuilder("DELETE FROM members WHERE ");
        stmt.append("email = \"").append(email).append("\";");
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt.toString());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAllMembers() {
        String stmt = "DELETE FROM members;";
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateOneMember(String memberEmail, String fieldToUpdate, String newValue) {
        return false;
    }

    @Override
    public boolean insertOneMember(Member member) {
        // I think this suppose to be in the Service Layer.
        // need to check if the email is unique
        if (findOneMember(member.getEmail()) != null) {
            // there is already an existing member with that email in the system.
            /*
            System.out.println("There is already an existing member with the email: " +
                    member.getEmail());

             */
            return false;
        }


        // building sql statement;
        StringBuilder stmt = new StringBuilder(
                "INSERT INTO members" +
                "(email,firstName,lastName,phone) " +
                "VALUES(?,?,?,?);");
        try {
            // making formatted sql statement and filling in values.
            PreparedStatement pstmt = db.getConnection().prepareStatement(String.valueOf(stmt));
            pstmt.setString(1, member.getEmail());
            pstmt.setString(2, member.getFirstName());
            pstmt.setString(3, member.getLastName());
            pstmt.setString(4, member.getPhone());
            // execute statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
