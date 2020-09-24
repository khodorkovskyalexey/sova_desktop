package database_classes;

import com.google.gson.annotations.SerializedName;

public class Teacher {

    @SerializedName("idteachers")
    private Integer id;

    @SerializedName("persons_id")
    private Integer personId;

    public Integer getId() {
        return id;
    }

    public Teacher(Integer id) {
        this.id = id;
    }
}
