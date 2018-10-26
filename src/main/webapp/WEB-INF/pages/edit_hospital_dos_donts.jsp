<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Edit HospitalDosDonts</h3>
		</div>
	</div>
	<div class="mid_content_section section_padding">
		<div class="container-fluid">
			<div class="row">

				<form:form method="POST" modelAttribute="edithospital_dos_donts"  enctype = "multipart/form-data">
					

					<div class="form-group">
						<label for="formGroupExampleInput">Dos Donts Name</label>
						<form:input class="form-control" path="name"
							value="${dto.name }" />
					</div>

					<div class="form-group">
						<label for="formGroupExampleInput">Description</label>
						<form:input class="form-control" path="description"
							value="${dto.description }" />
					</div>
					
					<div class="form-group">
						<label>Images</label> <img
							src="assets/images/hospitaldosdonts/images/${dto.images}" height="100"
							width="100"> <input type="file" name="images"
							class="form-control-file">
					</div>
					
					<div class="form-group">
						<label>Videos</label> <img
							src="assets/images/hospitaldosdonts/videos/${dto.videos}" height="100"
							width="100"> <input type="file" name="video"
							class="form-control-file">
					</div>
					
					<div class="form-group">
						<label>Document</label> <img
							src="assets/images/hospitaldosdonts/videos/${dto.document}" height="100"
							width="100"> <input type="file" name="document"
							class="form-control-file">
					</div>
					
					<input type="submit" value="Update" class="btn btn-success">

				</form:form>
				${updatemsg}
			</div>
		</div>
	</div>
</div>