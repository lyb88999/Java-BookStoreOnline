package com.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Book;
import com.bean.ConnD;

/**
 * Servlet implementation class putselectcarservlet
 */
@WebServlet("/putselectcarservlet")
public class putselectcarservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public putselectcarservlet() {
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
		ConnD con=new ConnD();
		con.getConnection();
		String name1=request.getParameter("name");
		if(name1=="") {
			response.getWriter().print("<script> alert(\"您什么都没有选哦!\"); </script>");
			response.getWriter().println("<script>window.location.href='/webwork/user/show.jsp'</script>");
		}
		String[] fname = name1.split(",");
		for(int i=0;i<fname.length;i++) {
		String sql="select * from book where id="+fname[i];
		int id2=Integer.parseInt(fname[i]);
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
				  
				 if(gwc.containsKey(fname[i])){
				  //存在
				  //设置数量+1
				   
				  //通过键 获得值
				  //键为商品编号 值为商品项 商品项里面包含商品对象信息 和数量信息
				  Book spx = gwc.get(fname[i]);
				  //得到原来的数量
				  int yldsl = spx.getCount();
				  //在原来的数量上+1
				  gwc.get(fname[i]).setCount(yldsl+1);
				   
				//  gwc.get(id).setCount(gwc.get(id).getCount()+1) ;
				   
				 }else{
				  //不存在
				  //创建一个新的商品项 数量为1
				   book.setCount(1);
				  //将此商品项放入gwc
				  gwc.put(fname[i], book);
				 }
				 //将购物车放入session
				 request.getSession().setAttribute("gwc", gwc);
				 //继续购物
				 response.getWriter().println("<script>window.location.href='user/show.jsp'</script>");
				 }
			 //}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
