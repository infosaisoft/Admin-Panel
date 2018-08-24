<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Content Start -->
<div class="content_wrapper">

	<div class="content_page_heading_section">
		<div class="container-fluid">
			<h3 class="content_page_heading">Appointment Setup</h3>
		</div>
	</div>

	<div class="mid_content_section section_padding">
		<div class="container-fluid">

			<div class="row">
				<div class="col-8">
					<h3 class="sub_head">Create Shifts</h3>
					<table class="table table-bordered table-custom table-shift">
						<thead class="thead-light">
							<tr>
								<th><input type="text" class="form-control" name="shift_name" placeholder="Enter Shift Name"/></th>
								<th><input type="text" class="form-control" name="start_time" placeholder="Shift Start Time"/></th>
								<th><input type="text" class="form-control" name="end_time" placeholder="Shift End Time"/></th>
								<th>
									<button type="button" class="btn btn-primary btn-sm">
										Add Shift
									</button>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td class="text-center">
									<button type="button"
										class="btn btn-danger btn-sm CursorPointer"
										data-toggle="tooltip" data-placement="top" title="Delete Shift">
										<i class="far fa-trash-alt"></i>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col-4">
					<h3 class="sub_head">General Settings</h3>
					<form class="FormSetting">
					
						<label>Slot Duration (per hour)</label> 
						<div class="row">
							<div class="col-9">
								<div class="form-group">
									<input  class="form-control" name="username" placeholder="Enter Slot Duration"/>
								</div>
							</div>
							<div class="col-3">
								<div class="form-group">
									<label>Mins</label>									
								</div>
							</div>
						</div>
						
						<label>Max. patients in each slot</label> 
						<div class="row">
							<div class="col-9">
								<div class="form-group">
									<input  class="form-control" name="username" placeholder="Total Patients"/>
								</div>
							</div>
							<div class="col-3">
								<div class="form-group">
									<label>Patients</label>									
								</div>
							</div>
						</div>
						
						<div class="btn_box text-right">
							<button type="submit" class="btn btn-primary btn-sm">Save Setting</button>
						</div>
						
					</form>
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