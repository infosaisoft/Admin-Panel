<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">HospitalDiseases</h3>
		</div>
	</div>
	<div class="mid_content_section section_padding">
		<div class="container-fluid">
			<div class="row">
			
				<c:choose>
					<c:when test="${!empty  listdto}">
						<table class="table table-bordered bg-white">
							<tr>
								<th>SNO</th>
								<th>hid</th>
								<th>Name</th>
								<th>Desc</th>
								<th>Image</th>
								<th>Video</th>
								<th>Documents</th>								
								
							<tr>

								<c:forEach var="dto" items="${listdto}">
									<tr>
										<td>${dto.id}</td>
										<td>${dto.hid}</td>
										<td>${dto.name}</td>
										<td>${dto.description}</td>
										<td><img src="assets/images/\hospitaldiseases/images/${dto.imges }" height="50" width="50"></td>
										<td><img src="assets/images/\hospitaldiseases/videos/${dto.videos }" height="50" width="50"></td>
										<td><img src="assets/images/\hospitaldiseases/documents/${dto.documents }" height="50" width="50"></td>
										
										<td><a class="btn btn-info btn-sm"
											href="edit_hospital_diseases?sno=${dto.id}">edit</a> <a
											class="btn btn-danger btn-sm"
											href="delete_hospital_diseases?sno=${dto.id}">delete</a></td>
									</tr>
								</c:forEach>
						</table>
					</c:when>

					<c:otherwise>
						<h1 style="color: red; text-align: center">No Records found</h1>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>

<br>
<br>
<br>
<c:if test="${!empty result }">
	<h3 style="color: red">Result status ::: ${result}</h3>
</c:if>
<br>
<br>
<br>
<br>
<a href="/home">home</a>