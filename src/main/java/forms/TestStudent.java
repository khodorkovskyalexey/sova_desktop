package forms;

import database_classes.Answer;
import database_classes.Question;
import database_classes.Result;
import database_classes.Test;
import other_classes.JTestCheckBox;
import other_classes.User;
import other_classes.UserAnswers;
import server_connector.ServerConnector;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestStudent extends JFrame{

    private JPanel panel;
    private JButton PreviosButton;//предыдущий вопрос
    private JButton NextButton;//следующий вопрос
    private JProgressBar progressBar;//и так понятно
    private JButton EndTestButton;//завершить тест
    private JLabel TestLabel;//имя теста
    private JLabel QuestionLabel;//текст вопроса
    private JPanel AnswersPanel;
    private JLabel TimeLabel;
    private JPanel QuestionButtonsPanel;
    private JLabel QuestionNumberLabel;
    private JButton RandomButton;
    private JScrollPane ScrollPanel;
    private JLabel TestNameLabel;
    private JPanel ProgressPanel;
    private JButton[] QuestionButtons;//массив с кнопками для переключения между вопрасами,появяться на QuestionButtonsPanel
    private JTestCheckBox[] AnswersButtons;

    private int QuestionNumber;
    private Test test;
    private Question[] questions;
    private boolean[] questionAnswered;
    private Answer[] answers;
    private ArrayList<Integer> CurrentAnswers = new ArrayList<>();//массив с индексами ответов на отображаемый вопрос
    private UserAnswers userAnswers = new UserAnswers();
    private User user;

    private void SetQuestion(){
        QuestionNumberLabel.setText("Вопрос: "+Integer.toString(QuestionNumber+1));
        QuestionLabel.setText(questions[QuestionNumber].getText());
        CleanAnsBut();
        int i=0;
        FindAnswers();//ищет варианты ответов в массиве
        AnswersButtons = new JTestCheckBox[CurrentAnswers.size()];
        while(i<CurrentAnswers.size()){//добавляет на панель варианты ответа
            AnswersButtons[i]=new JTestCheckBox(answers[CurrentAnswers.get(i)].getAnswerText(),
                    answers[CurrentAnswers.get(i)].getId());
            AnswersButtons[i].setText(answers[CurrentAnswers.get(i)].getAnswerText());
            AnswersPanel.add(AnswersButtons[i]);
            i++;
        }
        SelectAnswer();
        panel.revalidate();
    }

    private void FindAnswers(){//ищет варианты ответов в массиве
        CurrentAnswers.clear();
        for(int i=0; i<answers.length; i++){
            if(answers[i].getQuestionId()== questions[QuestionNumber].getId()){
                CurrentAnswers.add(i);
            }
        }

    }

    private void ChoseAnswers() {//должна пройти по чекбоксам и записать в массив какие выбраны
        int j=0;
        while (j < CurrentAnswers.size()) {
            if(AnswersButtons[j].isSelected()) {
                /*if(ChosenAnswers[QuestionNumber]==-1)//если раньше ответа на этот вопрос пользователь не дал,а сейчас дал
                    progressBar.setValue(progressBar.getValue()+1);//обнови прогрессбар
                ChosenAnswers[QuestionNumber] = j;//запомнить какие именно выбраны ответы
                break;*/
                if(!questionAnswered[QuestionNumber]) {
                    progressBar.setValue(progressBar.getValue()+1);//обнови прогрессбар
                    questionAnswered[QuestionNumber] = true;
                }

                Integer id = AnswersButtons[j].getAnswer_id();
                if(userAnswers.index_of(id) == -1) {
                    userAnswers.addAnswers_id(id);
                }
            }
            j++;
        }
    }
    private void SelectAnswer(){//должна достать те ответы которые пользователь в прошлый раз выбрал и проставить их
        for (int i = 0; i < AnswersButtons.length; i++) {
            if(userAnswers.index_of(AnswersButtons[i].getAnswer_id()) != -1) {
                AnswersButtons[i].setSelected(true);
            }
        }
//        if(ChosenAnswers[QuestionNumber]!=-1)
//            AnswersButtons[ChosenAnswers[QuestionNumber]].setSelected(true);
    }
    private void CleanAnsBut(){
        AnswersPanel.removeAll();
        AnswersPanel.repaint();
    }
    private void initListeners(){

        EndTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoseAnswers();
                //setVisible(false);
                checkTest();
            }
        });
        PreviosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoseAnswers();
                if(QuestionNumber>0) {
                    QuestionNumber--;
                    if(QuestionNumber == 0) {
                        PreviosButton.setEnabled(false);
                    }
                    NextButton.setEnabled(true);
                    SetQuestion();
                }
            }
        });
        NextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChoseAnswers();

                if(QuestionNumber < questions.length - 1){
                    QuestionNumber++;
                    PreviosButton.setEnabled(true);
                    SetQuestion();
                } else {
                    NextButton.setEnabled(false);
                }
            }
        });
    }

    private void CreateProgressBar(int size){
        progressBar.setMaximum(size);
        progressBar.setMinimum(0);
    }

    private void CreateQuestionButton(){
        int i=0;
        while (i<questions.length){
            QuestionButtons[i]=new JButton( Integer.toString(i+1) );
            final int finalI=i;
            QuestionButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    ChoseAnswers();
                    QuestionNumber=finalI;
                    SetQuestion();
                }
            });
            QuestionButtonsPanel.add(QuestionButtons[i]);
            i++;
        }
    }

    void writeTest(){
        //CurrentAnswers=null;
        QuestionNumber = 0;
        questionAnswered = new boolean[questions.length];
        for (int i = 0; i < questionAnswered.length; i++) {
            questionAnswered[i] = false;
        }
        TestLabel = new JLabel(test.getName());
        //TestLabel.setText(test.getName());
        QuestionButtons=new JButton[questions.length];
        AnswersPanel.setLayout(new BoxLayout(AnswersPanel,BoxLayout.Y_AXIS));
        QuestionButtonsPanel.setLayout(new BoxLayout(QuestionButtonsPanel,BoxLayout.X_AXIS));
        CreateProgressBar(questions.length);
        CreateQuestionButton();
        QuestionButtonsPanel.revalidate();
        SetQuestion();
        this.getContentPane().add(panel);
        pack();
        initListeners();
        setSize(new Dimension(500,300));
        //setResizable(false);
        setVisible(true);
    }

    private void checkTest() {
        int userAns = 0;
        int maxAns = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].isItCorrect()) {
                maxAns++;
                if(userAnswers.index_of(answers[i].getId()) != -1) {
                    userAns++;
                }
            }
        }
        Result result = new Result(user.getId(), test.getId());
        if (userAns == 0) {
            result.setMark((float) 0);
        } else {
            result.setMark((float) (userAns/maxAns));
        }
        System.out.println(result);
    }

    public TestStudent(User user, Integer test_id){
        super("SOVA");
        System.out.println("open");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.user = user;
        PreviosButton.setEnabled(false);
        userAnswers = new UserAnswers();

        test = ServerConnector.getTest(test_id);
        if(test != null) {
            questions = ServerConnector.getQuestions(test.getId());
            if(questions != null) {
                ArrayList<Answer> ans = new ArrayList<>();
                for (int i = 0; i < questions.length; i++) {
                    Answer[] a = ServerConnector.getAnswers(questions[i].getId());
                    for (int j = 0; j < a.length; j++) {
                        ans.add(a[i]);
                    }
                }
                if(ans != null) {
                    answers = new Answer[ans.size()];
                    for (int i = 0; i < answers.length; i++) {
                        answers[i] = ans.get(i);
                    }
                    writeTest();
                }
            }
        }
    }
}
