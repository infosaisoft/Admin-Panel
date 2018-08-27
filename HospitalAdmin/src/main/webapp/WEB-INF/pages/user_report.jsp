<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    
    <form:form method="POST" modelAttribute="insert_user">
   page Size :: <input type="text" name="size"><br>
 <input type="submit" value="start Report">
 
    </form:form>


<table class="table table-bordered table-custom ">
				<c:choose>
					<c:when test="${!empty listdto}">
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
							<c:forEach var="user" items="${listdto}">
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
								</tr>
							</c:forEach>
						</table>
					</c:when>

					<c:otherwise>
						<tbody>
							<tr>
								<td>No Records Found</td>
							</tr>
						</tbody>
					</c:otherwise>

				</c:choose>

    
<c:forEach var="i" begin="1" end="${pagecount}" step="1">
  <a href="userdetalis?pageno=${i }">${i}</a>

</c:forEach>
    