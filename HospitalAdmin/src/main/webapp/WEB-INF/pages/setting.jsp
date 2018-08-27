<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="POST" class="FormSetting" modelAttribute="setting">
	<label>Slot Duration (per hour)</label>
	<div class="row">
		<div class="col-9">
			<div class="form-group">
				<form:input class="form-control" path="slot_duration"
					placeholder="Enter Slot Duration" />
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
				<form:input class="form-control" path="max_patient"
					placeholder="Total Patients" />
			</div>
		</div>
		<div class="col-3">
			<div class="form-group">
				<label>Patients</label>
			</div>
		</div>
	</div>

	<div class="btn_box text-right">
		<button type="submit" class="btn btn-primary btn-sm">Save
			Setting</button>
	</div>
	${ result_set }
</form:form>