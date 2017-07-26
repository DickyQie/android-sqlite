package com.zhangqie.notepad.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhangqie.notepad.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqie on 2015/6/26..
 */

public class HelperSQLite extends SQLiteOpenHelper{

    private SQLiteDatabase sqLiteDatabase;

    /***
     * 创建数据库
     * @param context
     */
    public HelperSQLite(Context context){
        super(context, UtilDB.DATABASE_NAME, null, UtilDB.DATABASE_VERION);
        sqLiteDatabase=this.getWritableDatabase();
    }

    /***
     * 创建表
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UtilDB.showCreateSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /***
     * 添加数据
     * @param userContent
     * @param userYear
     * @param userTime
     * @return
     */
    public boolean insertDate(String userContent,String userYear,String userTime){

        //方式1  SQL语句的方式
      /*  String stu_sql="insert into "+UtilDB.DATABASE_TABLE+"("+UtilDB.USER_CONTENT+","+UtilDB.USER_YEAR+","+UtilDB.USER_TIME+") values('"+userContent+"','"+userYear+"','"+userTime+"')";
        sqLiteDatabase.execSQL(stu_sql);
        return true;*/
        //方式2
        ContentValues contentValues=new ContentValues();
        contentValues.put(UtilDB.USER_CONTENT,userContent);
        contentValues.put(UtilDB.USER_YEAR,userYear);
        contentValues.put(UtilDB.USER_TIME,userTime);
        return sqLiteDatabase.insert(UtilDB.DATABASE_TABLE,null,contentValues)>0;

    }

    /***
     * 删除数据
     * @param id
     * @return
     */
    public boolean deleteData(String id){


        /*String sql = "delete from "+UtilDB.DATABASE_TABLE+" where "+UtilDB.USER_ID+" = "+id;
        sqLiteDatabase.execSQL(sql);
        return true;*/

        String sql=UtilDB.USER_ID+"=?";
        String[] contentValuesArray=new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(UtilDB.DATABASE_TABLE,sql,contentValuesArray)>0;
    }

    /****
     * 修改数据
     * @param id
     * @param content
     * @param userYear
     * @param userTime
     * @return
     */
    public boolean updateData(String id,String content,String userYear,String userTime){
      /*  String sql = "update "+UtilDB.DATABASE_TABLE+" set "+UtilDB.USER_CONTENT+" = '"+content+"',"
                +UtilDB.USER_YEAR+" = '"+userYear+"',"+UtilDB.USER_TIME+"='"+userTime+"' where "+UtilDB.USER_ID+" = "+id;
        sqLiteDatabase.execSQL(sql);
        return true;*/
        ContentValues contentValues=new ContentValues();
        contentValues.put(UtilDB.USER_CONTENT,content);
        contentValues.put(UtilDB.USER_YEAR,userYear);
        contentValues.put(UtilDB.USER_TIME,userTime);
        String sql=UtilDB.USER_ID+"=?";
        String[] strings=new String[]{id};
        return sqLiteDatabase.update(UtilDB.DATABASE_TABLE,contentValues,sql,strings)>0;
    }

    /***
     * 查询数据
     * @return
     */
    public List<UserInfo> query(){
        List<UserInfo> list=new ArrayList<UserInfo>();
        Cursor cursor=sqLiteDatabase.query(UtilDB.DATABASE_TABLE,null,null,null,null,null,UtilDB.USER_ID+" desc");
        if (cursor!=null){
            while (cursor.moveToNext()){
                UserInfo userInfo=new UserInfo();
                userInfo.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(UtilDB.USER_ID))));
                userInfo.setUserContent(cursor.getString(cursor.getColumnIndex(UtilDB.USER_CONTENT)));
                userInfo.setUserYear(cursor.getString(cursor.getColumnIndex(UtilDB.USER_YEAR)));
                userInfo.setUserTime(cursor.getString(cursor.getColumnIndex(UtilDB.USER_TIME)));
                list.add(userInfo);
            }
        }
        return list;
    }




}
