<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




<form:form method="POST" modelAttribute="trafficCmd">
	TariFFName::<form:input type="text" path="tariff_name" />
	<input type="submit" value="Add">
</form:form>
${insert}


<c:choose>
	<c:when test="${! empty listdto }">
		<table border="1">
			<tr>

				<th>Tariff Name</th>
				<th>Action</th>
			</tr>
			<c:forEach var="dto" items="${listdto}">
				<tr>
					<td>${dto.tariff_name}</td>
					 <td><a class="btn btn-danger btn-sm"
					onclick="return confirm('Are you sure, you want to delete?');"
						href="delete_tariff?tariff_id=${dto.tariff_id}">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
</c:choose>

