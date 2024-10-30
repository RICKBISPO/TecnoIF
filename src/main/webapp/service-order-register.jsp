<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!doctype html>
<html lang="pt-BR">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link href="css/styles.css" rel="stylesheet">
<title>TecnoIF - Cadastrar Ordem de Serviço</title>
</head>
<body>

	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12">

			<c:if test="${errorMessage != null}">
				<div class="alert alert-danger alert-dismissible fade show"
					 role="alert">
						${errorMessage}
					<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
				</div>
			</c:if>
			
			<form action="serviceOrderRegister" method="post" id="form1">
				<h1 class="text-center">Cadastrar Ordem de Serviço</h1>

				<div class="mb-2">
					<label for="description">Descrição*</label> <input type="text"
						name="description" id="description" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="0"></span>
				</div>

				<div class="mb-2">
					<label for="status">Status*</label>
					<select class="form-select" aria-label="Default select example" id="status" name="status">
						<option value="COMPLETED" selected>FINALIZADA</option>
						<option value="IN_APPROVAL">EM APROVAÇÃO</option>
						<option value="APPROVED">APROVADA</option>
						<option value="IN_PROGRESS">EM ANDAMENTO</option>
					</select> <span id="1"></span>
				</div>

				<div class="mb-2">
					<label for="paymentType">Forma de Pagamento*</label>
					<select class="form-select" aria-label="Default select example" id="paymentType" name="paymentType">
						<c:forEach items="${resultList}" var="payment">
							<option value="${payment.paymentType}">${payment.paymentType}</option>
						</c:forEach>
					</select> <span id="2"></span>
				</div>

				<div class="mb-2">
					<label for="emissionDate">Data de Emissão*</label> <input type="date"
						name="emissionDate" id="emissionDate" class="form-control" required="required"> <span
						id="3"></span>
				</div>

				<div class="mb-2">
					<label for="finalizationDate">Data de Finalização*</label> <input type="date"
						name="finalizationDate" id="finalizationDate" class="form-control" required="required"> <span
						id="4"></span>
				</div>

				<div class="mb-2">
					<label for="price">Preço*</label> <input type="text"
						name="price" id="price" class="form-control" minlength="3"
						maxlength="50" required="required"> <span id="5"></span>
				</div>

				<div class="mb-2">
					<label for="notes">Observações</label> <input type="text"
						name="notes" id="notes" class="form-control" minlength="3"
						maxlength="50"> <span id="6"></span>
				</div>

				<div class="mb-2">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
			</form>
		</div>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="js/validate-fields.js"></script>
</body>
</html>







