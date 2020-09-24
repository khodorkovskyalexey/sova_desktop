package database_classes;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("idquestion")
    private Integer id;

    @SerializedName("question")
    private String text;

    @SerializedName("tests_idtests")
    private Integer testId;

    public Question() {

    }

    public Question(String text, Integer testId) {
        this.text = text;
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getId() {
        return id;
    }
}
