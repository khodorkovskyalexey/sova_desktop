package database_classes;

import com.google.gson.annotations.SerializedName;

public class Subject {

    @SerializedName("idsubject")
    private Integer id;

    @SerializedName("subject")
    private String subjectName;
}
