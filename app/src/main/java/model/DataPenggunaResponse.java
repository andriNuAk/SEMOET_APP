package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/11/2017.
 */

public class DataPenggunaResponse {
    @SerializedName("DataPengguna")
    @Expose
    private List<DataPengguna> dataPengguna = null;

    public List<DataPengguna> getDataPengguna() {
        return dataPengguna;
    }

    public void setDataPengguna(List<DataPengguna> dataPengguna) {
        this.dataPengguna = dataPengguna;
    }
}
