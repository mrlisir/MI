package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPFilter implements Filter {

	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {		
		resp.setCharacterEncoding("gbk");
		//resp.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		System.out.println(address);
		
		//String ip = request.getRemoteAddr();
		
		String ip = "192.168.43.173";
		
		//System.out.println(request.getRemoteHost());
		
		if(!ip.startsWith(address)){
			
			PrintWriter out = response.getWriter();
			out.println("IP地址非法，禁止访问！");
			out.flush();
			out.close();
		}else{
			chain.doFilter(req, resp);
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		//address = config.getInitParameter("address");
	}

	private String address = "192.168";
}
