package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/12/2017.
 */

public class TryoutHome {
    @SerializedName("nm_tryout")
    @Expose
    private String nmTryout;
    @SerializedName("nm_paket")
    @Expose
    private String nmPaket;

    public String getNmTryout() {
        return nmTryout;
    }

    public void setNmTryout(String nmTryout) {
        this.nmTryout = nmTryout;
    }

    public String getNmPaket() {
        return nmPaket;
    }

    public void setNmPaket(String nmPaket) {
        this.nmPaket = nmPaket;
    }
}
