package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Page;



public class PageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String operation=request.getParameter("op");
			//Operation有两个值，上一页 p,下一页n
			HttpSession session = request.getSession();
			
			Page page = (Page) session.getAttribute("page");
			int pageNum;
			pageNum=page.getPageNum();
			if(operation.equals("p"))
				pageNum=pageNum-1;
			else if(operation.equals("n"))
				pageNum=pageNum+1;
			else
			{
				pageNum=Integer.parseInt(operation);
			}
			
			page.setPageNum(pageNum);
			session.setAttribute("p", page.getPageList());
			
			response.sendRedirect("../shouye.jsp");
			
	}

}
