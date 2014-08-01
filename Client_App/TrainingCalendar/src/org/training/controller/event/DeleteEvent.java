package org.training.controller.event;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.controller.utill.EndPointUrl;
import org.training.jsonParser.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@WebServlet("/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String output;
	JSONObject jObject;
	EndPointUrl ep = new EndPointUrl();

	public DeleteEvent() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String eventId = request.getParameter("eventId");

			URL url = new URL(ep.getUrl() + "rest/tc/event/" + eventId);
			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/event/"+eventId);
			 */
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept-Type", "application/json");

			System.out.println("=====================.\n");
			// System.out.println(conn.getContent().);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			conn.disconnect();

			RequestDispatcher rd = request.getRequestDispatcher("GetEvent");
			rd.forward(request, response);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
