package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/12/2017.
 */

public class NilaiMinResponse {
    @SerializedName("NilaiMin")
    @Expose
    private List<NilaiMin> nilaiMin = null;

    public List<NilaiMin> getNilaiMin() {
        return nilaiMin;
    }

    public void setNilaiMin(List<NilaiMin> nilaiMin) {
        this.nilaiMin = nilaiMin;
    }
}
