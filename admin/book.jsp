<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.*" import="java.sql.*"%>
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
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 消费记录</strong></div>

    <table class="table table-hover text-center">
      <tr>
        <th width="120">ID</th>
        <th >姓名</th>       
        <th colspan=2>消费金额</th>
        <th colspan=2>消费日期</th>     
      </tr>      
        <%
        ConnD con=new ConnD();
        con.getConnection();
        String sql="select * from pur";
        ResultSet res=con.cx(sql);
        while(res.next()){
        	out.print("<tr><td>"+res.getInt(1)+"</td>");
        	out.print("<td>"+res.getString(2)+"</td>");
        	out.print("<td colspan=3>"+res.getFloat(3)+"</td>");
        	out.print("<td>"+res.getDate(4)+"</td></tr>");
        }
        con.close();
        %>
    </table>
  </div>
</form>
</body></html>