import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.Member;
import com.andrew.member.MemberDAO;
import com.andrew.member.MemberDAOImpl;
import com.andrew.membership.Membership;
import com.andrew.membership.MembershipDAO;
import com.andrew.membership.MembershipDAOImpl;
import com.andrew.membership.MembershipType;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MembershipTest {
    private static SQLDB db;
    private static MembershipDAO dao;

    @BeforeAll
    public static void initDB() {
        db = SqliteSQLDBImpl.getInstance();
        db.initialiseDb("src/main/sqlite_database/ClubDbTESTING.db");
        dao = new MembershipDAOImpl();
        dao.deleteAllMemberships();
        MemberDAO memberDao = new MemberDAOImpl();
        memberDao.deleteAllMembers();
        memberDao.insertOneMember(new Member("a.p@gmail.com", "Andrew", "Pham", "0987654321"));
    }

    @AfterAll
    public static void closeConnection() {
        db.closeConnection();
    }

    @Test
    @Order(1)
    public void insertAndFindOneMembershipTest() {
        LocalDate startDate = LocalDate.parse("2022-06-23");
        LocalDate endDate = LocalDate.parse("2023-06-24");
        Membership membership1 = new Membership(startDate,endDate, "a.p@gmail.com", MembershipType.REGULAR);
        dao.insertOneMembership(membership1);
        membership1.setMembershipId(1);
        Membership rsMembership = dao.findOneMembership(membership1);
        Assertions.assertTrue(membership1.equals(rsMembership));
        System.out.println("Insert and find one membership successfully");

    }

    @Test
    @Order(2)
    public void findAllMembershipsOfOneMemberTest() {
        LocalDate startDate = LocalDate.parse("2022-06-23");
        LocalDate endDate = LocalDate.parse("2023-06-24");
        Membership membership1 = new Membership(startDate,endDate, "a.p@gmail.com", MembershipType.REGULAR);
        LocalDate startDate2 = LocalDate.parse("2024-07-25");
        LocalDate endDate2 = LocalDate.parse("2025-07-26");
        Membership membership2 = new Membership(startDate2,endDate2, "a.p@gmail.com", MembershipType.REGULAR);
        dao.insertOneMembership(membership1);
        dao.insertOneMembership(membership2);
        membership1.setMembershipId(1);
        membership2.setMembershipId(2);
        List<Membership> rsMembershipList = dao.findAllMembershipsOfOneMember(membership1.getMemberEmail());
        List<Membership> expectedMembershipList = new ArrayList<>();
        expectedMembershipList.add(membership1);
        expectedMembershipList.add(membership2);
        expectedMembershipList.sort(new Comparator<Membership>() {
            @Override
            public int compare(Membership o1, Membership o2) {
                return o1.getMembershipId() - o2.getMembershipId();
            }
        });
        rsMembershipList.sort(new Comparator<Membership>() {
            @Override
            public int compare(Membership o1, Membership o2) {
                return o1.getMembershipId() - o2.getMembershipId();
            }
        });
        Assertions.assertTrue(expectedMembershipList.equals(rsMembershipList));
        System.out.println("Found all memberships of one member successfully");

    }
    @Test
    @Order(3)
    public void deleteOneMembershipTest() {
        // insert
        LocalDate startDate = LocalDate.parse("2020-06-23");
        LocalDate endDate = LocalDate.parse("2021-06-24");
        Membership membership1 = new Membership(startDate,endDate, "a.p@gmail.com", MembershipType.VIP);
        dao.insertOneMembership(membership1);
        // find membership_id
        Membership rsMembership = dao.findOneMembership(membership1);
        int rsMembershipId = rsMembership.getMembershipId();
        // delete
        dao.deleteOneMembership(rsMembershipId);
        // check if it is still there.
        Membership result = dao.findOneMembership(rsMembershipId);
        Assertions.assertNull(result);
        System.out.println("Deleted one membership Successfully");
    }
}
