package com.ppm.filter;

import com.ppm.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        if (path.startsWith("/login")
                || path.startsWith("/query")
                || path.startsWith("/logout")) {
            chain.doFilter(req, resp);
            return;
        }

        HttpSession session = request.getSession(false);

        User loginUser = null;
        if (session != null) {
            loginUser = (User) session.getAttribute("loginUser");
        }
        if (loginUser != null) {
            Integer jobType = loginUser.getJobType();
            if(jobType == 1) {
                chain.doFilter(req, resp);
            } else if (jobType == 2) {
                if(path.startsWith("/index") || path.startsWith("/fee")){
                    chain.doFilter(req, resp);
                }else{
                    response.sendError(403, "没有访问权限");
                }
            }else if(jobType == 3) {
                if(path.startsWith("/index") || path.startsWith("/repair")){
                    chain.doFilter(req, resp);
                }else{
                    response.sendError(403, "没有访问权限");
                }
            }else if(jobType == 4) {
                if(path.startsWith("/index") || path.startsWith("/car")){
                    chain.doFilter(req, resp);
                }else{
                    response.sendError(403, "没有访问权限");
                }
            }else {
                response.sendError(403, "JobType类型异常");
            }


        } else {
            if(path.startsWith("/index")
                    || path.startsWith("/building")
                    || path.startsWith("/car")
                    || path.startsWith("/fee")
                    || path.startsWith("/house")
                    || path.startsWith("/notice")
                    || path.startsWith("/owner")
                    || path.startsWith("/repair")
                    || path.startsWith("/user")){
                response.sendRedirect(contextPath + "/login?error=needLogin");
            }else{
                response.sendRedirect(contextPath + "/login");
            }

        }
    }
    public void np(HttpServletRequest req, HttpServletResponse resp,String error) throws IOException {
//        resp.setContentType("text/html;charset=utf-8");
        resp.sendError(403, "没有访问权限");
    }
    @Override
    public void destroy() {
    }
}
