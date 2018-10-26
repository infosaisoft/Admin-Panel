<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Login History</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<div class="row">
				<div class="col-12">
						<table class="table table-bordered table-custom table-shift">
							<thead class="thead-light">
								<tr>
									<th>Session ID</th>
									<th>Login Time</th>
									<th>Logout Time</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty listdto }">
										<c:forEach var="dto" items="${listdto}">
											<tr>
												<td>${dto.session_id}</td>
												<td>${dto.login_time}</td>
												<td>${dto.logout_time}</td>
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