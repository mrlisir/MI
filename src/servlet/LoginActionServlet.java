package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import dao.CartDAO;
import dao.ProductDAO;

import dao.UserDAO;

public class LoginActionServlet extends HttpServlet {
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		String code = request.getParameter("code");
		String remember = request.getParameter("remember");
	
		boolean result = true;
		User u = new User();
		u.setUname(uname);
		u.setUpwd(upwd);
		HttpSession session = request.getSession();
		
		String code2 = (String)session.getAttribute("code");
		if(code2.equals(code)){
			UserDAO dao = new UserDAO();
			List<User> users = dao.select(u);
			result = users.size()>0;
			if(!result){
				request.setAttribute("msg", "’À∫≈√‹¬Î¥ÌŒÛ");
			}
		}else{
			result = false;
			request.setAttribute("msg", "—È÷§¬Î¥ÌŒÛ");
		}					
		

		if(result){
			if("remember".equals(remember)){
				String uri = request.getContextPath() + "/";
				int expiry = 60*60*24*14;
				Cookie c = new Cookie("uname",uname);
				//c.setDomain("");
				c.setPath(uri);
				c.setMaxAge(expiry);
				response.addCookie(c);
				c = new Cookie("upwd", upwd);
				c.setPath(uri);
				c.setMaxAge(expiry);
				response.addCookie(c);
			}
			session.setAttribute("user", u);
			
			//º”‘ÿπ∫ŒÔ≥µ
			CartDAO cdao=new CartDAO(); 
			List<Cart> cart2 = cdao.getProductByuname(uname);
			session.setAttribute("c", cart2);
			//
			
			response.sendRedirect("../shouye.jsp");
		
		}else{
			request.setAttribute("user", u);
			request.getRequestDispatcher("../login.jsp").forward(request, response);
		}

	}


}
