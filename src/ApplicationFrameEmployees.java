import javax.swing.*;
import java.awt.*;

public class ApplicationFrameEmployees extends JFrame {
    JButton submit;
    JTextField login;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal, panelBienvenue, panelConnection;

    public ApplicationFrameEmployees() {
        //Création du panel de l'application
        setTitle("Espace employés");
        contentPane = getContentPane();
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(2,1));

        //Création 1er panel
        panelBienvenue = new JPanel();
        panelBienvenue.add(new JLabel("Bienvenue sur l'espace employés !"));

        //Création 2nd panel
        panelConnection = new JPanel();
        submit = new JButton("Soumettre");
        panelConnection.add(submit);



        getContentPane().add(panelPrincipal);
        panelPrincipal.add(panelBienvenue);
        panelPrincipal.add(panelConnection);


        setSize(600,400);
        setVisible(true);
    }



    public static void main(String[] args) {
        ApplicationFrameEmployees applicationFrameEmployees = new ApplicationFrameEmployees();
    }

}
