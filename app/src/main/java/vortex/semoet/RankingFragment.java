package vortex.semoet;


import android.app.ProgressDialog;
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

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import helper.PenggunaHelper;
import helper.SekolahID;
import helper.SekolahIDResponse;
import helper.SessionHelper;
import model.MaxSekolah;
import model.MaxSekolahResponse;
import model.MinSekolah;
import model.MinSekolahResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {

    TextView txtSekolah, txtNamaPaketMaxSBMPTN, txtNilaiMaxSBMPTN, txtNamaSiswaMaxSBMPTN, txtNamaPaketMaxUNBK, txtNilaiMaxUNBK, txtNamaSiswaMaxUNBK, txtNamaPaketMinSBMPTN, txtNilaiMinSBMPTN, txtNamaSiswaMinSBMPTN, txtNamaPaketMinUNBK, txtNilaiMinUNBK, txtNamaSiswaMinUNBK, txtText ;
    Typeface tfcAmaranthR;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<PenggunaHelper> penggunaHelper;
    int id;
    private String idSekolah;
    String inNamaPengguna, inKataSandi, inEMail, inHakAkses, inStatus;
    ArrayList<SekolahID> mSekolahID;
    ArrayList<MaxSekolah> mMaxSekolah;
    ArrayList<MinSekolah> mMinSekolah;
    View rootview;
    ProgressDialog mProgressDialog;
    private AVLoadingIndicatorView avi;

    public static RankingFragment newInstance() {
        RankingFragment fragment = new RankingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_ranking, container, false);
        txtSekolah = (TextView) rootview.findViewById(R.id.txtSekolah);
        txtNamaPaketMaxSBMPTN = (TextView) rootview.findViewById(R.id.txtNamaPaketMaxSBMPTN);
        txtNilaiMaxSBMPTN = (TextView) rootview.findViewById(R.id.txtNilaiMaxSBMPTN);
        txtNamaSiswaMaxSBMPTN = (TextView) rootview.findViewById(R.id.txtNamaSiswaMaxSBMPTN);
        txtNamaPaketMaxUNBK = (TextView) rootview.findViewById(R.id.txtNamaPaketMaxUNBK);
        txtNilaiMaxUNBK = (TextView) rootview.findViewById(R.id.txtNilaiMaxUNBK);
        txtNamaSiswaMaxUNBK = (TextView) rootview.findViewById(R.id.txtNamaSiswaMaxUNBK);
        txtNamaPaketMinSBMPTN = (TextView) rootview.findViewById(R.id.txtNamaPaketMinSBMPTN);
        txtNilaiMinSBMPTN = (TextView) rootview.findViewById(R.id.txtNilaiMinSBMPTN);
        txtNamaSiswaMinSBMPTN = (TextView) rootview.findViewById(R.id.txtNamaSiswaMinSBMPTN);
        txtNamaPaketMinUNBK = (TextView) rootview.findViewById(R.id.txtNamaPaketMinUNBK);
        txtNilaiMinUNBK = (TextView) rootview.findViewById(R.id.txtNilaiMinUNBK);
        txtNamaSiswaMinUNBK = (TextView) rootview.findViewById(R.id.txtNamaSiswaMinUNBK);
        txtText = (TextView) rootview.findViewById(R.id.txtText);
        tfcAmaranthR = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Amaranth_Regular.ttf");
        avi= (AVLoadingIndicatorView) rootview.findViewById(R.id.avi);
        avi.setVisibility(View.VISIBLE);

        txtSekolah.setTypeface(tfcAmaranthR);
        txtNamaPaketMaxSBMPTN.setTypeface(tfcAmaranthR);
        txtNilaiMaxSBMPTN.setTypeface(tfcAmaranthR);
        txtNamaSiswaMaxSBMPTN.setTypeface(tfcAmaranthR);
        txtNamaPaketMaxUNBK.setTypeface(tfcAmaranthR);
        txtNilaiMaxUNBK.setTypeface(tfcAmaranthR);
        txtNamaSiswaMaxUNBK.setTypeface(tfcAmaranthR);
        txtNamaPaketMinSBMPTN.setTypeface(tfcAmaranthR);
        txtNilaiMinSBMPTN.setTypeface(tfcAmaranthR);
        txtNamaSiswaMinSBMPTN.setTypeface(tfcAmaranthR);
        txtNamaPaketMinUNBK.setTypeface(tfcAmaranthR);
        txtNilaiMinUNBK.setTypeface(tfcAmaranthR);
        txtNamaSiswaMinUNBK.setTypeface(tfcAmaranthR);
        txtText.setTypeface(tfcAmaranthR);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();

        initPengguna();
        initSekolah();





        return rootview;
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

    public void initSekolah(){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<SekolahIDResponse> call = apiService.getSekolahID(id);
        call.enqueue(new Callback<SekolahIDResponse>() {
            @Override
            public void onResponse(Call<SekolahIDResponse> call, Response<SekolahIDResponse> response) {
                txtText.setText("Ini adalah nilai tryout tertinggi dan terendah sekolah kamu");
                ArrayList<SekolahID> sekolahID = (ArrayList<SekolahID>) response.body().getSekolahID();
                mSekolahID = sekolahID;

                if (mSekolahID != null){
                    int kd = mSekolahID.get(0).getSekolahID();
                    txtSekolah.setText(mSekolahID.get(0).getNamaSekolah());
                    initMaxSekolah(kd);
                    initMinSekolah(kd);
                } else {

                }

            }

            @Override
            public void onFailure(Call<SekolahIDResponse> call, Throwable t) {
                //System.out.println("Siswa error!!!!");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(rootview.getContext());

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
                        Toast.makeText(rootview.getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                txtText.setText("Tidak dapat terhubung ke server, harap periksa koneksi kamu");
            }
        });
        mProgressDialog.dismiss();

    }

    public void initMaxSekolah(int sekolahID){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);

        retrofit2.Call<MaxSekolahResponse> call = apiService.getMaxSekolah(sekolahID);
        call.enqueue(new Callback<MaxSekolahResponse>() {
            @Override
            public void onResponse(Call<MaxSekolahResponse> call, Response<MaxSekolahResponse> response) {
                txtText.setText("Ini adalah nilai tryout tertinggi dan terendah sekolah kamu");
                ArrayList<MaxSekolah> maxSekolah = (ArrayList<MaxSekolah>) response.body().getMaxSekolah();
                mMaxSekolah = maxSekolah;

                if (mMaxSekolah.size() < 2){
                    if (mMaxSekolah.get(0).getNmPaket().equalsIgnoreCase("Paket SBMPTN") || mMaxSekolah.get(0).getNmPaket().equalsIgnoreCase("SBMPTN") ){
                        txtNamaPaketMaxSBMPTN.setText(mMaxSekolah.get(0).getNmPaket());
                        txtNilaiMaxSBMPTN.setText(mMaxSekolah.get(0).getNilaiMax().toString());
                        txtNamaSiswaMaxSBMPTN.setText(mMaxSekolah.get(0).getNamaDepan()+" "+mMaxSekolah.get(0).getNamaBelakang());

                        txtNamaPaketMaxUNBK.setText("Tidak ada riwayat UNBK");
                        txtNilaiMaxUNBK.setText("0");
                        txtNamaSiswaMaxUNBK.setText("");
                    } else if (mMaxSekolah.get(0).getNmPaket().equalsIgnoreCase("Paket UNBK") || mMaxSekolah.get(0).getNmPaket().equalsIgnoreCase("UNBK")){
                        txtNamaPaketMaxUNBK.setText(mMaxSekolah.get(0).getNmPaket());
                        txtNilaiMaxUNBK.setText(mMaxSekolah.get(0).getNilaiMax().toString());
                        txtNamaSiswaMaxUNBK.setText(mMaxSekolah.get(0).getNamaDepan()+" "+mMaxSekolah.get(1).getNamaBelakang());

                        txtNamaPaketMaxSBMPTN.setText("Tidak ada riwayat SBMPTN");
                        txtNilaiMaxSBMPTN.setText("0");
                        txtNamaSiswaMaxSBMPTN.setText("");
                    }
                } else if (mMaxSekolah.size() == 2){
                    txtNamaPaketMaxSBMPTN.setText(mMaxSekolah.get(0).getNmPaket());
                    double maxSBMPTN = Double.parseDouble(String.valueOf(mMaxSekolah.get(0).getJmlhBenar())) / Double.parseDouble(String.valueOf(mMaxSekolah.get(0).getJumlahSoal())) * 100;
//                    System.out.println("Hasil = "+maxSBMPTN);
                    txtNilaiMaxSBMPTN.setText(mMaxSekolah.get(0).getNilaiMax().toString());
                    txtNamaSiswaMaxSBMPTN.setText(mMaxSekolah.get(0).getNamaDepan()+" "+mMaxSekolah.get(0).getNamaBelakang());


                    txtNamaPaketMaxUNBK.setText(mMaxSekolah.get(1).getNmPaket());
                    txtNilaiMaxUNBK.setText(mMaxSekolah.get(1).getNilaiMax().toString());
                    txtNamaSiswaMaxUNBK.setText(mMaxSekolah.get(1).getNamaDepan()+" "+mMaxSekolah.get(1).getNamaBelakang());
                }

//                if (mMaxSekolah.get(0).getNmPaket() == null || mMaxSekolah.get(0).getNmPaket() == ""){
//
//                } else {
//
//                    System.out.println("Siswa max sbmptn "+mMaxSekolah.get(0).getNamaDepan()+" "+mMaxSekolah.get(0).getNamaBelakang());
//                }
//
////                if (mMaxSekolah.get(1).getNmPaket() == null || mMaxSekolah.get(1).getNmPaket() == ""){
////
////                } else {
////
////                }



            }

            @Override
            public void onFailure(Call<MaxSekolahResponse> call, Throwable t) {
                //System.out.println("Siswa error!!!!");
                //System.out.println("Siswa error!!!!");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(rootview.getContext());

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
                        Toast.makeText(rootview.getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                txtText.setText("Tidak dapat terhubung ke server, harap periksa koneksi kamu");
            }
        });
    }

    public void initMinSekolah(int sekolahID){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);

        retrofit2.Call<MinSekolahResponse> call = apiService.getMinSekolah(sekolahID);
        call.enqueue(new Callback<MinSekolahResponse>() {
            @Override
            public void onResponse(Call<MinSekolahResponse> call, Response<MinSekolahResponse> response) {
                txtText.setText("Ini adalah nilai tryout tertinggi dan terendah sekolah kamu");
                ArrayList<MinSekolah> minSekolah = (ArrayList<MinSekolah>) response.body().getMinSekolah();
                mMinSekolah = minSekolah;

                if (mMinSekolah.size() < 2){
                    if (mMinSekolah.get(0).getNmPaket().equalsIgnoreCase("Paket SBMPTN")){
                        txtNamaPaketMinSBMPTN.setText(mMinSekolah.get(0).getNmPaket());

                        txtNilaiMinSBMPTN.setText(mMinSekolah.get(0).getNilaiMin().toString());
                        txtNamaSiswaMinSBMPTN.setText(mMinSekolah.get(0).getNamaDepan()+" "+mMinSekolah.get(0).getNamaBelakang());

                        txtNamaPaketMinUNBK.setText("Tidak ada riwayat UNBK");
                        txtNilaiMinUNBK.setText("0");
                        txtNamaSiswaMinUNBK.setText("");
                    } else if (mMinSekolah.get(0).getNmPaket().equalsIgnoreCase("Paket UNBK")){
                        txtNamaPaketMinUNBK.setText(mMinSekolah.get(0).getNmPaket());
                        txtNilaiMinUNBK.setText(mMinSekolah.get(0).getNilaiMin().toString());
                        txtNamaSiswaMinUNBK.setText(mMinSekolah.get(0).getNamaDepan()+" "+mMinSekolah.get(1).getNamaBelakang());

                        txtNamaPaketMinSBMPTN.setText("Tidak ada riwayat SBMPTN");
                        txtNilaiMinSBMPTN.setText("0");
                        txtNamaSiswaMinSBMPTN.setText("");
                    }
                } else if (mMinSekolah.size() == 2){
                    txtNamaPaketMinSBMPTN.setText(mMinSekolah.get(0).getNmPaket());
                    txtNilaiMinSBMPTN.setText(mMinSekolah.get(0).getNilaiMin().toString());
                    txtNamaSiswaMinSBMPTN.setText(mMinSekolah.get(0).getNamaDepan()+" "+mMinSekolah.get(0).getNamaBelakang());

                    txtNamaPaketMinUNBK.setText(mMinSekolah.get(1).getNmPaket());
                    txtNilaiMinUNBK.setText(mMinSekolah.get(1).getNilaiMin().toString());
                    txtNamaSiswaMinUNBK.setText(mMinSekolah.get(1).getNamaDepan()+" "+mMinSekolah.get(1).getNamaBelakang());
                }


//                if (mMinSekolah.get(0).getNmPaket() == null || mMinSekolah.get(0).getNmPaket() == ""){
//
//                } else {
//
//                }

//                if (mMinSekolah.get(1).getNmPaket() == null || mMinSekolah.get(1).getNmPaket() == ""){
//
//                } else {
//
//                }


                //System.out.println("Siswa max sbmptn "+mMaxSekolah.get(0).getNamaDepan()+" "+mMaxSekolah.get(0).getNamaBelakang());
                avi.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<MinSekolahResponse> call, Throwable t) {
                //System.out.println("Siswa error!!!!");
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(rootview.getContext());

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
                        Toast.makeText(rootview.getContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
                txtText.setText("Tidak dapat terhubung ke server, harap periksa koneksi kamu");
            }

        });

//
    }

}
