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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import adapter.ListTryoutHomeAdapter;
import helper.PenggunaHelper;
import helper.SessionHelper;
import model.DataPenggunaResponse;
import model.NilaiMax;
import model.NilaiMaxResponse;
import model.NilaiMin;
import model.NilaiMinResponse;
import model.TryoutHome;
import model.TryoutHomeResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.wang.avi.R.attr.indicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView txtNamaPengguna, txtNilaiMax, txtNilaiMin, txtText, txtStatusTryout;
    Typeface tfcAmaranthR;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<PenggunaHelper> penggunaHelper;
    int id;
    String inNamaPengguna, inKataSandi, inEMail, inHakAkses, inStatus;
    ArrayList<NilaiMax> mNilaiMax;
    ArrayList<NilaiMin> mNilaiMin;
    ExpandableHeightListView listTryout;
    ListTryoutHomeAdapter listTryoutHomeAdapter;
    ArrayList<TryoutHome> mTryoutHome;
    View rootView;
    ProgressDialog mProgressDialog;
    private AVLoadingIndicatorView avi;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        txtNamaPengguna = (TextView) rootView.findViewById(R.id.txtNamaPengguna);
        txtNilaiMax = (TextView) rootView.findViewById(R.id.txtNilaiMax);
        txtNilaiMin = (TextView) rootView.findViewById(R.id.txtNilaiMin);
        txtText = (TextView) rootView.findViewById(R.id.txtText);
        txtStatusTryout = (TextView) rootView.findViewById(R.id.txtStatusTryout);
        listTryout = (ExpandableHeightListView) rootView.findViewById(R.id.listTryout);
        tfcAmaranthR = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Amaranth_Regular.ttf");
        avi= (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        avi.setVisibility(View.VISIBLE);


        txtNamaPengguna.setTypeface(tfcAmaranthR);
        txtNilaiMax.setTypeface(tfcAmaranthR);
        txtNilaiMin.setTypeface(tfcAmaranthR);
        txtText.setTypeface(tfcAmaranthR);
        txtStatusTryout.setTypeface(tfcAmaranthR);

        mProgressDialog = new ProgressDialog(this.getContext());
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();


        initPengguna();
        txtNamaPengguna.setText(inNamaPengguna);

        initNilaiMax();
        initNilaiMin();

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<TryoutHomeResponse> call = apiService.getTryoutHome(id);


        call.enqueue(new Callback<TryoutHomeResponse>() {

            @Override
            public void onResponse(Call<TryoutHomeResponse> call, Response<TryoutHomeResponse> response) {
                txtText.setText("Ini adalah tryout yang pernah kamu ikuti");
                ArrayList<TryoutHome> tryoutHome = (ArrayList<TryoutHome>) response.body().getTryoutHome();
                mTryoutHome = tryoutHome;
                if (mTryoutHome != null){
                    listTryoutHomeAdapter = new ListTryoutHomeAdapter(rootView.getContext(), mTryoutHome);
                    listTryout.setAdapter(listTryoutHomeAdapter);
                    listTryout.setExpanded(true);
                } else {
                    txtText.setText("Ups kamu belum punya riwayat nilai !!!!");
                }


        }

            @Override
            public void onFailure(Call<TryoutHomeResponse> call, Throwable t) {
//                mProgressDialog.dismiss();
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


        mProgressDialog.dismiss();
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

    public void initNilaiMax(){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<NilaiMaxResponse> call = apiService.getNilaiMax(id);

        call.enqueue(new Callback<NilaiMaxResponse>() {
            @Override
            public void onResponse(Call<NilaiMaxResponse> call, Response<NilaiMaxResponse> response) {
                txtText.setText("Ini adalah tryout yang pernah kamu ikuti");
                ArrayList<NilaiMax> nilaiMax = (ArrayList<NilaiMax>) response.body().getNilaiMax();
                mNilaiMax = nilaiMax;

                if (mNilaiMax.get(0).getTerbesar() != null){
                    String nilai = mNilaiMax.get(0).getTerbesar().toString();
                    txtNilaiMax.setText("Nilai tertinggi kamu "+nilai+" ("+mNilaiMax.get(0).getNamaPaket()+")");
                } else {
                    txtNilaiMax.setText("Ups kamu belum punya riwayat nilai");
                }

            }

            @Override
            public void onFailure(Call<NilaiMaxResponse> call, Throwable t) {
//                mProgressDialog.dismiss();
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
                txtNilaiMax.setText("Ups kamu belum punya riwayat nilai");
            }
        });
    }

    public void initNilaiMin(){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<NilaiMinResponse> call = apiService.getNilaiMin(id);
        call.enqueue(new Callback<NilaiMinResponse>() {
            @Override
            public void onResponse(Call<NilaiMinResponse> call, Response<NilaiMinResponse> response) {
                txtText.setText("Ini adalah tryout yang pernah kamu ikuti");
                ArrayList<NilaiMin> nilaiMin = (ArrayList<NilaiMin>) response.body().getNilaiMin();
                mNilaiMin = nilaiMin;

                if (mNilaiMin.get(0).getTerkecil() != null){
                    String nilai = mNilaiMin.get(0).getTerkecil().toString();
                    txtNilaiMin.setText("Nilai terendah kamu "+nilai+" ("+mNilaiMin.get(0).getNamaPaket()+")");
                } else {
                    txtNilaiMin.setText("Ups kamu belum punya riwayat nilai");
                }
                avi.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NilaiMinResponse> call, Throwable t) {
//                mProgressDialog.dismiss();
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
                txtNilaiMin.setText("Ups kamu belum punya riwayat nilai");
            }
        });
    }

    public void initTryout(){

    }





}
