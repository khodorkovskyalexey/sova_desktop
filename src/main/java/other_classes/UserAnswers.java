package other_classes;

import database_classes.Answer;

import java.util.ArrayList;

public class UserAnswers {
    private ArrayList<Integer> answers_id;

    public UserAnswers() {
        answers_id = new ArrayList<>();
    }

    public void addAnswers_id(Integer id) {
        answers_id.add(id);
    }

    public Integer index_of(Integer id) {
        return answers_id.indexOf(id);
    }

    public ArrayList<Integer> getAnswers_id() {
        return answers_id;
    }

    public void setAnswers_id(ArrayList<Integer> answers_id) {
        this.answers_id = answers_id;
    }
}
