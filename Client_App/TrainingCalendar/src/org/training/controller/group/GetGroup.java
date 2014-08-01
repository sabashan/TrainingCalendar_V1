package org.training.controller.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.jsonParser.JSONArray;
import org.training.jsonParser.JSONObject;
import org.training.controller.utill.EndPointUrl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@WebServlet("/GetGroup")
public class GetGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String output;
	private JSONObject jObject;	
EndPointUrl ep=new EndPointUrl();
	
	public GetGroup() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			URL url = new URL(ep.getUrl()+"rest/tc/groups");
			
			/*URL url = new URL(
					"http://localhost:8080/TrainingCalendar/rest/tc/groups");*/
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Type", "application/json");

			System.out.println("=====================.\n");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);				
				jObject = new JSONObject(output);				
			}
			conn.disconnect();
			
			JSONArray am = jObject.getJSONArray("groups");
			request.setAttribute("msg", am);			

			RequestDispatcher rd = request.getRequestDispatcher("groupDetails.jsp");
			rd.forward(request, response);			

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
