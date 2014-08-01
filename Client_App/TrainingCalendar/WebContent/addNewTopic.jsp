<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/foundation.min.css" type="text/css">
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/style.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/pagestyle.css">
<title>Training Calendar | New Topic</title>

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
	<a href="adminHome.jsp"><img src="img/home.png"
				width="40" height="40" title="Go home"></a>
				
				
		<h2>Add New Topic</h2>
		<form onsubmit="return checkForm(this);" action="AddTopic"
			method="POST">
			<table>
				<tr>
					<td>Topic Name:</td>
					<td><input type="text" name="tname" required autofocus></td>
				</tr>
				<tr>
					<td>Trainer:</td>
					<td><input type="text" name="trainer" required></td>
				</tr>
			
				<tr>
					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="Add"></td>

				</tr>
			</table>
		</form>
	</div>
</body>
</html>
