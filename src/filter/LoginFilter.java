package filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//TODO Comlete the urlpattern for this Filter

@WebFilter(urlPatterns = {"/EditChollo", "/UserProfile"})
public class LoginFilter implements Filter{

	private FilterConfig fc;
	
	public void init (FilterConfig fc) {

		//TODO complete the code here

	}

	
	public void doFilter(ServletRequest request,
						ServletResponse response,
						FilterChain chain) throws IOException, ServletException {
		
		//TODO complete the code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = ((HttpServletRequest)request).getSession(true);
		
		if (session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath()+"/Registro");
		}else {
			chain.doFilter(request, response);
		}
		

		// If there is not a session established you must redirect to LoginServlet.do otherwise, follow the usual process. 
		
	}
	
	public void destroy() {
		// do the cleanup staff
	}
	
}
