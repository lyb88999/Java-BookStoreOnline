package com.servlet;

import java.io.IOException;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Book;
import com.bean.ConnD;
import com.bean.user;

/**
 * Servlet implementation class checkservlet
 */
@WebServlet("/checkservlet")
public class checkservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public String today() {
	    Date today = new Date(System.currentTimeMillis());  // 1000 * 60 * 60 * 24
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return simpleDateFormat.format(today);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkservlet() {
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
		ConnD con=new ConnD();
		con.getConnection();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type","text/html;charset=UTF-8");
		float sum=0;
		int z=0;
		Map<String,Book> gwc = (Map<String,Book>)request.getSession().getAttribute("gwc");
		if(gwc==null) {
			response.getWriter().print("<script> alert(\"您什么都没有选哦!\"); </script>");
			response.getWriter().println("<script>window.location.href='user/show.jsp'</script>");
		}
		for (Book value : gwc.values()) {
			sum+=value.getPrice()*value.getCount();
			String cx_sql="select count from book where id="+value.getId();
			ResultSet res=con.cx(cx_sql);
			try {
				while(res.next()) {
					int x=res.getInt(1);
					int y=x-value.getCount();
					if(y>=0) {
					String xg_sql="update book set "+"count="+"'"+y+"' where id="+value.getId();
					System.out.print(cx_sql);
					System.out.print(xg_sql);
					z=con.xg(xg_sql);}
					else {
						request.getSession().removeAttribute("gwc");
						response.getWriter().print("<script> alert(\"库存不足，请挑选其他商品!\"); </script>");
						response.getWriter().println("<script>window.location.href='/webwork/user/show.jsp'</script>");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(z>0) {
			response.getWriter().print("<script> alert(\"您本次共消费+"+sum+"元,欢迎下次光临！\"); </script>");
			response.getWriter().println("<script>window.location.href='user/show.jsp'</script>");
			request.getSession().removeAttribute("gwc");
			  HttpSession s=request.getSession();
			  user u=(user)s.getAttribute("user");
			  String nc="";
			  String yhm=u.getU();
			  ConnD conn= new ConnD();
			  conn.getConnection();
			  String sql="select nc from user where id='"+yhm+"'";
			  System.out.print(sql);
			  ResultSet res=conn.cx(sql);
			  try {
				while(res.next()){
					  nc=res.getString(1);
				  }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  String sql1="insert into pur(name,cost,date) values('"+nc+"','"+sum+"','"+today()+"')";
			  System.out.print(sql1);
				boolean x=con.cr(sql1);
			  conn.close();
		}else {
			response.getWriter().print("<script> alert(\"系统出错了，请稍后再试\"); </script>");
			response.getWriter().println("<script>window.location.href='user/show.jsp'</script>");
		}
		con.close();
	}

}
