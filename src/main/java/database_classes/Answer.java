package database_classes;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("idanswers")
    private Integer id;

    @SerializedName("answerscol")
    private String answerText;

    @SerializedName("questions_idquestion")
    private Integer questionId;

    @SerializedName("isItCorrect")
    private boolean isItCorrect;

    private int questionLocalId;

    @Override
    public String toString() {
        return "Answer{" +
                "answerText='" + answerText + '\'' +
                ", isItCorrect=" + isItCorrect +
                ", questionLocalId=" + questionLocalId +
                '}';
    }

    public int getQuestionLocalId() {
        return questionLocalId;
    }

    public void setQuestionLocalId(int questionLocalId) {
        this.questionLocalId = questionLocalId;
    }

    public Answer(String answerText, boolean isItCorrect) {
        this.answerText = answerText;
        this.isItCorrect = isItCorrect;
    }

    public Answer() {
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public boolean isItCorrect() {
        return isItCorrect;
    }
}
