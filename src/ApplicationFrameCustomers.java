import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {
    JButton submit,submitCreationCompte, newAccount, freeConnexion, addMovie1, addMovie2, addMovie3;
    JLabel inscription, lNewLogin, lNewPassword;
    MemberCustomers MC;
    JTextField login, newLogin;
    JPasswordField password, newPassword;
    Container contentPane;
    JPanel panelPrincipal, panelMember, panelCreationCompte, panelCreationCompteInscription, panelCreationCompteFormulaire, panelGuest, panelMainMenu, panelMovie1, panelMovie2, panelMovie3;

    public ApplicationFrameCustomers() {
        //connection avec la base de données des films
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
            MovieDao movieDao = new MovieDaoImpl(connection);
            movie1 = movieDao.get(1);
            movie2 = movieDao.get(2);
            movie3 = movieDao.get(3);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Création top-level container
        contentPane = getContentPane();
        setTitle("Réservation cinéma");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        //Création panel invité
        panelGuest = new JPanel();
        panelGuest.setLayout(new GridLayout());
        freeConnexion = new JButton("Réserver sans connexion");
        panelGuest.add(freeConnexion);

        //Création panel membre
        panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(4,2));
        //Ajout des composants
        panelMember.add(new JLabel("Espace Membre"));
        newAccount = new JButton("Creer un nouveau compte");
        newAccount.addActionListener(this);
        newAccount = new JButton("Créer un nouveau compte");
        panelMember.add(newAccount);
        panelMember.add(new JLabel("Identifiant : "));
        login = new JTextField();
        panelMember.add(login);
        panelMember.add(new JLabel("Mot de passe : "));
        password = new JPasswordField();
        panelMember.add(password);
        panelMember.add(new JLabel(""));
        submit = new JButton("Soumettre");
        panelMember.add(submit);


        //Creation panel pour s'inscrire
        panelCreationCompte = new JPanel();
        panelCreationCompteInscription = new JPanel();
        panelCreationCompteFormulaire = new JPanel(new GridLayout(3,2));
        //ajout de ses composants
        inscription = new JLabel("Inscription :");
        lNewLogin = new JLabel("Veillez entrez un identifiant");
        final String[] loginARemplir = {"login"};
        final String[] passwordARemplir = {"mot de passe"};
        newLogin = new JTextField(loginARemplir[0]);
        lNewPassword = new JLabel("Veuillez entrez un mot de passe");
        newPassword = new JPasswordField(passwordARemplir[0]);
        submitCreationCompte = new JButton("Création compte");
        panelCreationCompteInscription.add(inscription);
        panelCreationCompteFormulaire.add(lNewLogin);
        panelCreationCompteFormulaire.add(newLogin);
        panelCreationCompteFormulaire.add(lNewPassword);
        panelCreationCompteFormulaire.add(newPassword);
        panelCreationCompteFormulaire.add(submitCreationCompte);
        panelCreationCompte.add(panelCreationCompteInscription);
        panelCreationCompte.add(panelCreationCompteFormulaire);

        //Création menu principal après connection
        panelMainMenu = new JPanel();
        panelMainMenu.setLayout(new GridLayout(1,3));
        panelMovie1 = new JPanel();
        ImageIcon imageIcon = new ImageIcon(movie1.getUrl());
        JLabel jLabel = new JLabel(imageIcon);
        panelMovie1.add(jLabel);
        panelMovie1.add(new JLabel(movie1.getTitle()));
        panelMovie1.add(new JLabel(movie1.getGenre()));
        panelMovie1.add(new JLabel(movie1.getDate()));
        panelMovie1.add(new JLabel(movie1.getTime() + " minutes"));
        addMovie1 = new JButton("Voir ce film");

        panelMovie1.add(addMovie1);

        panelMovie2 = new JPanel();
        panelMovie2.setLayout(new GridLayout(6,1));
        /*panelMovie2.add(new JLabel(movie2.getUrl()));
        panelMovie2.add(new JLabel(movie2.getTitle()));
        panelMovie2.add(new JLabel("genre encore à régler"));
        panelMovie2.add(new JLabel(movie2.getDate()));
        panelMovie2.add(new JLabel(movie2.getTime() + " minutes"));*/
        addMovie2 = new JButton("Voir ce film");
        panelMovie2.add(addMovie2);

        panelMovie3 = new JPanel();
        panelMovie3.setLayout(new FlowLayout());
        panelMovie3.add(new JLabel("photo film 3"));
        panelMovie3.add(new JLabel("titre film 3"));
        panelMovie3.add(new JLabel("genre film 3"));
        panelMovie3.add(new JLabel("date de sortie film 3"));
        panelMovie3.add(new JLabel("durée en minutes film 3"));
        addMovie3 = new JButton("Voir ce film");
        panelMovie3.add(addMovie3);

        panelMainMenu.add(panelMovie1);
        panelMainMenu.add(panelMovie2);
        panelMainMenu.add(panelMovie3);

        //ajout au contentPane
        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelGuest);
        panelPrincipal.add(panelMember);


        //ouvre le panel du men film
        freeConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == freeConnexion){
                    panelPrincipal.setVisible(false);
                    contentPane.add(panelMainMenu);
                    panelMainMenu.setVisible(true);
                }
            }
        });
        //ouvre le panel pour s'inscrire
        newAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == newAccount) {
                    panelPrincipal.setVisible(false);
                    contentPane.add(panelCreationCompte);

                }
            }
        });

        //listener du bouton soumettre
        submitCreationCompte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == submitCreationCompte) {
                    MC = new MemberCustomers();
                    MC.setLogin(newLogin.getText());
                    MC.setPassword(newPassword.getText());
                    try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
                        UserDao userDao = new UserDaoImpl(connection);
                        userDao.add(MC);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    panelCreationCompte.setVisible(false);
                    panelPrincipal.add(new JLabel("Votre compte a bien été créé vous pouvez vous connecter"));
                    panelPrincipal.setVisible(true);
                }
            }
        });


        //ActionListener : une fois connecté, ouvre le menu de l'application pour choisir son film
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submit){
                    panelPrincipal.setVisible(false);
                    contentPane.add(panelMainMenu);
                    panelMainMenu.setVisible(true);
                }
            }
        });


        setSize(600,400);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public static void main(String[] args) {
        ApplicationFrameCustomers applicationFrameCustomers = new ApplicationFrameCustomers();


    }
}
