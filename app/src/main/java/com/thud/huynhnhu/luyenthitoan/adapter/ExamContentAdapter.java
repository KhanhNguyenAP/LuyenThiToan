package com.thud.huynhnhu.luyenthitoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thud.huynhnhu.luyenthitoan.R;
import com.thud.huynhnhu.luyenthitoan.model.ExamContent;
import com.thud.huynhnhu.luyenthitoan.model.Example;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;

/**
 * Created by NhuLe on 5/24/2016.
 */
public class ExamContentAdapter extends ArrayAdapter<ExamContent> {
    private Context context;
    private ArrayList<ExamContent> values;

    public ExamContentAdapter(Context context, ArrayList<ExamContent> values){
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
        final MathView mathview = (MathView) rowView.findViewById(R.id.mathview);

        text_view_title.setText("Câu " + (position+1) + " : " + values.get(position).getQuestion());
        mathview.setText(values.get(position).getAnwser());

        return rowView;
    }
}
