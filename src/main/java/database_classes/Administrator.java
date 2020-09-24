package database_classes;

import com.google.gson.annotations.SerializedName;

public class Administrator {

    @SerializedName("idadministrators")
    private Integer id;

    @SerializedName("persons_id")
    private Integer personId;
}
