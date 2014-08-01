package org.training.controller.topic;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.controller.utill.EndPointUrl;
import org.training.jsonParser.JSONArray;
import org.training.jsonParser.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


@WebServlet("/GetTopic")
public class GetTopic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String output;
	private JSONObject jObject;	
	EndPointUrl ep=new EndPointUrl();

	public GetTopic() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			URL url = new URL(ep.getUrl()+"rest/tc/topic");
			
			/*URL url = new URL(
					"http://localhost:8080/TrainingCalendar/rest/tc/topic");*/
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Type", "application/json");

			System.out.println("=====================.\n");
			//System.out.println(conn.getContent().);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);

				// output= output.substring(1, output.length()-1);
				jObject = new JSONObject(output);
				System.out.println("+++++++ewrwerewr+++++++++++++++++>>>>>>>");
			}
			conn.disconnect();
			

			JSONArray am = jObject.getJSONArray("topic");
			request.setAttribute("msg", am);
			//System.out.print(am);
			

			RequestDispatcher rd = request.getRequestDispatcher("topicDetails.jsp");
			rd.forward(request, response);
			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
}
