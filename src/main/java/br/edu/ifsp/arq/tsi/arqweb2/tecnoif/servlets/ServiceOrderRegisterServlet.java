package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Payment;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Status;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.PaymentDao;
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

@WebServlet("/serviceOrderRegister")
public class ServiceOrderRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public ServiceOrderRegisterServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PaymentDao paymentDao = new PaymentDao(DataSourceSearcher.getInstance().getDataSource());

		var payments = paymentDao.getAllPayments();

		if (payments.isPresent()) {
			req.setAttribute("resultList", payments);
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/service-order-register.jsp");

		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String description = req.getParameter("description");
		Status status = Status.valueOf(req.getParameter("status"));
		String paymentType = req.getParameter("paymentType");
		LocalDate emissionDate = LocalDate.parse(req.getParameter("emissionDate"));
		LocalDate finalizationDate = LocalDate.parse(req.getParameter("finalizationDate"));
		double price = Double.parseDouble(req.getParameter("price"));
		String notes = req.getParameter("notes");


		ServiceOrder serviceOrder = new ServiceOrder();
		serviceOrder.setDescription(description);
		serviceOrder.setStatus(status);
		serviceOrder.setEmissionDate(emissionDate);
		serviceOrder.setFinalizationDate(finalizationDate);
		serviceOrder.setPrice(price);
		serviceOrder.setNotes(notes);

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		String url = null;

		ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
		PaymentDao paymentDao = new PaymentDao(DataSourceSearcher.getInstance().getDataSource());

		Optional<Payment> payment = paymentDao.getPaymentByPaymentType(paymentType);

		if (payment.isEmpty()) {
			url = "/service-order-register.jsp";
			req.setAttribute("errorMessage", "Tipo de pagamento não cadastrado");
		}
		if (!ValidateFields.validate(serviceOrder)) {
			url = "/service-order-register.jsp";
			req.setAttribute("errorMessage", "Preencha todos os campos");
		}
		else if(serviceOrderDao.save(serviceOrder, user, payment.get())) {
			url = "/home.jsp";
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(url);

		dispatcher.forward(req, resp);
	}

}







