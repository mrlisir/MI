package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import dao.CartDAO;

public class CartDelServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	request.setCharacterEncoding("utf-8");
		 	HttpServletRequest reqest = (HttpServletRequest)request;
		 	HttpSession session = request.getSession();
		 	String cartID=request.getParameter("cartID");
		 	String uname = request.getParameter("uname");
		 	if(cartID != null){
			 	int cartid = Integer.parseInt(cartID);
			 	
			 	CartDAO cdao=new CartDAO();
			 	boolean t = cdao.delete(cartid);
			 	if(t){	
			 		//更新session，刷新页面
			 		List<Cart> cart2 = cdao.getProductByuname(uname);
					session.setAttribute("c", cart2);
			 		response.sendRedirect(reqest.getContextPath()+"/z.protect/del_success.jsp");
			 	}
		 	}  		    
	}

}
