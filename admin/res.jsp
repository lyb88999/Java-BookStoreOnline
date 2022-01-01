<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.ConnD" import="java.sql.*" import="java.util.ArrayList"%>
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
ConnD con= new ConnD();
%>
<form method="post" action="res.jsp" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="add.jsp"> 添加内容</a> </li>
        <li><a class=button border-red href=javascript:void(0) onclick="DelSelect()"><span class=icon-trash-o></span> 删除</a></li>
        <li>搜索：</li>
          
        
        <if condition="$iscid eq 1">
          <li>
            <select name="cid" class="input" style="width:200px; line-height:17px;" >
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
          </li>
        </if>
        <li>
          <input type="text" placeholder="请输入书名" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
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
        <th width=50>价格</th>
        <th>库存</th>
        <th width="250">操作</th>
      </tr>
      <volist name="list" id="vo">
        <tr>
        <%
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String keywords=request.getParameter("keywords");
        String cid=request.getParameter("cid");
        int totalpage=1;//总页数
        int nowpage=1;//当前页
        int totalrecord=1;//总条数
        Connection conn=con.getConnection();
        if(request.getParameter("page")!=null){
        try{
        	nowpage=Integer.parseInt(request.getParameter("page"));
        }
        catch(NumberFormatException e){
        	nowpage=1;
        }
        }
        Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql="";
        String sql1="";
        if(keywords==""){
        	sql="select * from book where (clazz like '%"+cid+"%')";
        	sql1="select count(1) from book where (clazz like '%"+cid+"%')";
        }else if(cid==""){
        	sql="select * from book where (name like '%"+keywords+"%')";
        	sql1="select count(*) from book where (name like '%"+keywords+"%')";
        }
        else {
        	sql="select * from book where (clazz like '%"+cid+"%') and (name like '%"+keywords+"%')";
        	sql1="select count(*) from book where (clazz like '%"+cid+"%') and (name like '%"+keywords+"%')";
        }
        if(cid==""&&keywords==""){
        	sql="select * from book";
        	sql1="select count(*) from book";
        }
        System.out.print(sql);
        //String sql1="select count(*) as totalrecord from book";
        ResultSet rs=stmt.executeQuery(sql1);
        rs.next();
        totalrecord=rs.getInt(1);
        if(totalrecord!=0){
        if(totalrecord%5==0){
        	totalpage=totalrecord/5;
        }
        else{
        	totalpage=(int)Math.floor(totalrecord/5)+1;
        }
        if(nowpage<1){
        	nowpage=1;
        }
        if(nowpage>totalpage){
        	nowpage=totalpage;
        }
        rs=stmt.executeQuery(sql);
        if(rs.next()){
        rs.absolute((nowpage-1)*5+1);
        for(int i=0;i<5;i++)
        {
        	out.print("<tr><td style=text-align:left; padding-left:20px;><input type=checkbox name="+rs.getInt(1)+"  value="+rs.getInt(1)+ ">"+rs.getInt(1)+
     	           "</td>");
        	out.print("<td>"+rs.getString(2)+"</td>");
        	out.print("<td width=10%"+">"+"<img src="+rs.getString(3)+" alt='' width=70 height=50 /></td>");
        	out.print("<td>"+rs.getString(4)+"</td>");
        	out.print("<td>"+rs.getString(5)+"</td>");
        	out.print("<td>"+rs.getString(6)+"</td>");
        	out.print("<td>"+rs.getDate(7)+"</td>");
        	out.print("<td>"+rs.getString(8)+"</td>");
        	out.print("<td>"+rs.getFloat(9)+"</td>");
        	out.print("<td width=50>"+rs.getInt(10)+"</td>");
        	out.print("<td><div class=button-group> <a class=button border-main href=update.jsp?id="+rs.getInt(1)+"><span class=icon-edit></span> 修改</a> <a class=button border-red href=javascript:void(0) onclick='return del("+rs.getInt(1)+")'><span class=icon-trash-o></span> 删除</a> </div></td></tr>");
        	//out.print(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5)+"<br>");
        	if(!rs.next())break; 
        }
        }
        stmt.close();
        conn.close();
        }
            //con.close();
            %>
        
        
      </tr>
      
      <tr>
      <%
      System.out.println("<td colspan=11><div class=pagelist> <a href=res.jsp?page=1&keywords="+keywords+"&cid="+cid+">首页</a> <a href=res.jsp?page="+(nowpage-1)+"&keywords="+keywords+"&cid="+cid+">上一页</a> </div></td>");
	  System.out.println("<td colspan=11><div class=pagelist> <a href=res.jsp?page="+(nowpage+1)+"&keywords="+keywords+"&cid="+cid+">下一页</a> <a href=res.jsp?page="+totalpage+"&keywords="+keywords+"&cid="+cid+">尾页</a> </div></td>");

      if(nowpage==1){
    	  if(totalpage==1){
        	  out.print("<td colspan=11><div class=pagelist> 当前仅一页 </div></td>");
    	  }else{
        	  out.print("<td colspan=11><div class=pagelist> <a href=res.jsp?page="+(nowpage+1)+"&keywords="+keywords+"&cid="+cid+">下一页</a> <a href=res.jsp?page="+totalpage+"&keywords="+keywords+"&cid="+cid+">尾页</a> </div></td>");
    	  }
      }
      else if(nowpage==totalpage){
    	  out.print("<td colspan=11><div class=pagelist> <a href=res.jsp?page=1&keywords="+keywords+"&cid="+cid+">首页</a> <a href=res.jsp?page="+(nowpage-1)+"&keywords="+keywords+"&cid="+cid+">上一页</a> </div></td>");
      }else{
    	  out.print("<td colspan=11><div class=pagelist> <a href=res.jsp?page=1&keywords="+keywords+"&cid="+cid+">首页</a> <a href=res.jsp?page="+(nowpage-1)+"&keywords="+keywords+"&cid="+cid+">上一页</a><a href=res.jsp?page="+(nowpage+1)+"&keywords="+keywords+"&cid="+cid+">下一页</a> <a href=res.jsp?page="+totalpage+"&keywords="+keywords+"&cid="+cid+">尾页</a> </div></td>");

      }
    	  %>
        <td><%=nowpage %>/<%=totalpage %>共<%=totalrecord %>条数据</td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	
		$("#listform").submit();
}

//单个删除
function del(id){
	if(confirm("您确定要删除吗?")){
		document.write("<form name=myform action=/webwork/deleteservlet method=POST><input type=hidden name=id value="+id+"></form");
		document.myform.submit();
	}
}

//批量删除
function DelSelect(){
		// 获取checkbox的所有选中的checked框 
		var option = $("input:checkbox:checked"); 
		//取出checked框中的值
	     var checkedName = ""; 
	     var flag = true;  
	     //循环取出输入框的值
	     for (var i = 0; i < option.length; i++) { 
	    	 //如果只有只有一条，则直接添加到容器中
	         if (flag) {
	        	 flag = false; 
	        	 checkedName += option[i].value; 
	         }else{
	        	//否则将值之间加上","分割在添加到容器中
	             checkedName += ","+option[i].value;  
	     	 } 
	     }
	     var name = window.confirm("你确定要删除这些记录吗");
		 /* alert(checkedId); */
		 if(name){
		 	window.location.href = "/webwork/delselectservlet?name="+checkedName;
	  	 }
	}

</script>
</body>
</html>