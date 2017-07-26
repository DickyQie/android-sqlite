package com.zhangqie.notepad.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangqie on 2015/6/26.
 */

public class UtilDB {

    public static final String DATABASE_NAME="User";//库名

    public static final String DATABASE_TABLE="UserInfo";//表单名

    public static final int DATABASE_VERION=1;//版本

    //字段
    public static final String USER_ID="id";

    public static final String USER_CONTENT="content";

    public static final String USER_YEAR="useryear";

    public static final String USER_TIME="usertime";

    public static final String showCreateSql(){
        return "create table "+DATABASE_TABLE+"("+USER_ID+" integer primary key autoincrement,"
                +USER_CONTENT+ " text," + USER_YEAR+ " text,"+USER_TIME+" text)";
    }

    /***
     * 获取当前日历
     * @return
     */
    public static final String shoTimeYear(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(new Date());
    }


    /***
     * 获取当前时间
     * @return
     */
    public static final String shoTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ahh:mm");
        return simpleDateFormat.format(new Date());
    }

}
