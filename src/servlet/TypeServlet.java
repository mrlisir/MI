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


public class TypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String typeId = request.getParameter("typeID");
		
		if (typeId != null) {
			int id = Integer.parseInt(typeId);
			ProductDAO dao = new ProductDAO();		
				List<Product> products = dao.getProductByTypeId(id);
				session.setAttribute("pro", products);
			
			response.sendRedirect("/MI/liebiao.jsp");
		}

	}

}
