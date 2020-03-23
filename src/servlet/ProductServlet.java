package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.Product;


public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String productID = request.getParameter("productID");
		
		if (productID != null) {
			int id = Integer.parseInt(productID);
			ProductDAO dao = new ProductDAO();
			
				List<Product> products = dao.getProductByproductID(id);
				session.setAttribute("pro2", products);
				
			response.sendRedirect("/MI/xiangqing.jsp");
		}

	}

}
