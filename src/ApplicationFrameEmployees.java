import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ApplicationFrameEmployees extends JFrame implements ActionListener{

    //Déclaration des variables
    Employees E;
    Movie movie;
    JButton addMovie, removeMovie, confirmAddMovie, confirmRemoveMovie;
    JTextField titleNewMovie, genreNewMovie, idNewMovie, dateNewMovie, timeNewMovie, urlNewMovie, idMovieRemove;
    Container contentPane;
    JPanel panelPrincipal, panelWelcome, panelLabelAjout, panelMenu, panelAddMovie,
            panelRemoveMovie, panelInfoMovie;
    JLabel welcome, labelAjout;


    public ApplicationFrameEmployees() {

        //Création du top-level container
        contentPane = getContentPane();
        setTitle("Espace employés");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Création d'un panel principal regroupant les sous-panels
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        //Création d'un sous-panel pour le label "Bienvenue sur l'espace employés"
        panelWelcome = new JPanel();
        welcome = new JLabel("Bienvenue sur l'espace employés");
        welcome.setFont(new Font("Calibri", Font.BOLD, 28));
        panelWelcome.add(welcome);
        panelPrincipal.add(panelWelcome);

        //Création d'un sous-panel pour les boutons d'ajout ou de suppression d'un film
        panelMenu = new JPanel();
        panelMenu.setLayout(new FlowLayout());

        addMovie = new JButton("Ajouter un film à l'affiche");
        panelMenu.add(addMovie);

        removeMovie = new JButton("Enlever un film à l'affiche");
        panelMenu.add(removeMovie);

        panelPrincipal.add(panelMenu);


        //Ajout au contentPane
        getContentPane().add(panelPrincipal);



        //Création d'un panel pour ajouter un film à l'affiche
        panelAddMovie = new JPanel();
        panelAddMovie.setLayout(new FlowLayout());

        //Création d'un sous-panel pour le label "Ajouter un film à l'affiche"
        panelLabelAjout = new JPanel();
        labelAjout = new JLabel("Ajouter un film à l'affiche");
        labelAjout.setFont(new Font("Calibri", Font.BOLD, 40));
        panelLabelAjout.add(labelAjout);
        panelAddMovie.add(panelLabelAjout);

        //Création d'un sous-panel regroupant les informations du film à remplir
        panelInfoMovie = new JPanel();
        panelInfoMovie.setLayout(new GridLayout(7,2));

        panelInfoMovie.add(new JLabel("Emplacement : "));
        idNewMovie = new JTextField(5);
        panelInfoMovie.add(idNewMovie);

        panelInfoMovie.add(new JLabel("Url de l'image : "));
        urlNewMovie = new JTextField(5);
        panelInfoMovie.add(urlNewMovie);

        panelInfoMovie.add(new JLabel("Titre : "));
        titleNewMovie = new JTextField(5);
        panelInfoMovie.add(titleNewMovie);

        panelInfoMovie.add(new JLabel("Genre : "));
        genreNewMovie = new JTextField(5);
        panelInfoMovie.add(genreNewMovie);

        panelInfoMovie.add(new JLabel("Date de sortie : "));
        dateNewMovie = new JTextField();
        panelInfoMovie.add(dateNewMovie);

        panelInfoMovie.add(new JLabel("Durée du film (en minutes) : "));
        timeNewMovie = new JTextField(5);
        panelInfoMovie.add(timeNewMovie);

        panelInfoMovie.add(new JLabel(""));
        confirmAddMovie = new JButton("Ajout du film");
        panelInfoMovie.add(confirmAddMovie);

        panelAddMovie.add(panelInfoMovie);


        //Création d'un panel pour enlever un film à l'affiche
        panelRemoveMovie = new JPanel();
        panelRemoveMovie.add(new JLabel("Quel est l'emplacement du film à remplacer ?"));
        idMovieRemove = new JTextField(5);
        panelRemoveMovie.add(idMovieRemove);
        confirmRemoveMovie = new JButton("Suppression du film");
        panelRemoveMovie.add(confirmRemoveMovie);



        //ActionListener : ouvre le panel pour ajouter un film
        addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addMovie){
                    panelMenu.setVisible(false);
                    contentPane.add(panelAddMovie);
                }
            }
        });

        //ActionListener : ouvre le panel pour enlever un film
        removeMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == removeMovie) {
                    panelMenu.setVisible(false);
                    contentPane.add(panelRemoveMovie);
                }
            }
        });


        //ActionListener pour ajouter un film à la base de données
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
                    contentPane.remove(panelAddMovie);
                    panelMenu.setVisible(true);
                }
            }
        });

        //ActionListener : supprime le film sélectionné de la base de données et ferme le panel
        confirmRemoveMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == confirmRemoveMovie) {
                    try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
                        MovieDao movieDao = new MovieDaoImpl(connection);
                        int intIdRemoveMovie = Integer.parseInt(idMovieRemove.getText());
                        movieDao.delete(intIdRemoveMovie);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    contentPane.remove(panelRemoveMovie);
                    panelMenu.setVisible(true);
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
        new ApplicationFrameEmployees();
    }
}
