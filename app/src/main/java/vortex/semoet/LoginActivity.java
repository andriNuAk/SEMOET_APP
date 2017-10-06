package vortex.semoet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import helper.PenggunaHelper;
import helper.SessionHelper;
import model.DataPengguna;
import model.DataPenggunaResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    Button btnLogin;
    EditText txtUsername, txtPassword;
    TextView txtStatus;
    Typeface tfcAmaranthR;
    String strPassword, md5Password, strUsername;
    ArrayList<DataPengguna> mDataPengguna;
    ArrayList<PenggunaHelper> mPenggunaHelpers;
    int id;
    String inNamaPengguna, inKataSandi, inStatus, inHakAkses, inEMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        tfcAmaranthR = Typeface.createFromAsset(getAssets(), "fonts/Amaranth_Regular.ttf");
        btnLogin.setTypeface(tfcAmaranthR);
        txtUsername.setTypeface(tfcAmaranthR);
        txtPassword.setTypeface(tfcAmaranthR);
        txtStatus.setTypeface(tfcAmaranthR);
        txtStatus.setText("");

        txtUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0 ){
                    txtStatus.setText("");
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUsername = txtUsername.getText().toString();
                strPassword = txtPassword.getText().toString();
                md5Password = md5(strPassword);
                APIInterface apiService = APIClient.getURL().create(APIInterface.class);
                retrofit2.Call<DataPenggunaResponse> call = apiService.getPengguna(md5Password, strUsername, strUsername);
                //Toast.makeText(getApplicationContext(), "Pengguna + "+strUsername+", Password = "+md5Password, Toast.LENGTH_LONG).show();
                call.enqueue(new Callback<DataPenggunaResponse>() {
                    @Override
                    public void onResponse(Call<DataPenggunaResponse> call, Response<DataPenggunaResponse> response) {
                        ArrayList<DataPengguna> dataPengguna = (ArrayList<DataPengguna>) response.body().getDataPengguna();
                        mDataPengguna = dataPengguna;
                        if (dataPengguna != null){
                            mDataPengguna = dataPengguna;
                            for (int i = 0; i < mDataPengguna.size(); i++) {
                                id = mDataPengguna.get(i).getId();
                                inNamaPengguna = mDataPengguna.get(i).getNamaPengguna();
                                inKataSandi = mDataPengguna.get(i).getKataSandi();
                                inHakAkses = mDataPengguna.get(i).getHakAkses();
                                inEMail = mDataPengguna.get(i).getEMail();
                                inStatus = mDataPengguna.get(i).getStatus();
                            }
                            Toast.makeText(getApplicationContext(), "Login sebagai : "+mDataPengguna.get(0).getNamaPengguna(), Toast.LENGTH_LONG).show();
                            SessionHelper dbHelper =  new SessionHelper(LoginActivity.this);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            dbHelper.createTabelPengguna(db);
                            dbHelper.insertAkun(db, id, inNamaPengguna, inKataSandi, inEMail, inHakAkses, inStatus);
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            //intent.putExtra("NIK", NIK);
                            startActivity(intent);

                            //statusText.setText("Username dan password benar, username = "+username+" password = "+password+" No keluarga = "+kodeKeluarga+" NIK = "+NIK);

                        }
                    }

                    @Override
                    public void onFailure(Call<DataPenggunaResponse> call, Throwable t) {
                        txtStatus.setText("Ups kamu gagal login, coba periksa username dan password kamu");

                    }
                });
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
            }
        });
    }


    private static String md5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(s.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);
            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}
