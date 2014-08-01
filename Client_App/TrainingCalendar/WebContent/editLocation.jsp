<%@page import="org.training.jsonParser.JSONArray"%>
<%@page import="org.training.jsonParser.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" href="css/foundation.min.css" type="text/css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<title>Update Topic</title>
<link rel="stylesheet" type="text/css" href="css/pagestyle.css">
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

				<span><a href="editUserbyuser.jsp?id_email=<%=userName%>">Hi
						<%=userName%></a></span>
			</div>
		</div>
	</div>
	<div id="content">

	 <a href="adminHome.jsp"><img src="img/home.png"
				width="40" height="40" title="Go home"></a>
		<h2>Location Updates</h2>
		<%
			Object value = null;
			value = request.getAttribute("msg");
			if (value != null) {
				JSONArray jsonArray = new JSONArray("[" + value.toString()
						+ "]");
				JSONObject jsonObject = new JSONObject(jsonArray.get(0)
						.toString());
			
		%>
		<form action="UpdateLocation?key=<%=jsonObject.get("locationId").toString()%>" method="post">
			<table>
				
				<tr>
					<td>Location_Name:</td>
					<td><select name="location">
							<option value="<%=jsonObject.get("location").toString()%>"><%=jsonObject.get("location").toString()%>
							</option>
							<option value="Boiler">Boiler</option>
							<option value="Area52">Area52</option>
							<option value="Carbon">Carbon</option>
							<option value="Kernel">Kernel</option>
							<option value="cpu">cpu</option>
							
					</select></td>
				</tr>
				
				<tr>
					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="Update"></td>
				</tr>
			</table>
		</form>
		<!-- <form action="Logout" method="POST">
			<input type="image" src="img/out.png" alt="Submit" value="Logout"
				width="25" height="25" />
		</form> -->
		<%
			}
		%>
	</div>
</body>
</html>