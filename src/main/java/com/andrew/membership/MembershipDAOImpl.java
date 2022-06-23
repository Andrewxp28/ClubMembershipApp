package com.andrew.membership;

import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.Member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {

    private SQLDB db;

    public MembershipDAOImpl() {
        db = SqliteSQLDBImpl.getInstance();
    }

    @Override
    public Membership findOneMembership(int membershipId) {

        String stmt = "SELECT * FROM memberships WHERE membership_id = " + membershipId + ";";
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // extracting fields from result set containing a record of membership
                int rsMembershipId = rs.getInt(1);
                String rsStartDate = rs.getString(2);
                String rsEndDate = rs.getString(3);
                String rsType = rs.getString(4);
                String rsMemberEmail = rs.getString(5);

                LocalDate startDate = LocalDate.parse(rsStartDate);
                LocalDate endDate = LocalDate.parse(rsEndDate);
                MembershipType type = MembershipType.valueOf(rsType);
                // return membership
                return new Membership(startDate, endDate, rsMemberEmail, type, rsMembershipId);
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public Membership findOneMembership(Membership membership) {
        StringBuilder stmt = new StringBuilder("SELECT * FROM memberships WHERE ");
        stmt
                .append("start_date = \"").append(membership.getStartDate().toString()).append("\" ")
                .append("AND ")
                .append("end_date = \"").append(membership.getEndDate().toString()).append("\" ")
                .append("AND ")
                .append("type = \"").append(membership.getType().toString()).append("\" ")
                .append("AND ")
                .append("member_email = \"").append(membership.getMemberEmail()).append("\";");

        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt.toString());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // extracting fields from result set containing a record of membership
                int rsMembershipId = rs.getInt(1);
                String rsStartDate = rs.getString(2);
                String rsEndDate = rs.getString(3);
                String rsType = rs.getString(4);
                String rsMemberEmail = rs.getString(5);

                LocalDate startDate = LocalDate.parse(rsStartDate);
                LocalDate endDate = LocalDate.parse(rsEndDate);
                MembershipType type = MembershipType.valueOf(rsType);
                return new Membership(startDate, endDate, rsMemberEmail, type, rsMembershipId);
            } else {
                return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Membership> findAllMembershipsOfOneMember(String memberEmail) {

        List<Membership> rsMemberships = new ArrayList<>();
        String stmt = "SELECT * FROM memberships WHERE member_email = \"" + memberEmail + "\";";
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // getting fields from the result set which contains a record of memberships.
                int rsMembershipId = rs.getInt(1);
                String rsStartDate = rs.getString(2);
                String rsEndDate = rs.getString(3);
                String rsType = rs.getString(4);
                String rsMemberEmail = rs.getString(5);

                LocalDate startDate = LocalDate.parse(rsStartDate);
                LocalDate endDate = LocalDate.parse(rsEndDate);
                MembershipType type = MembershipType.valueOf(rsType);
                // making membership object to return
                rsMemberships.add(new Membership(startDate, endDate, rsMemberEmail, type, rsMembershipId));
            }
            // return list of memberships
            return rsMemberships.size() == 0? null: rsMemberships;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsMemberships;
    }


    @Override
    public List<Membership> findAllMemberships() {
        List<Membership> rsMemberships = new ArrayList<>();
        String stmt = "SELECT * FROM memberships;";
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                // getting fields from the result set which contains a record of memberships.
                int rsMembershipId = rs.getInt(1);
                String rsStartDate = rs.getString(2);
                String rsEndDate = rs.getString(3);
                String rsType = rs.getString(4);
                String rsMemberEmail = rs.getString(5);

                LocalDate startDate = LocalDate.parse(rsStartDate);
                LocalDate endDate = LocalDate.parse(rsEndDate);
                MembershipType type = MembershipType.valueOf(rsType);
                // making membership object to return
                rsMemberships.add(new Membership(startDate, endDate, rsMemberEmail, type, rsMembershipId));
            }
            return rsMemberships.size() == 0? null: rsMemberships;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertOneMembership(Membership membership) {
        /* Reminder service layer needs to check for duplicates
        *   Remove this check later. */
        // well check cannot be made feasibly here due to PK(membership_id) of membership table.
        // service layer will check.

        String stmt = "INSERT INTO memberships" +
                "(start_date,end_date,type,member_email) " +
                "VALUES(?,?,?,?);";
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            pstmt.setString(1, membership.getStartDate().toString());
            pstmt.setString(2, membership.getEndDate().toString());
            pstmt.setString(3, membership.getType().toString());
            pstmt.setString(4, membership.getMemberEmail());
            // execute statement
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    /*
    @Override
    public boolean updateOneMembership(Membership membership) {
        return false;
    }

     */

    @Override
    public boolean deleteOneMembership(Membership membership) {
        StringBuilder stmt = new StringBuilder("DELETE FROM memberships WHERE ");
        stmt
                .append("start_date = \"").append(membership.getStartDate().toString()).append("\" ")
                .append("AND ")
                .append("end_date = \"").append(membership.getEndDate().toString()).append("\" ")
                .append("AND ")
                .append("type = \"").append(membership.getType().toString()).append("\" ")
                .append("AND ")
                .append("member_email = \"").append(membership.getMemberEmail()).append("\";");
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt.toString());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOneMembership(int membershipId) {
        String stmt = "DELETE FROM memberships WHERE " +
                "membership_id = " + membershipId + ";";
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
    public boolean deleteAllMemberships() {
        String stmt = "DELETE FROM memberships;";
        try {
            PreparedStatement pstmt = db.getConnection().prepareStatement(stmt);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
