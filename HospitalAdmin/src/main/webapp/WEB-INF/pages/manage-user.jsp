<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Manage Users</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<div class="row justify-content-end">
				<div class="col-2 text-right">
					<button type="button" data-toggle="modal" data-target="#AddUser"
						class="btn btn-success">
						<i class="fas fa-plus"></i> Add User
					</button>
				</div>
				<div class="col-4">
					<div class="input-group mb-3 SearchBox">
						<input type="text" class="form-control"
							placeholder="Search In Table">
						<div class="input-group-append">
							<button class="btn btn-primary" type="button">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</div>
			</div>

			<table class="table table-bordered table-custom ">
				<c:choose>
					<c:when test="${!empty userDto}">
						<thead class="thead-light">
							<tr>
								<th>Photo</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Nick Name</th>
								<th>Gender</th>
								<th>Address</th>
								<th>Mobile No.</th>
								<th>Last Login Time</th>
								<th>adminid</th>
								<th>Action</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${userDto}">
								<tr>
							
									<td><img src="assets/images/hospital/${user.photo }" height="50" width="50"></td>
									<td>${user.fname}</td>
									<td>${user.lname}</td>
									<td>${user.nick_name}</td>
									<td>${user.gender}</td>
									<td>${user.address}</td>
									<td>${user.contact}</td>
									<td>${user.last_login}</td>
									<td>${user.admin_id }</td>
									<td>
										<button type="button" class="btn btn-primary btn-sm CursorPointer" data-toggle="tooltip" data-placement="top" title="Edit User">
											<i class="far fa-edit"></i>
										</button>
										<a href="delete_admin?admin_id=${user.admin_id}" class="btn btn-danger btn-sm CursorPointer" data-toggle="tooltip" data-placement="top" title="Delete User">
											<i class="far fa-trash-alt"></i>
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</c:when>

					<c:otherwise>
						<tbody>
							<tr>
								<td>No Records Found</td>
							</tr>
						</tbody>
					</c:otherwise>

				</c:choose>
			</table>
		</div>
	</div>
<<<<<<< HEAD
=======
 <p>${userResult}</p>
<script>
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
</script>
>>>>>>> branch 'master' of https://github.com/infosaisoft/Hospital-Admin.git

	<!-- Add User Modal Start  -->
	<div class="modal fade" id="AddUser" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg">
			<div class="modal-content AddUserContent">
				<form:form method="POST" modelAttribute="insert_user" enctype="multipart/form-data">
				<div class="modal-header">
					<h5 class="modal-title">Add User</h5>
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
								<form:input class="form-control" path="fname" placeholder="Enter First Name"/>
								<form:errors path="fname" />
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label>Last Name</label> 
								<form:input class="form-control" path="lname" placeholder="Enter Last Name"/>
								<form:errors path="lname" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label>Nick Name</label> 
								<form:input class="form-control" path="nick_name" placeholder="Enter Username"/>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
							    <label>Assign Role</label>
							    <form:select multiple="true" class="form-control" path="role">
							      <%-- <form:option value="NONE">Assign Role</form:option> --%>
							      <form:options items="${rolelist}"/>
							    </form:select>
						  	</div>
						</div>
						
						<div class="col">
						<dl class="dropdown">   
						    <dt>
							    <a href="#">
							      <span class="hida">Select</span>    
							      <p class="multiSel"></p>  
							    </a>
						    </dt>
						  
						    <dd>
						        <div class="mutliSelect">
						            <ul>
						                <li><input type="checkbox" value="Apple" />Apple</li>
						                <li><input type="checkbox" value="Blackberry" />Blackberry</li>
						                <li><input type="checkbox" value="HTC" />HTC</li>
						                <li><input type="checkbox" value="Sony Ericson" />Sony Ericson</li>
						                <li><input type="checkbox" value="Motorola" />Motorola</li>
						                <li><input type="checkbox" value="Nokia" />Nokia</li>
						            </ul>
						        </div>
						    </dd>
						</dl>
						</div>
						
					</div>
					
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label>Username</label> 
								<form:input class="form-control" path="username" placeholder="Enter Username"/>
								<form:errors path="username" />
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label>Password</label> 
								<form:password class="form-control" path="password" placeholder="Enter Password"/>
								<form:errors path="password" />
							</div>
						</div>
					</div>
					
					<div class="row">						
						<div class="col-6">
							<div class="form-group">
								<label>Confirm Password</label> 
								<form:password class="form-control" path="cpassword" placeholder="Confirm Password"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label>Address</label> 
								<form:input class="form-control" path="address" placeholder="Enter Address"/>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
								<label>Mobile No.</label> 
								<form:input class="form-control" path="contact" placeholder="Enter Mobile Number"/>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col">
							<div class="form-group GenderBox">
								<div><label>Gender</label></div>
								<div class="form-check form-check-inline">
									  <form:radiobutton class="form-check-input" path="gender" value="male"/>
									  <label class="form-check-label" for="inlineRadio1">Male</label>
								</div>
								<div class="form-check form-check-inline">
									  <form:radiobutton class="form-check-input" path="gender" value="female"/>
									  <label class="form-check-label" for="inlineRadio2">Female</label>
								</div>
							</div>
						</div>
						<div class="col">
							<div class="form-group">
							    <label>Photo</label>
							    <input type="file" name="photo" class="form-control-file">
						  	</div>
						</div>
					</div>
					
					<p>${userResult }</p>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary btn-md"
						data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary btn-md">Submit</button>
				</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Add User Modal End  -->

	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>