import javax.swing.*;
import java.awt.*;

public class ApplicationFrameEmployees extends JFrame {
    Employees E;
    JButton connect;
    JTextField login;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelWelcome, panelConnection;
    JLabel welcome;

    public ApplicationFrameEmployees() {
        //Création du top level container de l'application
        setTitle("Espace employés");
        contentPane = getContentPane();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new FlowLayout());

        //Création 1er panel
        panelWelcome = new JPanel();
        welcome = new JLabel("Bienvenue sur l'espace employés !");
        welcome.setFont(new Font("Serif", Font.BOLD, 30));
        //welcome.setForeground(Color.BLACK);
        //panelWelcome.setBackground(Color.GRAY);
        panelWelcome.add(welcome);

        //Création 2nd panel
        panelConnection = new JPanel();
        panelConnection.setLayout(new GridLayout(3,2));
        panelConnection.add(new JLabel("Identifiant : "));
        login = new JTextField();
        panelConnection.add(login);
        panelConnection.add(new JLabel("Mot de passe : "));
        password = new JPasswordField();
        panelConnection.add(password);
        panelConnection.add(new JLabel(""));
        connect = new JButton("Se connecter");
        panelConnection.add(connect);


        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelWelcome);
        panelPrincipal.add(panelConnection);


        setSize(600,400);
        setVisible(true);
    }



    public static void main(String[] args) {
        ApplicationFrameEmployees applicationFrameEmployees = new ApplicationFrameEmployees();
    }

}
