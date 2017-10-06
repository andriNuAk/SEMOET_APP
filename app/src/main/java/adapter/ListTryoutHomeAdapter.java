package adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import model.TryoutHome;
import vortex.semoet.R;

/**
 * Created by M on 9/12/2017.
 */

public class ListTryoutHomeAdapter extends BaseAdapter {

    Context context;
    ArrayList<TryoutHome> tryoutHome;
    LayoutInflater inflater;

    public ListTryoutHomeAdapter(Context context, ArrayList<TryoutHome> tryoutHome) {
        this.context = context;
        this.tryoutHome = tryoutHome;
    }

    @Override
    public int getCount() {
        return tryoutHome.size();
    }

    @Override
    public Object getItem(int position) {
        return tryoutHome.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.card_tryout_home, parent, false);
        }

        TextView txtNamaTryout = (TextView) convertView.findViewById(R.id.txtNamaTryout);
        TextView txtNamaPaket = (TextView) convertView.findViewById(R.id.txtNamaPaket);
        Typeface tfcAmaranthR;
        tfcAmaranthR = Typeface.createFromAsset(convertView.getContext().getAssets(), "fonts/Amaranth_Regular.ttf");
        txtNamaTryout.setTypeface(tfcAmaranthR);
        txtNamaPaket.setTypeface(tfcAmaranthR);

        txtNamaTryout.setText("Nama Tryout : "+tryoutHome.get(position).getNmTryout());
        txtNamaPaket.setText("Nama Paket : "+tryoutHome.get(position).getNmPaket());


        return convertView;
    }
}
