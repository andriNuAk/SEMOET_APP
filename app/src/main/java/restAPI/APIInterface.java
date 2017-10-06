package restAPI;

import helper.IdPaketResponse;
import helper.SekolahIDResponse;
import model.DataPenggunaResponse;
import model.MaxSekolahResponse;
import model.MinSekolahResponse;
import model.NilaiMaxResponse;
import model.NilaiMinResponse;
import model.PilihanJawabanResponse;
import model.ReportPenggunaResponse;
import model.SoalResponse;
import model.TryoutHomeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by M on 9/11/2017.
 */

public interface APIInterface {

    //ambil data pengguna
    @GET("/check_pengguna")
    Call<DataPenggunaResponse> getPengguna(@Query("kataSandi") String kataSandi,
                             @Query("namaPengguna") String namaPengguna,
                             @Query("eMail") String eMail);

    //ambil nilai max pengguna
    @GET("/get_max_pengguna")
    Call<NilaiMaxResponse> getNilaiMax(@Query("id_pengguna") int id_pengguna);

    //ambil nilai min pengguna
    @GET("/get_min_pengguna")
    Call<NilaiMinResponse> getNilaiMin(@Query("id_pengguna") int id_pengguna);

    //ambil tryout untuk di home
    @GET("/get_tryout_home")
    Call<TryoutHomeResponse> getTryoutHome(@Query("id_pengguna") int id_pengguna);

    //ambil id sekolah
    @GET("/get_id_sekolah")
    Call<SekolahIDResponse> getSekolahID(@Query("penggunaID") int penggunaID);

    //ambil nilai max sekolah
    @GET("/get_max_by_sekolah")
    Call<MaxSekolahResponse> getMaxSekolah(@Query("sekolahID") int sekolahID);

    //ambil nilai min sekolah
    @GET("/get_min_by_sekolah")
    Call<MinSekolahResponse> getMinSekolah(@Query("sekolahID") int sekolahID);

    //ambil report tryout
    @GET("/get_report_by_pengguna")
    Call<ReportPenggunaResponse> getReportPengguna(@Query("id_pengguna") int id_pengguna);

    //ambil id paket
    @GET("/get_id_paket")
    Call<IdPaketResponse> getIdPaket(@Query("id") int id);

    //ambil soal
    @GET("/get_soal")
    Call<SoalResponse> getSoal(@Query("id_paket") int id_paket);

    //ambil jawaban
    @GET("/get_pilihan_jawaban_to")
    Call<PilihanJawabanResponse> getPilihanJawaban(@Query("id_paket") int id_paket,
                                                   @Query("id_soal") int id_soal);


}
