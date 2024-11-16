package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.filters;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/paymentRegister", "/payment-type-register.jsp"}, filterName = "Access")
public class AccessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getRole().equals("CLIENT")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home.jsp");
        } else {
            chain.doFilter(request, response);
        }

    }

}
