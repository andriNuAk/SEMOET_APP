package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/11/2017.
 */

public class DataPengguna {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("namaPengguna")
    @Expose
    private String namaPengguna;
    @SerializedName("kataSandi")
    @Expose
    private String kataSandi;
    @SerializedName("eMail")
    @Expose
    private String eMail;
    @SerializedName("regTime")
    @Expose
    private Object regTime;
    @SerializedName("aktivasi")
    @Expose
    private Object aktivasi;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("oauth_uid")
    @Expose
    private Object oauthUid;
    @SerializedName("oauth_provider")
    @Expose
    private Object oauthProvider;
    @SerializedName("hakAkses")
    @Expose
    private String hakAkses;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("last_akses")
    @Expose
    private Object lastAkses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public Object getRegTime() {
        return regTime;
    }

    public void setRegTime(Object regTime) {
        this.regTime = regTime;
    }

    public Object getAktivasi() {
        return aktivasi;
    }

    public void setAktivasi(Object aktivasi) {
        this.aktivasi = aktivasi;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getOauthUid() {
        return oauthUid;
    }

    public void setOauthUid(Object oauthUid) {
        this.oauthUid = oauthUid;
    }

    public Object getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(Object oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getLastAkses() {
        return lastAkses;
    }

    public void setLastAkses(Object lastAkses) {
        this.lastAkses = lastAkses;
    }

}
