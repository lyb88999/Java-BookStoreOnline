package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.ConnD;
import com.bean.user;

/**
 * Servlet implementation class updateuserservlet
 */
@WebServlet("/updateuserservlet")
public class updateuserservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateuserservlet() {
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
		HttpSession s=request.getSession();
		user u=(user)s.getAttribute("user");
		String yhm=u.getU();
		String mpass=request.getParameter("mpass");
		String newpass=request.getParameter("newpass");
		String renewpass=request.getParameter("renewpass");
		if(!newpass.equals(renewpass)) {
			response.getWriter().print("<script> alert(\"两次输入密码不一致，请重新输入!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/pass.jsp'</script>");
		}else {
			if(mpass.equals(u.getPs())) {
				String sql="update user set ps='"+newpass+"' where id='"+yhm+"'";
				System.out.print(sql);
				ConnD con=new ConnD();
				con.getConnection();
				int x=con.xg(sql);
				if(x>0){
					response.getWriter().print("<script> alert(\"更新成功!\"); </script>");
					response.getWriter().println("<script>window.location.href='login.jsp?id=clear'</script>");
					//response.getWriter().print("<a class=\"button button-little bg-red\" href=\"/login.jsp?id=clear\"><span class=\"icon-power-off\"></span> 重新登录</a>");
				}else{
					response.getWriter().print("<script> alert(\"更新失败!\"); </script>");
					response.getWriter().println("<script>window.location.href='admin/pass.jsp'</script>");
				}
				con.close();
			}else {
				response.getWriter().print("<script> alert(\"原密码错误!\"); </script>");
				response.getWriter().println("<script>window.location.href='admin/pass.jsp'</script>");
			}
		}
	}

}
