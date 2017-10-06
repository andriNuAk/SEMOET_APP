package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/14/2017.
 */

public class MinSekolahResponse {
    @SerializedName("MinSekolah")
    @Expose
    private List<MinSekolah> minSekolah = null;

    public List<MinSekolah> getMinSekolah() {
        return minSekolah;
    }

    public void setMinSekolah(List<MinSekolah> minSekolah) {
        this.minSekolah = minSekolah;
    }
}
