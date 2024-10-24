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
<link href="css/errors.css" rel="stylesheet">
<link rel="stylesheet" href="css/home.css">
<title>TecnoIF - Listar Ordem de Serviço</title>
</head>
<body>

	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div class="col-lg-6 offset-lg-3 col-sm-12">

			<h1 class="text-center">Listar Ordem de Serviço</h1>

			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Status</th>
						<th scope="col">Preço</th>
						<th scope="col">Data de Emissão</th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList}" var="serviceOrder">
						<tr>
							<td>${serviceOrder.id}</td>
							<c:choose>
								<c:when test="${serviceOrder.status == 'COMPLETED'}">
									<td>FINALIZADA</td>
								</c:when>
								<c:when test="${serviceOrder.status == 'IN_APPROVAL'}">
									<td>EM APROVAÇÃO</td>
								</c:when>
								<c:when test="${serviceOrder.status == 'APPROVED'}">
									<td>APROVADA</td>
								</c:when>
								<c:when test="${serviceOrder.status == 'IN_PROGRESS'}">
									<td>EM ANDAMENTO</td>
								</c:when>
							</c:choose>
							<td>${serviceOrder.price}</td>
							<td>${serviceOrder.emissionDate}</td>
							<td>
								<a href="serviceOrderEdit?id=${serviceOrder.id}"
								   class="btn btn-warning">Editar</a>
							</td>
							<td>
								<a href="serviceOrderDelete?id=${serviceOrder.id}"
								   class="btn btn-danger">Excluir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>







