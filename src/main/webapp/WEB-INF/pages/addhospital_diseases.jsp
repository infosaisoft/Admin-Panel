
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Hospital Diseases</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">
			<div class="row">

				<form:form method="post" modelAttribute="hospital_diseases" enctype = "multipart/form-data">

					<div class="form-group">
						<label for="formGroupExampleInput">Name</label>
						<form:input class="form-control" path="name" />
					</div>

					<div class="form-group">
						<label for="formGroupExampleInput">Disease Description</label>
						<form:input class="form-control" path="description" />
					</div>


					<div class="form-group">
						<label class="form-group control-lable" for="file">Upload
							a image</label>
						<form:input type="file" path="imges"
							class="form-control input-sm" />
					</div>


					<div class="form-group">
						<label class="form-group control-lable" for="file">Upload
							a video</label>
						<form:input type="file" path="videos"
							class="form-control input-sm" />
					</div>
					
					<div class="form-group">
						<label class="form-group control-lable" for="file">Upload
							a documents</label>
						<form:input type="file" path="documents"
							class="form-control input-sm" />
					</div>

		
					<input type="submit" value="Add">

				</form:form>
				${result}
			</div>
		</div>
	</div>
</div>