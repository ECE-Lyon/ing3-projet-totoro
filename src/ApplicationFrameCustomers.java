import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {
    JButton submit, newAccount;
    MemberCustomers MC;
    JTextField login, nouvLogin;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelMember, creationCompte, panelGuest;

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


        setSize(600,400);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newAccount) {
            panelMember.setVisible(false);
            panelGuest.setVisible(false);
            creationCompte.setVisible(true);
        }

    }

    public static void main(String[] args) {
        ApplicationFrameCustomers applicationFrameCustomers = new ApplicationFrameCustomers();
    }
}


