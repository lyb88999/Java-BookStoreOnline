<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.ConnD" import="java.sql.*" import="com.bean.Book" import="java.util.Map" import="java.util.HashMap"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title>网上书店</title>
<link rel="stylesheet" href="../css/pintuer.css">
<link rel="stylesheet" href="../css/admin.css">
<script src="../js/jquery.js"></script>
<script src="../js/pintuer.js"></script>
</head>
<body>
<form method="post" action="/webwork/putcarservlet" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        
            <li><a href="show.jsp" class="button border-main icon-search"  > 返回继续购物</a></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">书目编号</th>
        <th width="10%">书名</th>
        <th>图片</th>
        <th>类别</th>
        <th>描述</th>
        <th>出版社</th>
        <th width="10%">出版日期</th>
        <th>作者</th>
        <th>价格</th>
        <th width="310">购买数量</th>
      </tr>
      <volist name="list" id="vo">
        <tr>
        <%
        if(request.getSession().getAttribute("gwc")!=null){
        Map<String,Book> gwc = (Map<String,Book>)request.getSession().getAttribute("gwc");
        for (Book value : gwc.values()) {
        	out.print("<tr><td align=center style= padding-left:20px;>"+value.getId()+
      	           "</td>");
      		out.print("<td>"+value.getName()+"</td>");
      		out.print("<td width=10%"+">"+"<img src="+value.getPic()+" alt='' width=70 height=50 /></td>");
      		out.print("<td>"+value.getClazz()+"</td>");
      		out.print("<td>"+value.getDisc()+"</td>");
      		out.print("<td>"+value.getPub()+"</td>");
      		out.print("<td>"+value.getPub_data()+"</td>");
      		out.print("<td>"+value.getEditor()+"</td>");
      		out.print("<td>"+value.getPrice()+"</td>");
      		out.print("<td>"+value.getCount()+"</td><td><a class=button border-red href=javascript:void(0) onclick='return del("+value.getId()+")'><span class=icon-trash-o></span> 不想要了</a> </div></td></tr>");
        }
        }
        
        
        
            %>
        
      </tr>
      <tr>
        <td colspan="10"><td><a class="button border-main icon-search" href=javascript:void(0) onclick="return check()"> 结账</a> </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	
		
}

//结账
function check(){	
	if(confirm("确认购买？")){
		document.write("<form name=myform action=/webwork/checkservlet method=POST></form")
		document.myform.submit();
	}
}

//单个删除
function del(id){
	if(confirm("您确定要删除吗?")){
		document.write("<form name=myform action=/webwork/deletecartservlet method=POST><input type=hidden name=id value="+id+"></form");
		document.myform.submit();
	}
}



</script>
</body>
</html>