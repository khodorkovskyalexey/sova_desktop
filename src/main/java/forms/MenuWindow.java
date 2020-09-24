package forms;

import database_classes.Test;
import forms.create_test.CreateTest;
import other_classes.JTestButton;
import other_classes.PersonStatus;
import other_classes.User;
import server_connector.ServerConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

//менюшка и для преподователя и для студента
public class MenuWindow extends JFrame {
    private JPanel panel;
    private JLabel userInfoLabel;
    private JButton optionsBtn;
    private JScrollPane testScrollPane;
    private JPanel testPanel;
    private JButton newTestBtn;
    private JButton teacherBtn;
    private JTestButton bTest[];

    private User user;

    private void sayHello(String user_name) {
        userInfoLabel.setText("Hello, " + user_name);
    }

    private void writeTest(Test[] tests){
        if(tests == null) return;
        bTest=new JTestButton[tests.length];
        testPanel.setLayout(new BoxLayout(testPanel,BoxLayout.Y_AXIS));
        int k=0;
        while(k<tests.length){
            bTest[k]=new JTestButton(tests[k].getId());
            bTest[k].setText(tests[k].getName());
            addBtnListener(bTest[k]);
            testPanel.add(bTest[k]);
            k++;
        }
        panel.revalidate();
    }

    private void addBtnListener(JTestButton btn) {
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setVisible(false);
                if(user.getStatus() == PersonStatus.Teacher) {
                    new TeacherResult(btn.getTest_id(), user);
                } else if(user.getStatus() == PersonStatus.Student) {
                    new TestStudent(user, btn.getTest_id());
                }
            }
        });
    }

    private void initListener() {
        newTestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CreateTest(user);
            }
        });
    }

    public MenuWindow(User user){
        super("SOVA");
        this.user = user;
        this.getContentPane().add(panel);
        pack();
        setSize(new Dimension(871,573));
        Test[] tests = null;
        if(user.getStatus() == PersonStatus.Student) {
            newTestBtn.setVisible(false);
            try {
                tests = ServerConnector.getAllTests();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Integer id = ServerConnector.getTeacherId(user.getId());
                tests = ServerConnector.getTestsForTeacher(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeTest(tests);
        initListener();
        sayHello(user.getName());
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
