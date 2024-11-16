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
<link rel="icon" href="img/tecnoif-logo.ico" type="image/x-icon">
<link href="css/styles.css" rel="stylesheet">
<title>TecnoIF - Cadastrar Tipo de Pagemento</title>
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
			
			<form action="paymentRegister" method="post" id="form1">
				<h1 class="text-center">Cadastrar Tipo de Pagamento</h1>

				<div class="mb-2">
					<label for="paymentType">Tipo de Pagamento*</label>
					<input type="text" name="paymentType" id="paymentType" class="form-control" minlength="3" maxlength="50" required="required">
					<span id="0"></span>
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







