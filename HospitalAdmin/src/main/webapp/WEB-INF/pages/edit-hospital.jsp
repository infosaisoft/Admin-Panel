<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Edit Hospital</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<form:form method="POST" modelAttribute="HospitalCmd"
				enctype="multipart/form-data">

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>Name</label>
							<form:input class="form-control" path="name"
								placeholder="Enter First Name" />
							<form:errors path="name" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Address</label>
							<form:input class="form-control" path="address"
								placeholder="Enter Last Name" />
							<form:errors path="address" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>City</label>
							<form:input class="form-control" path="city"
								placeholder="Enter Username" />
						</div>
					</div>

				</div>

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>State</label>
							<form:input class="form-control" path="state"
								placeholder="Enter Address" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Pincode.</label>
							<form:input class="form-control" path="pincode"
								placeholder="Enter Mobile Number" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<div class="form-group GenderBox">
							<div>
								<label>contact</label>
							</div>
							<div class="form-check form-check-inline">
								<form:input class="form-check-input" path="contact" />
							</div>
						</div>
					</div>


					<div class="row">
						<div class="col">
							<div class="form-group GenderBox">
								<div>
									<label>reg_number</label>
								</div>
								<div class="form-check form-check-inline">
									<form:input class="form-check-input" path="reg_number" />
								</div>
							</div>
						</div>



						<div class="col">
							<div class="form-group">
								<label>logo</label> <img
									src="assets/images/hospital/${userdto.photo}" height="100"
									width="100"> <input type="file" name="logo_photo" value="logo_photo"
									class="form-control-file">

							</div>
						</div>


						<button type="submit" class="btn btn-primary btn-md">Submit</button>
					</div>
			</form:form>
			<p>${modify}</p>

		</div>
	</div>

	<script>
		$(function() {
			$(".multiSelectRole").multiselect({
				header : false,
				noneSelectedText : 'Update Roles'
			});
		});
	</script>

	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>