package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 9/14/2017.
 */

public class ReportPengguna {
    @SerializedName("id_report")
    @Expose
    private Integer idReport;
    @SerializedName("siswaID")
    @Expose
    private Integer siswaID;
    @SerializedName("id_pengguna")
    @Expose
    private Integer idPengguna;
    @SerializedName("id_mm-tryout-paket")
    @Expose
    private Integer idMmTryoutPaket;
    @SerializedName("jmlh_kosong")
    @Expose
    private Integer jmlhKosong;
    @SerializedName("jmlh_benar")
    @Expose
    private Integer jmlhBenar;
    @SerializedName("jmlh_salah")
    @Expose
    private Integer jmlhSalah;
    @SerializedName("total_nilai")
    @Expose
    private Integer totalNilai;
    @SerializedName("poin")
    @Expose
    private Integer poin;
    @SerializedName("tgl_pengerjaan")
    @Expose
    private String tglPengerjaan;
    @SerializedName("status_pengerjaan")
    @Expose
    private String statusPengerjaan;
    @SerializedName("rekap_hasil_koreksi")
    @Expose
    private String rekapHasilKoreksi;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_tryout")
    @Expose
    private Integer idTryout;
    @SerializedName("id_paket")
    @Expose
    private Integer idPaket;
    @SerializedName("nm_paket")
    @Expose
    private String nmPaket;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("jumlah_soal")
    @Expose
    private Integer jumlahSoal;
    @SerializedName("durasi")
    @Expose
    private Integer durasi;
    @SerializedName("random")
    @Expose
    private String random;
    @SerializedName("penggunaID")
    @Expose
    private Integer penggunaID;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("jenis_penilaian")
    @Expose
    private String jenisPenilaian;
    @SerializedName("nm_tryout")
    @Expose
    private String nmTryout;
    @SerializedName("tgl_mulai")
    @Expose
    private String tglMulai;
    @SerializedName("tgl_berhenti")
    @Expose
    private String tglBerhenti;
    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("wkt_mulai")
    @Expose
    private String wktMulai;
    @SerializedName("wkt_berakhir")
    @Expose
    private String wktBerakhir;

    public Integer getIdReport() {
        return idReport;
    }

    public void setIdReport(Integer idReport) {
        this.idReport = idReport;
    }

    public Integer getSiswaID() {
        return siswaID;
    }

    public void setSiswaID(Integer siswaID) {
        this.siswaID = siswaID;
    }

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    public Integer getIdMmTryoutPaket() {
        return idMmTryoutPaket;
    }

    public void setIdMmTryoutPaket(Integer idMmTryoutPaket) {
        this.idMmTryoutPaket = idMmTryoutPaket;
    }

    public Integer getJmlhKosong() {
        return jmlhKosong;
    }

    public void setJmlhKosong(Integer jmlhKosong) {
        this.jmlhKosong = jmlhKosong;
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

    public Integer getTotalNilai() {
        return totalNilai;
    }

    public void setTotalNilai(Integer totalNilai) {
        this.totalNilai = totalNilai;
    }

    public Integer getPoin() {
        return poin;
    }

    public void setPoin(Integer poin) {
        this.poin = poin;
    }

    public String getTglPengerjaan() {
        return tglPengerjaan;
    }

    public void setTglPengerjaan(String tglPengerjaan) {
        this.tglPengerjaan = tglPengerjaan;
    }

    public String getStatusPengerjaan() {
        return statusPengerjaan;
    }

    public void setStatusPengerjaan(String statusPengerjaan) {
        this.statusPengerjaan = statusPengerjaan;
    }

//    public ArrayList<RekapHasilKoreksi> getRekapHasilKoreksi() {
//        return rekapHasilKoreksi;
//    }
//
//    public void setRekapHasilKoreksi(ArrayList<RekapHasilKoreksi> rekapHasilKoreksi) {
//        this.rekapHasilKoreksi = rekapHasilKoreksi;
//    }


    public String getRekapHasilKoreksi() {
        return rekapHasilKoreksi;
    }

    public void setRekapHasilKoreksi(String rekapHasilKoreksi) {
        this.rekapHasilKoreksi = rekapHasilKoreksi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTryout() {
        return idTryout;
    }

    public void setIdTryout(Integer idTryout) {
        this.idTryout = idTryout;
    }

    public Integer getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }

    public String getNmPaket() {
        return nmPaket;
    }

    public void setNmPaket(String nmPaket) {
        this.nmPaket = nmPaket;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJumlahSoal() {
        return jumlahSoal;
    }

    public void setJumlahSoal(Integer jumlahSoal) {
        this.jumlahSoal = jumlahSoal;
    }

    public Integer getDurasi() {
        return durasi;
    }

    public void setDurasi(Integer durasi) {
        this.durasi = durasi;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public Integer getPenggunaID() {
        return penggunaID;
    }

    public void setPenggunaID(Integer penggunaID) {
        this.penggunaID = penggunaID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getJenisPenilaian() {
        return jenisPenilaian;
    }

    public void setJenisPenilaian(String jenisPenilaian) {
        this.jenisPenilaian = jenisPenilaian;
    }

    public String getNmTryout() {
        return nmTryout;
    }

    public void setNmTryout(String nmTryout) {
        this.nmTryout = nmTryout;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getTglBerhenti() {
        return tglBerhenti;
    }

    public void setTglBerhenti(String tglBerhenti) {
        this.tglBerhenti = tglBerhenti;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getUUID() {
        return uUID;
    }

    public void setUUID(String uUID) {
        this.uUID = uUID;
    }

    public String getWktMulai() {
        return wktMulai;
    }

    public void setWktMulai(String wktMulai) {
        this.wktMulai = wktMulai;
    }

    public String getWktBerakhir() {
        return wktBerakhir;
    }

    public void setWktBerakhir(String wktBerakhir) {
        this.wktBerakhir = wktBerakhir;
    }

//    public class RekapHasilKoreksi{
//        @SerializedName("id_soal")
//        @Expose
//        private String idSoal;
//        @SerializedName("status_koreksi")
//        @Expose
//        private Integer statusKoreksi;
//
//        public String getIdSoal() {
//            return idSoal;
//        }
//
//        public void setIdSoal(String idSoal) {
//            this.idSoal = idSoal;
//        }
//
//        public Integer getStatusKoreksi() {
//            return statusKoreksi;
//        }
//
//        public void setStatusKoreksi(Integer statusKoreksi) {
//            this.statusKoreksi = statusKoreksi;
//        }
//    }

//    public class RekapHasilKoreksiResponse{
//        @SerializedName("rekap_hasil_koreksi")
//        @Expose
//        private List<RekapHasilKoreksi> rekapHasilKoreksi = null;
//
//        public List<RekapHasilKoreksi> getRekapHasilKoreksi() {
//            return rekapHasilKoreksi;
//        }
//
//        public void setRekapHasilKoreksi(List<RekapHasilKoreksi> rekapHasilKoreksi) {
//            this.rekapHasilKoreksi = rekapHasilKoreksi;
//        }
//    }

}
