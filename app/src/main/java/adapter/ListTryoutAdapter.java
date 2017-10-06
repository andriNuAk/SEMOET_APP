package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.ReportPengguna;
import model.Soal;
import model.SoalResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vortex.semoet.NilaiActivity;
import vortex.semoet.PembahasanActivity;
import vortex.semoet.R;

/**
 * Created by M on 9/14/2017.
 */

public class ListTryoutAdapter extends BaseAdapter {

    Context context;
    ArrayList<ReportPengguna> reportPengguna;
    LayoutInflater inflater;
    ArrayList<Soal> mSoal;

    public ListTryoutAdapter(Context context, ArrayList<ReportPengguna> reportPengguna) {
        this.context = context;
        this.reportPengguna = reportPengguna;
    }

    @Override
    public int getCount() {
        return reportPengguna.size();
    }

    @Override
    public Object getItem(int position) {
        return reportPengguna.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.card_daftar_tryout, parent, false);
        }

        TextView txtNamaTryout = (TextView) convertView.findViewById(R.id.txtNamaTryout);
        TextView txtNamaPaket = (TextView) convertView.findViewById(R.id.txtNamaPaket);
        Button btnLihatNilai = (Button) convertView.findViewById(R.id.btnLihatNilai);
        Button btnPembahasan = (Button) convertView.findViewById(R.id.btnPembahasan);
        Typeface tfcAmaranthR;
        tfcAmaranthR = Typeface.createFromAsset(convertView.getContext().getAssets(), "fonts/Amaranth_Regular.ttf");
        txtNamaTryout.setTypeface(tfcAmaranthR);
        txtNamaPaket.setTypeface(tfcAmaranthR);
        btnLihatNilai.setTypeface(tfcAmaranthR);
        btnPembahasan.setTypeface(tfcAmaranthR);

        txtNamaTryout.setText("Nama Tryout : "+reportPengguna.get(position).getNmTryout());
        txtNamaPaket.setText("Nama Paket : "+reportPengguna.get(position).getNmPaket());

        btnLihatNilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), NilaiActivity.class);

                Gson gson = new Gson();

                //HasilKoreksiHelper[] hasilKoreksiHelper = gson.fromJson(reportPengguna.get(position).getRekapHasilKoreksi(), HasilKoreksiHelper[].class);
                String namaPaket = reportPengguna.get(position).getNmPaket();

                //System.out.println("id soal = "+hasilKoreksiHelper.getId_soal()+" hasil koreksi = "+hasilKoreksiHelper.getStatus_koreksi());

                //buat ambil pembahasan jangan di hapus ini lieur nyari koding nya okeeeeee
//                ArrayList<HasilKoreksiHelper> hasilKoreksiHelper = new ArrayList<HasilKoreksiHelper>();
//                hasilKoreksiHelper = new Gson().fromJson(namaPaket, new TypeToken<List<HasilKoreksiHelper>>(){}.getType());

                int nilai = reportPengguna.get(position).getTotalNilai();
                int jmlBenar = reportPengguna.get(position).getJmlhBenar();
                int jmlSalah = reportPengguna.get(position).getJmlhSalah();
                int jmlKosong = reportPengguna.get(position).getJmlhKosong();
                int jmlSoal = reportPengguna.get(position).getJumlahSoal();
                String jenisPenilaian = reportPengguna.get(position).getJenisPenilaian();


                intent.putExtra("NamaPaket", namaPaket);
                intent.putExtra("Nilai", String.valueOf(nilai));
                intent.putExtra("JmlBenar", String.valueOf(jmlBenar));
                intent.putExtra("JmlSalah", String.valueOf(jmlSalah));
                intent.putExtra("JmlKosong", String.valueOf(jmlKosong));
                intent.putExtra("JmlSoal", String.valueOf(jmlSoal));
                intent.putExtra("JenisPenilaian", jenisPenilaian);
                context.startActivity(intent);
            }
        });

        btnPembahasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idPaket = reportPengguna.get(position).getIdPaket();
//                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
//                retrofit2.Call<SoalResponse> call = apiService.getSoal(idPaket);
//                call.enqueue(new Callback<SoalResponse>() {
//                    @Override
//                    public void onResponse(Call<SoalResponse> call, Response<SoalResponse> response) {
//                        ArrayList<Soal> soal = (ArrayList<Soal>) response.body().getSoal();
//                        mSoal = soal;
//                    }
//
//                    @Override
//                    public void onFailure(Call<SoalResponse> call, Throwable t) {
//
//                    }
//                });
                Intent intent = new Intent(context.getApplicationContext(), PembahasanActivity.class);

                intent.putExtra("IdPaket", String.valueOf(idPaket));
                intent.putExtra("HasilKoreksi", reportPengguna.get(position).getRekapHasilKoreksi());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
