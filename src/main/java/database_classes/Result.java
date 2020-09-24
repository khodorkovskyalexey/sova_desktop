package database_classes;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("idresult")
    private Integer id;

    @SerializedName("mark")
    private Float mark;

    @SerializedName("students_idstudents")
    private Integer studentId;

    @SerializedName("tests_idtests")
    private Integer testId;

    public Result(Float mark, Integer studentId) {
        this.mark = mark;
        this.studentId = studentId;
    }

    public Result(Integer studentId, Integer testId) {
        this.studentId = studentId;
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", mark=" + mark +
                ", studentId=" + studentId +
                ", testId=" + testId +
                '}';
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }

    public Integer getTestId() {
        return testId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public Float getMark() {
        return mark;
    }

    public Integer getId() {
        return id;
    }
    public void setTestId(Integer testId) {
        this.testId = testId;
    }


    public void setId(Integer id) {
        this.id = id;
    }

}
