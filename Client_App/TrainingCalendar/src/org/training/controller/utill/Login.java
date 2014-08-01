package org.training.controller.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.jsonParser.JSONObject;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String output;
	private JSONObject jObject;
	EndPointUrl ep = new EndPointUrl();

	public Login() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			String userid = request.getParameter("username");
			String pass = request.getParameter("password");

			URL url = new URL(ep.getUrl() + "rest/tc/user/auth/" + userid + ","
					+ pass);
			
		/*	URL url = new URL(
					"http://localhost:8080/TrainingCalendar/rest/tc/user/auth/"
							+ userid + "," + pass);*/
			System.out.println(url);
			
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			while ((output = br.readLine()) != null) {
				jObject = new JSONObject(output);
				
			}
			conn.disconnect();
			if (jObject == null || conn.getResponseCode() == 204) {
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/index.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=red><center>Either user name or password is wrong.</center></font>");
				rd.include(request, response);
			} else if (Integer.parseInt(jObject.getString("roleId").toString()) == 2
					&& jObject != null) {
				Cookie loginCookie = new Cookie("user", userid);
				// setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(10 * 60);
				response.addCookie(loginCookie);
				response.sendRedirect("userHome.jsp");

			} else if (Integer.parseInt(jObject.getString("roleId").toString()) == 1
					&& jObject != null) {
				Cookie loginCookie = new Cookie("user", userid);
				// setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(10 * 60);
				response.addCookie(loginCookie);
				response.sendRedirect("adminHome.jsp");
			} else {
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher("/index.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=red><center>Either user name or password is wrong.</center></font>");
				rd.include(request, response);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
