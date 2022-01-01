package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnD;

/**
 * Servlet implementation class clazzservlet
 */
@WebServlet("/clazzservlet")
public class clazzservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public clazzservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		String id=request.getParameter("id");
		String clazz=request.getParameter("clazz");
		String insert_sql="insert into clazz values('"+id+"','"+clazz+"')";
		System.out.print(insert_sql);
		ConnD con=new ConnD();
		con.getConnection();
		boolean x=con.cr(insert_sql);
		if(x==true){
			response.getWriter().print("<script> alert(\"添加成功!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/cate.jsp'</script>");
		}else{
			response.getWriter().print("<script> alert(\"添加失败!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/cate.jsp'</script>");
		}
		con.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
