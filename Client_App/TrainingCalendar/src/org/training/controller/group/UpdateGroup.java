package org.training.controller.group;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/UpdateGroup")
public class UpdateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EndPointUrl ep = new EndPointUrl();

	public UpdateGroup() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String groupId = request.getParameter("key");
		String group = request.getParameter("group");

		try {

			// URL url = new
			// URL("https://appserver.cloud.wso2.com/t/sabashan/webapps/trainingcalendar1-5.0.0/rest/tc/event");
			URL url = new URL(ep.getUrl() + "rest/tc/groups");
			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/groups");
			 */
			
			System.out.println(url);
		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/xml");
			
			System.out.println("===========XML==============>>>>>>>>>>>");
			String input = "<groups>" + "<groupId>" + groupId + "</groupId>"
					+ "<group>" + group + "</group>" + "</groups>";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();		

			RequestDispatcher rd = request.getRequestDispatcher("GetGroup");
			rd.forward(request, response);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
