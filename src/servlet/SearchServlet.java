package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;
import model.Product;
import dao.ProductDAO;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
    	String searchname=request.getParameter("searchname"); 
    	
    	if (!StringUtil.isEmpty(searchname)) {
    		ProductDAO dao = new ProductDAO();
			List<Product> products = dao.getProductByproductname(searchname);
			session.setAttribute("pro", products);
			response.sendRedirect("/MI/liebiao.jsp");
		}else{
			response.sendRedirect("/MI/shouye.jsp");
		}
	}

}
