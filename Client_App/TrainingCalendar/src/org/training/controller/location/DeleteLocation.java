package org.training.controller.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.training.controller.utill.EndPointUrl;

@WebServlet("/DeleteLocation")
public class DeleteLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EndPointUrl ep = new EndPointUrl();

	public DeleteLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String locationId = request.getParameter("key");
		try {
			// URL url = new
			// URL("https://appserver.cloud.wso2.com/t/sabashan/webapps/trainingcalendar1-5.0.0/rest/tc/event");
			URL url = new URL(ep.getUrl() + "rest/tc/location/" + locationId);
			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/location/"
			 * +locationId);
			 */
			System.out.println(locationId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept-Type", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			// response.sendRedirect("userDetails.jsp");

			RequestDispatcher rd = request.getRequestDispatcher("GetLocation");
			rd.forward(request, response);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
