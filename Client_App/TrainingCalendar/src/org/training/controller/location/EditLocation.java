/**
* Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*/


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
import org.training.jsonParser.JSONObject;

@WebServlet("/EditLocation")
public class EditLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JSONObject jObject;
	private String output;
	EndPointUrl ep = new EndPointUrl();

	public EditLocation() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String locationId = request.getParameter("key");

		try {

			URL url = new URL(ep.getUrl() + "rest/tc/location/searchloid/"
					+ locationId);
			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/location/searchloid/"
			 * + locationId);
			 */
			System.out.println(url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// output= output.substring(1, output.length()-1);
				jObject = new JSONObject(output);
			}
			conn.disconnect();
			System.out.println(jObject);
			/*
			 * System.out.print(jObject.getJSONArray("user").getJSONObject(0)
			 * .getString("firstName"));
			 */

			// am=jObject.getJSONArray("user").getJSONObject(0).getString("firstName");
			// JSONArray am = jObject.toJSONArray(names);
			request.setAttribute("msg", jObject);

			// response.sendRedirect("userDetails.jsp");

			RequestDispatcher rd = request
					.getRequestDispatcher("editLocation.jsp");
			rd.forward(request, response);

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

}
