import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.Member;
import com.andrew.member.MemberDAO;
import com.andrew.member.MemberDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class MemberTest {
    private static SQLDB db;
    private static MemberDAO dao;

    @BeforeAll
    public static void initDB() {
        db = SqliteSQLDBImpl.getInstance();
        db.setPath("ClubDb3.db");
        db.getConnection();
        dao = new MemberDAOImpl();
    }

    @AfterAll
    public static void closeConnection() {
        db.closeConnection();
    }

    @Test
    public void findOneMemberTest() {
        Member member = new Member("a.p@gmail.com", "Andrew", "Pham", "0466503107");
        dao.insertOne(member);
        Member rsMember = dao.findOne(member.getEmail());
        Assertions.assertEquals(member, rsMember);
        //dao.deleteOne(member.getEmail());
        System.out.println("Found a member successfully");
    }

    @Test
    public void memberNotFoundTest() {
        String email = "1234567890";
        Member rsMember = dao.findOne(email);
        Assertions.assertNull(rsMember);
        System.out.println("Member not found successfully");
    }
}
