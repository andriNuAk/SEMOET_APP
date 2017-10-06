package vortex.semoet;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.transitionseverywhere.TransitionManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import helper.PenggunaHelper;
import helper.SessionHelper;

public class SplashScreenActivity extends Activity {
    private static int splashInterval = 3000;
    protected SessionHelper dbHelper;
    protected SQLiteDatabase db;
    ArrayList<PenggunaHelper> penggunaHelper;
    int id;
    String inNamaPengguna, inKataSandi, inEMail, inHakAkses, inStatus;
    RelativeLayout relSplash;
    CircleImageView imageView;
    Animation animFadeIn, animRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        relSplash = (RelativeLayout) findViewById(R.id.relSplash);
        imageView = (CircleImageView) findViewById(R.id.imageView);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        boolean visible = false;

        initPengguna();
//        TransitionManager.beginDelayedTransition(relSplash);
//        visible = !visible;
        imageView.setVisibility(View.VISIBLE);
        imageView.startAnimation(animFadeIn);
        if (penggunaHelper == null){
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);


                    //jeda selesai Splashscreen
                    this.finish();
                }

                private void finish() {
                    // TODO Auto-generated method stub

                }
            }, splashInterval);
        } else {
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(i);


                    //jeda selesai Splashscreen
                    this.finish();
                }

                private void finish() {
                    // TODO Auto-generated method stub

                }
            }, splashInterval);
        }
    }

//    private void startHeavyProcessing(){
//        new LongOperation().execute("");
//    }
//
//    private class LongOperation extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            for (int i = 0; i < 5; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.interrupted();
//                }
//            }
//            return "whatever result you have";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
//            i.putExtra("data", result);
//            startActivity(i);
//            finish();
//        }
//
//        @Override
//        protected void onPreExecute() {}
//
//        @Override
//        protected void onProgressUpdate(Void... values) {}
//    }

    public void initPengguna(){
//        for (int i=0 ; i < akunHelpers.size(); i++){
//            kodeKeluarga = akunHelpers.get(i).getKodeKeluarga();
//            noIdentitas = akunHelpers.get(i).getNoIdentitas();
//            username = akunHelpers.get(i).getUsername();
//            password = akunHelpers.get(i).getPassword();
//        }

        dbHelper = new SessionHelper(getApplicationContext());
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
