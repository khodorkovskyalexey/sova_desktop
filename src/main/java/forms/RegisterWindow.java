package forms;

import server_connector.ServerConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;

public class RegisterWindow extends JFrame{

    private JPanel panel;
    private JTextField studentFioField;
    private JTextField groupField;
    private JTextField studentLoginField;
    private JPasswordField StudentPasswordField;
    private JButton studentRegisterBtn;
    private JButton studentAuthBtn;
    private JPanel studentPanel;
    private JTextField teacherFioField;
    private JTextField teacherLoginField;
    private JPasswordField teacherPasswordField;
    private JButton teacherRegisterBtn;
    private JButton teacherAuthBtn;
    private JPanel teacherPanel;

    private boolean studentFieldsIsNotEmpty() {
        if(studentFioField.getText().isEmpty()) {
            return false;
        }
        /*if(groupField.getText().isEmpty()) {
            return false;
        }*/
        if(studentLoginField.getText().isEmpty()) {
            return false;
        }
        if(StudentPasswordField.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean teacherFieldsIsNotEmpty() {
        if(teacherFioField.getText().isEmpty()) {
            return false;
        }
        if(teacherLoginField.getText().isEmpty()) {
            return false;
        }
        if(teacherPasswordField.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private void initListeners() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
            super.windowOpened(e);
            studentRegisterBtn.requestFocus();
            }
        });

        studentRegisterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(!studentFieldsIsNotEmpty()) {
                System.out.println("fill in the required fields");
                return;
            }
            System.out.print("Add new person... ");
                if(ServerConnector.addStudent(studentFioField.getText(), studentLoginField.getText(),
                        StudentPasswordField.getText(), groupField.getText()) == HttpURLConnection.HTTP_OK) {
                    setVisible(false);
                    //new AuthorizationWindow();
                }
            }
        });

        teacherRegisterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!teacherFieldsIsNotEmpty()) {
                    System.out.println("fill in the required fields");
                    return;
                }
                System.out.println("Add new teacher... ");
                if(ServerConnector.addTeacher(teacherFioField.getText(), teacherLoginField.getText(),
                        teacherPasswordField.getText()) == HttpURLConnection.HTTP_OK) {
                    setVisible(false);
                    //new AuthorizationWindow();
                }
            }
        });

        studentAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //new AuthorizationWindow();
            }
        });

        teacherAuthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                //new AuthorizationWindow();
            }
        });

        studentFioField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            studentFioField.setText("");
            }
        });

        studentFioField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(studentFioField.getText().isEmpty()) {
                    studentFioField.setText("ФИО");
                }
            }
        });

        groupField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                groupField.setText("");
            }
        });

        groupField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(groupField.getText().isEmpty()) {
                    groupField.setText("Группа");
                }
            }
        });

        studentLoginField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                studentLoginField.setText("");
            }
        });

        studentLoginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(studentLoginField.getText().isEmpty()) {
                    studentLoginField.setText("login");
                }
            }
        });

        StudentPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StudentPasswordField.setText("");
            }
        });

        StudentPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(StudentPasswordField.getText().isEmpty()) {
                    StudentPasswordField.setText("password");
                }
            }
        });

        teacherFioField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                studentFioField.setText("");
            }
        });

        teacherFioField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(studentFioField.getText().isEmpty()) {
                    studentFioField.setText("ФИО");
                }
            }
        });

        teacherLoginField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                studentLoginField.setText("");
            }
        });

        teacherLoginField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(studentLoginField.getText().isEmpty()) {
                    studentLoginField.setText("login");
                }
            }
        });

        teacherPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                StudentPasswordField.setText("");
            }
        });

        teacherPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(StudentPasswordField.getText().isEmpty()) {
                    StudentPasswordField.setText("password");
                }
            }
        });
    }

    public RegisterWindow(boolean isItStudent) {
        super("SOVA");
        if(isItStudent) {
            studentPanel.setVisible(true);
            teacherPanel.setVisible(false);
        } else {
            studentPanel.setVisible(false);
            teacherPanel.setVisible(true);
        }
        this.getContentPane().add(panel);
        pack();
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(343,352));
        setVisible(true);
        setResizable(false);
        initListeners();

    }
}
