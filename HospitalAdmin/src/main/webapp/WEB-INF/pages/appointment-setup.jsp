<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Appointment Setup</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<div class="row">
				<div class="col-8">
					<h3 class="sub_head">Create Shifts</h3>
					<table class="table table-bordered table-custom table-shift">
						<thead class="thead-light">
							<form:form method="POST" id="shiftadd" modelAttribute="shiftadd">
								<tr>
									<th><form:input class="form-control" path="shift_name"
											placeholder="Enter Shift Name" /></th>
									<th><form:input class="form-control" type="time" path="start_time"
											placeholder="Shift Start Time" /></th>
									<th><form:input class="form-control" type="time" path="end_time"
											placeholder="Shift End Time" /></th>
									<th>
										<button id="resetForm" type="submit" class="btn btn-primary btn-sm">
											Add Shift</button>
									</th>
								</tr>
							</form:form>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${!empty listdto }">
									<c:forEach var="dto" items="${listdto}">
										<tr>
											<td>${dto.shift_name}</td>
											<td>${dto.start_time}</td>
											<td>${dto.end_time}</td>
											<td class="text-center">
												<a href="delete_shift?sft_id=${dto.shift_id}"
												onclick="return confirm('Are you sure, you want to delete?');"
													class="btn btn-danger btn-sm CursorPointer"
													data-toggle="tooltip" data-placement="top"
													title="Delete Shift">
													<i class="far fa-trash-alt"></i>
												</a>
											</td>
										</tr>
									</c:forEach>
								</c:when>	
								<c:otherwise>
									<tbody>
										<tr>
											<td colspan="4" class="text-center">No Records Found</td>
										</tr>
									</tbody>
								</c:otherwise>							
							</c:choose>
						</tbody>
					</table>
					${ delete }
				</div>
				<div class="col-4">
					<h3 class="sub_head">General Settings</h3>
					<form class="FormSetting">

						<label>Slot Duration (per hour)</label>
						<div class="row">
							<div class="col-9">
								<div class="form-group">
									<input class="form-control" name="username"
										placeholder="Enter Slot Duration" />
								</div>
							</div>
							<div class="col-3">
								<div class="form-group">
									<label>Mins</label>
								</div>
							</div>
						</div>

						<label>Max. patients in each slot</label>
						<div class="row">
							<div class="col-9">
								<div class="form-group">
									<input class="form-control" name="username"
										placeholder="Total Patients" />
								</div>
							</div>
							<div class="col-3">
								<div class="form-group">
									<label>Patients</label>
								</div>
							</div>
						</div>

						<div class="btn_box text-right">
							<button type="submit" class="btn btn-primary btn-sm">Save
								Setting</button>
						</div>

					</form>
				</div>
			</div>

		</div>
	</div>


<script>
	$("#resetForm").click(function(){
		$("#shiftadd").reset();
	});
</script>

	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>