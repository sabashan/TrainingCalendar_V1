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

@WebServlet("/UpdateLocation")
public class UpdateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EndPointUrl ep = new EndPointUrl();

	public UpdateLocation() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String locationId = request.getParameter("key");
		String location = request.getParameter("location");

		try {

			URL url = new URL(ep.getUrl() + "rest/tc/location");
			/*
			 * URL url = new URL(
			 * "http://localhost:8080/TrainingCalendar/rest/tc/location");
			 */
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/xml");
			// conn.set

			System.out
					.println("++++++++++++++++++++++++++++++++++++++++++++++++>");
			System.out.println("===========XML==============>>>>>>>>>>>");
			String input = "<location>" + "<locationId>" + locationId
					+ "</locationId>" + "<location>" + location + "</location>"
					+ "</location>";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			System.out.println("000000000 Update fininshed.... update servlet");
			// response.sendRedirect("GetUsers");
			RequestDispatcher rd = request.getRequestDispatcher("GetLocation");
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
