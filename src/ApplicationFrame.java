import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ApplicationFrame extends JFrame implements ActionListener {
    JButton submit, newAccount;
    MemberCustomers MC;
    JTextField login;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelMember;

    public ApplicationFrame() {
        setTitle("Réservation cinéma");
        contentPane = getContentPane();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());
        JPanel panelGuest = new JPanel();
        panelGuest.setLayout(new GridLayout());
        panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(4, 2));

        panelGuest.add(new JButton("Réserver sans connection"));

        panelMember.add(new JLabel("Espace Membre"));
        newAccount = new JButton("Creer un nouveau compte");
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

        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelGuest);
        panelPrincipal.add(panelMember);


        setSize(400, 400);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == submit) {
            submit.setText("test");
        }
        if (event.getSource() == newAccount) {
            newAccount.setText(("bleh"));
        }
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
    }

}
