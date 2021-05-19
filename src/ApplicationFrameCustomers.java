import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame{

    //Déclaration des variables
    private final JButton submit, submitCreationCompte, newAccount, freeConnection;
    JButton[] bookMovie;
    JButton[] confirmBookMovie;
    final JLabel inscription, labelNewLogin, labelNewPassword;
    private MemberCustomers MC, memberCustomersCheck;
    MovieDao movieDao;
    private final JTextField login, newLogin;
    private final JPasswordField password, newPassword;
    private final Container contentPane;
    final JPanel panelPrincipal, panelMember, panelCreationCompte, panelMenuInscription, panelLabelInscription,
            panelGuest, panelBooking, panelButtonCreateAccount;
    JPanel[] panelMovie;
    private final JComboBox boxCategorieAge;

    Movie[] movie;


    public ApplicationFrameCustomers() {

        /*Connexion à la bdd pour récupérer le nombre de film
        Puis initialisation du nombre d'objet de façon dynamique
        afin de pouvoir afficher le nombre de films que l'on veut
        */
        int nombreFilm = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        nombreFilm++;

                    }
                }
            }
            movie = new Movie[nombreFilm];
            panelMovie = new JPanel[nombreFilm];
            bookMovie = new  JButton[nombreFilm];
            confirmBookMovie = new  JButton[nombreFilm];
            //Maintenant on connait le nombre de films
            //Ici on va mettre les films qui sont dans la base de données dans des objets Movie
            movieDao = new MovieDaoImpl(connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM film")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    Integer i = 0;
                    while (resultSet.next()) {
                        movie[i] = new Movie();
                        movie[i] = movieDao.get(resultSet.getInt("id"));
                        i++;

                    }
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        //Création du top-level container
        contentPane = getContentPane();
        setTitle("Réservation cinéma");
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Création d'un panel principal regroupant les sous-panels
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        //Création d'un sous-panel pour le bouton "Réserver sans connexion"
        panelGuest = new JPanel();
        freeConnection = new JButton("Réserver sans connexion");
        panelGuest.add(freeConnection);
        panelPrincipal.add(panelGuest);

        //Création d'un sous-panel pour le bouton "Créer un nouveau compte"
        panelButtonCreateAccount = new JPanel();
        newAccount = new JButton("Créer un nouveau compte");
        panelButtonCreateAccount.add(newAccount);
        panelPrincipal.add(panelButtonCreateAccount);

        //Création d'un sous-panel pour s'identifier
        panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(4,2));
        panelMember.add(new JLabel("Espace Membre"));
        panelMember.add(new JLabel(""));

        panelMember.add(new JLabel("Identifiant : "));
        login = new JTextField(5);
        panelMember.add(login);

        panelMember.add(new JLabel("Mot de passe : "));
        password = new JPasswordField(5);
        panelMember.add(password);

        panelMember.add(new JLabel(""));
        submit = new JButton("Soumettre");
        panelMember.add(submit);

        panelPrincipal.add(panelMember);


        //Ajout du panelPrincipal au contentPane
        getContentPane().add(panelPrincipal);



        //Création du menu pour l'inscription (panel regroupant 2 sous-panels)
        panelMenuInscription = new JPanel();
        panelMenuInscription.setLayout(new FlowLayout());

        //Création d'un sous-panel pour le label "Inscription"
        panelLabelInscription = new JPanel();
        inscription = new JLabel("Inscription");
        inscription.setFont(new Font("Calibri", Font.BOLD, 60));
        panelLabelInscription.add(inscription);
        panelMenuInscription.add(panelLabelInscription);

        //Création d'un sous-panel pou le formulaire d'inscription
        panelCreationCompte = new JPanel();
        panelCreationCompte.setLayout(new GridLayout(4,2));

        labelNewLogin = new JLabel("Identifiant : ");
        panelCreationCompte.add(labelNewLogin);
        final String[] loginARemplir = {""};
        newLogin = new JTextField(loginARemplir[0]);
        panelCreationCompte.add(newLogin);

        labelNewPassword = new JLabel("Mot de passe : ");
        panelCreationCompte.add(labelNewPassword);
        final String[] passwordARemplir = {""};
        newPassword = new JPasswordField(passwordARemplir[0]);
        panelCreationCompte.add(newPassword);

        panelCreationCompte.add(new JLabel("Catégorie d'âge :"));
        String[] boxItems = new String[]{"NORMAL", "ENFANT (jusqu'à 12 ans)", "SENIOR (à partir de 65 ans)"};
        boxCategorieAge = new JComboBox<>(boxItems);
        panelCreationCompte.add(boxCategorieAge);

        panelCreationCompte.add(new JLabel(""));
        submitCreationCompte = new JButton("Création du compte");
        panelCreationCompte.add(submitCreationCompte);

        panelMenuInscription.add(panelCreationCompte);



        //Création du menu principal après connexion
        panelBooking = new JPanel();
        panelBooking.setLayout(new GridLayout(1,3));

        /*Création des panels pour afficher les films et ajout au menu réservation
        en fonction du nombre de films qu'il y a dans la bdd
        */
        for(int i = 0; i < nombreFilm; i++) {
            panelMovie[i] = new JPanel();
            ImageIcon imageIcon = new ImageIcon(movie[i].getUrl());
            panelMovie[i].add(new JLabel(imageIcon));
            panelMovie[i].add(new JLabel(movie[i].getTitle()));
            panelMovie[i].add(new JLabel(movie[i].getGenre()));
            panelMovie[i].add(new JLabel(movie[i].getDate()));
            panelMovie[i].add(new JLabel(movie[i].getTime() + " minutes"));
            bookMovie[i] = new JButton("Voir ce film");
            panelMovie[i].add(bookMovie[i]);
            panelBooking.add(panelMovie[i]);
        }



        //ActionListener pour ouvrir le menu pour réserver les films (panelMainMenu)
        freeConnection.addActionListener(e -> {
            if (e.getSource() == freeConnection){
                panelPrincipal.setVisible(false);
                contentPane.add(panelBooking);
                panelBooking.setVisible(true);
            }
        });

        //ActionListener pour ouvrir le menu pour s'inscrire (panelMenuInscription)
        newAccount.addActionListener(e -> {
            if(e.getSource() == newAccount) {
                panelPrincipal.setVisible(false);
                contentPane.add(panelMenuInscription);
                panelButtonCreateAccount.remove(newAccount);
            }
        });

        //ActionListener pour créer un compte
        submitCreationCompte.addActionListener(e -> {
            if(e.getSource() == submitCreationCompte) {
                MC = new MemberCustomers();
                MC.setLogin(newLogin.getText());
                MC.setHash(hashPassword(newPassword.getText()).toString());
                int itemIndex = boxCategorieAge.getSelectedIndex();
                MC.setCategorieAge(boxItems[itemIndex]);
                try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
                    UserDao userDao = new UserDaoImpl(connection);
                    userDao.add(MC);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                panelCreationCompte.setVisible(false);
                panelPrincipal.add(new JLabel("   Votre compte a bien été créé, vous pouvez vous connecter   "));
                panelLabelInscription.setVisible(false);
                panelMenuInscription.setVisible(false);
                panelPrincipal.setVisible(true);
            }
        });

        //ActionListener pour vérifier l'identité de l'utilisateur
        submit.addActionListener(e -> {
            if (e.getSource() == submit){
                try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
                    memberCustomersCheck = new MemberCustomers();
                    UserDao userDao = new UserDaoImpl(connection);
                    memberCustomersCheck = userDao.get(login.getText(), hashPassword(password.getText()).toString());
                    if(memberCustomersCheck == null) {
                        login.setText("");
                        password.setText("");
                        panelPrincipal.add(new JLabel("Erreur d'identifiant et/ou de mot de passe, veuillez réessayer"));
                        panelPrincipal.setVisible(false);
                        panelPrincipal.setVisible(true);
                    }

                    else{
                        panelPrincipal.setVisible(false);
                        contentPane.add(panelBooking);
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        /*Action listener pour réserver un film en fonction de la fidélité
        PLEIN TARIF pour ceux qui n'ont pas de compte par exemple*/
        for (int i = 0; i < nombreFilm; i++) {
            int finalI = i;
            bookMovie[i].addActionListener(e -> {
                if (e.getSource() == bookMovie[finalI]) {
                    panelMovie[finalI].removeAll();
                    panelMovie[finalI].setVisible(false);
                    panelMovie[finalI].setVisible(true);
                    if(memberCustomersCheck == null) {
                        panelMovie[finalI].add(new JLabel("Tarif : PLEIN TARIF"));
                    }
                    else{
                        panelMovie[finalI].add(new JLabel("Tarif " + memberCustomersCheck.getCategorieAge()));
                    }

                    panelMovie[finalI].add(new JLabel("Quel jour ?"));
                    String[] boxJours = new String[]{"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
                    JComboBox comboBoxJours = new JComboBox<>(boxJours);
                    panelMovie[finalI].add(comboBoxJours);

                    panelMovie[finalI].add(new JLabel("À quelle heure ?"));
                    String[] boxHeures = new String[]{"10h", "12h", "14h", "16h", "18h", "20h"};
                    JComboBox comboBoxHeures = new JComboBox<>(boxHeures);
                    panelMovie[finalI].add(comboBoxHeures);

                    confirmBookMovie[finalI] = new JButton("Confirmer réservation");
                    panelMovie[finalI].add(confirmBookMovie[finalI]);

                    confirmBookMovie[finalI].addActionListener(e1 -> {
                        if (e1.getSource() == confirmBookMovie[finalI]) {
                            panelMovie[finalI].add(new JLabel("Votre ticket est réservé."));
                            panelMovie[finalI].add(new JLabel("Le " + boxJours[comboBoxJours.getSelectedIndex()]));
                            panelMovie[finalI].add(new JLabel("à " + boxHeures[comboBoxHeures.getSelectedIndex()] + "."));
                            panelMovie[finalI].add(new JLabel("À bientôt dans nos cinémas !"));
                            panelMovie[finalI].setVisible(false);
                            panelMovie[finalI].setVisible(true);

                        }
                    });
                }
            });
        }

        setSize(660,400);
        setVisible(true);
    }


    // Hashage des mots de passe pour les stocker nulle part en clair
    public static String hashPassword (String password) {
        try{

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hashedPassword = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }



    public static void main(String[] args) {
        new ApplicationFrameCustomers();
    }
}
