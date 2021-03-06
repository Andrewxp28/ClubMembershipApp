package com.andrew.member;

import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * A concrete implementation of MemberDAO. Handles all database queries and operations with a SQLDB database.
 */
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
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateOneMember(String memberEmail, Member newMemberDetails) {

        String stmt = "UPDATE members SET" +
                " email = \"" + newMemberDetails.getEmail() + "\"," +
                " first_name = \"" + newMemberDetails.getFirstName() + "\"," +
                " last_name = \"" + newMemberDetails.getLastName() + "\"," +
                " phone = \"" + newMemberDetails.getPhone() + "\" " +
                "WHERE email = \"" + memberEmail + "\";";

        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insertOneMember(Member member) {

        // building sql statement;
        StringBuilder stmt = new StringBuilder(
                "INSERT INTO members" +
                "(email,first_name,last_name,phone) " +
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
        } catch (SQLIntegrityConstraintViolationException throwables) {
            System.out.println("A member with this email already exists.");
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
