import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ApplicationFrameEmployees extends JFrame {
    JButton submit;
    JTextField login;
    JPasswordField password;
    Container contentPane;
    JPanel panelPrincipal;

    public ApplicationFrameEmployees() {
        //Création du panel de l'application
        setTitle("Espace employés");
        contentPane = getContentPane();
        panelPrincipal = new JPanel();

        //Création des composants du panel
        panelPrincipal.add(new JLabel("Bienvenue sur l'espace employés"));


        panelPrincipal.add(new JButton("Soumettre"));

        getContentPane().add(panelPrincipal);

        setSize(600,400);
        setVisible(true);
    }



    public static void main(String[] args) {
        ApplicationFrameEmployees applicationFrameEmployees = new ApplicationFrameEmployees();
    }

}
