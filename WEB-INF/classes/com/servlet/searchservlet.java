package com.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnD;

/**
 * Servlet implementation class searchservlet
 */
@WebServlet("/searchservlet")
public class searchservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchservlet() {
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
		ArrayList<Integer> list=new ArrayList<Integer>();
		String keywords=request.getParameter("keywords");
		String cid=request.getParameter("cid");
		//response.getWriter().print(keywords+cid);
		String sql="select * from book where clazz='"+keywords+"' and "+"name="+"'"+cid+"'";
		System.out.print(sql);
		ConnD con=new ConnD();
		con.getConnection();
		ResultSet res=con.cx(sql);
		try {
			while(res.next()) {
				list.add(res.getInt(1));
				request.getSession().setAttribute("res", list);
				request.getRequestDispatcher("admin/res.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/admin/index.jsp");
	}

}
