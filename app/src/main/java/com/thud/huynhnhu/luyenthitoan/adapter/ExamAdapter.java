package com.thud.huynhnhu.luyenthitoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.model.Exam;
import com.thud.huynhnhu.luyenthitoan.model.Topic;
import com.thud.huynhnhu.luyenthitoan.utils.interfaces.Flags;

import java.util.ArrayList;

/**
 * Created by nhu on 5/19/2016.
 */
public class ExamAdapter extends ArrayAdapter<Exam> {
    private Context context;
    private ArrayList<Exam> values;

    public ExamAdapter(Context context, ArrayList<Exam> values) {
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
        View rowView;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_text_view, parent, false);
        }
        else {
            rowView= convertView;
        }

        rowView.setTag(values.get(position).getId());
        final TextView text_view_title = (TextView) rowView.findViewById(R.id.text_view_title_default);
        if (Flags.chosen_exam == 0){
            text_view_title.setText(values.get(position).getQuestion());
        }
        else{
            text_view_title.setText(values.get(position).getAnswer());
        }
        return rowView;
    }
}
