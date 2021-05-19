import java.sql.*;


public class UserDaoImpl implements UserDao{
    private final Connection connection;
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(MemberCustomers memberCustomers) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateur(login, hash, categorieAge) VALUES (?,?,?)")) {
            preparedStatement.setString(1, memberCustomers.getLogin());
            preparedStatement.setString(2, memberCustomers.getHash());
            preparedStatement.setString(3, memberCustomers.getCategorieAge());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public MemberCustomers get(String log, String psw) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM utilisateur WHERE login=? AND hash=?")) {
            preparedStatement.setString(1, log);
            preparedStatement.setString(2, psw);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    MemberCustomers memberCustomers = new MemberCustomers();

                    memberCustomers.setLogin(resultSet.getString("login"));
                    memberCustomers.setHash(resultSet.getString("hash"));
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
