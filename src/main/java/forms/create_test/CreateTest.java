package forms.create_test;

import com.sun.deploy.uitoolkit.impl.fx.FXPluginToolkit;
import database_classes.Answer;
import database_classes.Question;
import database_classes.Test;
import other_classes.User;
import server_connector.ServerConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTest extends JFrame{
    private JPanel panel;
    private JPanel answersPanel;
    private JTextArea questionTextArea;
    private JLabel QuestionTextLabel;
    private JButton answersBtn;
    private JButton previosQuestionBtn;
    private JButton nextQuesionBtn;
    private JTextField subjectNameField;
    private JButton compliteBtn;
    private JLabel testNameField;
    private JTextField nameTestField;
    private JLabel GroupsLabel;
    private JLabel questionNumber;
    private JLabel NumAnsLabel;
    private JLabel AnsLabel;
    private JPanel answerPanel;
    private JTextField questionSize;
    private JButton answerBtn;
    private JButton okButton;
    private JPanel ansPanel;
    private JButton otmenaBtn;
    private JCheckBox[] checkBox;
    private JTextField[] textField;

    private int questionCount;
    private ArrayList<Question> questionList = new ArrayList<>();
    private ArrayList<Answer> answerList = new ArrayList<>();

    private User user;

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

    private void saveQuestion() {
        questionList.get(questionCount).setText(questionTextArea.getText());
    }

    private void rewriteQuestionField() {
        questionTextArea.setText(questionList.get(questionCount).getText());
    }

    private void initListener() {
        previosQuestionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveQuestion();
                if(questionCount > 0) {
                    questionCount--;
                }
                rewriteQuestionField();

                questionNumber.setText("Вопрос номер " + (questionCount + 1) + "/" + questionList.size());
            }
        });

        rewriteQuestionField();
        nextQuesionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveQuestion();
                questionCount++;
                if(questionCount == questionList.size()) {
                    questionList.add(new Question());
                }
                rewriteQuestionField();

                questionNumber.setText("Вопрос номер " + (questionCount + 1) + "/" + questionList.size());
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(getSize().toString());
            }
        });

        answersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compliteBtn.setVisible(false);
                ansPanel.setVisible(true);
                previosQuestionBtn.setEnabled(false);
                nextQuesionBtn.setEnabled(false);
                answersBtn.setEnabled(false);
                pack();
                setSize(812, 617);
            }
        });

        compliteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveQuestion();
                registerTest();
            }
        });

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
                for (int i = 0; i < size; i++) {
                    Answer answer = new Answer(textField[i].getText(), checkBox[i].isSelected());
                    answer.setQuestionLocalId(questionCount);
                    answerList.add(answer);
                }
                compliteBtn.setEnabled(true);
                compliteBtn.setVisible(true);
                ansPanel.setVisible(false);
                previosQuestionBtn.setEnabled(true);
                nextQuesionBtn.setEnabled(true);
                answersBtn.setEnabled(true);
                answerPanel.removeAll();
                questionSize.setText("");
                okButton.setEnabled(false);
            }
        });

        otmenaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ansPanel.setVisible(false);
                compliteBtn.setVisible(true);
                previosQuestionBtn.setEnabled(true);
                nextQuesionBtn.setEnabled(true);
                answersBtn.setEnabled(true);
                answerPanel.removeAll();
                questionSize.setText("");
                okButton.setEnabled(false);
            }
        });
    }

    private void registerTest() {
        System.out.println("Question");
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println("    " + questionList.get(i));
        }
        System.out.println("Answer");
        for (int i = 0; i < answerList.size(); i++) {
            System.out.println("    " + answerList.get(i));
        }
        Integer teacher_id = null;
        try {
            teacher_id = ServerConnector.getTeacherId(user.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(teacher_id == null) {
            System.err.println("teacher_id = null");
            return;
        }
        Test test = new Test(nameTestField.getText(), teacher_id);
        ServerConnector.addTestAndQuestionsAndAnswers(test, questionList, answerList);
    }

    public CreateTest(User user){
        super("SOVA");
        this.user = user;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panel.revalidate();
        panel.repaint();
        ansPanel.setVisible(false);
        this.getContentPane().add(panel);
        pack();
        setSize(new Dimension(563,460));
        setVisible(true);
        questionCount = 0;
        questionList.add(new Question());

        questionNumber.setText("Вопрос номер " + (questionCount + 1) + "/" + questionList.size());

        initListener();
    }
}
