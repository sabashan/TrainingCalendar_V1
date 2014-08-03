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

@WebServlet("/GetLocation")
public class GetLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String output;
	private JSONObject jObject;
	EndPointUrl ep = new EndPointUrl();

	public GetLocation() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			URL url = new URL(ep.getUrl() + "rest/tc/location");

			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/location");
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
			}
			conn.disconnect();			

			JSONArray am = jObject.getJSONArray("location");
			request.setAttribute("msg", am);			

			RequestDispatcher rd = request
					.getRequestDispatcher("locationDetails.jsp");
			rd.forward(request, response);
			

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
