package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/18/2017.
 */

public class PilihanJawaban {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_paket")
    @Expose
    private Integer idPaket;
    @SerializedName("id_soal")
    @Expose
    private Integer idSoal;
    @SerializedName("id_subbab")
    @Expose
    private Integer idSubbab;
    @SerializedName("judul_soal")
    @Expose
    private String judulSoal;
    @SerializedName("soal")
    @Expose
    private String soal;
    @SerializedName("jawaban")
    @Expose
    private String jawaban;
    @SerializedName("kesulitan")
    @Expose
    private String kesulitan;
    @SerializedName("id_tingkat-pelajaran")
    @Expose
    private Integer idTingkatPelajaran;
    @SerializedName("sumber")
    @Expose
    private String sumber;
    @SerializedName("create_by")
    @Expose
    private String createBy;
    @SerializedName("random")
    @Expose
    private String random;
    @SerializedName("publish")
    @Expose
    private String publish;
    @SerializedName("UUID")
    @Expose
    private String uUID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gambar_soal")
    @Expose
    private String gambarSoal;
    @SerializedName("audio")
    @Expose
    private String audio;
    @SerializedName("pembahasan")
    @Expose
    private String pembahasan;
    @SerializedName("gambar_pembahasan")
    @Expose
    private String gambarPembahasan;
    @SerializedName("video_pembahasan")
    @Expose
    private String videoPembahasan;
    @SerializedName("status_pembahasan")
    @Expose
    private Integer statusPembahasan;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("id_pilihan")
    @Expose
    private Integer idPilihan;
    @SerializedName("pilihan")
    @Expose
    private String pilihan;
    @SerializedName("gambar")
    @Expose
    private String gambar;
    @SerializedName("idpak")
    @Expose
    private Integer idpak;
    @SerializedName("pilid")
    @Expose
    private Integer pilid;
    @SerializedName("soalid")
    @Expose
    private Integer soalid;
    @SerializedName("pilpil")
    @Expose
    private String pilpil;
    @SerializedName("piljaw")
    @Expose
    private String piljaw;
    @SerializedName("pilgam")
    @Expose
    private String pilgam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }

    public Integer getIdSoal() {
        return idSoal;
    }

    public void setIdSoal(Integer idSoal) {
        this.idSoal = idSoal;
    }

    public Integer getIdSubbab() {
        return idSubbab;
    }

    public void setIdSubbab(Integer idSubbab) {
        this.idSubbab = idSubbab;
    }

    public String getJudulSoal() {
        return judulSoal;
    }

    public void setJudulSoal(String judulSoal) {
        this.judulSoal = judulSoal;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public String getKesulitan() {
        return kesulitan;
    }

    public void setKesulitan(String kesulitan) {
        this.kesulitan = kesulitan;
    }

    public Integer getIdTingkatPelajaran() {
        return idTingkatPelajaran;
    }

    public void setIdTingkatPelajaran(Integer idTingkatPelajaran) {
        this.idTingkatPelajaran = idTingkatPelajaran;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGambarSoal() {
        return gambarSoal;
    }

    public void setGambarSoal(String gambarSoal) {
        this.gambarSoal = gambarSoal;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getPembahasan() {
        return pembahasan;
    }

    public void setPembahasan(String pembahasan) {
        this.pembahasan = pembahasan;
    }

    public String getGambarPembahasan() {
        return gambarPembahasan;
    }

    public void setGambarPembahasan(String gambarPembahasan) {
        this.gambarPembahasan = gambarPembahasan;
    }

    public String getVideoPembahasan() {
        return videoPembahasan;
    }

    public void setVideoPembahasan(String videoPembahasan) {
        this.videoPembahasan = videoPembahasan;
    }

    public Integer getStatusPembahasan() {
        return statusPembahasan;
    }

    public void setStatusPembahasan(Integer statusPembahasan) {
        this.statusPembahasan = statusPembahasan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getIdPilihan() {
        return idPilihan;
    }

    public void setIdPilihan(Integer idPilihan) {
        this.idPilihan = idPilihan;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Integer getIdpak() {
        return idpak;
    }

    public void setIdpak(Integer idpak) {
        this.idpak = idpak;
    }

    public Integer getPilid() {
        return pilid;
    }

    public void setPilid(Integer pilid) {
        this.pilid = pilid;
    }

    public Integer getSoalid() {
        return soalid;
    }

    public void setSoalid(Integer soalid) {
        this.soalid = soalid;
    }

    public String getPilpil() {
        return pilpil;
    }

    public void setPilpil(String pilpil) {
        this.pilpil = pilpil;
    }

    public String getPiljaw() {
        return piljaw;
    }

    public void setPiljaw(String piljaw) {
        this.piljaw = piljaw;
    }

    public String getPilgam() {
        return pilgam;
    }

    public void setPilgam(String pilgam) {
        this.pilgam = pilgam;
    }
}
