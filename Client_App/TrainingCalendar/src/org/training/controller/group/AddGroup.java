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

@WebServlet("/AddGroup")
public class AddGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EndPointUrl ep = new EndPointUrl();

	public AddGroup() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String gname = request.getParameter("gname");
	

		try {

			URL url = new URL(ep.getUrl() + "rest/tc/groups");		
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");

			System.out.println("===========XML==============>>>>>>>>>>>");
			String input = "<groups>"
								+ "<group>" + gname	+ "</group>" 
							+ "</groups>";
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}			

			conn.disconnect();
			RequestDispatcher rd = request.getRequestDispatcher("GetGroup");
			rd.forward(request, response);


		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
