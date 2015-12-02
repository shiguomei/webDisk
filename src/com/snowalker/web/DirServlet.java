package com.snowalker.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snowalker.web.utils.FileUtils;

@WebServlet("/DirServlet")
public class DirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//输出页面内容
		out.println("<html>");
		out.println("<body leftmargin=50>");
		String path = request.getParameter("path");
		if (path != null && !path.equals("")) {
			out.println("[<a href='WriterServlet.shtml?path=" + path + "'>创建文件/文件夹</a>]");
			File file = new File(path);
			out.println("<a href='DirServlet.shtml?path=" + file.getParent() + "'><<后退</a>");
			out.println("&nbsp;&nbsp;&nbsp;&nbsp;");
			out.println("<form action='QueryServlet.shtml' method=post>");
			out.println("<input type=hidden name=path value='" + path + "'>");
			out.println("</form>");
		    out.println("<br><br><br>");
		    
		    //得到根目录下的文件夹及文件
			File[] files = FileUtils.fileList(path);
		    //过滤隐藏文件,并展现
			out.println("<table align=left border=0 width=700>");
			out.println("<tr>");
			out.println("<th>名称</th><th>类型</th><th>大小</th><th>修改日期</th><th>操作</th>");
			out.println("</tr>");
			
			for (File f : files) {
				if (!f.isHidden()) {
					out.println("<tr>");
					String url = f.isDirectory() ? "DirServlet.shtml?path=" + f.getAbsolutePath() 
							                     : "ReaderServlet.shtml?path=" + f.getAbsolutePath();
					out.print("<td><a href='" + url + "'>" + f.getName() + "</a></td>");
					out.print("<td>" + (f.isFile() ? "文件" : "文件夹") + "</td>");
					out.println("<td>" + (f.isDirectory() ? "" : (f.length() / 1024) + "KB" ) + "</td>");
					out.println("<td>" + new Date(f.lastModified()).toLocaleString() + "</td>");
					out.print("<td><a href='OperateServlet.shtml?mtype=delete&path=" + f.getAbsolutePath() + "'>删除</a>&nbsp;&nbsp;&nbsp;");

				    out.println("</tr>");
				}
			} 
			out.println("</table>");
			
		} else {
			response.sendRedirect("DiskServlet.shtml");
		}
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
