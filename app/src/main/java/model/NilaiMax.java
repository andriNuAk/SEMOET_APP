package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/12/2017.
 */

public class NilaiMax {
    @SerializedName("id_pengguna")
    @Expose
    private Integer idPengguna;
    @SerializedName("nama_paket")
    @Expose
    private String namaPaket;
    @SerializedName("terbesar")
    @Expose
    private Integer terbesar;

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public Integer getTerbesar() {
        return terbesar;
    }

    public void setTerbesar(Integer terbesar) {
        this.terbesar = terbesar;
    }
}
