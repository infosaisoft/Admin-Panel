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
								placeholder="Enter Name" />
							<form:errors path="name" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Address</label>
							<form:input class="form-control" path="address"
								placeholder="Enter Address" />
							<form:errors path="address" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>City</label>
							<form:input class="form-control" path="city"
								placeholder="Enter City" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>State</label>
							<form:input class="form-control" path="state"
								placeholder="Enter State" />
						</div>
					</div>
				</div>

				<div class="row">

					<div class="col">
						<div class="form-group">
							<label>Pincode.</label>
							<form:input class="form-control" path="pincode"
								placeholder="Enter Pin Code" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>contact</label>
							<form:input class="form-control" path="contact"
							placeholder="Enter Contact No." />
						</div>
					</div>
				</div>

					<div class="row">
						<div class="col">
							<div class="form-group">
								<label>Registration No.</label>
								<form:input class="form-control" path="reg_number"
								placeholder="Enter Contact No." />
							</div>
						</div>

						<div class="col">
							<div class="form-group">
								<label>Logo</label>
								<div>
									<img src="assets/images/hospital/${HospitalCmd.logo}" height="100"
										width="100"> <input class="mt-2" type="file"
										name="logo_photo" value="logo_photo" class="form-control-file">
								</div>

							</div>
						</div>

					</div>
					
					<div class="row">
						<div class="col">
							<a href="home" class="btn btn-secondary btn-md">Cancel</a>
							<button type="submit" class="btn btn-primary btn-md">Save Hospital</button>						
						</div>
					</div>
					
			</form:form>
			<p>${modify}</p>

		</div>
	</div>


	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>