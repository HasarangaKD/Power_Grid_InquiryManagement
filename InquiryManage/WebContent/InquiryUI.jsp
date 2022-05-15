<%@page import="com.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/InquiryVal.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Inquiry Management</h1>
				<form id="formInquiry" name="formInquiry" method="post" action="InquiryUI.jsp">
					Customer Name: <input id="INname" name="INname" type="text"
						class="form-control form-control-sm"><br> Address: <input
						id="INadderss" name="INadderss" type="text"
						class="form-control form-control-sm"> <br> Date: <input
						id="INdate" name="INdate" type="date"
						class="form-control form-control-sm"> <br> Reason: <input
						id="INreason" name="INreason" type="text"
						class="form-control form-control-sm"> <br> 
						<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary"> 
						<input type="hidden"id="hidInquiryIDSave" name="hidInquiryIDSave" value="">
				</form>

			<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divInquiryGrid">
					<%
					Inquiry inquiryObj = new Inquiry();
						out.print(inquiryObj.readInquiry());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>