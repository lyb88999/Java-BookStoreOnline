<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.*" import ="javax.servlet.http.HttpSession"
    import="java.sql.*"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="../images/y1.png" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="../user/show.jsp" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp; &nbsp;&nbsp;<a class="button button-little bg-red" href="../login.jsp?id=clear"><span class="icon-power-off"></span> 退出登录</a> </div>
  <%
  HttpSession s=request.getSession();
  user u=(user)s.getAttribute("user");
  String nc="";
  String yhm=u.getU();
  ConnD conn= new ConnD();
  conn.getConnection();
  String sql="select nc from user where id='"+yhm+"'";
  System.out.print(sql);
  ResultSet res=conn.cx(sql);
  while(res.next()){
	  nc=res.getString(1);
  }
  conn.close();
  %>
  <div class="head-l"><h1>欢迎<%=nc%></h1></div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2><span class="icon-user"></span>基本设置</h2>
  <ul style="display:block">
    <li><a href="pass.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>   
    <li><a href="book.jsp" target="right"><span class="icon-caret-right"></span>购买记录</a></li>     
  </ul>   
  <h2><span class="icon-pencil-square-o"></span>栏目管理</h2>
  <ul>
    <li><a href="list.jsp" target="right"><span class="icon-caret-right"></span>内容管理</a></li>
    <li><a href="add.jsp" target="right"><span class="icon-caret-right"></span>添加内容</a></li>
    <li><a href="cate.jsp" target="right"><span class="icon-caret-right"></span>分类管理</a></li>        
  </ul>  
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="##" id="a_leader_txt">网站信息</a></li>
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="list.jsp" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>