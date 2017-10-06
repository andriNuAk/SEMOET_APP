package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/18/2017.
 */

public class IdPaket {
    @SerializedName("id_paket")
    @Expose
    private Integer idPaket;

    public Integer getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }
}
