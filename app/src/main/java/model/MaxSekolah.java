package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/13/2017.
 */

public class MaxSekolah {
    @SerializedName("namaDepan")
    @Expose
    private String namaDepan;
    @SerializedName("namaBelakang")
    @Expose
    private String namaBelakang;
    @SerializedName("nm_paket")
    @Expose
    private String nmPaket;
    @SerializedName("namaSekolah")
    @Expose
    private String namaSekolah;
    @SerializedName("jenis_penilaian")
    @Expose
    private String jenisPenilaian;
    @SerializedName("jumlah_soal")
    @Expose
    private Integer jumlahSoal;
    @SerializedName("jmlh_benar")
    @Expose
    private Integer jmlhBenar;
    @SerializedName("jmlh_salah")
    @Expose
    private Integer jmlhSalah;
    @SerializedName("nilai_max")
    @Expose
    private Integer nilaiMax;

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getNmPaket() {
        return nmPaket;
    }

    public void setNmPaket(String nmPaket) {
        this.nmPaket = nmPaket;
    }

    public String getNamaSekolah() {
        return namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    public String getJenisPenilaian() {
        return jenisPenilaian;
    }

    public void setJenisPenilaian(String jenisPenilaian) {
        this.jenisPenilaian = jenisPenilaian;
    }

    public Integer getJumlahSoal() {
        return jumlahSoal;
    }

    public void setJumlahSoal(Integer jumlahSoal) {
        this.jumlahSoal = jumlahSoal;
    }

    public Integer getJmlhBenar() {
        return jmlhBenar;
    }

    public void setJmlhBenar(Integer jmlhBenar) {
        this.jmlhBenar = jmlhBenar;
    }

    public Integer getJmlhSalah() {
        return jmlhSalah;
    }

    public void setJmlhSalah(Integer jmlhSalah) {
        this.jmlhSalah = jmlhSalah;
    }

    public Integer getNilaiMax() {
        return nilaiMax;
    }

    public void setNilaiMax(Integer nilaiMax) {
        this.nilaiMax = nilaiMax;
    }
}
