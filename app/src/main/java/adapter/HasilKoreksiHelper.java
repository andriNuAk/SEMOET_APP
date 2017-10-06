package adapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/15/2017.
 */

public class HasilKoreksiHelper {
    @SerializedName("id_soal")
    @Expose
    String id_soal;
    @SerializedName("status_koreksi")
    @Expose
    int status_koreksi;

    public HasilKoreksiHelper(String id_soal, int status_koreksi) {
        this.id_soal = id_soal;
        this.status_koreksi = status_koreksi;
    }

    public String getId_soal() {
        return id_soal;
    }

    public void setId_soal(String id_soal) {
        this.id_soal = id_soal;
    }

    public int getStatus_koreksi() {
        return status_koreksi;
    }

    public void setStatus_koreksi(int status_koreksi) {
        this.status_koreksi = status_koreksi;
    }
}
