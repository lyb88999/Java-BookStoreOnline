<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.ConnD" import="java.sql.*"%>
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
con.getConnection();
%>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
  <div class="padding border-bottom">
  </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="20%">编号</th>
      <th width="20%">类别</th>
      <th width="20%">操作</th>
    </tr>
    <%
    con.getConnection();
    String cx_sql1="select * from clazz";
    ResultSet res=con.cx(cx_sql1);
    while(res.next()){
    	out.print("<tr><td>"+res.getString(1)+"</td>");
    	out.print("<td>"+res.getString(2)+"</td>");
    	out.print("<td><div class=button-group> <a class=button border-main href=cateedit.jsp?id="+res.getString(1)+"><span class=icon-edit></span> 修改</a> <a class=button border-red href=javascript:void(0) onclick='return del("+res.getInt(1)+")'><span class=icon-trash-o></span> 删除</a> </div></td></tr>");
    }
    %>
  </table>
</div>
<script type="text/javascript">
function del(id,mid){
	if(confirm("您确定要删除吗?")){			
		document.write("<form name=myform action=/webwork/deleteclazzservlet method=POST><input type=hidden name=id value="+id+"></form");
		document.myform.submit();
	}
}
</script>
<div class="panel admin-panel margin-top">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/webwork/clazzservlet">
      <div class="form-group">
        <div class="label">
          <label>编号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="id" value="" />
          <div class="tips"></div>
        </div>
      </div>        
      <div class="form-group">
        <div class="label">
          <label>类别：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="clazz" value="" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</body>
</html>