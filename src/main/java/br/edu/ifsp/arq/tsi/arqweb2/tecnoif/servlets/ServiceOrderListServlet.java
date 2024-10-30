package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/serviceOrderList")
public class ServiceOrderListServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ServiceOrderListServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

		var serviceOrders = serviceOrderDao.getAllServiceOrders();

		if(serviceOrders.isPresent() && !serviceOrders.get().isEmpty()) {
			req.setAttribute("resultList", serviceOrders);
		} else {
			req.setAttribute("errorMessage", "Nenhuma ordem de serviço encontrada");
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/service-order-list.jsp");

		dispatcher.forward(req, resp);
	}

}







