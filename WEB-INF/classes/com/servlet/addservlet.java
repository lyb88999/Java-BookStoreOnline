package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnD;

/**
 * Servlet implementation class addservlet
 */
@WebServlet("/addservlet")
public class addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addservlet() {
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
		String name=request.getParameter("name");
		String pic=request.getParameter("pic");
		String clazz=request.getParameter("clazz");
		String disc=request.getParameter("disc");
		String pub=request.getParameter("pub");
		String pub_data=request.getParameter("pub_data");
		String editor=request.getParameter("editor");
		String price=request.getParameter("price");
		String count=request.getParameter("count");
		String insert_sql="insert into book values("+"'"+id+"',"+"'"+name+"',"+"'"+pic+"',"+"'"+clazz+"',"+"'"+disc+"',"+"'"+pub+"',"+"'"+pub_data+"',"+"'"+editor+"','"+price+"','"+count+"')";
		System.out.print(insert_sql);
		ConnD con=new ConnD();
		con.getConnection();
		boolean x=con.cr(insert_sql);
		if(x==true){
			response.getWriter().print("<script> alert(\"插入成功!\"); </script>");
			response.getWriter().println("<script>window.location.href='./admin/list.jsp'</script>");
		}else{
			response.getWriter().print("<script> alert(\"插入失败!\"); </script>");
			response.getWriter().println("<script>window.location.href='./admin/add.jsp'</script>");
		}
		con.close();
	}

}
