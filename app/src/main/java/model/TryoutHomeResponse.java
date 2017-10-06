package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by M on 9/12/2017.
 */

public class TryoutHomeResponse {
    @SerializedName("TryoutHome")
    @Expose
    private List<TryoutHome> tryoutHome = null;

    public List<TryoutHome> getTryoutHome() {
        return tryoutHome;
    }

    public void setTryoutHome(List<TryoutHome> tryoutHome) {
        this.tryoutHome = tryoutHome;
    }
}
