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

@WebServlet("/paymentRegister")
public class PaymentRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public PaymentRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String paymentType = req.getParameter("paymentType");


		Payment payment = new Payment();
		payment.setPaymentType(paymentType);

		String url = null;

		PaymentDao paymentDao = new PaymentDao(DataSourceSearcher.getInstance().getDataSource());

		if (!ValidateFields.validate(payment)) {
			url = "/payment-type-register.jsp";
			req.setAttribute("errorMessage", "Preencha todos os campos");
		}
		else if(paymentDao.save(payment)) {
			url = "/home.jsp";
		} else {
			url = "/payment-type-register.jsp";
			req.setAttribute("errorMessage", "Tipo de pagamento já cadastrado");
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(url);

		dispatcher.forward(req, resp);
	}

}







