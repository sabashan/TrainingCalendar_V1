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

@WebServlet("/EditEvent")
public class EditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String output;
	JSONObject jObject;
	EndPointUrl ep = new EndPointUrl();

	public EditEvent() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String eventId = request.getParameter("eventId");

			URL url = new URL(ep.getUrl() + "rest/tc/event/searchid/" + eventId);
			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/event/searchid/"
			 * + eventId);
			 */
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			System.out.println("Output from Server .... \n");

			while ((output = br.readLine()) != null) {
				System.out.println(output);

				jObject = new JSONObject(output);

				System.out.println(jObject.getJSONObject("event"));
			}
			conn.disconnect();

			request.setAttribute("msg", jObject.getJSONObject("event"));

			RequestDispatcher rd = request
					.getRequestDispatcher("editEvent.jsp");
			rd.forward(request, response);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}