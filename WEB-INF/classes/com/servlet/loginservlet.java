package com.servlet;
import com.bean.ConnD;
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.user;

/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		String name=request.getParameter("name");
		String ps=request.getParameter("password");
		user u1=new user();
		u1.setU(name);
		u1.setPs(ps);
		ConnD con=new ConnD();
		con.getConnection();
		String cx_sql="select id,ps from user where id="+"'"+name+"'";
		System.out.println(cx_sql);
		ResultSet res=con.cx(cx_sql);
		try {
			if(res.next()) {
				if(res.getString(1).equals(name)&&res.getString(2).equals(ps)) {
					HttpSession s1=request.getSession();
					s1.setAttribute("user", u1);
					if(name.equals("admin")) {
						s1.setAttribute("role",1);
						response.sendRedirect("admin/index.jsp");
					}else if(name.equals("test")) {
						s1.setAttribute("role", 3);
						response.sendRedirect("user/show.jsp");
					}
					else {
						s1.setAttribute("role",2);
						response.sendRedirect("user/show.jsp");
					}
				}else {
					response.getWriter().print("<script> alert(\"用户名或密码错误!\"); </script>");
					response.getWriter().println("<script>window.location.href='login.jsp'</script>");
				}
			}else {
				response.getWriter().print("<script> alert(\"找不到该用户名，请注册!\"); </script>");
				response.getWriter().println("<script>window.location.href='register.jsp'</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		//response.getWriter().append("您输入的用户名密码不正确").append("<a href='login.jsp'>返回</a>");
	}
}
