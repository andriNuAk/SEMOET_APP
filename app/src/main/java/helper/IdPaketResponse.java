package helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/18/2017.
 */

public class IdPaketResponse {
    @SerializedName("IdPaket")
    @Expose
    private List<IdPaket> idPaket = null;

    public List<IdPaket> getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(List<IdPaket> idPaket) {
        this.idPaket = idPaket;
    }
}
