package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/13/2017.
 */

public class SekolahID {
    @SerializedName("sekolahID")
    @Expose
    private Integer sekolahID;
    @SerializedName("namaSekolah")
    @Expose
    private String namaSekolah;

    public Integer getSekolahID() {
        return sekolahID;
    }

    public void setSekolahID(Integer sekolahID) {
        this.sekolahID = sekolahID;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }
}
