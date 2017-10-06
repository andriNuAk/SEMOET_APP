package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/18/2017.
 */

public class PilihanJawabanResponse {
    @SerializedName("PilihanJawaban")
    @Expose
    private List<PilihanJawaban> pilihanJawaban = null;

    public List<PilihanJawaban> getPilihanJawaban() {
        return pilihanJawaban;
    }

    public void setPilihanJawaban(List<PilihanJawaban> pilihanJawaban) {
        this.pilihanJawaban = pilihanJawaban;
    }
}
