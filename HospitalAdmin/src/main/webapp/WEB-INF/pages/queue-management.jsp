<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Queue Management</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<div class="row">
				<div class="col-12">
					<h3 class="sub_head">Add Queue</h3>
					<form:form method="POST" modelAttribute="queuecmd">
						<table class="table table-bordered table-custom table-shift">
							<thead class="thead-light">
								<tr>
									<th>
										<p  class="week-head">Department Name</p> <form:select
											class="form-control selectDoc" path="dpt_name"
											required="true">
											<form:option value="">Select department</form:option>
											 <form:options items="${dptlist.name}" /> 

										</form:select>
									</th>


									<th><form:input class="form-control" 
											path="room_name" placeholder="Room Name"
											required="true" /></th>
									<th>
										<p class="week-head">Select Doctor</p> <form:select
											class="form-control selectDoc" path="doc_name"
											required="true">
											<form:option value="">Select Doctor</form:option>
											<form:options items="${doclist.name}" />

										</form:select>
									</th>
									<th class="text-center">
										<button type="submit" class="btn btn-primary btn-sm">Add
											Queue</button>
									</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty listdto }">
										<c:forEach var="dto" items="${listdto}">
											<tr>
												<td>${dto.dpt_name}</td>
												<td>${dto.room_name}</td>
												<td>${dto.doc_name}</td>
												<td class="text-center">
												<a href="delete_queue?queue_id=${dto.queue_id}"
													onclick="return confirm('Are you sure, you want to delete?');"
													class="btn btn-danger btn-sm CursorPointer"
													data-toggle="tooltip" data-placement="top"
													title="Delete Queue"> <i class="far fa-trash-alt"></i>
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

			</div>

		</div>
	</div>


	<!-- Footer Start -->
	<div id="footer-section" class="footer-section">
		<p class="text-center">Copyright &copy; Harbor Vision 2018</p>
	</div>
	<!-- Footer End -->

</div>