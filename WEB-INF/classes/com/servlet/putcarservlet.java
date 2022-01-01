package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ConnD;
import com.bean.Book;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class putcarservlet
 */
@WebServlet("/putcarservlet")
public class putcarservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public putcarservlet() {
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
		 //得到编号
		 String id=request.getParameter("id");
		 String sql="select * from book where id="+id;
		 int id2=Integer.parseInt(id);
		 ConnD con=new ConnD();
		 con.getConnection();
		 ResultSet res=con.cx(sql);
		 try {
			while(res.next()) {
				 String name=res.getString(2);
				 String pic=res.getString(3);
				 String clazz=res.getString(4);
				 String disc=res.getString(5);
				 String pub=res.getString(6);
				 Date pub_data=res.getDate(7);
				 String editor=res.getString(8);
				 Float price=res.getFloat(9);
				 Integer count=0;
				 Book book = new Book(id2,name,pic,clazz,disc,pub,pub_data,editor,price,count);
				 @SuppressWarnings("unchecked")
				 Map<String,Book> gwc = (Map<String,Book>)request.getSession().getAttribute("gwc");
				 if(gwc==null){
					  //创建购物车
					  gwc = new HashMap<String, Book>();
				 }
				//将商品项放入购物车
				 //put(商品编号,商品项) 向gwc集合中添加数据
				 //你要想 购物车中是否已存在该商品
				 // 说白了 就是在gwc集合中去匹配是否存在这样一个商品项 ==》去集合中匹配是否存在这样一个商品编号的key
				  
				 //判断是否存在商品编号的键
				  
				 if(gwc.containsKey(id)){
				  //存在
				  //设置数量+1
				   
				  //通过键 获得值
				  //键为商品编号 值为商品项 商品项里面包含商品对象信息 和数量信息
				  Book spx = gwc.get(id);
				  //得到原来的数量
				  int yldsl = spx.getCount();
				  //在原来的数量上+1
				  gwc.get(id).setCount(yldsl+1);
				   
				//  gwc.get(id).setCount(gwc.get(id).getCount()+1) ;
				   
				 }else{
				  //不存在
				  //创建一个新的商品项 数量为1
				   book.setCount(1);
				  //将此商品项放入gwc
				  gwc.put(id, book);
				 }
				  
				 //将购物车放入session
				 request.getSession().setAttribute("gwc", gwc);
				  
				 //继续购物
				 response.sendRedirect("user/show.jsp");
				 }
			 //}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		 
	}

}
