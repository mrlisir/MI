package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Page;

public class IndexServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//·ÖÒ³¹¦ÄÜ
		Page page = new Page();
		page.setPageNum(1);
		page.setPageCount(5);
		
		session.setAttribute("page", page);
		session.setAttribute("p", page.getPageList());
		//
		
		response.sendRedirect("/MI/shouye.jsp");
	}

}
