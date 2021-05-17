

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserDaoImpl implements UserDao{
    private final Connection connection;
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(MemberCustomers memberCustomers) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateur(login, mdp) VALUES (?,?)")) {
            preparedStatement.setString(1, memberCustomers.getLogin());
            preparedStatement.setString(2, memberCustomers.getPassword());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
