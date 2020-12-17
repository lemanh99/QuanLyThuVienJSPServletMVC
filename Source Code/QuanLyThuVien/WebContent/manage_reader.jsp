<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content">
		<div class="row justify-content-center">
			<div style="margin-top: 20px; color: red;">${errorString}</div>
		</div>
		<div class="container-fluid">
			<div class="row">

				<div class="col-md-12">
					<div class="card">
						<c:if
							test="${sessionScope.Check.toString().equals('ManageReader_0')}">
							<form role="form" method="post"
								action="${pageContext.request.contextPath}/SearchReader">
						</c:if>
						<c:if
							test="${sessionScope.Check.toString().equals('ManageReader_1')}">
							<form role="form" method="post"
								action="${pageContext.request.contextPath}/SearchReader?status=1">
						</c:if>
						<div class="card-header">
							<c:if
								test="${sessionScope.Check.toString().equals('ManageReader_0')}">
								<h3 class="card-title">Danh sách sách những người đang mượn
									sách</h3>
							</c:if>
							<c:if
								test="${sessionScope.Check.toString().equals('ManageReader_1')}">
								<h3 class="card-title">Danh sách lịch sử mượn sách sách</h3>
							</c:if>


							<div class="card-tools" style="margin-right: 1px;">
								<div class="input-group input-group-sm" style="width: 200px;">
									<input type="text" name="data_search"
										class="form-control float-right"
										placeholder="Tìm kiếm theo tên">

									<div class="input-group-append">
										<button type="submit" class="btn btn-primary">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</div>
							</div>

						</div>
						</form>
						<!-- /.card-header -->
						<div class="card-body">
							<table class="table table-bordered table-hover" id="example2">
								<thead>
									<tr>
										<th style="width: 10px">STT</th>
										<th>Tên</th>
										<th>Số CMNN</th>
										<th>Tên sách mượn</th>
										<th>Ngày mượn</th>
										<c:if
											test="${sessionScope.Check.toString().equals('ManageReader_0')}">
											<th>Ngày phải trả</th>
											<th>Xác nhận</th>
										</c:if>
										<c:if
											test="${sessionScope.Check.toString().equals('ManageReader_1')}">
											<th>Ngày trả</th>
										</c:if>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${readerList}" var="reader" varStatus="loop">
										<tr>
											<td>${loop.index+1}</td>
											<td>${reader.getName()}</td>
											<td>${reader.getIdentify()}</td>
											<td>${reader.getBook().getName()}</td>
											<td>${reader.getStart_day()}</td>
											<td>${reader.getEnd_day()}</td>
											<c:if
												test="${sessionScope.Check.toString().equals('ManageReader_0')}">
												<td>
													<button type="submit"
														class="btn btn-warning  btn-secondary"
														onclick="location.href='${pageContext.request.contextPath}/ConfirmReader?id=${reader.getId()}'">Đã
														trả</button>
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>

					</div>
					<!-- /.card -->
				</div>
			</div>
			<!-- /.container-fluid -->
	</section>
	<%@ include file="footer.jsp"%>
	<!-- DataTables -->
	<script src="Resources/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="Resources/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="Resources/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="Resources/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<!-- AdminLTE App -->
	<script src="Resources/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="Resources/dist/js/demo.js"></script>
	<!-- page script -->
	<script>
		$(function() {
			$("#example1").DataTable({
				"responsive" : true,
				"autoWidth" : false,
			});
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
			});
		});
	</script>