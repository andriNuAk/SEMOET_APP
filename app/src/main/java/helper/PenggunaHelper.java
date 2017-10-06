package helper;

/**
 * Created by M on 9/11/2017.
 */

public class PenggunaHelper {
    private int id;
    private String namaPengguna;
    private String kataSandi;
    private String eMail;
    private String hakAkses;
    private String status;


    public PenggunaHelper(int id,String namaPengguna, String kataSandi, String eMail, String hakAkses, String status) {
        this.id = id;
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
        this.eMail = eMail;
        this.hakAkses = hakAkses;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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


    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }
}
