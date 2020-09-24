package forms;

import database_classes.Result;
import database_classes.Test;

import javax.swing.*;


public class StudentResults extends JFrame{
    private JPanel panel;
    private JButton MenuButton;
    private JLabel TestNameLabel;
    private JLabel Results;
    private JPanel ResultPanel;

    public StudentResults( ){
        super("SOVA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Result r=new Result((float)7.8,7);
        writeResults(new Result((float)7.8,7),new Test("Test Name"));

    }
    void writeResults(Result result, Test test){
        TestNameLabel.setText("Тест: "+test.getName());
        Results.setText("Ваш результат: "+result.getMark());
        this.getContentPane().add(panel);
        panel.revalidate();
        pack();
        setVisible(true);
    }
}
