import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationFrameEmployees extends JFrame implements ActionListener{
    Employees E;
    Movie movie;
    JButton connect, addMovie, removeMovie, confirmAddMovie;
    JTextField login, titleNewMovie, genreNewMovie, idNewMovie, dateNewMovie, timeNewMovie, urlNewMovie;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelWelcome, panelConnection, panelMenu, panelAddMovie;
    JLabel welcome;


    public ApplicationFrameEmployees() {

        //Création du top level container de l'application
        setTitle("Espace employés");
        contentPane = getContentPane();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());


        //Création 1er panel
        panelWelcome = new JPanel();
        welcome = new JLabel("Bienvenue sur l'espace employés");
        welcome.setFont(new Font("Calibri", Font.BOLD, 30));
        panelWelcome.add(welcome);


        //Création 2nd panel
        panelConnection = new JPanel();
        panelConnection.setLayout(new GridLayout(3, 2));
        panelConnection.add(new JLabel("Identifiant : "));
        login = new JTextField();
        panelConnection.add(login);
        panelConnection.add(new JLabel("Mot de passe : "));
        password = new JPasswordField();
        panelConnection.add(password);
        panelConnection.add(new JLabel(""));
        connect = new JButton("Se connecter");
        panelConnection.add(connect);

        //ActionListener : une fois connecté, l'employé choisit s'il faut ajouter ou supprimer un film
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());
        addMovie = new JButton("Ajouter un film");
        panelMenu.add(addMovie);
        removeMovie = new JButton("Enlever un film");
        panelMenu.add(removeMovie);

        //Panel pour ajouter un film
        panelAddMovie = new JPanel();
        panelAddMovie.add(new JLabel("Ajout d'un film"));
        panelAddMovie.add(new JLabel("Quelle sera l'emplacement du film ?"));
        idNewMovie = new JTextField(5);
        panelAddMovie.add(idNewMovie);
        panelAddMovie.add(new JLabel("Url de l'image :"));
        urlNewMovie = new JTextField(5);
        panelAddMovie.add(urlNewMovie);
        panelAddMovie.add(new JLabel("Titre : "));
        titleNewMovie = new JTextField(5);
        panelAddMovie.add(titleNewMovie);
        panelAddMovie.add(new JLabel("Genre : "));
        genreNewMovie = new JTextField(5);
        panelAddMovie.add(genreNewMovie);
        panelAddMovie.add(new JLabel("Date de sortie :"));
        dateNewMovie = new JTextField(5);
        panelAddMovie.add(dateNewMovie);
        panelAddMovie.add(new JLabel("Durée du film :"));
        timeNewMovie = new JTextField(5);
        panelAddMovie.add(timeNewMovie);
        confirmAddMovie = new JButton("Ajout du film");
        panelAddMovie.add(confirmAddMovie);

        //Ajout au contentPane
        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelWelcome);
        panelPrincipal.add(panelConnection);




        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == connect){
                    panelPrincipal.setVisible(false);
                    contentPane.add(panelMenu);
                }
            }
        });




        //ActionListener : clic "Ajouter un film"
        addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addMovie){
                    panelMenu.setVisible(false);
                    contentPane.add(panelAddMovie);
                }
            }
        });
        //Listener pour ajouter un film à la base de donnée
        confirmAddMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmAddMovie){
                    movie = new Movie();
                    int intIdNewMovie = Integer.parseInt(idNewMovie.getText());
                    int intTimeNewMovie = Integer.parseInt(timeNewMovie.getText());
                    movie.setId(intIdNewMovie);
                    movie.setTitle(titleNewMovie.getText());
                    movie.setDate(dateNewMovie.getText());
                    movie.setTime(intTimeNewMovie);
                    movie.setUrl(urlNewMovie.getText());
                    movie.setGenre(genreNewMovie.getText());
                    try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
                        MovieDao movieDao = new MovieDaoImpl(connection);
                        movieDao.add(movie);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });



        setSize(600, 400);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {
        ApplicationFrameEmployees applicationFrameEmployees = new ApplicationFrameEmployees();
    }
}
