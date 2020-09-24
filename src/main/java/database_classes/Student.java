package database_classes;

import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("idstudents")
    private Integer id;

    @SerializedName("persons_id")
    private Integer personId;

    @SerializedName("group_idgroup")
    private Integer group_id;
}
