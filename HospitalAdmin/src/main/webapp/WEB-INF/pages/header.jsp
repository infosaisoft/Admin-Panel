<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<<<<<<< HEAD

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

=======
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Top Fixed Header -->
<div class="content_header">
	<div class="container-fluid">
		<div class="row">
			<div class="col-12 col-sm-6">

				<div class="logo_section">
					<div class="head_left_section" id="menu_tgl_btn">
						<i class="fa fa-outdent"></i>
					</div>
					<span>Admin Panel</span>
				</div>
			</div>
			<div class="col-12 col-sm-6">
				<ul class="head_right_section">
					<li>Welcome! <span id="shopName"></span></li>
					<li><a id="logoutSession" href="logout"><i
							class="fa fa-sign-out"></i> Logout</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<!-- Fixed Left Menu start -->
<div class="left_header_section">

	<div class="menu_blank_sect"></div>
	<ul class="left_nav_menu">
		

		<li class='<c:if  test="${activeurl.url=='home'}"> active_li</c:if>'><a href="home"><i class="fas fa-home"></i>Home</a></li>
		<li class='<c:if  test="${activeurl.url=='user'}">active_li</c:if>'><a href="manage-user"><i class="fas fa-users"></i>User Management</a></li>
		<li class='<c:if  test="${activeurl.url=='tariff'}">active_li</c:if>'><a href="tariff"><i class="fas fa-rupee-sign"></i>Tariff/Rate Charts</a></li>
		<li class='<c:if  test="${activeurl.url=='appcontroller'}">active_li</c:if>'><a href="appointment-setup"><i class="far fa-calendar-check"></i>Appointment Setup</a></li>
		<li class='<c:if  test="${activeurl.url=='queue'}">active_li</c:if>'><a href="queue-management"><i class="fas fa-list-ol"></i>Queue Management</a></li>
		<li><a href="#"><i class="fas fa-th"></i>Content Management</a></li>
		<li><a href="#"><i class="far fa-address-card"></i>Letter Head Design</a></li>
		<li><a href="#"><i class="fas fa-file-medical"></i>MIS Reports</a></li>
		
		
	<!-- 	<li><a class="left_main_menu" href="javascript:void();"><i class="fa fa-tachometer"></i> 
		 		Admin Management <i class="fa fa-angle-right float-right m-top5"></i></a>
			<ul class="left_sub_menu">
				<li><a href="admin-add.html"><i class="fa fa-caret-right"></i>Add
						Sub Admin</a></li>
				<li><a href="#"><i class="fa fa-caret-right"></i>Sub Admin
						Listings</a></li>
			</ul>
		</li> -->
		<li></li>

	</ul>

</div>
