import java.sql.SQLException;

public interface MovieDao {
    void add(Movie movie) throws SQLException;
    Movie get(Integer id) throws SQLException;
    void delete(Integer id) throws SQLException;
}
