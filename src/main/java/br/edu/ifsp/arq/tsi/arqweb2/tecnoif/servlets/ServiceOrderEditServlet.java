package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.ValidateFields;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/serviceOrderEdit")
public class ServiceOrderEditServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ServiceOrderEditServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.valueOf(req.getParameter("id"));

		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

		Optional<ServiceOrder> serviceOrder = serviceOrderDao.getServiceOrderById(id);

		if (serviceOrder.isPresent()) {
			req.setAttribute("id", serviceOrder.get().getId());
			req.setAttribute("status", serviceOrder.get().getStatus());
			req.setAttribute("finalizationDate", serviceOrder.get().getFinalizationDate());
			req.setAttribute("notes", serviceOrder.get().getNotes());
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/service-order-edit.jsp");

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.valueOf(req.getParameter("id"));
		Status status = Status.valueOf(req.getParameter("status"));
		LocalDate finalizationDate = LocalDate.parse(req.getParameter("finalizationDate"));
		String notes = req.getParameter("notes");


		ServiceOrder serviceOrder = new ServiceOrder();
		serviceOrder.setStatus(status);
		serviceOrder.setFinalizationDate(finalizationDate);
		serviceOrder.setNotes(notes);
		serviceOrder.setId(id);

		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

		if (!ValidateFields.validate(serviceOrder)) {
			req.setAttribute("errorMessage", "Preencha todos os campos");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/service-order-edit.jsp");
			dispatcher.forward(req, resp);
		}
		else if (serviceOrderDao.editServiceOrder(serviceOrder)) {
			resp.sendRedirect("serviceOrderList");
		}
	}

}







