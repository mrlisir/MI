package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutActionServelt extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 处理请求---
		
		//2. 业务处理---		
		HttpSession session = request.getSession();		
		session.invalidate();		
		
		//3. 处理响应
		String uri = request.getContextPath() + "/";
		Cookie c = new Cookie("uname","");
		c.setPath(uri);
		c.setMaxAge(0);
		response.addCookie(c);
		c = new Cookie("upwd","");
		c.setPath(uri);
		c.setMaxAge(0);
		response.addCookie(c);
		response.sendRedirect("/MI/");
	}

}
