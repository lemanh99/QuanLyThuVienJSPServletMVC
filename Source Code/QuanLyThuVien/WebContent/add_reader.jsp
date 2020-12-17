<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark"></h1>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->
	<section class="content">

		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<!-- general form elements -->
					<form role="form" method="post"
						action="${pageContext.request.contextPath}/AddReader">

						<div class="card card-primary">
							<div class="card-header">
								<h3 class="card-title">Thêm người mượn sách</h3>
							</div>
							<!-- /.card-header -->
							<!-- form start -->
							<div class="row justify-content-center card-body"
								style="margin-bottom: -34px;">
								<div style="color: red;">${errorString}</div>
							</div>
							<div class="card-body">
								<div class="form-group">
									<label>Họ và tên</label> <input type="text"
										class="form-control" id="name_reader" name="name_reader"
										placeholder="Nhập họ và tên" required="required">
								</div>
								<div class="form-group">
									<label>Số chứng minh nhân dân</label> <input type="text"
										class="form-control" id="identify" name="identify"
										placeholder="Số chứng minh nhân dân" required="required">
								</div>
								<!-- /.form-group -->
								<div class="form-group">
									<label>Tên sách</label> <select class="form-control select2"
										style="width: 100%;" name="id_book" required="required">
										<c:forEach items="${bookList}" var="book">
											<option value="${book.getId()}">${book.getName()}</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>Ngày trả sách:</label>
									<div class="input-group date" id="reservationdate"
										data-target-input="nearest">
										<input type="text" class="form-control datetimepicker-input"
											id="end_day" data-target="#reservationdate"
											oninput="generateFullName()" required="required"
											name="end_day" />
										<div class="input-group-append" data-target="#reservationdate"
											data-toggle="datetimepicker">
											<div class="input-group-text">
												<i class="fa fa-calendar"></i>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-primary ">Lưu</button>
								<input type="button" value="Hủy" class="btn btn-primary"
									onclick="location.href='${pageContext.request.contextPath}/ManageReader'">
							</div>
						</div>
					</form>
				</div>
				<!-- /.card -->

			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
	<%@ include file="footer.jsp"%>
	<!-- jQuery -->
	<script src="Resources/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="Resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- Select2 -->
	<script src="Resources/plugins/select2/js/select2.full.min.js"></script>
	<!-- Bootstrap4 Duallistbox -->
	<script
		src="Resources/plugins/bootstrap4-duallistbox/jquery.bootstrap-duallistbox.min.js"></script>
	<!-- InputMask -->
	<script src="Resources/plugins/moment/moment.min.js"></script>
	<script
		src="Resources/plugins/inputmask/min/jquery.inputmask.bundle.min.js"></script>
	<!-- date-range-picker -->
	<script src="Resources/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap color picker -->
	<script
		src="Resources/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<!-- Tempusdominus Bootstrap 4 -->
	<script
		src="Resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
	<!-- Bootstrap Switch -->
	<script
		src="Resources/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	<!-- AdminLTE App -->
	<script src="Resources/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="Resources/js/demo.js"></script>
	<!-- Page script -->
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})
			//Date range picker

		})
	</script>
	<script type="text/javascript">
		function generateFullName() {
			document.getElementById('end_day').value = document
					.getElementById('end_day').value.slice(3, 6)
					+ document.getElementById('end_day').value.slice(0, 3)
					+ document.getElementById('end_day').value.slice(6, 10);
			;
		}
	</script>