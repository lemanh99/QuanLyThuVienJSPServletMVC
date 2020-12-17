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
				<div class=""></div>
				<div class="col-md-12">
					<div class="card card-primary" style="margin-top: 12px;">
						<div class="card-header">
							<h3 class="card-title">Hướng dẫn sử dụng trang web</h3>
						</div>
						<div style="margin-top: 12px; margin-left: 12px;">
							<%
								if (request.getSession().getAttribute("User") == null) {
							%>
							<p>
								&nbsp;- Muốn sử dụng trang web này thì đầu tiên bạn cần <b>
									<a href="${pageContext.request.contextPath}/Login">đăng
										nhập.</a>
								</b>. Tên tài khoản: <b>nobita</b>, Mật khẩu: <b>doraemon</b>.
							</p>
							<%
								} else {
							%>
							<p>&nbsp;Cám ơn bạn đã đăng nhập, bây giờ bạn có thể sử dụng
								trang web.</p>
							<%
								}
							%>
							<p>- Các chức năng của trang web là quản lý sách và quản lý
								mượn sách</p>
							<p>
								<b>I. Quản lý sách.</b>
							</p>
							<p>
								- Muốn xem danh sách trong thư viện chọn <b>Quản lý sách
									-&gt; Danh sách.</b>
							</p>
							<p>
								<b>-&nbsp; </b>Trong mục danh sách:&nbsp;
							</p>
							<p></p>
							<ol style="text-align: left;">
								<li>Muốn tìm kiếm sách trong danh sách thì gõ vào ô Tìm
									kiếm và nhấn enter.</li>
								<li>Muốn chỉnh sửa thông tin sách nào thì nút <b>Chỉnh
										sửa</b>-&gt; Chỉnh sửa những thông tin cần thiết-&gt; Nhấn <b>Lưu-</b>&gt;
									nhấn <b>Hủy</b> để trở lại danh sách.
								</li>
								<li>Muốn xóa sách nào thì bạn chọn nút <b>Xóa</b> tương ứng
									của sách đó . Có thông báo xác nhận hiện ra. Chọn <b>Xóa</b> để
									xóa vĩnh viễn hoặc chọn <b>Hủy</b> để hủy thao tác xóa.
								</li>
								<li>Muốn xóa tất cả thì chọn nút <b>Xóa tất cả</b>.
								</li>
							</ol>
							<p>
								- Muốn thêm sách thì chọn&nbsp;<b>Quản lý sách -&gt; Thêm
									sách -&gt; </b>Điền những thông tin cần thiết , chọn thể loại sách
								và hình ảnh tương ứng-&gt; Nhấn <b>Lưu</b> để lưu lại và nhấn <b>Hủy</b>
								để trở lại danh sách.
							</p>
							<p>
								-Muốn xem thể loại sách thì chọn&nbsp;<b>Quản lý sách -&gt;
									Thể loại.</b>
							</p>
							<p>-Trong mục thể loại, muốn thêm thể loại thì chọn thêm thể
								loại, muốn chỉnh sửa chọn chỉnh sửa, muốn xóa thì chọn nút Xóa.</p>
							<p>
								<b>II. Quản lý mượn sách.</b>
							</p>
							<p>
								- Muốn thêm người mượn thì chọn <b>Quản lý mượn
									sách-&gt;Thêm người mượn sách.</b>
							</p>
							<p>
								-Muốn xem danh sách đang mượn sách thì chọn <b>Quản lý mượn
									sách-&gt;Danh sách đang mượn.</b> Ở mục này bạn có thể tìm kiếm,
								quản lý xác nhận đã đã sách hay chưa. Nếu người đó đến trả thì
								bạn chọn <b>Đã trả.</b>
							</p>
							<p>
								- Muốn xem danh sách đang mượn sách thì chọn<b> Quản lý mượn
									sách-&gt;Danh sách đang mượn.&nbsp;</b>
							</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>