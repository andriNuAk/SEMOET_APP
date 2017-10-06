package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/12/2017.
 */

public class NilaiMaxResponse {
    @SerializedName("NilaiMax")
    @Expose
    private List<NilaiMax> nilaiMax = null;

    public List<NilaiMax> getNilaiMax() {
        return nilaiMax;
    }

    public void setNilaiMax(List<NilaiMax> nilaiMax) {
        this.nilaiMax = nilaiMax;
    }
}
