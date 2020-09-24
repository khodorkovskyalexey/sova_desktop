package forms;

import database_classes.Person;
import other_classes.PersonStatus;
import other_classes.User;
import server_connector.ServerConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class AuthorizationWindow extends JFrame {
    private JPanel panel;
    private JButton enterBtn;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton registerStudBtn;
    private JButton registerTeachBtn;

    private void initListeners() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                enterBtn.requestFocus();
            }
        });

        enterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person person =
                        ServerConnector.getPersonByLoginAndPassword(loginField.getText(), passwordField.getText());
                if(person != null) {
                    PersonStatus status = null;
                    try {
                        status = ServerConnector.getPersonStatus(person.getId());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    if(status != null) {
                        setVisible(false);
                        System.out.println(person);
                        User user = new User(person, status);
                        new MenuWindow(user);
                    } else {
                        System.out.println("user does not exist in student or teacher or admin databases tables");
                    }
                } else {
                    System.out.println("user does not exist");
                }
            }
        });

        registerStudBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                new RegisterWindow(true);
            }
        });

        registerTeachBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                new RegisterWindow(false);
            }
        });

        loginField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loginField.setText("");
            }
        });

        loginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(loginField.getText() == "") {
                    loginField.setText("login");
                }
            }
        });

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passwordField.setText("");
            }
        });

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(passwordField.getText() == "") {
                    passwordField.setText("password");
                }
            }
        });
    }

    public AuthorizationWindow() {
        super("SOVA");
        this.getContentPane().add(panel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(390,302));
        setResizable(false);
        setVisible(true);
        initListeners();

    }
}
