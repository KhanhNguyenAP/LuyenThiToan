package com.thud.huynhnhu.luyenthitoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.model.Content;
import com.thud.huynhnhu.luyenthitoan.model.Topic;

import java.util.ArrayList;

/**
 * Created by KhanhNguyen on 5/19/2016.
 */
public class ContentAdapter extends ArrayAdapter<Content> {
    private Context context;
    private ArrayList<Content> values;

    public ContentAdapter(Context context, ArrayList<Content> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public void clear(){
        this.values = null;
        notifyDataSetChanged();
        super.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_show_detail, parent, false);
        rowView.setTag(values.get(position).getId());

        final TextView text_view_title = (TextView) rowView.findViewById(R.id.text_view_title);
        final TextView text_view_content = (TextView) rowView.findViewById(R.id.text_view_content);

        text_view_title.setText(values.get(position).getName());
        text_view_content.setText(values.get(position).getContent());

        return rowView;
    }
}
