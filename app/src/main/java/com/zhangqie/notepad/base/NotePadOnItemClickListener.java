package com.zhangqie.notepad.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.zhangqie.notepad.MainActivity;
import com.zhangqie.notepad.entity.UserInfo;
import com.zhangqie.notepad.ui.activity.FunctionActivity;

import java.util.List;

/**
 * Created by zhangqie on 2015/6/26.
 */

public class NotePadOnItemClickListener implements AdapterView.OnItemClickListener {

    private List<UserInfo> list;
    private MainActivity mainActivity;
    public NotePadOnItemClickListener(MainActivity mainActivity,List<UserInfo> list) {
        this.mainActivity=mainActivity;
        this.list=list;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserInfo userInfo=list.get(position);
        Intent intent=new Intent(mainActivity, FunctionActivity.class);
        intent.putExtra("id",userInfo.getId());
        intent.putExtra("year",userInfo.getUserYear());
        intent.putExtra("content",userInfo.getUserContent());
        intent.putExtra("time",userInfo.getUserTime());
        mainActivity.startActivityForResult(intent,1);
    }
}
