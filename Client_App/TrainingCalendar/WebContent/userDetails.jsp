<%@page import="org.training.jsonParser.JSONArray"%>
<%@page import="org.training.jsonParser.JSONObject"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User details</title>
<!-- <META HTTP-EQUIV="refresh" CONTENT="15"> -->
<link rel="stylesheet" href="css/foundation.min.css" type="text/css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/pagestyle.css">
<title>User Details</title>

<script src="js/search.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("table tr:nth-child(odd)").addClass("odd-row");
		$("table td:first-child, table th:first-child").addClass("first");
		$("table td:last-child, table th:last-child").addClass("last");
	});
</script>


</head>
<body>
	<%
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userName = cookie.getValue();
			}
		}
		if (userName == null)
			response.sendRedirect("index.html");
	%>
	<div class="call top-call-to-action">
		<div class="row">
			<div class="large-12 columns">
				<div class="social-icons"></div>

				<span><a href="#">Hi <%=userName%></a></span>
			</div>
		</div>
	</div>

	<div id="content">
		<span style="float: left;"> <a href="adminHome.jsp"><img src="img/home.png"
				width="40" height="40" title="Go home"></a>
		</span>
		<span style="float: right;"> 
				<form action="Logout" method="POST">
				<input type="image" title="Signout" src="img/outbox.png"
					alt="Submit" value="Home" width="40" height="40" value="logout" />
			</form>
	</span>
		<hr>

		<h2>User Details</h2>

		<%
			Object value = null;
			value = request.getAttribute("msg");
			if (value != null) {
		%>
		<span style="float: left;">
		<input type="text" id="searchTerm" onkeyup="doSearch()" title="Search" size="10"/>
			 <div class="scrollWrapper">
			
				<table id="dataTable" cellspacing="0">

					<th>No</th>
					<th>First_Name</th>
					<th>Last_Name</th>
					<th>Email_Id</th>
					<th>Group</th>
					<th>Edit</th>
					<th>Delete</th>

					<%
						JSONArray jsonArray = new JSONArray(value.toString());
							for (int j = 0; j < jsonArray.length(); j++) {
								JSONObject jsonObject = new JSONObject(jsonArray.get(j)
										.toString());
					%>
					<tr>
						<td>
							<%
								out.print(j + 1);
							%>
						</td>
						<td><%=jsonObject.get("firstName").toString()%></td>
						<td><%=jsonObject.get("lastName").toString()%></td>
						<td><%=jsonObject.get("email").toString()%></td>
						<td><%=jsonObject.get("group").toString()%></td>

						<td>
							<form
								action="EditUserAdmin?key=<%=jsonObject.get("email").toString()%>"
								method="POST">
								<input type="image" src="img/edit.png" alt="Submit" value="edit" />
							</form>
						</td>
						<td>
							<form
								action="DeleteUser?key=<%=jsonObject.get("email").toString()%>"
								method="POST">
								<input type="image" src="img/delete.png" alt="Submit"
									value="delete" />
							</form>
						</td>

						<!-- type="image" src="img/user.png" alt="Submit" value="Home" width="25" height="25" -->
					</tr>
					<%
						}
					%>
				</table>
			</div><%
			}
		%> <br>
			<hr>
	</div>
</body>
</html>
