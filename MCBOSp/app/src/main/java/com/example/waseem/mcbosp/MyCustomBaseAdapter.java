package com.example.waseem.mcbosp;


import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * Created by Waseem on 28-04-2016.
 */
public class MyCustomBaseAdapter extends BaseAdapter {
    private static ArrayList<String[]> searchArrayList;
    private LayoutInflater mInflater;
    public MyCustomBaseAdapter(Context context, ArrayList<String[]> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
    }
    public int getCount() {
        return searchArrayList.size();
    }
    public Object getItem(int position) {
        return searchArrayList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.textView1);
            holder.txtCityState = (TextView) convertView
                    .findViewById(R.id.textView2);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.textView3);
            convertView.setTag(holder);
            holder.txtPhone1 = (TextView) convertView.findViewById(R.id.textView4);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String []s=searchArrayList.get(position);
        holder.txtName.setText(s[0]);
        holder.txtCityState.setText(s[1]);
        holder.txtPhone.setText(s[2]);
        holder.txtPhone1.setText(s[3]);
        return convertView;
    }
    static class ViewHolder {
        TextView txtName;
        TextView txtCityState;
        TextView txtPhone;
        TextView txtPhone1;
    }
}

