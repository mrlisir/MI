package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Product;
import model.User;
import dao.CartDAO;
import dao.ProductDAO;

public class CartServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpServletRequest reqest = (HttpServletRequest)request;
		HttpSession session = request.getSession();
		
		String productID = request.getParameter("productID");
		String produtname = request.getParameter("productname");
		String price = request.getParameter("price");
		String uname = request.getParameter("uname");
		
		User user = (User)session.getAttribute("user");
		
		if(user!=null && uname != null){
			
			int id = Integer.parseInt(productID);
			float jiage = Float.parseFloat(price);											
				Cart c = new Cart();
				c.setCartname(produtname);
				c.setCartprice(jiage);
				c.setProductID(id);
				c.setUname(uname);
				//数据库中增加此商品
				CartDAO cdao = new CartDAO();
				boolean cart = cdao.insert(c);	
				//页面显示出此用户的所有商品
				List<Cart> cart2 = cdao.getProductByuname(uname);
				session.setAttribute("c", cart2);
				
				if(cart && cart2 != null){	
					response.sendRedirect(reqest.getContextPath()+"/z.protect/gouwuche.jsp");
				}
				
			}else{
				response.sendRedirect("/MI/please_login.jsp");
			}
		
	}

}
