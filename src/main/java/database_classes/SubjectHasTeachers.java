package database_classes;

import com.google.gson.annotations.SerializedName;

public class SubjectHasTeachers {

    @SerializedName("subject_idsubject")
    private Integer subjectId;

    @SerializedName("teachers_idteachers")
    private Integer teacherId;
}
