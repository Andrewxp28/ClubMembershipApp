import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.Member;
import com.andrew.member.MemberDAO;
import com.andrew.member.MemberDAOImpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class MemberTest {
    private static SQLDB db;
    private static MemberDAO dao;

    @BeforeAll
    public static void initDB() {
        db = SqliteSQLDBImpl.getInstance();
        /*
        db.setPath("ClubDb5.db");
        db.getConnection();
         */
        db.initialiseDb("ClubDb5.db");
        dao = new MemberDAOImpl();
    }

    @AfterAll
    public static void closeConnection() {
        db.closeConnection();
    }

    @Test
    @Order(1)
    public void deleteAllMembersTest() {

        dao.deleteAllMembers();
        List<Member> rsMemberList = dao.findAllMembers();
        Assertions.assertNull(rsMemberList);
        System.out.println("Deleted all members successfully");
    }

    @Test
    @Order(2)
    public void findOneMemberTest() {
        Member member = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        dao.insertOneMember(member);
        Member rsMember = dao.findOneMember(member.getEmail());
        Assertions.assertEquals(member, rsMember);
        System.out.println("Inserted and found a member successfully");
    }

    @Test
    @Order(3)
    public void memberNotFoundTest() {
        String email = "1234567890";
        Member rsMember = dao.findOneMember(email);
        Assertions.assertNull(rsMember);
        System.out.println("Member not found successfully");
    }

    /*
     */
    @Test
    @Order(4)
    public void deleteOneMemberTest() {
        // insert member
        Member member1 = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        dao.insertOneMember(member1);
        // delete member
        dao.deleteOneMember(member1.getEmail());
        // test to see if it is no longer in db
        Member resultMember = dao.findOneMember(member1.getEmail());
        Assertions.assertNull(resultMember);
        // test finished successfully
        System.out.println("Deleted one member successfully");
    }

    @Test
    @Order(5)
    public void findAllMembersTest() {
        // make member objects
        Member member1 = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        Member member2 = new Member("andy.fam@gmail.com", "Andy", "fam", "1234567890");
        Member member3 = new Member("Em.chen@gmail.com", "Em", "Chen", "12934857492");
        List<Member> memberList = new ArrayList<>();
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);
        memberList.sort(new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                return o1.getEmail().compareTo(o2.getEmail());
            }
        });
        // insert into database
        dao.insertOneMember(member1);
        dao.insertOneMember(member2);
        dao.insertOneMember(member3);
        // search for all members
        List<Member> rsMemberList = dao.findAllMembers();
        rsMemberList.sort(new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                return o1.getEmail().compareTo(o2.getEmail());
            }
        });
        Assertions.assertEquals(memberList, rsMemberList);
        System.out.println("All members found successfully");
    }

}
