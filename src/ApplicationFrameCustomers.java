import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {
    JButton submit, newAccount, movie1, movie2, movie3;
    MemberCustomers MC;
    JTextField login, nouvLogin;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelMember, creationCompte, panelGuest, panelMainMenu, panelMovie1, panelMovie2, panelMovie3;

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

        //ajout des composants
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
        creationCompte = new JPanel();
        creationCompte.setLayout(new GridLayout(2,1));
        JLabel inscription = new JLabel("Inscription");
        nouvLogin = new JTextField();
        creationCompte.add(inscription, nouvLogin);
        creationCompte.setVisible(false);


        //ajout au contentPane
        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelGuest);
        panelPrincipal.add(panelMember);
        panelPrincipal.add(creationCompte);


        //ouvre le panel pour s'inscrire
        newAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == newAccount) {
                    panelPrincipal.setVisible(false);
                    contentPane.add(creationCompte);
                    creationCompte.setVisible(true);
                }
            }
        });


        //Création menu principal après connection
        panelMainMenu = new JPanel();
        panelMainMenu.setLayout(new GridLayout(1,3));

        panelMovie1 = new JPanel();
        panelMovie1.setLayout(new FlowLayout());
        panelMovie1.add(new JLabel("photo film 1"));
        panelMovie1.add(new JLabel("titre film 1"));
        panelMovie1.add(new JLabel("genre film 1"));
        panelMovie1.add(new JLabel("date de sortie film 1"));
        panelMovie1.add(new JLabel("durée en minutes film 1"));
        movie1 = new JButton("Voir ce film");
        panelMovie1.add(movie1);

        panelMovie2 = new JPanel();
        panelMovie2.setLayout(new FlowLayout());
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
