package forms.create_test;

import database_classes.Answer;
import database_classes.Question;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class CreateAnswers extends JFrame{
    private JPanel panel;
    private JSpinner numOfAnswersSpin;
    private JButton okButton;
    private JPanel answerPanel;
    private JLabel NumAnsLabel;
    private JLabel AnsLabel;
    private JTextField questionSize;
    private JButton answerBtn;
    private JCheckBox[] checkBox;
    private JTextField[] textField;

    private Answer[] answers;
    private boolean isAnwersReady;

    private void rewriteAnswers(int size) {
        answerPanel.removeAll();
        answerPanel.setLayout(new BoxLayout(answerPanel,BoxLayout.Y_AXIS));
        checkBox = new JCheckBox[size];
        textField = new JTextField[size];
        for (int i = 0; i < size; i++) {
            JPanel pan = new JPanel();
            pan.setLayout(new BoxLayout(pan,BoxLayout.X_AXIS));
            checkBox[i] = new JCheckBox("");
            pan.add(checkBox[i]);
            textField[i] = new JTextField("");
            pan.add(textField[i]);
            answerPanel.add(pan);
        }
    }

    private void initListeners() {
        answerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(questionSize.getText()) > 0) {
                    okButton.setEnabled(true);
                }
                rewriteAnswers(Integer.parseInt(questionSize.getText()));
                panel.revalidate();
                panel.repaint();
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = textField.length;
                answers = new Answer[size];
                for (int i = 0; i < size; i++) {
                    answers[i] = new Answer(textField[i].getText(), checkBox[i].isSelected());
                }
                setVisible(false);
                //isAnwersReady = true;
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(getSize().toString());
                setVisible(false);
//                isAnwersReady = true;
            }
        });
    }

    public boolean isAnwersReady() {
        return isAnwersReady;
    }

    public Answer[] getAnswers() {
        //while (!isAnwersReady);
        return answers;
    }
    public CreateAnswers(){
        super("SOVA");
        /*panel.revalidate();
        panel.repaint();*/
        this.getContentPane().add(panel);
        pack();
        setVisible(true);
        isAnwersReady = false;
        initListeners();
    }
}
