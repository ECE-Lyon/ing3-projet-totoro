import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {
    JButton submit,submitCreationCompte, newAccount, freeConnection;
    JButton bookMovie[] = new JButton[3];
    JLabel inscription, lNewLogin, lNewPassword;
    MemberCustomers MC, memberCustomersCheck;
    JTextField login, newLogin;
    JPasswordField password, newPassword;
    Container contentPane;
    JPanel panelPrincipal, panelMember, panelCreationCompte, panelCreationCompteInscription,
            panelCreationCompteFormulaire, panelGuest, panelMainMenu;
    JPanel panelMovie[] = new JPanel[3];

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
        freeConnection = new JButton("Réserver sans connexion");
        panelGuest.add(freeConnection);

        //Création panel membre pour s'identifier
        panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(5,2));
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
        //Panel pour le film 1
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

        //Panel film 2
        panelMovie[1] = new JPanel();
        ImageIcon imageIcon2 = new ImageIcon(movie2.getUrl());
        panelMovie[1].add(new JLabel(imageIcon2));
        panelMovie[1].add(new JLabel(movie2.getTitle()));
        panelMovie[1].add(new JLabel(movie2.getGenre()));
        panelMovie[1].add(new JLabel(movie2.getDate()));
        panelMovie[1].add(new JLabel(movie2.getTime() + " minutes"));
        bookMovie[1] = new JButton("Voir ce film");
        panelMovie[1].add(bookMovie[1]);

        //Panel film 3
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

        //ajout au contentPane
        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelGuest);
        panelPrincipal.add(panelMember);


        //ouvre le panel du menu pour réserver les films
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

        //Ouvre le panel pour s'inscrire
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


        //ActionListener vérifiant l'identité de l'utilisateur
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
                        panelPrincipal.add(new JLabel("Erreur de login ou de mot de passe, réessayer"));
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
