import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {
    JButton submit,submitCreationCompte, newAccount, movie1, movie2, movie3;
    JLabel inscription, lNewLogin, lNewPassword;
    MemberCustomers MC;
    JTextField login, newLogin;
    JPasswordField password, newPassword;
    Container contentPane;
    JPanel panelPrincipal, panelMember, panelCreationCompte, panelCreationCompteInscription, panelCreationCompteFormulaire, panelGuest, panelMainMenu, panelMovie1, panelMovie2, panelMovie3;

    public ApplicationFrameCustomers() {
        //Création top-level container
        setTitle("Réservation cinéma");
        contentPane = getContentPane();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        //Création panel invité
        panelGuest = new JPanel();
        panelGuest.setLayout(new GridLayout());

        //Création panel membre
        panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(4,2));
        //Ajout des composants
        panelGuest.add(new JButton("Réserver sans connection"));
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
        final String[] loginARemplir = {"Rien"};
        final String[] passwordARemplir = {"mdp"};
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



        //ajout au contentPane
        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelGuest);
        panelPrincipal.add(panelMember);


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
                }
            }
        });


        //Création menu principal après connection
        panelMainMenu = new JPanel();
        panelMainMenu.setLayout(new GridLayout(1,3));

        panelMovie1 = new JPanel();
        panelMovie1.setLayout(new GridLayout(6,1));
        panelMovie1.add(new JLabel("photo film 1"));
        panelMovie1.add(new JLabel("titre film 1"));
        panelMovie1.add(new JLabel("genre film 1"));
        panelMovie1.add(new JLabel("date de sortie film 1"));
        panelMovie1.add(new JLabel("durée en minutes film 1"));
        movie1 = new JButton("Voir ce film");

        panelMovie1.add(movie1);

        panelMovie2 = new JPanel();
        panelMovie2.setLayout(new GridLayout(6,1));
        panelMovie2.add(new JLabel("photo film 2"));
        panelMovie2.add(new JLabel("titre film 2"));
        panelMovie2.add(new JLabel("genre film 2"));
        panelMovie2.add(new JLabel("date de sortie film 2"));
        panelMovie2.add(new JLabel("durée en minutes film 2"));
        movie2 = new JButton("Voir ce film");
        panelMovie2.add(movie2);

        panelMovie3 = new JPanel();
        panelMovie3.setLayout(new FlowLayout());
        panelMovie3.add(new JLabel("photo film 3"));
        panelMovie3.add(new JLabel("titre film 3"));
        panelMovie3.add(new JLabel("genre film 3"));
        panelMovie3.add(new JLabel("date de sortie film 3"));
        panelMovie3.add(new JLabel("durée en minutes film 3"));
        movie3 = new JButton("Voir ce film");
        panelMovie3.add(movie3);

        panelMainMenu.add(panelMovie1);
        panelMainMenu.add(panelMovie2);
        panelMainMenu.add(panelMovie3);


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
