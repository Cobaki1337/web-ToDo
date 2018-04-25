package by.gsu.epamlab.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.controller.constants.AttributeConstants.ATTRIBUTE_USER;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_HEADER;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_MAIN;
import static by.gsu.epamlab.controller.constants.PageConstants.PAGE_ROOT;

public class JSPFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(request.getRequestURI().equals(PAGE_HEADER) ||
                (request.getRequestURI().equals(PAGE_MAIN) ^ (request.getSession().getAttribute(ATTRIBUTE_USER) != null))) {
            ((HttpServletResponse)servletResponse).sendRedirect(PAGE_ROOT);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
