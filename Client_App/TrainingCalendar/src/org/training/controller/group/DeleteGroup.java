package org.training.controller.group;

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
import org.training.jsonParser.JSONObject;

@WebServlet("/DeleteGroup")
public class DeleteGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EndPointUrl ep = new EndPointUrl();
	private String output;
	private JSONObject jObject;

	public DeleteGroup() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
				String groupId = request.getParameter("key");
				
		try {
			// URL url = new
				// URL("https://appserver.cloud.wso2.com/t/sabashan/webapps/trainingcalendar1-5.0.0/rest/tc/event");
			
			URL url = new URL(ep.getUrl() + "rest/tc/groups/"+groupId);
			System.out.println(groupId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept-Type", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));		

			RequestDispatcher rd = request
					.getRequestDispatcher("GetGroup");
			rd.forward(request, response);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
