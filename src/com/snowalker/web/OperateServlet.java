package com.snowalker.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snowalker.web.utils.FileUtils;

/**
 * Servlet implementation class OperateServlet
 */
@WebServlet("/OperateServlet")
public class OperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Text/html;charset=UTF-8");
		//获取传递参数
		String mtype = request.getParameter("mtype");
		String path = request.getParameter("path");
		if (mtype != null && path != null) {
			if ("delete".equals(mtype)) {
				FileUtils.delete(path);
			} else if ("rename".equals(mtype)) {
				
			}
			response.sendRedirect("DirServlet.shtml?path=" + path.substring(0, path.lastIndexOf("\\") + 1));
		} else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
