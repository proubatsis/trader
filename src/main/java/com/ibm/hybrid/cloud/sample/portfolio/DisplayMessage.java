/*
       Copyright 2017 IBM Corp All Rights Reserved

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ibm.hybrid.cloud.sample.portfolio;

import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;

//JSON-P 1.0 (JSR 353).  The replaces my old usage of IBM's JSON4J (com.ibm.json.java.JSONObject)
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

//Servlet 3.1
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayMessage
 */
@WebServlet(description = "Display Message servlet", urlPatterns = { "/message" })
@ServletSecurity(@HttpConstraint(rolesAllowed = { "StockTrader" } ))
public class DisplayMessage extends HttpServlet {
	private static final long serialVersionUID = 4815162342L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String encodedMessage = request.getParameter("message");
		String message = URLDecoder.decode(encodedMessage, "UTF-8");
		Writer writer = response.getWriter();
		writer.append("<!DOCTYPE html>");
		writer.append("<html>");
		writer.append("  <head>");
		writer.append("    <title>Stock Trader</title>");
		writer.append("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		writer.append("  </head>");
		writer.append("  <body>");
		writer.append("    <img src=\"header.jpg\" width=\"534\" height=\"200\"/>");
		writer.append("    <p/>");
		writer.append("    "+message);
		writer.append("    <p/>");
		writer.append("    <form method=\"post\"/>");
		writer.append("      <input type=\"submit\" name=\"submit\" value=\"OK\" style=\"font-family: sans-serif; font-size: 16px;\" />");
		writer.append("    </form>");
		writer.append("    <br/>");
		writer.append("    <a href=\"https://github.com/IBMStockTrader\">");
		writer.append("      <img src=\"footer.jpg\"/>");
		writer.append("    </a>");
		writer.append("  </body>");
		writer.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String owner = request.getParameter("owner");

		response.sendRedirect("viewPortfolio?owner="+owner); //send control to the ViewPortfolio servlet
	}
}
