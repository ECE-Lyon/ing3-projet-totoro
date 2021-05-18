import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImpl implements UserDao{
    private final Connection connection;
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(MemberCustomers memberCustomers) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateur(login, mdp, categorieAge) VALUES (?,?,?)")) {
            preparedStatement.setString(1, memberCustomers.getLogin());
            preparedStatement.setString(2, memberCustomers.getPassword());
            preparedStatement.setString(3, memberCustomers.getCategorieAge());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public MemberCustomers get(String log, String psw) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE login=? AND mdp=?")) {
            preparedStatement.setString(1, log);
            preparedStatement.setString(2, psw);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    MemberCustomers memberCustomers = new MemberCustomers();

                    memberCustomers.setLogin(resultSet.getString("login"));
                    memberCustomers.setPassword(resultSet.getString("mdp"));
                    memberCustomers.setCategorieAge(resultSet.getString("categorieAge"));
                    return memberCustomers;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
