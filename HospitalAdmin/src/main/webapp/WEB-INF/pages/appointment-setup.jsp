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
					<form:form method="POST" action="appointment-setup?action=1"
						modelAttribute="shiftadd">
						<table class="table table-bordered table-custom table-shift">
							<thead class="thead-light">
								<tr>
									<th><form:input class="form-control" path="shift_name"
											placeholder="Enter Shift Name" required="true" />
											<form:errors path="shift_name" />
											
											</th>
									<th><form:input class="form-control" type="time"
											path="start_time" placeholder="Shift Start Time"
											required="true" /></th>
									<th><form:input class="form-control" type="time"
											path="end_time" placeholder="Shift End Time" required="true" /></th>
									<th>
										<button id="resetForm" type="submit"
											class="btn btn-primary btn-sm">Add Shift</button>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty listdto }">
										<c:forEach var="dto" items="${listdto}">
											<tr>
												<td>${dto.shift_name}</td>
												<td>${dto.start_time}</td>
												<td>${dto.end_time}</td>
												<td class="text-center"><a
													href="delete_shift?sft_id=${dto.shift_id}"
													onclick="return confirm('Are you sure, you want to delete?');"
													class="btn btn-danger btn-sm CursorPointer"
													data-toggle="tooltip" data-placement="top"
													title="Delete Shift"> <i class="far fa-trash-alt"></i>
												</a></td>
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
					</form:form>
					${ delete }
				</div>
				<div class="col-4">
					<h3 class="sub_head">General Settings</h3>

					<form method="POST" action="appointment-setup?action=2"
						id="FormSetting" class="FormSetting">
						<label>Slot Duration (per hour)</label>
						<div class="row">
							<div class="col-9">
								<div class="form-group">
									<input class="form-control" type="number" id="slot_duration"
										name="slot_duration" value="${ setdto.slot_duration }"
										placeholder="Enter Slot Duration" required="true" />
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
									<input class="form-control" type="number" id="max_patient"
										name="max_patient" value="${ setdto.max_patient }"
										placeholder="Total Patients" required="true" />
								</div>
							</div>
							<div class="col-3">
								<div class="form-group">
									<label>Patients</label>
								</div>
							</div>
						</div>

						<input type="hidden" id="hiddenval" value="1" />

						<div class="btn_box text-right">
							<input type="submit" value="Save Setting" name="action1"
								class="btn btn-primary btn-sm">
						</div>
						<div id="successMessage"></div>
						${ result_set }
					</form>

				</div>
			</div>

			<!--  Doctor Availability Section  -->

			<div class="row">
				<div class="col-12">
					<h3 class="sub_head" style="margin-top: 20px;">Doctor
						Availability: Current Week</h3>
					<form:form method="POST" action="appointment-setup?action=3"
						modelAttribute="shiftadd">
						<table class="table table-bordered table-custom table-shift">
							<thead class="thead-light">
								<tr>
									<th>
									
									<div class="row">
										<div class="col-6">
											<p class="week-head">Select Doctor</p> 
										<form:select
											class="form-control selectDoc" path="doc_name"
											required="true">
											<form:option value="">Select Doctor</form:option>
											<form:options value="${doclist.id}" items="${doclist.name}" />

										</form:select>
										</div>
										<div class="col-6">
											<p class="week-head">Select Department</p> 
										<form:select
											class="form-control selectDoc" path="dpt_name"
											required="true">
											<form:option value="">Select Department</form:option>
											<form:options value="${dptlist.id}" items="${dptlist.name}" />

										</form:select> 
										</div>
									</div>
										
									</th>
									

									
									<th>
										<p class="week-head">Select Date</p>
										<form:input path="date"  class="form-control selectDoc"/>
										 <%-- <form:select
											class="form-control selectDoc" path="date"
											required="true">
											<form:option value="">Select Date</form:option>
											<form:options items="${shiftdate.date}" />

										</form:select>
 --%>									</th>
 									<th>
										<p class="week-head">Shift</p> <form:select
											class="form-control multiSelect" path="shift_name"
											multiple="multiple" size="5" required="true">
											<form:options items="${shiftlist.shift}" />
											<form:option value="Off">Off</form:option>
										</form:select>
									</th>
									
									<th>
										<button type="submit" class="btn btn-primary btn-sm">Add</button>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty listdocdto }">
										<c:forEach var="dto" items="${listdocdto}">
											<tr>
												<td>${dto.doc_name}</td>
												<%-- <td>${dto.mon}</td>
												<td>${dto.tue}</td>
												<td>${dto.wed}</td>
												<td>${dto.thu}</td>
												<td>${dto.fri }</td>
												<td>${dto.sat}</td>
												<td>${dto.sun }</td> --%>
											
												<td>${dto.date }</td>
													<td>${dto.shift_name }</td>

												<td class="text-center"><a
													href="delete_app?avail_id=${dto.avail_id}"
													onclick="return confirm('Are you sure, you want to delete?');"
													class="btn btn-danger btn-sm CursorPointer"
													data-toggle="tooltip" data-placement="top"
													title="Delete Shift"> <i class="far fa-trash-alt"></i>
												</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tbody>
											<tr>
												<td colspan="9" class="text-center">No Records Found</td>
											</tr>
										</tbody>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</form:form>
				</div>
			</div>

			<!--  Doctor Availability Section End -->

		</div>
	</div>

	<script>
		$(function() {
			$(".multiSelect").multiselect({
				header : false,
				noneSelectedText : 'Select',
				width : 75,
				minWidth : 75
			});
		});
	</script>
	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>