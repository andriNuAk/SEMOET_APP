package helper;

/**
 * Created by M on 9/15/2017.
 */

public class ItemNilaiHelper {
    String title;
    int icon;
    int valueNilai;
    int jumlahSoal;

    public ItemNilaiHelper(String title, int icon, int valueNilai, int jumlahSoal) {
        this.title = title;
        this.icon = icon;
        this.valueNilai = valueNilai;
        this.jumlahSoal = jumlahSoal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getValueNilai() {
        return valueNilai;
    }

    public void setValueNilai(int valueNilai) {
        this.valueNilai = valueNilai;
    }

    public int getJumlahSoal() {
        return jumlahSoal;
    }

    public void setJumlahSoal(int jumlahSoal) {
        this.jumlahSoal = jumlahSoal;
    }
}
