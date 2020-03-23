package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Page;

public class IndexFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		
		Page s = (Page) session.getAttribute("page");
		
		if(s == null){
			String url = req.getRequestURI();
			System.out.println(url);
			session.setAttribute("goto", url);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}else{
			chain.doFilter(request, response);
		}	
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
