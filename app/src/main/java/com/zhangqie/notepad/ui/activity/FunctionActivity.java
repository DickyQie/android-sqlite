package com.zhangqie.notepad.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangqie.notepad.R;
import com.zhangqie.notepad.base.BaseActivity;
import com.zhangqie.notepad.util.HelperSQLite;
import com.zhangqie.notepad.util.UtilDB;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zhangqie on 2015/6/26.
 */

public class FunctionActivity extends BaseActivity {

    @Bind(R.id.notepad_close)
    ImageView notepadClose;
    @Bind(R.id.notepad_name)
    TextView notepadName;
    @Bind(R.id.notepad_content)
    EditText notepadContent;

    String id;
    HelperSQLite helperSQLite;

    @Override
    protected int setContentViewLayout() {
        return R.layout.function_layout;
    }

    @Override
    protected void initBeforeData() {
        notepadClose.setVisibility(View.VISIBLE);
        Intent intent=getIntent();
        if(intent!= null){
            id=intent.getStringExtra("id");
            if (id!=null){
                notepadName.setText(intent.getStringExtra("year")+intent.getStringExtra("time"));
                notepadContent.setText(intent.getStringExtra("content"));
            }else {
                notepadName.setText("添加语录");
            }
        }else {
            notepadName.setText("添加语录");
        }
        helperSQLite=new HelperSQLite(this);
    }


    @OnClick({R.id.notepad_close, R.id.delete, R.id.note_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notepad_close:
                finish();
                break;
            case R.id.delete:
                if (id!=null){
                    if (helperSQLite.deleteData(id)){
                        showToastShort("删除成功!");
                        setResult(2);
                        finish();
                    }else {
                        showToastShort("删除失败!");
                    }
                }else {
                    notepadContent.setText("");
                }
                break;
            case R.id.note_add:
                String noteContent=notepadContent.getText().toString().trim();
                if (id!=null){//修改操作
                    if (noteContent.length()>0){
                         if (helperSQLite.updateData(id,noteContent, UtilDB.shoTimeYear(),UtilDB.shoTime())){
                             showToastShort("修改成功!");
                             setResult(2);
                             finish();
                         }else {
                             showToastShort("修改失败!");
                         }
                    }else {
                        showToastShort("修改内容不能为空!");
                    }
                }else {//添加
                    if (noteContent.length()>0){

                        if (helperSQLite.insertDate(noteContent, UtilDB.shoTimeYear(),UtilDB.shoTime())){
                            showToastShort("添加成功!");
                            setResult(2);
                            finish();
                        }else {
                            showToastShort("添加失败!");
                        }
                    }else {
                        showToastShort("添加内容不能为空!");
                    }
                }
                break;
        }
    }
}
