package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/13/2017.
 */

public class SekolahIDResponse {
    @SerializedName("SekolahID")
    @Expose
    private List<SekolahID> sekolahID = null;

    public List<SekolahID> getSekolahID() {
        return sekolahID;
    }

    public void setSekolahID(List<SekolahID> sekolahID) {
        this.sekolahID = sekolahID;
    }
}
