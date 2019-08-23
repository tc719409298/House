package com.team.house.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RequestFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
      /*  System.out.println("想进去吗？");
*/

       // chain.doFilter(req, resp);
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;

        String requestURI = request.getRequestURI();
         /*System.out.println(requestURI);*/
            String path=requestURI.substring(requestURI.lastIndexOf("/")+1);
       /*System.out.println(path);*/

        if (path.equals("login.jsp")||path.equals("loginByNamePsw")||path.equals("regs.jsp")
                ||path.equals("reg")||path.equals("getUserName")||path.equals("list.jsp")||path.equals("getSelectHouse")
        ){ //放行
            chain.doFilter(req,resp);
        }else {
            HttpSession session = request.getSession();
            Object o = session.getAttribute("logininfo");
            if (o==null){
               response.sendRedirect("login.jsp");
            }else {
                chain.doFilter(req,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
