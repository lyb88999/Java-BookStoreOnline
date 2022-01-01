package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bean.ConnD;
//import java.sql.*;
/**
 * Servlet implementation class registerservlet
 */
@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		String nc=request.getParameter("nc");
		String name=request.getParameter("name");
		String ps=request.getParameter("password");
		String insert_sql="insert into user (nc,id,ps)values("+"'"+nc+"',"+"'"+name+"',"+"'"+ps+"')";
		System.out.print(insert_sql);
		ConnD con=new ConnD();
		con.getConnection();
		boolean x=con.cr(insert_sql);
		if(x==true){
			response.getWriter().print("<script> alert(\"注册成功!\"); </script>");
			response.getWriter().println("<script>window.location.href='./login.jsp'</script>");
		}else{
			response.getWriter().print("<script> alert(\"该用户名已被注册!\"); </script>");
			response.getWriter().println("<script>window.location.href='./register.jsp'</script>");
		}
		con.close();
		
	}

}
