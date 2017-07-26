package com.zhangqie.notepad;


import android.content.Intent;

import com.zhangqie.notepad.base.BaseActivity;
import com.zhangqie.notepad.adapter.NotePadAdapter;
import com.zhangqie.notepad.base.NotePadOnItemClickListener;
import com.zhangqie.notepad.entity.UserInfo;
import com.zhangqie.notepad.ui.activity.FunctionActivity;
import com.zhangqie.notepad.ui.widget.LinearLayoutForListView;
import com.zhangqie.notepad.util.HelperSQLite;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.listview)
    LinearLayoutForListView listview;

    HelperSQLite helperSQLite;
    List<UserInfo> list;

    @Override
    protected int setContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initBeforeData() {
        helperSQLite=new HelperSQLite(this);
        showQueryData();
    }

    private void showQueryData(){
        if (list!=null){
            list.clear();
        }
        list=helperSQLite.query();
        listview.setAdapter(new NotePadAdapter(this,list));
        listview.setOnItemClickListener(new NotePadOnItemClickListener(this,list));
    }


    @OnClick(R.id.add)
    public void onClick() {
       startActivityForResult(new Intent(this,FunctionActivity.class),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==2){
                showQueryData();
            }
        }
    }
}
