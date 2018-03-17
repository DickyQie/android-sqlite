## Android我的便签-----SQLite的使用方法 
  <p>在Android开发中也有数据库的存在，最近有空，把以前写的一个便签来讲述一下Android中的数据库，跟大家分享分享的，希望对大家有所帮助。</p> 
<span id="OSC_h2_1"></span>
<h2>SQLite简介</h2> 
<p>SQLite，是一款轻量级的关系型数据库。由于它占用的资源非常少，所以在很多嵌入式设备都是用SQLite来存储数据。</p> 
<p>&nbsp;</p> 
<p>SQLite数据库操作和常用的数据库操作差不多；如：MySQL......； 增删改查等语句操作基本相同； 今天给大家<span style="color:#008000"><strong>Android SQLite语句相关操作的两种方式</strong></span></p> 
<p>&nbsp;</p> 
<p>首先来看一下我的便签的效果图：（图中操作顺序：查询，添加，修改，删除）</p> 
<p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <img alt="" src="https://static.oschina.net/uploads/img/201707/26172746_lFEy.gif"></p> 


