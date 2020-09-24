package database_classes;

import com.google.gson.annotations.SerializedName;

public class Test {

    @SerializedName("idtests")
    private Integer id;

    @SerializedName("name_test")
    private String name;

    @SerializedName("teachers_idteachers")
    private Integer teacherId;

    @SerializedName("subject_idsubject")
    private Integer subjectId;

    public Test(Integer id) {
        this.id = id;
    }

    public Test(String name) {
        this.name = name;
    }

    public Test(String name, Integer teacherId) {
        this.name = name;
        this.teacherId = teacherId;
    }

    public Test(Integer id, String name, Integer teacherId, Integer subjectId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }
}
