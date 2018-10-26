<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Edit HospitalDiseases</h3>
		</div>
	</div>
	<div class="mid_content_section section_padding">
		<div class="container-fluid">
			<div class="row">

				<form:form method="POST" modelAttribute="edithospital_diseases"  enctype = "multipart/form-data">
					<div class="form-group">
						<label for="formGroupExampleInput">Hid</label>
						<form:input class="form-control" path="hid" value="${dto.hid }" />
					</div>
					<div class="form-group">
						<label for="formGroupExampleInput">Disease Name</label>
						<form:input class="form-control" path="name"
							value="${dto.name }" />
					</div>
					<div class="form-group">
						<label for="formGroupExampleInput">Disease Description</label>
						<form:input class="form-control" path="description"
							value="${dto.description }" />
					</div>

					<div class="form-group">
						<label>Images</label> <img
							src="assets/images/hospitaldiseases/images/${dto.imges}" height="100"
							width="100"> <input type="file" name="photo"
							class="form-control-file">
					</div>
					
					<div class="form-group">
						<label>Videos</label> <img
							src="assets/images/hospitaldiseases/videos/${dto.videos}" height="100"
							width="100"> <input type="file" name="video"
							class="form-control-file">
					</div>
					
					<div class="form-group">
						<label>Documents</label> <img
							src="assets/images/hospitaldiseases/documents/${dto.documents}" height="100"
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