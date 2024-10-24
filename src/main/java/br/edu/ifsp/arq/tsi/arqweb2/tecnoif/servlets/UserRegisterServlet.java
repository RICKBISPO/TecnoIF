package br.edu.ifsp.arq.tsi.arqweb2.tecnoif.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.Address;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.User;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.AddressDao;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.model.dao.UserDao;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.tecnoif.utils.ValidateFields;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public UserRegisterServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String telephone = req.getParameter("telephone");
		String cpf = req.getParameter("cpf");


		String streetName = req.getParameter("streetName");
		String houseNumber = req.getParameter("houseNumber");
		String addressComplement = req.getParameter("addressComplement");
		String neighborhood = req.getParameter("neighborhood");
		String postalCode = req.getParameter("postalCode");
		String city = req.getParameter("city");
		String state = req.getParameter("state");


		Address address = new Address();
		address.setStreetName(streetName);
		address.setHouseNumber(houseNumber);
		address.setAddressComplement(addressComplement);
		address.setNeighborhood(neighborhood);
		address.setPostalCode(postalCode);
		address.setCity(city);
		address.setState(state);


		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setTelephone(telephone);
		user.setCpf(cpf);

		String url = null;
		
		UserDao userDao = new UserDao(DataSourceSearcher.getInstance().getDataSource());
		AddressDao addressDao = new AddressDao(DataSourceSearcher.getInstance().getDataSource());

		if (!ValidateFields.validate(user) || !ValidateFields.validate(address)) {
			url = "/user-register.jsp";
			req.setAttribute("errorMessage", "Preencha todos os campos");
		}
		else if(userDao.save(user)) {
			if (addressDao.save(address, user)) {
				req.setAttribute("result", "registered");
				url = "/login.jsp";
			}
		}else {
			req.setAttribute("errorMessage", "Usuário já cadastrado");
			url = "/user-register.jsp";
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher(url);

        dispatcher.forward(req, resp);
	}

}







