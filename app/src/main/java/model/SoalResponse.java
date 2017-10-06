package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/18/2017.
 */

public class SoalResponse {
    @SerializedName("Soal")
    @Expose
    private List<Soal> soal = null;

    public List<Soal> getSoal() {
        return soal;
    }

    public void setSoal(List<Soal> soal) {
        this.soal = soal;
    }
}
