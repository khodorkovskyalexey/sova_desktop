package forms;

import database_classes.Result;
import other_classes.User;
import server_connector.ServerConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.BoxLayout.*;

public class TeacherResult extends JFrame{
    private JPanel panel;
    private JButton BackToMenu;
    private JLabel TestName;
    private JScrollPane ScrollPanel;
    private JPanel ResultPanel;

    private User user;

    private void writeResults(Result[] results){
        if(results != null) {
            Object[] Header={"Студент","Оценка"};
            Object[][] Res = new Object[results.length][2];
            for(int i=0;i<results.length;i++){
                Res[i][0]=Integer.toString(results[i].getStudentId());
                Res[i][1]=Float.toString(results[i].getMark());
            }
            JTable ResultTable=new JTable(Res,Header);
            ResultPanel.setLayout(new BoxLayout(ResultPanel, BoxLayout.Y_AXIS));
            ResultPanel.add((ResultTable).getTableHeader(), BorderLayout.NORTH);
            ResultPanel.add(ResultTable,BorderLayout.CENTER);
            panel.revalidate();
        }
    }

    public TeacherResult(Integer test_id, User user) {
        super("SOVA");
        this.user = user;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Result[] results = null;
        try {
            results = ServerConnector.getResultsForTest(test_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Result[] results = initEXres();
        writeResults(results);
        TestName.setText("Test #1");//Достать имя теста
        setVisible(true);
        this.getContentPane().add(panel);
        pack();

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new MenuWindow(user);
            }
        });
    }
        private Result[] initEXres(){
        Result[] r=new Result[5];
        for(int i=0;i<5;++i) {
            r[i]=new Result((float)i/2,i);
        }
        return r;
    }

}

