package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnD;

/**
 * Servlet implementation class delselectservlet
 */
@WebServlet("/delselectservlet")
public class delselectservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delselectservlet() {
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
		int i=0;
		ConnD con=new ConnD();
		con.getConnection();
		String name=request.getParameter("name");
		if(name=="") {
			response.getWriter().print("<script> alert(\"您什么都没有选哦!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/list.jsp'</script>");
		}
		String[] fname = name.split(",");
		//response.getWriter().append(name);
		for(i = 0;i<fname.length;i++) {
		if(fname[i].equals("全选")) continue;
		String sql="delete from book where"+" id="+fname[i];
		System.out.print(sql);
		int x=con.xg(sql);
		if(x<=0) {
			response.getWriter().print("<script> alert(\"删除失败!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/list.jsp'</script>");
			break;
		}
		}
		if(i==fname.length) {
			response.getWriter().print("<script> alert(\"删除成功!\"); </script>");
			response.getWriter().println("<script>window.location.href='admin/list.jsp'</script>");
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
