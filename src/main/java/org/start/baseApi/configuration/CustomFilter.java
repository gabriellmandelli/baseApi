package org.start.baseApi.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.start.baseApi.util.authentication.ProjectToken;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class CustomFilter implements Filter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String ROUTE_LOGIN_MANAGER = "/manager/login";
    private static final String ROUTE_LOGIN_SALESMAN = "/salesman/login";

    private static final Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("Header - AUTHORIZATION:"+ ((HttpServletRequest)servletRequest).getHeader(AUTHORIZATION));
        logger.info("PATH:"+ ((HttpServletRequest)servletRequest).getServletPath());

        String lsHeader = ((HttpServletRequest)servletRequest).getHeader(AUTHORIZATION);
        String servletPath = ((HttpServletRequest)servletRequest).getServletPath();

        ProjectToken projectToken = new ProjectToken();

        if (servletPath.equals(ROUTE_LOGIN_MANAGER) || servletPath.equals(ROUTE_LOGIN_SALESMAN) || projectToken.validToken(lsHeader)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            ((HttpServletResponse)servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }
}
