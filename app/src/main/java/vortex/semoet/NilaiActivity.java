package vortex.semoet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import adapter.ListNilaiAdapter;
import helper.ItemNilaiHelper;


public class NilaiActivity extends AppCompatActivity {

//    BarChart chart ;
//    ArrayList<BarEntry> BARENTRY ;
//    ArrayList<String> BarEntryLabels ;
//    BarDataSet Bardataset ;
//    BarData BARDATA ;
    TextView txtNamaPaket, txtNilai;
    String nilai, jmlBenar, jmlSalah, jmlKosong, jmlSoal, jenisPenilaian;
    int initNilai;
    String namaPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GridView gridNilai = (GridView) findViewById(R.id.gridNilai);
        txtNamaPaket = (TextView) findViewById(R.id.txtNamaPaket);
        txtNilai = (TextView) findViewById(R.id.txtNilai);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            jenisPenilaian = bundle.getString("JenisPenilaian");
            nilai = bundle.getString("Nilai");
            jmlBenar = bundle.getString("JmlBenar");
            jmlSalah = bundle.getString("JmlSalah");
            jmlKosong = bundle.getString("JmlKosong");
            jmlSoal = bundle.getString("JmlSoal");
            namaPaket = bundle.getString("NamaPaket");
        }

        txtNamaPaket.setText(namaPaket);
        if (jenisPenilaian.equalsIgnoreCase("SBMPTN") || jenisPenilaian.equalsIgnoreCase("Paket SBMPTN")){
            initNilai = ((Integer.valueOf(jmlBenar)*4 - Integer.valueOf(jmlSalah)*1)*100 / (Integer.valueOf(jmlSoal)*4));
        } else if (jenisPenilaian.equalsIgnoreCase("UNBK") || jenisPenilaian.equalsIgnoreCase("Paket UNBK")){
            initNilai = 100 * Integer.valueOf(jmlBenar) / Integer.valueOf(jmlSoal);
        }
        txtNilai.setText(String.valueOf(initNilai));
        List<ItemNilaiHelper> allItem = getAllItemObject();
        ListNilaiAdapter listNilaiAdapter = new ListNilaiAdapter(getApplicationContext(), allItem);
        gridNilai.setAdapter(listNilaiAdapter);
//        chart = (BarChart) findViewById(R.id.chart1);
//
//        BARENTRY = new ArrayList<>();
//
//        BarEntryLabels = new ArrayList<String>();
//
//        AddValuesToBARENTRY();
//
//        AddValuesToBarEntryLabels();
//
//        Bardataset = new BarDataSet(BARENTRY, "Projects");
//
//        BARDATA = new BarData(BarEntryLabels, Bardataset);
//        int color = getResources().getColor(R.color.colorPrimaryDark);
//        Bardataset.setColors(new int[] { R.color.colorPrimaryDark, R.color.grey_dark, R.color.colorAccent, R.color.grey_dark }, getApplicationContext());
//
//        chart.setData(BARDATA);
//
//        chart.animateY(3000);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

//    public void AddValuesToBARENTRY(){
//
//        BARENTRY.add(new BarEntry(0, 0));
//        BARENTRY.add(new BarEntry(60, 1));
//        BARENTRY.add(new BarEntry(0, 2));
//
//    }
//
//    public void AddValuesToBarEntryLabels(){
//
//        BarEntryLabels.add("Benar");
//        BarEntryLabels.add("Salah");
//        BarEntryLabels.add("Tidak menjawab");
//
//    }

    private List<ItemNilaiHelper> getAllItemObject(){


        List<ItemNilaiHelper> items = new ArrayList<>();
        items.add(new ItemNilaiHelper("Jawaban Benar",R.drawable.ic_circle_flat_smile,Integer.valueOf(jmlBenar),Integer.valueOf(jmlSoal)));
        items.add(new ItemNilaiHelper("Jawaban Salah",R.drawable.ic_circle_flat_bad,Integer.valueOf(jmlSalah),Integer.valueOf(jmlSoal)));
        items.add(new ItemNilaiHelper("Tidak Menjawab",R.drawable.ic_circle_flat_confuse,Integer.valueOf(jmlKosong),Integer.valueOf(jmlSoal)));
        return items;
    }


}
