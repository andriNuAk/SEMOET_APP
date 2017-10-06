package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/13/2017.
 */

public class MaxSekolahResponse {
    @SerializedName("MaxSekolah")
    @Expose
    private List<MaxSekolah> maxSekolah = null;

    public List<MaxSekolah> getMaxSekolah() {
        return maxSekolah;
    }

    public void setMaxSekolah(List<MaxSekolah> maxSekolah) {
        this.maxSekolah = maxSekolah;
    }
}
