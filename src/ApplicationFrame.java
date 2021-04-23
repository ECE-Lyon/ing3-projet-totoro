import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static java.awt.Component.LEFT_ALIGNMENT;

public class ApplicationFrame extends JFrame implements ActionListener{
    JButton submit;
    MemberCustomers MC;
    JTextField login;
    JPasswordField password;
    public ApplicationFrame() {
        setTitle("Reservation cinéma");
        Container contentPane = getContentPane();
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());
        JPanel panelGuest = new JPanel();
        panelGuest.setLayout(new GridLayout());
        JPanel panelMember = new JPanel();
        panelMember.setLayout(new GridLayout(4,2));

        panelGuest.add(new JButton("Réserve sans te connecter"));

        panelMember.add(new JLabel("Espace Membre"));
        panelMember.add(new JButton("Creer un nouveau compte"));
        panelMember.add(new JLabel("Login : "));
        login = new JTextField();
        panelMember.add(login);
        panelMember.add(new JLabel("Password : "));
        password = new JPasswordField();
        panelMember.add(password);
        panelMember.add(new JLabel(""));
        submit = new JButton("submit");
        submit.addActionListener(this);
        panelMember.add(submit);

        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelGuest);
        panelPrincipal.add(panelMember);


        setSize(400,400);
        setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit) {
            MC = new MemberCustomers(login.getText(), "j", MemberCustomers.enumCategorieAge.REGULAR);
        }
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
    }


    }


