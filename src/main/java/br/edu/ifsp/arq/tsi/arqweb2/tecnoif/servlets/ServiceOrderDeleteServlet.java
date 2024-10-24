package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/serviceOrderDelete")
public class ServiceOrderDeleteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ServiceOrderDeleteServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.valueOf(req.getParameter("id"));

		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

		Optional<ServiceOrder> serviceOrder = serviceOrderDao.getServiceOrderById(id);

		if (serviceOrder.isPresent()) {
			serviceOrderDao.deleteServiceOrder(id);
			resp.sendRedirect("serviceOrderList");
		}
	}

}







