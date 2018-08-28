<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Tariff/Rate Charts</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">
			<div class="row">
				<div class="col-4">
					<h3 class="sub_head">Create Tariff</h3>
					
					<form:form method="POST" action="tariff?action=1" modelAttribute="trafficCmd">
						<table class="table table-bordered table-custom table-shift">
							<thead class="thead-light">
								<tr>
									<th><form:input class="form-control" type="text"
											placeholder="Enter Tariff Name" path="tariff_name" required="true"/></th>
									<th><button class="btn btn-primary btn-sm" type="submit">Add</button></th>
								</tr>
							</thead>
							<c:choose>
								<c:when test="${! empty listdto }">
									<c:forEach var="dto" items="${listdto}">
										<tr>
											<td>${dto.tariff_name}</td>
											<td><a class="btn btn-danger btn-sm"
												onclick="return confirm('Are you sure, you want to delete?');"
												data-toggle="tooltip" data-placement="top"
												href="delete_tariff?tariff_id=${dto.tariff_id}">
													<i class="far fa-trash-alt"></i>
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
						</table>
					</form:form>
${ insert }

				</div>

				<div class="col-12">
					<div class="row">
						<div class="col-4"><h3 class="sub_head" style="margin-bottom:0px; margin-top:5px;">Add Tariff</h3></div>
						<div class="col-8 text-right">
							<button class="btn btn-info btn-sm" style="margin-bottom:10px; margin-right:5px;">Download Excel for Bulk Upload</button>
							<button class="btn btn-success btn-sm" style="margin-bottom:10px;">Upload</button>
						</div>
					</div>
					
					
					<form:form method="POST" action="tariff?action=2" modelAttribute="trafficCmd">
						<table class="table table-bordered table-custom table-tariff">
							<thead class="thead-light">
								<tr>
									<th>
										<form:select class="form-control" path="tariff_name" style="min-width:110px" required="true">
											<form:option value="">Select Tariff</form:option>									     
									     	<form:options items="${tarifflist.getname}" />
									    </form:select>
									</th>
									<th>
										<form:input class="form-control" type="text" style="max-width:130px"
											placeholder="Service Name" path="service_name" required="true"/>
									</th>
									<th>
										<form:input class="form-control" type="text" 
											placeholder="Service Category" path="service_category" required="true"/>
									</th>
									<th>
										<form:input class="form-control" type="number" style="max-width:100px"
											placeholder="Rate" path="rates" required="true"/>
									</th>
									<th>
										<form:select class="form-control" path="doctor_name" required="true">									     
									      	<form:options items="${tarifflist}"/>
									    </form:select>
									</th>
									<th>
										<form:select class="form-control" path="department" required="true">									     
									      	<form:options items="${tarifflist}"/>
									    </form:select>
									</th>
									<th>
										<div class="form-group form-check form-mand">
										    <form:checkbox class="form-check-input" path="is_mandatory"/>
										    <label class="form-check-label" >Is Mandatory</label>
									 	</div>
									</th>
									<th><button class="btn btn-primary btn-sm" type="submit">Add</button></th>
								</tr>
							</thead>
							<c:choose>
								<c:when test="${! empty ratelistdto }">
									<c:forEach var="dto" items="${ratelistdto}">
										<tr>
											<td>${dto.tariff_name}</td>
											<td>${dto.service_name}</td>
											<td>${dto.service_category}</td>
											<td>${dto.rates}</td>
											<td>${dto.doctor_name}</td>
											<td>${dto.department}</td>
											<td>${dto.is_mandatory}</td>
											<td><a class="btn btn-danger btn-sm"
												onclick="return confirm('Are you sure, you want to delete?');"
												data-toggle="tooltip" data-placement="top"
												href="delete_rate?rate_id=${dto.rate_id}">
													<i class="far fa-trash-alt"></i>
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
						</table>
						${deleteRate }
					</form:form>	
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