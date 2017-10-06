package vortex.semoet;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import adapter.ListTryoutAdapter;
import helper.PenggunaHelper;
import helper.SessionHelper;
import model.ReportPengguna;
import model.ReportPenggunaResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarTryoutFragment extends Fragment {

    TextView txtNamaPengguna, txtNilaiMax, txtNilaiMin, txtText, txtStatusTryout;
    Typeface tfcAmaranthR;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<PenggunaHelper> penggunaHelper;
    int id;
    String inNamaPengguna, inKataSandi, inEMail, inHakAkses, inStatus;
    ArrayList<ReportPengguna> mReportPengguna;
    ExpandableHeightListView listDaftarTryout;
    ListTryoutAdapter listTryoutAdapter;
    private AVLoadingIndicatorView avi;


    public DaftarTryoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_daftar_tryout, container, false);
        listDaftarTryout = (ExpandableHeightListView) rootView.findViewById(R.id.listDaftarTryout);
        txtText = (TextView) rootView.findViewById(R.id.txtText);
        tfcAmaranthR = Typeface.createFromAsset(rootView.getContext().getAssets(), "fonts/Amaranth_Regular.ttf");
        avi= (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        avi.setVisibility(View.VISIBLE);

        txtText.setTypeface(tfcAmaranthR);

        initPengguna();
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<ReportPenggunaResponse> call = apiService.getReportPengguna(id);
        call.enqueue(new Callback<ReportPenggunaResponse>() {
            @Override
            public void onResponse(Call<ReportPenggunaResponse> call, Response<ReportPenggunaResponse> response) {
                txtText.setText("Ini adalah daftar tryout kamu");
                ArrayList<ReportPengguna> reportPengguna = (ArrayList<ReportPengguna>) response.body().getReportPengguna();
                mReportPengguna = reportPengguna;
                if (mReportPengguna != null){
                    listTryoutAdapter = new ListTryoutAdapter(rootView.getContext(), mReportPengguna);
                    listDaftarTryout.setAdapter(listTryoutAdapter);
                    listDaftarTryout.setExpanded(true);
                } else {
                    txtText.setText("Ups kamu belum punya riwayat nilai !!!!");
                }

                avi.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ReportPenggunaResponse> call, Throwable t) {
                //System.out.println("Siswa error!!!!");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(rootView.getContext());

                // Setting Dialog Title
                alertDialog.setTitle("Data Tidak Tersedia");

                // Setting Dialog Message
                alertDialog.setMessage("Audio yang akan anda unduh tidak tersedia !!!");

                // Setting icon
                alertDialog.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert));

                // Setting Icon to Dialog
                //alertDialog.setIcon(R.drawable.delete);

                // Setting Positive "Yes" Button
//        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog,int which) {
//
//                // Write your code here to invoke YES event
//                //Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
//                initPengguna();
//                SessionHelper dbHelper =  new SessionHelper(HomeActivity.this);
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                dbHelper.deleteDataAkun(db, id);
//                initPengguna();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent);
//            }
//        });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(rootView.getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                txtText.setText("Tidak dapat terhubung ke server, harap periksa koneksi kamu");
            }
        });
        return rootView;
    }

    public void initPengguna(){
//        for (int i=0 ; i < akunHelpers.size(); i++){
//            kodeKeluarga = akunHelpers.get(i).getKodeKeluarga();
//            noIdentitas = akunHelpers.get(i).getNoIdentitas();
//            username = akunHelpers.get(i).getUsername();
//            password = akunHelpers.get(i).getPassword();
//        }

        dbHelper = new SessionHelper(getActivity().getApplicationContext());
        db = dbHelper.getWritableDatabase();
        try {
//            loadDataMahasiswa();
            Cursor cursor = dbHelper.getAllAkun(db);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
//            String data = cursor.getString(0)+" - "+cursor.getString(1);
//            akun.add(data);
                penggunaHelper = new ArrayList();

                id = Integer.valueOf(cursor.getString(0));
                inNamaPengguna = cursor.getString(1);
                inKataSandi = cursor.getString(2);
                inEMail = cursor.getString(3);
                inHakAkses = cursor.getString(4);
                inStatus = cursor.getString(5);
                PenggunaHelper ph = new PenggunaHelper(id, inNamaPengguna, inKataSandi, inEMail, inHakAkses, inStatus);
                penggunaHelper.add(ph);
                cursor.moveToNext();
            }
// close cursor
            cursor.close();
//            Collections.sort(akun);
        } catch (Exception e){
            Log.e("masuk","-> "+e.getMessage()) ;
        }

        System.out.println("Udah ada di arraylist, datanya : kode user = "+id);
    }


}
