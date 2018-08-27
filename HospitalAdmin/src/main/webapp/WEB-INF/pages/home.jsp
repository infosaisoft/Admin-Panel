<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<h1>${uid}</h1>
<h1>${hid}</h1>

${dto.hid} ${dto.name } ${dto.address } ${dto.city } ${dto.state }
${dto.pincode} ${dto.contact } ${dto.reg_number} ${dto.logo }
${dto.creation_date }
<img src="assets/images/hospital/${dto.logo }">
${filelist}

<form:form method="POST" modelAttribute="departmentcmd">
	<form:input path="dpt_name" />
	<form:input path="dpt_location" />
	<input type="submit" value="Add">
</form:form>
${result}

<c:choose>
	<c:when test="${!empty listdto }">
		<table border="1">
			<tr>
				
				<th>DPTNAME</th>
				<th>DPTLOCATION</th>
				<th>Action</th>
			</tr>
			<c:forEach var="dto" items="${listdto}">
				<tr>
					<td>${dto.dpt_name}</td>
					<td>${dto.dpt_location}</td>
					<td><a class="btn btn-danger btn-sm"
					onclick="return confirm('Are you sure, you want to delete?');"
						href="delete_dpt?dpt_id=${dto.dpt_id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
</c:choose>





