<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.ConnD" import="java.sql.*" import="com.bean.*"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script src="../js/jquery.js"></script>
<script src="../js/pintuer.js"></script>
</head>
<body>
<%
ConnD con=new ConnD();
String name="";
String pic="";
String clazz="";
String disc="";
String pub="";
Date pub_data=null;
String editor="";
float price=0;
int count=0;
con.getConnection();
String id=request.getParameter("id");
String sql="select * from book where id="+id;
ResultSet res1=con.cx(sql);
while(res1.next()){
  name=res1.getString(2);
  pic=res1.getString(3);
  clazz=res1.getString(4);
  disc=res1.getString(5);
  pub=res1.getString(6);
  pub_data=res1.getDate(7);
  editor=res1.getString(8);
  price=res1.getFloat(9);
  count=res1.getInt(10);
}
con.close();
%>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/webwork/updateservlet"> 
      <div class="form-group">
        <div class="label">
          <label>编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value=<%=id%> name="id" data-validate="required:请输入编号" readonly/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>书名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value=<%=name%> name="name" data-validate="required:请输入书名" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>图片：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="pic" class="input tips" style="width:25%; float:left;"  value=<%=pic%>  data-toggle="hover" data-place="right" readonly/>
        </div>
      </div> 
      <if condition="$iscid eq 1">
        <div class="form-group">
          <div class="label">
            <label>类别：</label>
          </div>
          <div class="field">
            <select name="clazz" class="input w50">
              <option value="">请选择分类</option>
              <%
            con.getConnection();
            String cx_sql="select * from clazz";
            System.out.println(cx_sql);
            ResultSet res=con.cx(cx_sql);
            while(res.next()){
            	out.print("<option value='"+res.getString(2)+"'>"+res.getString(2)+"</option>");
            }
            con.close();
            %>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      </if>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>描述：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="disc" value=<%=disc%>  />
          <div class="tips"></div>
        </div>
      </div>
     
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label>出版社：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="pub" value=<%=pub%> />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>出版时间：</label>
        </div>
        <div class="field"> 
          <script src="js/laydate/laydate.js"></script>
          <input type="text" class="laydate-icon input w50" name="pub_data" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" value=<%=pub_data%>  data-validate="required:日期不能为空" style="padding:10px!important; height:auto!important;border:1px solid #ddd!important;" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>作者：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="editor" value=<%=editor%>  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="price" value=<%=price%>  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>库存：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="count" value=<%=count%>  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 修改</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>