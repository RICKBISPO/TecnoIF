<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<a class="navbar-brand" href="home.jsp">
			<img src="img/tecnoif-logo-text.png" alt="tecnoif-logo" width="100" height="35">
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link" href="serviceOrderRegister">Cadastrar Ordem de Servico</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="serviceOrderList">Listar Ordem de Servico</a>
				</li>
				<c:if test="${sessionScope.user.role == 'ADMIN'}">
					<li class="nav-item">
						<a class="nav-link" href="payment-type-register.jsp">Cadastrar Tipo de Pagamento</a>
					</li>
				</c:if>
				<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
					${sessionScope.user.name} </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="logout">Sair</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>