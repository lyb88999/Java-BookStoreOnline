package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnD;

/**
 * Servlet implementation class deleteclazzservlet
 */
@WebServlet("/deleteclazzservlet")
public class deleteclazzservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteclazzservlet() {
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
		String id=request.getParameter("id");
		String sql="delete from clazz where"+" id="+id;
		System.out.print(sql);
		ConnD con=new ConnD();
		con.getConnection();
		int x=con.xg(sql);
		if(x>0){
			response.getWriter().print("<script> alert(\"删除成功!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/cate.jsp'</script>");
		}else{
			response.getWriter().print("<script> alert(\"删除失败!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/cate.jsp'</script>");
		}
		con.close();
	}

}
