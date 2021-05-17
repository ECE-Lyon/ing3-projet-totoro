import java.sql.SQLException;

public interface UserDao {
    void add(MemberCustomers memberCustomers) throws SQLException;
    MemberCustomers get(String log, String psw) throws SQLException;



}
