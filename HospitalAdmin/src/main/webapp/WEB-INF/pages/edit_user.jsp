<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>



<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="POST" modelAttribute="insert_user"
	enctype="multipart/form-data">
	<div class="modal-header">
		<h5 class="modal-title">Edit User</h5>
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<div class="modal-body">

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
				<div class="form-group">
					<label>Assign Role</label>
					<form:select multiple="true" class="form-control" path="role">
						<form:option value="${dto.role}">${userdto.role }</form:option>
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
		</div>

		<p>${modify}</p>

	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-secondary btn-md"
			data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary btn-md">Submit</button>
	</div>
</form:form>
