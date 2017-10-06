package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/14/2017.
 */

public class ReportPenggunaResponse {
    @SerializedName("ReportPengguna")
    @Expose
    private List<ReportPengguna> reportPengguna = null;

    public List<ReportPengguna> getReportPengguna() {
        return reportPengguna;
    }

    public void setReportPengguna(List<ReportPengguna> reportPengguna) {
        this.reportPengguna = reportPengguna;
    }
}
