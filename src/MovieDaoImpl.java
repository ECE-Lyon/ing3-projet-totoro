import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDaoImpl implements MovieDao{
    private final Connection connection;
    public MovieDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Movie movie) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO film(id, url, titre, genre, dateSortie, duree) VALUES (?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.setString(2, movie.getUrl());
            preparedStatement.setString(3, movie.getTitle());
            preparedStatement.setString(4, "testgenre");
            preparedStatement.setString(5, movie.getDate());
            preparedStatement.setInt(6, movie.getTime());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Movie get(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM film WHERE id=?")) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Movie movie = new Movie();

                    movie.setId(resultSet.getInt("id"));
                    movie.setUrl(resultSet.getString("url"));
                    movie.setTitle(resultSet.getString("titre"));
                    movie.setGenre(resultSet.getString("genre"));
                    movie.setDate(resultSet.getString("datesortie"));
                    movie.setTime(resultSet.getInt("duree"));

                    return movie;
                }
            }
        }
        return null;
    }
}
