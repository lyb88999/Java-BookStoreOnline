<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
 <head>
  <title>404</title>  
  <meta http-equiv="Content-Type" content="text/html; charset="/>   
 </head>
 <body>
  <!--先编写好网页布局-->
  <h3>找不到该页面</h3>
  <p>
      <b id="second">5</b>秒后返回登陆界面 <a href="javascript:goBack();">返回</a>
  </p>
 
  <script type="text/javascript">  
   //获取显示秒数的元素，通过定时器来更改秒数。
  var sec = document.getElementById("second");
  var i=5;
  var timer = setInterval(function(){
      i--;
      sec.innerHTML = i;   
      if(i ==1){
    	  window.location.href="/webwork/login.jsp";
      }
  },1000) 
  
   //通过window的location和history对象来控制网页的跳转。
   function goBack(){
       window.history.go(-1);
   }
   
 </script> 
</body>
</html>
