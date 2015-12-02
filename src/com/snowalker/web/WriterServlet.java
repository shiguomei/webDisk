package com.snowalker.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snowalker.web.utils.FileUtils;

/**
 * Servlet implementation class WriterServlet
 */
@WebServlet("/WriterServlet")
public class WriterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String path = request.getParameter("path");
		if (path != null && !path.equals("")) {
			out.println("<form action='WriterServlet.shtml' method=post>");
			out.println("<input type=hidden name=path value='" + path + "'>");
			out.println("<input name=type type=radio value=1>文件");
			out.println("<input name=type type=radio value=2>文件夹");
			out.println("<br>文件/文件夹名称:<input name=name size=30>");
			out.println("<br>");
			out.println("<textarea rows=5 cols=50 name=content></textarea><br>");
			out.println("<input type=submit>");
			out.println("</form>");
			out.println("  </BODY>");
			out.println("</HTML>");
		}
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		//获取参数
		String path = request.getParameter("path");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		if (type.equals("1")) {
			String content = request.getParameter("content");
			FileUtils.writeFile(path + "\\" + name, content);
		} else {
			boolean result = FileUtils.mkdirs(path + "\\" + name);
			if (!result) {
				out.println("创建失败");
				out.println("<a href='#'>返回</a>");
			}
		}
		response.sendRedirect("DirServlet.shtml?path=" + path);
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}

}
