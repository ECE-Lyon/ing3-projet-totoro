import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {

    //Déclaration des variables
    JButton submit, submitCreationCompte, newAccount, freeConnection;
    JButton bookMovie[] = new JButton[3];
    JLabel inscription, labelNewLogin, labelNewPassword;
    MemberCustomers MC, memberCustomersCheck;
    Movie movie1, movie2, movie3;
    MovieDao movieDao;
    JTextField login, newLogin;
    JPasswordField password, newPassword;
    Container contentPane;
    JPanel panelPrincipal, panelMember, panelCreationCompte, panelMenuInscription, panelLabelInscription,
            panelGuest, panelMainMenu, panelButtonCreateAccount;
    JPanel panelMovie[] = new JPanel[3];


    public ApplicationFrameCustomers() {

        //Connection avec la base de données des films
        movie1 = new Movie();
        movie2 = new Movie();
        movie3 = new Movie();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
            movieDao = new MovieDaoImpl(connection);
            movie1 = movieDao.get(1);
            movie2 = movieDao.get(2);
            movie3 = movieDao.get(3);
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
        login = new JTextField();
        panelMember.add(login);

        panelMember.add(new JLabel("Mot de passe : "));
        password = new JPasswordField();
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

        panelCreationCompte.add(new JLabel(""));
        submitCreationCompte = new JButton("Création du compte");
        panelCreationCompte.add(submitCreationCompte);

        panelMenuInscription.add(panelCreationCompte);



        //Création du menu principal après connexion
        panelMainMenu = new JPanel();
        panelMainMenu.setLayout(new GridLayout(1,3));

        //Sous-panel pour le film 1
        panelMovie[0] = new JPanel();
        ImageIcon imageIcon1 = new ImageIcon(movie1.getUrl());
        JLabel jLabel = new JLabel(imageIcon1);
        panelMovie[0].add(jLabel);
        panelMovie[0].add(new JLabel(movie1.getTitle()));
        panelMovie[0].add(new JLabel(movie1.getGenre()));
        panelMovie[0].add(new JLabel(movie1.getDate()));
        panelMovie[0].add(new JLabel(movie1.getTime() + " minutes"));
        bookMovie[0] = new JButton("Voir ce film");
        panelMovie[0].add(bookMovie[0]);

        //Sous-panel pour le film 2
        panelMovie[1] = new JPanel();
        ImageIcon imageIcon2 = new ImageIcon(movie2.getUrl());
        panelMovie[1].add(new JLabel(imageIcon2));
        panelMovie[1].add(new JLabel(movie2.getTitle()));
        panelMovie[1].add(new JLabel(movie2.getGenre()));
        panelMovie[1].add(new JLabel(movie2.getDate()));
        panelMovie[1].add(new JLabel(movie2.getTime() + " minutes"));
        bookMovie[1] = new JButton("Voir ce film");
        panelMovie[1].add(bookMovie[1]);

        //Sous-panel pour le film 3
        panelMovie[2] = new JPanel();
        ImageIcon imageIcon3 = new ImageIcon(movie3.getUrl());
        panelMovie[2].add(new JLabel(imageIcon3));
        panelMovie[2].add(new JLabel(movie3.getTitle()));
        panelMovie[2].add(new JLabel(movie3.getGenre()));
        panelMovie[2].add(new JLabel(movie3.getDate()));
        panelMovie[2].add(new JLabel(movie3.getTime() + " minutes"));
        bookMovie[2] = new JButton("Voir ce film");
        panelMovie[2].add(bookMovie[2]);

        panelMainMenu.add(panelMovie[0]);
        panelMainMenu.add(panelMovie[1]);
        panelMainMenu.add(panelMovie[2]);



        //ActionListener pour ouvrir le menu pour réserver les films (panelMainMenu)
        freeConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == freeConnection){
                    panelPrincipal.setVisible(false);
                    contentPane.add(panelMainMenu);
                    panelMainMenu.setVisible(true);
                }
            }
        });

        //ActionListener pour ouvrir le menu pour s'inscrire (panelMenuInscription)
        newAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == newAccount) {
                    panelPrincipal.setVisible(false);
                    contentPane.add(panelMenuInscription);
                }
            }
        });

        //ActionListener pour créer un compte
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
                    panelPrincipal.add(new JLabel("Votre compte a bien été créé, vous pouvez vous connecter"));
                    panelPrincipal.setVisible(true);
                }
            }
        });

        //ActionListener pour vérifier l'identité de l'utilisateur
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submit){
                    try (Connection connection = DriverManager.getConnection("jdbc:h2:./default")){
                        memberCustomersCheck = new MemberCustomers();
                        UserDao userDao = new UserDaoImpl(connection);
                        memberCustomersCheck = userDao.get(login.getText(), password.getText());

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    if(memberCustomersCheck == null) {
                        login.setText("");
                        password.setText("");
                        panelPrincipal.add(new JLabel("Erreur d'identifiant et/ou de mot de passe, veuillez réessayer"));
                        panelPrincipal.setVisible(false);
                        panelPrincipal.setVisible(true);
                    }

                    else{
                        panelPrincipal.setVisible(false);
                        contentPane.add(panelMainMenu);
                    }

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
