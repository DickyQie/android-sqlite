# Android我的便签-----SQLite的使用方法 
  <p>在Android开发中也有数据库的存在，最近有空，把以前写的一个便签来讲述一下Android中的数据库，跟大家分享分享的，希望对大家有所帮助。</p> 
<span id="OSC_h2_1"></span>
<h2>SQLite简介</h2> 
<p>SQLite，是一款轻量级的关系型数据库。由于它占用的资源非常少，所以在很多嵌入式设备都是用SQLite来存储数据。</p> 
<p>&nbsp;</p> 
<p>SQLite数据库操作和常用的数据库操作差不多；如：MySQL......； 增删改查等语句操作基本相同； 今天给大家<span style="color:#008000"><strong>Android SQLite语句相关操作的两种方式</strong></span></p> 
<p>&nbsp;</p> 
<p>首先来看一下我的便签的效果图：（图中操作顺序：查询，添加，修改，删除）</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <img alt="" src="https://static.oschina.net/uploads/img/201707/26172746_lFEy.gif"></p> 
<p>1：创建数据库和表，创建一个类；如下：</p> 
<pre><code class="language-java">public class HelperSQLite extends SQLiteOpenHelper{

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
        //用于更新
    }
}</code></pre> 
<p>2：介绍添加数据的两种方法</p> 
<pre><code class="language-java">     //方式1  SQL语句的方式
        String stu_sql="insert into "+UtilDB.DATABASE_TABLE+"("+UtilDB.USER_CONTENT+","+UtilDB.USER_YEAR+","+UtilDB.USER_TIME+") values('"+userContent+"','"+userYear+"','"+userTime+"')";
        sqLiteDatabase.execSQL(stu_sql);

        //方式2
        ContentValues contentValues=new ContentValues();
        contentValues.put(UtilDB.USER_CONTENT,userContent);
        contentValues.put(UtilDB.USER_YEAR,userYear);
        contentValues.put(UtilDB.USER_TIME,userTime);
        return sqLiteDatabase.insert(UtilDB.DATABASE_TABLE,null,contentValues)&gt;0;//成功返回true</code></pre> 
<p>3：删除的两种方式</p> 
<pre><code class="language-java"> String sql = "delete from "+UtilDB.DATABASE_TABLE+" where "+UtilDB.USER_ID+" = "+id;
        sqLiteDatabase.execSQL(sql);


        String sql=UtilDB.USER_ID+"=?";
        String[] contentValuesArray=new String[]{String.valueOf(id)};
        return sqLiteDatabase.delete(UtilDB.DATABASE_TABLE,sql,contentValuesArray)&gt;0;//成功返回true</code></pre> 
<p>4：修改的两种方式</p> 
<pre><code class="language-java">/方式1 
        String sql = "update "+UtilDB.DATABASE_TABLE+" set "+UtilDB.USER_CONTENT+" = '"+content+"',"
                +UtilDB.USER_YEAR+" = '"+userYear+"',"+UtilDB.USER_TIME+"='"+userTime+"' where "+UtilDB.USER_ID+" = "+id;
        sqLiteDatabase.execSQL(sql);

        //方式2
        ContentValues contentValues=new ContentValues();
        contentValues.put(UtilDB.USER_CONTENT,content);
        contentValues.put(UtilDB.USER_YEAR,userYear);
        contentValues.put(UtilDB.USER_TIME,userTime);
        String sql=UtilDB.USER_ID+"=?";
        String[] strings=new String[]{id};
        return sqLiteDatabase.update(UtilDB.DATABASE_TABLE,contentValues,sql,strings)&gt;0;  //成功返回true</code></pre> 
<p><span style="color:#000000">5：查询数据&nbsp; 便签中&nbsp; 使用的是降序排列的</span></p> 
<pre><code class="language-java">  Cursor cursor=sqLiteDatabase.query(UtilDB.DATABASE_TABLE,null,null,null,null,null,UtilDB.USER_ID+" desc");//条件查询降序排序   </code></pre> 
<p><span style="color:#008000">方式1</span></p> 
<pre><code class="language-java">List&lt;UserInfo&gt; list=new ArrayList&lt;UserInfo&gt;();
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
        }</code></pre> 
<p><span style="color:#008000">方式2：通过游标得到数据</span></p> 
<pre><code class="language-java">List&lt;UserInfo&gt; list=new ArrayList&lt;UserInfo&gt;();
        Cursor cursor=sqLiteDatabase.query(UtilDB.DATABASE_TABLE,null,null,null,null,null,UtilDB.USER_ID+" desc");
        if (cursor!=null){
            while (cursor.moveToNext()){//通过游标得到数据
                UserInfo userInfo=new UserInfo();
                userInfo.setId(String.valueOf(cursor.getInt(0)));
                userInfo.setUserContent(cursor.getString(1));
                userInfo.setUserYear(cursor.getString(2));
                userInfo.setUserTime(cursor.getString(3));
                list.add(userInfo);
            }
        }</code></pre> 
<p><span style="color:#000000">由于代码太多，就不一一贴出来了，直接下载即可，&nbsp;</span></p> 

