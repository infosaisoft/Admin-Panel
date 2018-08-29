<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Edit User</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<form:form method="POST" modelAttribute="insert_user"
				enctype="multipart/form-data">

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>First Name</label>
							<form:input class="form-control" path="fname"
								placeholder="Enter First Name" />
							<form:errors path="fname" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Last Name</label>
							<form:input class="form-control" path="lname"
								placeholder="Enter Last Name" />
							<form:errors path="lname" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>Nick Name</label>
							<form:input class="form-control" path="nick_name"
								placeholder="Enter Username" />
						</div>
					</div>
					<div class="col">
						<div class="form-group multiSelectRoleDiv">
							<label>Assign Role</label>
							<%-- <form:select multiple="true" class="form-control" path="role">
								<form:option value="${dto.role}">${userdto.role }</form:option>
							</form:select> --%>
							<form:select
								class="form-control multiSelectRole" path="role"
								multiple="multiple" size="5" required="true">
								<form:options items="${rolelist }"/>
							</form:select>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<div class="form-group">
							<label>Address</label>
							<form:input class="form-control" path="address"
								placeholder="Enter Address" />
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Mobile No.</label>
							<form:input class="form-control" path="contact"
								placeholder="Enter Mobile Number" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col">
						<div class="form-group GenderBox">
							<div>
								<label>Gender</label>
							</div>
							<div class="form-check form-check-inline">
								<form:radiobutton class="form-check-input" path="gender"
									value="male" />
								<label class="form-check-label" for="inlineRadio1">Male</label>
							</div>
							<div class="form-check form-check-inline">
								<form:radiobutton class="form-check-input" path="gender"
									value="female" />
								<label class="form-check-label" for="inlineRadio2">Female</label>
							</div>
						</div>
					</div>
					<div class="col">
						<div class="form-group">
							<label>Photo</label> <img
								src="assets/images/hospital/${userdto.photo}" height="100"
								width="100"> <input type="file" name="photo"
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