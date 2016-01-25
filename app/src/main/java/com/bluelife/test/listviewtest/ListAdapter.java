package com.bluelife.test.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by slomka.jin on 2016/1/25.
 */
public class ListAdapter extends BaseAdapter {
    private Context context;
    private List<String> items;

    public ListAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public void addData(String item){
        items.add(item);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, viewGroup, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        textView.setText(items.get(i));
        return rowView;
    }
}
