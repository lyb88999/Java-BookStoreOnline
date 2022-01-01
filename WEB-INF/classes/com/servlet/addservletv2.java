package com.servlet;


import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;
import java.io.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.bean.ConnD;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addservletv2
 */
@WebServlet("/addservletv2")
public class addservletv2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addservletv2() {
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
		//System.out.print(1);
		request.setCharacterEncoding("utf-8");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
	    response.setCharacterEncoding("utf-8");
	    //判断是普通表单还是文件上传
	    boolean flag = ServletFileUpload.isMultipartContent(request);
	    System.out.print(flag);
	    if(flag){
	    	 int id=0;
	    	 String name="";
	    	 String pic="";
	    	 String clazz="";
	    	 String disc="";
	    	 String pub="";
	    	 String pub_data="";
	    	 String editor="";
	    	 float price=0;
	    	 int count=0;
	        //定义一个解析器，来分析请求中各个项目
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        //解析upload创建成功
	        ServletFileUpload upload = new ServletFileUpload(factory);
	        List<FileItem> fileList = null;
			try {
				fileList = upload.parseRequest(request);
			} catch (FileUploadException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        //在未加入commons-io-2.8.0.jar之前会出现500错误
	        //java.lang.ClassNotFoundException: org.apache.commons.io.output.DeferredFileOutputStream
	        //原因可能是：使用commons-fileupload的版本大于1.0
	        //每一个fileItem都代表一个表单元素
	        Iterator<FileItem>myitor = fileList.iterator();
	        while (myitor.hasNext()) {
	            FileItem item = myitor.next();
	            if (item.isFormField()) { //判断表单内容是非文件形式还是文件形式，此处为非文件形式
	                String namef = item.getFieldName(); //获取表单中元素的名称
	                String value = item.getString("utf-8"); //获取表单中元素的值
	                if (namef.equals("id")) { //将名称与相对的输入框匹配
	                    id = Integer.parseInt(value);  //传值
	                }
	                if (namef.equals("name")) {
	                    name = value;//传值
	                }
	                if (namef.equals("clazz")) {
	                    clazz = value;//传值
	                }
	                if (namef.equals("disc")) {
	                    disc = value;//传值
	                }
	                if (namef.equals("pub")) {
	                    pub = value;//传值
	                }
	                if (namef.equals("pub_data")) {
	                    pub_data = value;
	                }
	                if (namef.equals("editor")) {
	                    editor = value;//传值
	                }
	                if (namef.equals("price")) {
	                    price = Float.parseFloat(value);//传值
	                }
	                if (namef.equals("count")) {
	                    count = Integer.parseInt(value);//传值
	                }
	            }
	            else { //当表单内容为文件形式，上传文件到指导路径
	                String filename = item.getName();
	                //服务器目录下相对目录 相对路径
	                System.out.print(filename);
	                String path="pic";
	                String absolutepath = this.getServletConfig().getServletContext().getRealPath(path);
	                System.out.print(absolutepath);
	                File file = new File(filename);
	                pic="../pic/"+file.getName();
	                File uploadFile = new File(absolutepath, file.getName());
	                try { //这里必须加一个异常处理不然会报错
	                    item.write(uploadFile); //上传
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                System.out.println("上传成功");
	            }
	        }
	        System.out.print(2);
	        //连接数据库，将附值后的全局变量存入数据库中
	                ConnD con = new ConnD();
	        		Connection conn = con.getConnection();
	                //创建连接
	                    //插入记录的sql语句
	                    String insert_sql="insert into book values("+"'"+id+"',"+"'"+name+"',"+"'"+pic+"',"+"'"+clazz+"',"+"'"+disc+"',"+"'"+pub+"',"+"'"+pub_data+"',"+"'"+editor+"','"+price+"','"+count+"')";
	                    System.out.print(insert_sql);
						boolean x=con.cr(insert_sql);
						if(x==true){
							response.getWriter().print("<script> alert(\"插入成功!\"); </script>");
							response.getWriter().println("<script>window.location.href='./admin/list.jsp'</script>");
						}else{
							response.getWriter().print("<script> alert(\"插入失败!\"); </script>");
							response.getWriter().println("<script>window.location.href='./admin/add.jsp'</script>");
						}
	               
	    }
	}

}
