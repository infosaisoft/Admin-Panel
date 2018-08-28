<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid mt-5" style="padding: 0px 30px 20px;">
			<div class="row">
				<div class="col-4">
					<div class="hosp-logo">
						<img src="assets/images/hospital/${dto.logo }">
					</div>
				</div>
				<div class="col-8">
					<div class="hos-details">
						<h3>${dto.name }</h3>
						<div class="row">
							<div class="col-12">								
									
								<!-- Adress Details -->
								<div class="hos-detail-box">
									<div class="row">
										<div class="col-5">
											<p class="hos-detail-sub-head"><i class="fas fa-map-marker-alt"></i> Address</p>
										</div>
										<div class="col-7">
											<div class="hos-inner-details">
												<p>${dto.address }, ${dto.city }, <br/> ${dto.state }, ${dto.pincode}</p>
											</div>
										</div>
									</div>
								</div>
									
								<!-- Phone no Details -->
								<div class="hos-detail-box">
									<div class="row">
										<div class="col-5">
											<p class="hos-detail-sub-head"><i class="fas fa-phone"></i> Phone Number</p>
										</div>
										<div class="col-7">
											<div class="hos-inner-details">
												<p>${dto.contact }</p>
											</div>
										</div>
									</div>
								</div>
									
								<!-- Phone no Details -->
								<div class="hos-detail-box">
									<div class="row">
										<div class="col-5">
											<p class="hos-detail-sub-head"><i class="fas fa-registered"></i> Registration Number</p>
										</div>
										<div class="col-7">
											<div class="hos-inner-details">
												<p>${dto.reg_number }</p>
											</div>
										</div>
									</div>
								</div>
								
								<div class="mt-3 hos-btn-box"><button class="btn btn-primary btn-sm"><i class="far fa-edit"></i> Edit Profile</button></div>
									
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Department Form -->
			<div class="row">
				<div class="col-12">
					<h3 class="sub_head mt-5" style="font-size:18px;">Manage Departments</h3>
					<form:form method="POST" modelAttribute="departmentcmd">
						<table class="table table-bordered table-custom table-shift">
							<thead class="thead-light">
								<tr>
									<th>
										<p style="margin-bottom:3px; font-weight: normal;">Department Name</p>
										<form:input class="form-control" path="dpt_name"
											placeholder="Enter Department Name" required="true" /></th>
									<th>
										<p style="margin-bottom:3px; font-weight: normal;">Department Location</p>
										<form:input class="form-control" path="dpt_location" placeholder="Enter Department Location"
											required="true" /></th>
									<th class="text-center">
										<button type="submit"
											class="btn btn-primary btn-sm">Add</button>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty listdto }">
										<c:forEach var="dto" items="${listdto}">
											<tr>
												<td>${dto.dpt_name}</td>
												<td>${dto.dpt_location}</td>
												<td class="text-center"><a
													href="delete_dpt?dpt_id=${dto.dpt_id}"
													onclick="return confirm('Are you sure, you want to delete?');"
													class="btn btn-danger btn-sm CursorPointer"
													data-toggle="tooltip" data-placement="top"
													title="Delete Department"> <i class="far fa-trash-alt"></i>
												</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tbody>
											<tr>
												<td colspan="3" class="text-center">No Records Found</td>
											</tr>
										</tbody>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</form:form>
					${ delete }
				</div>
			</div>
			
		</div>
	</div>

	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>



