package vortex.semoet;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.nishant.math.MathView;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import adapter.HasilKoreksiHelper;
import helper.SessionHelper;
//import io.github.kexanie.library.MathView;
import katex.hourglass.in.mathlib.MathView;
import model.PilihanJawaban;
import model.PilihanJawabanResponse;
import model.Soal;
import model.SoalResponse;
import restAPI.APIClient;
import restAPI.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembahasanActivity extends AppCompatActivity {

    int idPaket, soalHelper = 0;
    String hasilKoreksi, hasilKoreksiJaw, MY_URL, fileName, statSearch, jawabanA, jawabanB, jawabanC, jawabanD, jawabanE, soalMath;
    ArrayList<Soal> mSoal;
    ArrayList<PilihanJawaban> mPilihanJawaban;
    TextView txtNoSoal,  txtPembahasan, txtJawabanBenar, txtStatusJawaban, txtJawaban, txtSebelum, txtLanjut, txtStatusAudio, txtText, txtTextPilJaw, txtTextPembahasan;
    ImageButton btnLanjut, btnKembali, btnPlay, btnStop, btnDownload, btnPause;
    ImageView imgPembahasan;
    VideoView videoPembahasan;
    MediaPlayer mediaPlayer;
    RelativeLayout relLoading;
    //@BindView(R.id.txtJawabanMath)
//    MathView ;
    public static final int progress_bar_type = 0;
    private ProgressDialog pDialog;
    Typeface tfcAmaranthR;
    ScrollView scrollView;
    MathView txtSoal, txtJawabMathA, txtJawabMathB, txtJawabMathC, txtJawabMathD, txtJawabMathE;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembahasan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tfcAmaranthR = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Amaranth_Regular.ttf");
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        relLoading = (RelativeLayout) findViewById(R.id.relLoading);


        txtSoal = (MathView) findViewById(R.id.txtSoal);

        btnLanjut = (ImageButton) findViewById(R.id.btnLanjut);
        btnKembali = (ImageButton) findViewById(R.id.btnKembali);
        txtPembahasan = (TextView) findViewById(R.id.txtPembahasan);
        imgPembahasan = (ImageView) findViewById(R.id.imgPembahasan);
        videoPembahasan = (VideoView) findViewById(R.id.videoPembahasan);
        txtJawabanBenar = (TextView) findViewById(R.id.txtJawabanBenar);
        txtStatusJawaban = (TextView) findViewById(R.id.txtStatusJawaban);
        txtSebelum = (TextView) findViewById(R.id.txtSebelum);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnDownload = (ImageButton) findViewById(R.id.btnDownload);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
        txtStatusAudio = (TextView) findViewById(R.id.txtStatusAudio);


        txtText = (TextView) findViewById(R.id.txtText);

        txtNoSoal = (TextView) findViewById(R.id.txtNoSoal);
        txtTextPilJaw = (TextView) findViewById(R.id.txtText2);
        txtTextPembahasan = (TextView) findViewById(R.id.txtText3);
        txtJawaban = (TextView) findViewById(R.id.txtJawaban);
        txtLanjut = (TextView) findViewById(R.id.txtLanjut);

        txtText.setTypeface(tfcAmaranthR);
        txtNoSoal.setTypeface(tfcAmaranthR);
        txtTextPilJaw.setTypeface(tfcAmaranthR);
        txtTextPembahasan.setTypeface(tfcAmaranthR);
        txtLanjut.setTypeface(tfcAmaranthR);
        txtSebelum.setTypeface(tfcAmaranthR);
        txtStatusAudio.setTypeface(tfcAmaranthR);
        scrollView.smoothScrollTo(0,txtText.getTop());

//        mProgressDialog = new ProgressDialog(this);
//        mProgressDialog.setMessage("Memuat ...");
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        mProgressDialog.show();

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idPaket = Integer.valueOf(bundle.getString("IdPaket"));
            hasilKoreksi = bundle.getString("HasilKoreksi");
            soalHelper = bundle.getInt("Helper");
        }


        initSoal(soalHelper);

//

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void initSoal(final int noSoalHelper){
        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<SoalResponse> call = apiService.getSoal(idPaket);
        call.enqueue(new Callback<SoalResponse>() {
            @Override
            public void onResponse(Call<SoalResponse> call, Response<SoalResponse> response) {
                ArrayList<Soal> soal = (ArrayList<Soal>) response.body().getSoal();
                mSoal = soal;
                final int[] noSoal = {noSoalHelper};
                soalHelper = noSoal[0];
                final int[] no = {noSoal[0] + 1};
                final String[] pembahasanText = {mSoal.get(noSoal[0]).getPembahasan()};
                final String[] pembahasanImage = {mSoal.get(noSoal[0]).getGambarPembahasan()};
                final String[] pembahasanVideo = {mSoal.get(noSoal[0]).getVideoPembahasan()};


                //hasil koreksi
                Gson gson = new Gson();
                ArrayList<HasilKoreksiHelper> hasilKoreksiHelper = new ArrayList<HasilKoreksiHelper>();

                    hasilKoreksiHelper = new Gson().fromJson(hasilKoreksi, new TypeToken<List<HasilKoreksiHelper>>(){}.getType());

                if (noSoal[0] == 0){
                    btnKembali.setVisibility(View.GONE);
                    txtSebelum.setVisibility(View.GONE);
                } else if (noSoal[0] == (mSoal.size()-1)){
                    btnLanjut.setVisibility(View.GONE);
                    txtLanjut.setVisibility(View.GONE);
                }

                txtNoSoal.setText("No Soal = "+ no[0] +" / "+mSoal.size());
                initSoalMath(mSoal.get(noSoal[0]).getSoal());
                txtSoal.setDisplayText(soalMath);
                initJawaban(idPaket, mSoal.get(noSoal[0]).getSoalid());
                txtJawabanBenar.setText("Jawaban : "+mSoal.get(noSoal[0]).getJaw());
                hasilKoreksiJaw = String.valueOf(hasilKoreksiHelper.get(noSoal[0]).getStatus_koreksi());
                if (hasilKoreksiJaw.equalsIgnoreCase("1")){
                    txtStatusJawaban.setText("Jawaban kamu benar");
                    txtStatusJawaban.setTextColor(Color.parseColor("#00998a"));
                } else if (hasilKoreksiJaw.equalsIgnoreCase("2")){
                    txtStatusJawaban.setText("Jawaban kamu salah");
                    txtStatusJawaban.setTextColor(Color.parseColor("#FF830002"));
                } else {
                    txtStatusJawaban.setText("Kamu tidak menjawab");
                }

                if (pembahasanText[0] == null || pembahasanText[0] == ""){
                    if (pembahasanVideo[0] == null || pembahasanVideo[0] == ""){
                        if (pembahasanImage[0] == null || pembahasanImage[0] == ""){

                            txtPembahasan.setText("Tidak ada pembahasan");
                            imgPembahasan.setVisibility(View.GONE);
                            videoPembahasan.setVisibility(View.GONE);
                        } else {
                            imgPembahasan.setVisibility(View.VISIBLE);
                            Picasso.with(getApplicationContext()).load("http://soc.neonjogja.com/assets/image/pembahasan/"+ pembahasanImage[0]).into(imgPembahasan);
                            txtPembahasan.setVisibility(View.GONE);
                            videoPembahasan.setVisibility(View.GONE);
                        }
                    } else {
                        imgPembahasan.setVisibility(View.GONE);
                        txtPembahasan.setVisibility(View.GONE);
                        videoPembahasan.setVisibility(View.VISIBLE);
                        initVideo("http://soc.neonjogja.com/assets/video/"+pembahasanVideo[0]);
                    }
                } else {
                    txtPembahasan.setVisibility(View.VISIBLE);
                    txtPembahasan.setText(pembahasanText[0]);
                    imgPembahasan.setVisibility(View.GONE);
                    videoPembahasan.setVisibility(View.GONE);
                }
//                else if (pembahasanImage == null){
//                    Picasso.with(getApplicationContext()).load("http://soc.neonjogja.com/assets/image/pembahasan/"+pembahasanImage).into(imgPembahasan);
//                    txtPembahasan.setVisibility(View.GONE);
//                    videoPembahasan.setVisibility(View.GONE);
//                } else if (pembahasanVideo == null){
//
//                } else {
//                    txtPembahasan.setText("Gak masuk apa apa");
//                }
                final ArrayList<HasilKoreksiHelper> finalHasilKoreksiHelper = hasilKoreksiHelper;
                btnLanjut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollView.smoothScrollTo(0,txtText.getTop());
                        Intent intent = new Intent(getApplicationContext(), PembahasanActivity.class);
                        int helper = soalHelper + 1;
                        intent.putExtra("Helper", helper);
                        intent.putExtra("IdPaket", String.valueOf(idPaket));
                        intent.putExtra("HasilKoreksi", hasilKoreksi);
                        startActivity(intent);
//                        no[0] = no[0] +1;
//                        noSoal[0] = noSoal[0] +1;
//                        if (noSoal[0] == (mSoal.size()-1)){
//                            btnLanjut.setVisibility(View.GONE);
//                            txtLanjut.setVisibility(View.GONE);
//                        }
//                        btnKembali.setVisibility(View.VISIBLE);
//                        txtSebelum.setVisibility(View.VISIBLE);
//                        txtNoSoal.setText("No Soal = "+no[0]+" / "+mSoal.size());
//
//                        initSoalMath(mSoal.get(noSoal[0]).getSoal());
//
//                        txtSoal.setDisplayText(soalMath);
//
//                        //txtSoal.setText(mSoal.get(noSoal[0]).getSoal());
//                        initJawaban(idPaket, mSoal.get(noSoal[0]).getSoalid());
//                        txtJawabanBenar.setText("Jawaban : "+mSoal.get(noSoal[0]).getJaw());
//                        hasilKoreksi = String.valueOf(finalHasilKoreksiHelper.get(noSoal[0]).getStatus_koreksi());
//                        if (hasilKoreksi.equalsIgnoreCase("1")){
//                            txtStatusJawaban.setText("Jawaban kamu benar");
//                            txtStatusJawaban.setTextColor(Color.parseColor("#00998a"));
//                        } else if (hasilKoreksi.equalsIgnoreCase("2")){
//                            txtStatusJawaban.setText("Jawaban kamu salah");
//                            txtStatusJawaban.setTextColor(Color.parseColor("#FF830002"));
//                        }
//                        pembahasanText[0] = mSoal.get(noSoal[0]).getPembahasan();
//                        pembahasanImage[0] = mSoal.get(noSoal[0]).getGambarPembahasan();
//                        pembahasanVideo[0] = mSoal.get(noSoal[0]).getVideoPembahasan();
//                        if (pembahasanText[0] == null || pembahasanText[0] == ""){
//                            if (pembahasanVideo[0] == null || pembahasanVideo[0] == ""){
//                                if (pembahasanImage[0] == null || pembahasanImage[0] == ""){
//                                    txtPembahasan.setVisibility(View.VISIBLE);
//                                    txtPembahasan.setText("Tidak ada pembahasan");
//                                    imgPembahasan.setVisibility(View.GONE);
//                                    videoPembahasan.setVisibility(View.GONE);
//                                } else {
//                                    imgPembahasan.setVisibility(View.VISIBLE);
//                                    Picasso.with(getApplicationContext()).load("http://soc.neonjogja.com/assets/image/pembahasan/"+ pembahasanImage[0]).into(imgPembahasan);
//                                    txtPembahasan.setVisibility(View.GONE);
//                                    videoPembahasan.setVisibility(View.GONE);
//                                }
//                            } else {
//                                videoPembahasan.setVisibility(View.VISIBLE);
//                                imgPembahasan.setVisibility(View.GONE);
//                                txtPembahasan.setVisibility(View.GONE);
//                                initVideo("http://soc.neonjogja.com/assets/video/"+pembahasanVideo[0]);
//                            }
//                        } else {
//                            txtPembahasan.setVisibility(View.VISIBLE);
//                            txtPembahasan.setText(pembahasanText[0]);
//                            imgPembahasan.setVisibility(View.GONE);
//                            videoPembahasan.setVisibility(View.GONE);
//                        }
                    }
                });

                btnKembali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scrollView.smoothScrollTo(0,txtText.getTop());
                        Intent intent = new Intent(getApplicationContext(), PembahasanActivity.class);
                        int helper = soalHelper - 1;
                        intent.putExtra("Helper", helper);
                        intent.putExtra("IdPaket", String.valueOf(idPaket));
                        intent.putExtra("HasilKoreksi", hasilKoreksi);
                        startActivity(intent);
//                        no[0] = no[0] -1;
//                        noSoal[0] = noSoal[0] -1;
//                        if (noSoal[0] == 0){
//                            btnKembali.setVisibility(View.GONE);
//                            txtSebelum.setVisibility(View.GONE);
//                        }
//
//                        btnLanjut.setVisibility(View.VISIBLE);
//                        txtLanjut.setVisibility(View.VISIBLE);
//                        txtNoSoal.setText("No Soal = "+no[0]+" / "+mSoal.size());
//                        initSoalMath(mSoal.get(noSoal[0]).getSoal());
//                        txtSoal.setDisplayText(soalMath);
//                        //txtSoal.setText(mSoal.get(noSoal[0]).getSoal());
//                        initJawaban(idPaket, mSoal.get(noSoal[0]).getSoalid());
//                        txtJawabanBenar.setText("Jawaban : "+mSoal.get(noSoal[0]).getJaw());
//                        hasilKoreksi = String.valueOf(finalHasilKoreksiHelper.get(noSoal[0]).getStatus_koreksi());
//                        if (hasilKoreksi.equalsIgnoreCase("1")){
//                            txtStatusJawaban.setText("Jawaban kamu benar");
//                            txtStatusJawaban.setTextColor(Color.parseColor("#00998a"));
//                        } else if (hasilKoreksi.equalsIgnoreCase("2")){
//                            txtStatusJawaban.setText("Jawaban kamu salah");
//                            txtStatusJawaban.setTextColor(Color.parseColor("#FF830002"));
//                        }
//                        pembahasanText[0] = mSoal.get(noSoal[0]).getPembahasan();
//                        pembahasanImage[0] = mSoal.get(noSoal[0]).getGambarPembahasan();
//                        pembahasanVideo[0] = mSoal.get(noSoal[0]).getVideoPembahasan();
//                        if (pembahasanText[0] == null || pembahasanText[0] == ""){
//                            if (pembahasanVideo[0] == null || pembahasanVideo[0] == ""){
//                                if (pembahasanImage[0] == null || pembahasanImage[0] == ""){
//                                    txtPembahasan.setVisibility(View.VISIBLE);
//                                    txtPembahasan.setText("Tidak ada pembahasan");
//                                    imgPembahasan.setVisibility(View.GONE);
//                                    videoPembahasan.setVisibility(View.GONE);
//                                } else {
//                                    imgPembahasan.setVisibility(View.VISIBLE);
//                                    Picasso.with(getApplicationContext()).load("http://soc.neonjogja.com/assets/image/pembahasan/"+ pembahasanImage[0]).into(imgPembahasan);
//                                    txtPembahasan.setVisibility(View.GONE);
//                                    videoPembahasan.setVisibility(View.GONE);
//                                }
//                            } else {
//                                videoPembahasan.setVisibility(View.VISIBLE);
//                                imgPembahasan.setVisibility(View.GONE);
//                                txtPembahasan.setVisibility(View.GONE);
//                                initVideo("http://soc.neonjogja.com/assets/video/"+pembahasanVideo[0]);
//                            }
//                        } else {
//                            txtPembahasan.setVisibility(View.VISIBLE);
//                            txtPembahasan.setText(pembahasanText[0]);
//                            imgPembahasan.setVisibility(View.GONE);
//                            videoPembahasan.setVisibility(View.GONE);
//                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<SoalResponse> call, Throwable t) {

            }
        });

    }

    public void initJawaban(int idPaket, int idSoal){
        txtJawabMathA = (MathView) findViewById(R.id.txtJawabanMathA);
        //txtJawabMathA.config("MathJax.Hub.Config({"+"  CommonHTML: { linebreaks: { automatic: true } },"+"  \"HTML-CSS\": { linebreaks: { automatic: true } },"+"         SVG: { linebreaks: { automatic: true } }"+ "});");
        txtJawabMathB = (MathView) findViewById(R.id.txtJawabanMathB);
//        txtJawabMathB.config("MathJax.Hub.Config({"+"  CommonHTML: { linebreaks: { automatic: true } },"+"  \"HTML-CSS\": { linebreaks: { automatic: true } },"+"         SVG: { linebreaks: { automatic: true } }"+ "});");
        txtJawabMathC = (MathView) findViewById(R.id.txtJawabanMathC);
//        txtJawabMathC.config("MathJax.Hub.Config({"+"  CommonHTML: { linebreaks: { automatic: true } },"+"  \"HTML-CSS\": { linebreaks: { automatic: true } },"+"         SVG: { linebreaks: { automatic: true } }"+ "});");
        txtJawabMathD = (MathView) findViewById(R.id.txtJawabanMathD);
//        txtJawabMathD.config("MathJax.Hub.Config({"+"  CommonHTML: { linebreaks: { automatic: true } },"+"  \"HTML-CSS\": { linebreaks: { automatic: true } },"+"         SVG: { linebreaks: { automatic: true } }"+ "});");
        txtJawabMathE = (MathView) findViewById(R.id.txtJawabanMathE);
//        txtJawabMathE.config("MathJax.Hub.Config({"+"  CommonHTML: { linebreaks: { automatic: true } },"+"  \"HTML-CSS\": { linebreaks: { automatic: true } },"+"         SVG: { linebreaks: { automatic: true } }"+ "});");

        APIInterface apiService = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<PilihanJawabanResponse> call = apiService.getPilihanJawaban(idPaket, idSoal);
        call.enqueue(new Callback<PilihanJawabanResponse>() {
            @Override
            public void onResponse(Call<PilihanJawabanResponse> call, Response<PilihanJawabanResponse> response) {
                ArrayList<PilihanJawaban> pilihanJawaban = (ArrayList<PilihanJawaban>) response.body().getPilihanJawaban();
                mPilihanJawaban = pilihanJawaban;
                if (mPilihanJawaban.size() == 5){

                    String mediaURL = "http://soc.neonjogja.com/assets/audio/soal/"+mPilihanJawaban.get(0).getAudio();
//                    txtJawaban.setText(jawaban);
//                    txtJawaban.setText("$\\frac{{{\\left( 16 \\right)}^{^{\\frac{3}{4}}}}+\\ {{\\left( 81 \\right)}^{^{\\frac{3}{4}}}}}{{{\\left( 27 \\right)}^{^{\\frac{2}{3}}}}-{{\\left( 125 \\right)}^{^{\\frac{2}{3}}}}}=...$");
//                    txtJawaban.setVisibility(View.GONE);
//                    txtJawabMath.setText("$$\\frac{36}{16}$$");
//                    txtJawabMath.setText("$$\\frac{{{( 16)}^{\\frac{3}{4}}}+ {{( 81 )}^{^\\frac{3}{4}}}}{{{( 27)}^{\\frac{2}{3}}}-{{( 125 )}^{\\frac{2}{3}}}}=...$$ cobain");
                    String b = mPilihanJawaban.get(2).getJawaban().toString(); // value from API

                    String tampung = null;
                    String[] cobab = b.split("\\\\");
                    for (int i = 1; i < cobab.length; i++){
                        System.out.println("geluuttt "+cobab[i]);
                        if (i == 1){
                            tampung = "$$\\"+cobab[i];
                        } else {
                            tampung = tampung +"\\"+ cobab[i];
                        }
                    }
                    tampung = tampung + "$";
                    System.out.println("geluuuuttt gabung "+tampung);

                    String a = " $$\\sqrt{5}-\\sqrt{7}$$"; // value from JAVA
                    System.out.println("geluuuuttt gabung a "+a);

                    jawabanA(mPilihanJawaban.get(0).getJawaban());
                    jawabanB(mPilihanJawaban.get(1).getJawaban());
                    jawabanC(mPilihanJawaban.get(2).getJawaban());
                    jawabanD(mPilihanJawaban.get(3).getJawaban());
                    jawabanE(mPilihanJawaban.get(4).getJawaban());


                    System.out.println("Beeeeeeeeeeeeeeee "+b); // sout to show value from API, value from API and value from JAVA is same
                    String jawaban = mPilihanJawaban.get(0).getPilihan()+". "+jawabanA+'\n'+mPilihanJawaban.get(1).getPilihan()+". "+jawabanB+System.getProperty("line.separator")+mPilihanJawaban.get(2).getPilihan()+". "+jawabanC+System.getProperty("line.separator")+mPilihanJawaban.get(3).getPilihan()+". "+jawabanD+System.getProperty("line.separator")+mPilihanJawaban.get(4).getPilihan()+". "+jawabanE;
                    if (!b.equals("$$\\frac{36}{16}$$")){ // but variabel a and variabel b not same, but value variabel a and variabel b is same
                        txtJawaban.setVisibility(View.GONE);
                        txtJawabMathA.setDisplayText(mPilihanJawaban.get(0).getPilihan()+". "+jawabanA);// display to mathview, variabel b not showed to math, variabel a is showed to math
                        txtJawabMathB.setDisplayText(mPilihanJawaban.get(1).getPilihan()+". "+jawabanB);
                        txtJawabMathC.setDisplayText(mPilihanJawaban.get(2).getPilihan()+". "+jawabanC);
                        txtJawabMathD.setDisplayText(mPilihanJawaban.get(3).getPilihan()+". "+jawabanD);
                        txtJawabMathE.setDisplayText(mPilihanJawaban.get(4).getPilihan()+". "+jawabanE);
                    } else {
                        txtJawabMathA.setDisplayText("Same value");
                    }

                    if (mPilihanJawaban.get(0).getAudio() == null || mPilihanJawaban.get(0).getAudio() == ""){
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.GONE);
                        btnStop.setVisibility(View.GONE);
                        btnDownload.setVisibility(View.GONE);
                        txtStatusAudio.setText("Audio tersedia, silahkan download terlebih dahulu untuk memutar audio");
                    } else {
                        btnDownload.setVisibility(View.VISIBLE);
                    }

                    initAudio(mediaURL, mPilihanJawaban.get(0).getAudio());
                } else if (mPilihanJawaban.size() == 4){
                    String jawaban = mPilihanJawaban.get(0).getPilihan()+". "+mPilihanJawaban.get(0).getJawaban()+System.getProperty("line.separator")+mPilihanJawaban.get(1).getPilihan()+". "+mPilihanJawaban.get(1).getJawaban()+System.getProperty("line.separator")+mPilihanJawaban.get(2).getPilihan()+". "+mPilihanJawaban.get(2).getJawaban()+System.getProperty("line.separator")+mPilihanJawaban.get(3).getPilihan()+". "+mPilihanJawaban.get(3).getJawaban();
                    String mediaURL = "http://soc.neonjogja.com/assets/audio/soal/"+mPilihanJawaban.get(0).getAudio();
//                    //txtJawaban.setText(jawaban);
//                    txtJawabMath.setText("This come from string. You can insert inline formula:" +
//                            " \\(ax^2 + bx + c = 0\\) " +
//                            "or displayed formula: $$\\sum_{i=0}^n i^2 = \\frac{(n^2+n)(2n+1)}{6}$$");
                    //txtJawaban.setText(jawaban);

                    jawabanA(mPilihanJawaban.get(0).getJawaban());
                    jawabanB(mPilihanJawaban.get(1).getJawaban());
                    jawabanC(mPilihanJawaban.get(2).getJawaban());
                    jawabanD(mPilihanJawaban.get(3).getJawaban());

                    txtJawaban.setVisibility(View.GONE);
                    txtJawabMathA.setDisplayText(mPilihanJawaban.get(0).getPilihan()+". "+jawabanA);// display to mathview, variabel b not showed to math, variabel a is showed to math
                    txtJawabMathB.setDisplayText(mPilihanJawaban.get(1).getPilihan()+". "+jawabanB);
                    txtJawabMathC.setDisplayText(mPilihanJawaban.get(2).getPilihan()+". "+jawabanC);
                    txtJawabMathD.setDisplayText(mPilihanJawaban.get(3).getPilihan()+". "+jawabanD);

                    if (mPilihanJawaban.get(0).getAudio() == null || mPilihanJawaban.get(0).getAudio() == ""){
                        btnPause.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.GONE);
                        btnStop.setVisibility(View.GONE);
                        btnDownload.setVisibility(View.GONE);
                        txtStatusAudio.setText("Audio tersedia, silahkan download terlebih dahulu untuk memutar audio");
                    } else {
                        btnDownload.setVisibility(View.VISIBLE);
                    }
                    initAudio(mediaURL, mPilihanJawaban.get(0).getAudio());
                }

//                mProgressDialog.dismiss();
                relLoading.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<PilihanJawabanResponse> call, Throwable t) {

            }
        });
    }

    public void initAudio(final String url, final String namaFile){
        MY_URL = url;
        fileName = namaFile;
        final int[] stat = {0};
        final int[] length = {0};

        //String file = Environment.getExternalStorageDirectory().getPath()+"/"+namaFile;
        //System.out.println("File ablakadag "+file);

        String path =Environment.getExternalStorageDirectory().toString()+"/";
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: "+ file.length);
        for (int i=0; i < file.length; i++)
        {
            System.out.println("Files FileName:" + file[i].getName());
            if (file[i].getName().equalsIgnoreCase(namaFile)){
                statSearch = "found";
            } else {
                statSearch = "not found";
            }
        }

        System.out.println("Ketemu : "+statSearch);

        if (statSearch.equals("found")){
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.VISIBLE);
            btnStop.setVisibility(View.VISIBLE);
            btnStop.setEnabled(false);
            btnPause.setEnabled(false);
            btnDownload.setEnabled(false);
            btnDownload.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));
            txtStatusAudio.setText("Audio tersedia diperangkat kamu");

        } else if (statSearch.equals("not found")){
            btnDownload.setColorFilter(getApplicationContext().getResources().getColor(R.color.cardview_dark_background));
            if (namaFile == null || namaFile == ""){
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.GONE);
                btnStop.setVisibility(View.GONE);
                btnDownload.setVisibility(View.GONE);
                txtStatusAudio.setVisibility(View.GONE);
            } else {
                btnDownload.setVisibility(View.VISIBLE);
                btnDownload.setEnabled(true);
                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.GONE);
                btnStop.setVisibility(View.GONE);
                txtStatusAudio.setText("Audio tersedia, silahkan download terlebih dahulu untuk memutar audio");
            }

        }

        //download file
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new DownloadFileFromURL().execute(MY_URL);
                btnPause.setVisibility(View.VISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.VISIBLE);
                btnDownload.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimaryDark));
            }
        });

        //test audio


//        try {
//            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getPath()+"/"+namaFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mediaPlayer.setLooping(true);
                if (stat[0] == 0){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/"+namaFile));
                    mediaPlayer.start();
                    System.out.println("wewewe"+mediaPlayer);
                    stat[0] = 1;
                    btnStop.setEnabled(true);
                    btnPause.setEnabled(true);
                    btnPlay.setEnabled(false);
                    btnLanjut.setEnabled(false);
                    txtStatusAudio.setText("Audio diputar");
                } else if (stat[0] == 1){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/"+namaFile));
                    mediaPlayer.seekTo(length[0]);
                    mediaPlayer.start();
                    btnStop.setEnabled(true);
                    btnPause.setEnabled(true);
                    btnPlay.setEnabled(false);
                    btnLanjut.setEnabled(false);
                    txtStatusAudio.setText("Audio diputar");
                }



            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stat[0] = 0;
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    System.out.println("wewewe"+mediaPlayer);
                    btnStop.setEnabled(false);
                    btnPause.setEnabled(false);
                    btnPlay.setEnabled(true);
                    txtStatusAudio.setText("Audio berhenti");
                    btnLanjut.setEnabled(true);
                    //mediaPlayer = MediaPlayer.create(this, Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/"+namaFile));
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    length[0] =mediaPlayer.getCurrentPosition();
                    System.out.println("wewewe"+mediaPlayer);
                    btnStop.setEnabled(true);
                    btnPause.setEnabled(false);
                    btnPlay.setEnabled(true);
                    txtStatusAudio.setText("Audio dipause");
                    btnLanjut.setEnabled(false);
                    //mediaPlayer = MediaPlayer.create(this, Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/"+namaFile));
                }
            }
        });
//

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    public void downloadStreams() {
        try {
            URL url = new URL(MY_URL);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory()
                    + "/download/";
            Log.v("log_tag", "PATH: " + PATH);
            File file = new File(PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            File outputFile = new File(file, fileName);
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            Log.e("log_tag", "Error: " + e);
        }
        Log.v("log_tag", "Check: ");
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream("/sdcard/"
                        + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                //Toast.makeText(getApplicationContext(), "Oopss, file yang akan anda unduh tidal tersedia!!!", Toast.LENGTH_LONG).show();
//                confirmDialog();
                Log.e("Error wtf: ", e.getMessage());
                return "Error wtf";
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory()
                    .toString() + fileName;
            // setting downloaded into image view
            // my_image.setImageDrawable(Drawable.createFromPath(imagePath));
            //txtJawaban.setText("File downloaded and saved to directory==>"+imagePath);
            if(file_url != null && file_url.equals("Error wtf")){
                confirmDialog();
            }
        }

    }

    public void initVideo(String VideoURL){
//        pDialog = new ProgressDialog(PembahasanActivity.this);
//        // Set progressbar title
//        pDialog.setTitle("Android Video Streaming Tutorial");
//        // Set progressbar message
//        pDialog.setMessage("Buffering...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        // Show progressbar
//        pDialog.show();

//        try {
            // Start the MediaController
//            MediaController mediacontroller = new MediaController(
//                    PembahasanActivity.this);
//            mediacontroller.setAnchorView(videoPembahasan);
//            // Get the URL from String VideoURL
//            Uri video = Uri.parse(VideoURL);
//            videoPembahasan.setMediaController(mediacontroller);
//            videoPembahasan.setVideoURI(video);
//            videoPembahasan.start();
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//            e.printStackTrace();
//        }

//        videoPembahasan.requestFocus();
//        videoPembahasan.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            // Close the progress bar and play the video
//            public void onPrepared(MediaPlayer mp) {
//                pDialog.dismiss();
//                videoPembahasan.start();
//            }
//        });
        pDialog = new ProgressDialog(PembahasanActivity.this);
//        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
//        // Set progressbar message
        pDialog.setMessage("Please Wait...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        try {
            Uri uri = Uri.parse(VideoURL);
            // Start the MediaController
//            MediaController mediacontroller = new MediaController(
//                    PembahasanActivity.this);
//            mediacontroller.setAnchorView(videoPembahasan);
//            // Get the URL from String VideoURL
//            videoPembahasan.setMediaController(mediacontroller);
            videoPembahasan.setVideoURI(uri);

        } catch (Exception ex){

        }

        videoPembahasan.requestFocus();
        //videoPembahasan.setZOrderOnTop(true);
        videoPembahasan.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                mp.setLooping(true);
                videoPembahasan.start();
            }
        });

    }

//    private class StreamVideo extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Create a progressbar
//            pDialog = new ProgressDialog(PembahasanActivity.this);
//            // Set progressbar title
//            pDialog.setTitle("Android Video Streaming Tutorial");
//            // Set progressbar message
//            pDialog.setMessage("Buffering...");
//            pDialog.setIndeterminate(false);
//            // Show progressbar
//            pDialog.show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            // TODO Auto-generated method stub
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void args) {
//
//            try {
//                // Start the MediaController
//                MediaController mediacontroller = new MediaController(
//                        PembahasanActivity.this);
//                mediacontroller.setAnchorView(videoPembahasan);
//                // Get the URL from String VideoURL
//                Uri video = Uri.parse("http://www.androidbegin.com/tutorial/AndroidCommercial.3gp");
//                videoPembahasan.setMediaController(mediacontroller);
//                videoPembahasan.setVideoURI(video);
//
//                videoPembahasan.requestFocus();
//                videoPembahasan.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    // Close the progress bar and play the video
//                    public void onPrepared(MediaPlayer mp) {
//                        pDialog.dismiss();
//                        videoPembahasan.start();
//                    }
//                });
//            } catch (Exception e) {
//                pDialog.dismiss();
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    // Not using options menu for this tutorial
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu., menu);
//        return true;
//    }

    private void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PembahasanActivity.this);

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
                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        btnPause.setVisibility(View.GONE);
        btnPlay.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);
        btnDownload.setVisibility(View.VISIBLE);
        txtStatusAudio.setText("Audio tersedia, silahkan download terlebih dahulu untuk memutar audio");
        btnDownload.setColorFilter(getApplicationContext().getResources().getColor(R.color.cardview_dark_background));

        // Showing Alert Message
        alertDialog.show();
    }

    private void jawabanA(String jawaban){
        System.out.println("geluuuuuutt jawaban "+jawaban);
       // String tampungBackslash = jawaban.replace("\\\\", "\\");
        //String tampungStrip = jawaban.replace("$\\","\\(\\");
//        String tampung = jawaban.replace("$","$$");
//        int tampungDolar = tampungBackslash.indexOf("$-");
//        String tampung = null;
//        System.out.println("Geluuuutttt dolar : "+tampungDolar);
//        if (tampungDolar == 0){
//            String tampung1 = tampungBackslash.replaceAll("$-","$$");
//            tampung = tampung1.replaceAll("$","$$");
//        }
//        String tampung = null;
//        String[] cobab = jawaban.split("\\\\");
//        for (int i = 1; i < cobab.length; i++){
//            System.out.println("geluuttt "+cobab[i]);
//            if (i == 1){
//                tampung = "$$\\"+cobab[i];
//            } else {
//                tampung = tampung +"\\"+ cobab[i];
//            }
//        }
//        tampung = tampung + "$";



        String kalimat = new String();
        String[] kata = jawaban.split("\\$");
        if (jawaban.charAt(0) == '$') {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        } else {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        }
        System.out.println("Pecahan taeekkk A"+kalimat);
        System.out.println("geluuuuttt gabung "+kalimat);
        jawabanA = kalimat;

    }

    private void jawabanB(String jawaban){
//        String tampung = null;
//        String[] cobab = jawaban.split("\\\\");
//        for (int i = 1; i < cobab.length; i++){
//            System.out.println("geluuttt "+cobab[i]);
//            if (i == 1){
//                tampung = "$$\\"+cobab[i];
//            } else {
//                tampung = tampung +"\\"+ cobab[i];
//            }
//        }
//        tampung = tampung + "$";
//        System.out.println("geluuuuttt gabung "+tampung);
//        jawabanB = tampung;
        String kalimat = new String();
        String[] kata = jawaban.split("\\$");
        if (jawaban.charAt(0) == '$') {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        } else {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        }
        System.out.println("Pecahan taeekkk B "+kalimat);
        System.out.println("geluuuuttt gabung "+kalimat);
        jawabanB = kalimat;
    }

    private void jawabanC(String jawaban){
//        String tampung = null;
//        String[] cobab = jawaban.split("\\\\");
//        for (int i = 1; i < cobab.length; i++){
//            System.out.println("geluuttt "+cobab[i]);
//            if (i == 1){
//                tampung = "$$\\"+cobab[i];
//            } else {
//                tampung = tampung +"\\"+ cobab[i];
//            }
//        }
//        tampung = tampung + "$";
//        System.out.println("geluuuuttt gabung "+tampung);
//        jawabanC = tampung;
        String kalimat = new String();
        String[] kata = jawaban.split("\\$");
        if (jawaban.charAt(0) == '$') {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        } else {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        }
        System.out.println("Pecahan taeekkk C "+kalimat);
        System.out.println("geluuuuttt gabung "+kalimat);
        jawabanC = kalimat;
    }

    private void jawabanD(String jawaban){
//        String tampung = null;
//        String[] cobab = jawaban.split("\\\\");
//        for (int i = 1; i < cobab.length; i++){
//            System.out.println("geluuttt "+cobab[i]);
//            if (i == 1){
//                tampung = "$$\\"+cobab[i];
//            } else {
//                tampung = tampung +"\\"+ cobab[i];
//            }
//        }
//        tampung = tampung + "$";
//        System.out.println("geluuuuttt gabung "+tampung);
//        jawabanD = tampung;
        String kalimat = new String();
        String[] kata = jawaban.split("\\$");
        if (jawaban.charAt(0) == '$') {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        } else {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        }
        System.out.println("Pecahan taeekkk D "+kalimat);
        System.out.println("geluuuuttt gabung "+kalimat);
        jawabanD = kalimat;
    }

    private void jawabanE(String jawaban){
//        String tampung = null;
//        String[] cobab = jawaban.split("\\\\");
//        for (int i = 1; i < cobab.length; i++){
//            System.out.println("geluuttt "+cobab[i]);
//            if (i == 1){
//                tampung = "$$\\"+cobab[i];
//            } else {
//                tampung = tampung +"\\"+ cobab[i];
//            }
//        }
//        tampung = tampung + "$";
//        System.out.println("geluuuuttt gabung "+tampung);
//        jawabanE = tampung;
        String kalimat = new String();
        String[] kata = jawaban.split("\\$");
        if (jawaban.charAt(0) == '$') {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        } else {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        }
        System.out.println("Pecahan taeekkk E "+kalimat);
        System.out.println("geluuuuttt gabung "+kalimat);
        jawabanE = kalimat;
    }

    public void initSoalMath(String soals){
//        txtSoal = new MathView(getApplicationContext());
        txtSoal = (MathView) findViewById(R.id.txtSoal);
//        txtSoal.config("MathJax.Hub.Config({"+"  CommonHTML: { linebreaks: { automatic: true } },"+"  \"HTML-CSS\": { linebreaks: { automatic: true, height: auto } },"+"         SVG: { linebreaks: { automatic: true } }"+ "});");
        String kalimat = new String();
        String[] kata = soals.split("\\$");
        if (soals.charAt(0) == '$') {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        } else {
            for (int i = 0; i < kata.length; i++) {
                System.out.println("Pecahan ke "+i+" adalah "+kata[i]);
                if (i == 0) {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kata[i]+"\\(";
                        }

                    }
                } else {
                    if (i % 2 == 1) {
                        kalimat = kalimat + kata[i]+"\\)";
                    } else {
                        if (i == kata.length-1) {
                            kalimat = kalimat + kata[i]+"";
                        } else {
                            kalimat = kalimat + kata[i]+"\\(";
                        }

                    }
                }

            }
        }
        System.out.println("Pecahan taeekkk A"+kalimat);
        System.out.println("geluuuuttt gabung "+kalimat);
        soalMath = kalimat;
    }

}
