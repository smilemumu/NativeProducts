package com.shibro.nativeproducts.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();
		String path = req.getRequestURL().toString();
		if(!path.endsWith("login.html")){
			if(!"login".equals(session.getAttribute("login"))){
				res.sendRedirect(req.getContextPath()+"/login.html");
			}
		}
		chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
