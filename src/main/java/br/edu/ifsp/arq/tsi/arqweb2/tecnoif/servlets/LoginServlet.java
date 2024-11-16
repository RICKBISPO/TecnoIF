package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public LoginServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String cpf = req.getParameter("cpf");
		
		String url;
		
		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());

		Optional<User> userByCpf = userDao.getUserByCpf(cpf);
		User user = (User) userByCpf.get();

		if(user.getEmail().equals(email) && user.getCpf().equals(cpf)) {
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("user", user);
			url = "/home.jsp";
		}else {
			req.setAttribute("result", "loginError");
			url = "/login.jsp";
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(url);

		dispatcher.forward(req, resp);
	}

}





