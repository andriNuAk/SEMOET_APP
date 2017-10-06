package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import helper.ItemNilaiHelper;
import vortex.semoet.R;

/**
 * Created by M on 9/15/2017.
 */

public class ListNilaiAdapter extends BaseAdapter {

    Context context;
    List<ItemNilaiHelper> itemNilaiHelper;
    LayoutInflater inflater;

    public ListNilaiAdapter(Context context, List<ItemNilaiHelper> itemNilaiHelper) {
        this.context = context;
        this.itemNilaiHelper = itemNilaiHelper;
    }

    @Override
    public int getCount() {
        return itemNilaiHelper.size();
    }

    @Override
    public Object getItem(int position) {
        return itemNilaiHelper.get(position);
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
            convertView = inflater.inflate(R.layout.card_nilai, parent, false);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
        CircleImageView imgIcon = (CircleImageView) convertView.findViewById(R.id.imgIcon);
        TextView txtValueNilai = (TextView) convertView.findViewById(R.id.txtValueSoal);
        TextView txtJumlahSoal = (TextView) convertView.findViewById(R.id.txtJumlahSoal);

        txtTitle.setText(itemNilaiHelper.get(position).getTitle());
        txtValueNilai.setText(String.valueOf(itemNilaiHelper.get(position).getValueNilai()));
        txtJumlahSoal.setText(String.valueOf("Dari "+itemNilaiHelper.get(position).getJumlahSoal())+" soal");
        imgIcon.setImageResource(itemNilaiHelper.get(position).getIcon());

        return convertView;
    }
}
