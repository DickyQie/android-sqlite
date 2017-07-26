package com.zhangqie.notepad.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhangqie.notepad.R;
import com.zhangqie.notepad.entity.UserInfo;

import java.util.List;

/**
 * Created by zhnagqie on 2015/6/26.
 */

public class NotePadAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<UserInfo> list;

    public NotePadAdapter(Context context,List<UserInfo> list){
        this.layoutInflater=LayoutInflater.from(context);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.notepad_item_layout,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        UserInfo userInfo=(UserInfo) getItem(position);
        viewHolder.tvNotepadYear.setText(userInfo.getUserYear());
        viewHolder.tvNoteoadContent.setText(userInfo.getUserContent());
        viewHolder.tvNoteoadTime.setText(userInfo.getUserTime());
        return convertView;
    }

    class ViewHolder{
        TextView tvNotepadYear;
        TextView tvNoteoadContent;
        TextView tvNoteoadTime;
        public ViewHolder(View view){
            tvNotepadYear=(TextView) view.findViewById(R.id.notepad_item_year);
            tvNoteoadContent=(TextView) view.findViewById(R.id.notepad_item_content);
            tvNoteoadTime=(TextView) view.findViewById(R.id.notepad_item_time);
        }
    }

}
