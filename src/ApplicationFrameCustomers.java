import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ApplicationFrameCustomers extends JFrame implements ActionListener {
    JButton submit, newAccount;
    MemberCustomers MC;
    JTextField login;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelMember, creationCompte;

    public ApplicationFrameCustomers() {
        //Création top-level container
        setTitle("Réservation cinéma");
        contentPane = getContentPane();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        //Création panel invité
        JPanel panelGuest = new JPanel();
        panelGuest.setLayout(new GridLayout());

        //Création panel membre
        panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(4,2));

        //ajout des composants
        panelGuest.add(new JButton("Réserver sans connection"));
        panelMember.add(new JLabel("Espace Membre"));
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
        submit.addActionListener(this);
        panelMember.add(submit);


        //Creation panel pour s'inscrire
        creationCompte = new JPanel();
        creationCompte.setLayout(new GridLayout(1,1));
        JLabel inscription = new JLabel("Inscription");
        inscription.add(creationCompte);
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
        if(e.getSource() == submit) {
            panelMember.setVisible(false);
            creationCompte.setVisible(true);
        }

    }

    public static void main(String[] args) {
        ApplicationFrameCustomers applicationFrameCustomers = new ApplicationFrameCustomers();
    }
}


