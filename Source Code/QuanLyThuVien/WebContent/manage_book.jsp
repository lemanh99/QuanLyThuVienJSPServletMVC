<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Main content -->
	<section class="content">

		<div class="container-fluid">
			<div class="row">

				<div class="col-md-12">
					<div class="card">
						<form role="form" method="post"
							action="${pageContext.request.contextPath}/SearchBook">
							<div class="card-header">
								<h3 class="card-title">Danh sách sách trong thư viện</h3>

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
						<div class="row justify-content-center">
							<div style="margin-top: 20px; color: red;">${errorString}</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body" >
							<div class="card-header" style="margin-left: -20px; margin-top: -40px;">
								<input type="button" value="Thêm sách"
									class="btn btn-primary"
									onclick="location.href='${pageContext.request.contextPath}/AddBook'">
							</div>
							<table class="table table-bordered table-hover" id="example2">
								<thead>
									<tr>
										<th style="width: 10px">STT</th>
										<th style="width: 318px;">Tên</th>
										<th>Thể loại</th>
										<th>Số lượng</th>
										<th>Ngày nhập</th>
										<th>Hình ảnh</th>
										<th>Chỉnh sửa</th>
										<th>Xóa</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bookList}" var="book" varStatus="loop">
										<tr>
											<td>${loop.index+1}</td>
											<td>${book.getName()}</td>
											<td>${book.getCategory().getName()}</td>
											<td style="text-align: center;">${book.getAmount()}</td>
											<td>${book.getDay()}</td>
											<td style="text-align: center;"><img
												src="Resources/img/products/${book.getImage()}" width="35"
												height="50">
												<figcaption>
													<a href="Resources/img/products/${book.getImage()}"
														style="font-size: 14px;" target="_blank">Xem chi tiết</a>
												</figcaption>
											<td><a
												href="${pageContext.request.contextPath}/EditBook?id=${book.getId()}"
												class="btn btn-sm btn-info">Chỉnh sửa</a></td>
											<td>
												<button type="button" class="btn btn-primary btn-danger"
													data-toggle="modal"
													data-target="#staticBackdrop-${Integer.toString(book.getId())}"
													style="padding-bottom: 5px; padding-top: 3px; font-size: 14px">Xóa</button>
											</td>
										</tr>

										<div class="modal fade"
											id="staticBackdrop-${Integer.toString(book.getId())}"
											data-backdrop="static" data-keyboard="false" tabindex="-1"
											aria-labelledby="staticBackdropLabel" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="staticBackdropLabel1">Chú
															ý</h5>
														<button type="button" class="close" data-dismiss="modal"
															aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body">
														<span class="text-danger"> Bạn có muốn chắc xóa
															cuốn sách '${book.getName()}'</span>
													</div>
													<div class="modal-footer">
														<button type="button"
															class="btn btn-warning  btn-secondary"
															data-dismiss="modal">Hủy</button>
														<form
															action="${pageContext.request.contextPath}/DeleteBook?id=${book.getId()}"
															method="POST">
															<button type="submit" class="btn btn-danger">Xóa</button>
														</form>

													</div>
												</div>
											</div>
										</div>

									</c:forEach>
								</tbody>
							</table>

							<div class="card-header">
								<div class="card-tools">
									<input type="button" value="Xóa tất cả" class="btn btn-danger"
										data-toggle="modal" data-target="#staticBackdrop-DeleteAll">
								</div>
								<div class="modal fade" id="staticBackdrop-DeleteAll"
									data-backdrop="static" data-keyboard="false" tabindex="-1"
									aria-labelledby="staticBackdropLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="staticBackdropLabel1">Chú ý</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<span class="text-danger"> Bạn có muốn chắc xóa tất
													cả sách</span>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-warning  btn-secondary"
													data-dismiss="modal">Hủy</button>

												<button type="submit" class="btn btn-danger"
													onclick="location.href='${pageContext.request.contextPath}/DeleteBook'">Xóa</button>


											</div>
										</div>
									</div>
								</div>
							</div>

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