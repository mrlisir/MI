package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

import dao.CartDAO;
import dao.UserDAO;

public class AutoLoginFilter implements Filter {
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//1. 未进入登录状态-session无标志位
		HttpSession session = request.getSession();
		boolean flag1 = session.getAttribute("user") == null;
		//2. Cookie有账号|密码
		String uname = null;
		String upwd = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			boolean flag2 = false;
			for(Cookie c : cookies){
				if(c.getName().equals("uname")){
					uname = c.getValue();
				}else if(c.getName().equals("upwd")){
					upwd = c.getValue();
				}
				if(uname!=null && upwd!=null){
					flag2 = true;
					break;
				}
			}
			if(flag1&&flag2){ // request			
				boolean result = true;
				User u = new User();
				u.setUname(uname);
				u.setUpwd(upwd);
				UserDAO dao = new UserDAO();
				List<User> users = dao.select(u);
				result = users.size()>0;	
				
				//session
				if(result){	
					//加载购物车
					CartDAO cdao=new CartDAO(); 
					List<Cart> cart2 = cdao.getProductByuname(uname);
					session.setAttribute("c", cart2);
					//
				
					session.setAttribute("user", u);			
				}
				
			}
			chain.doFilter(req, resp);
		}else{
			chain.doFilter(req, resp);
		}	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
