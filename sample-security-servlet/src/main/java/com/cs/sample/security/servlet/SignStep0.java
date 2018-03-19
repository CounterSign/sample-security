/*
Copyright (c) 2018 CounterSign, www.countersign.com.br

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 
 NOTICE: this is a part of source code of the Digital Signature Sample using the 
         CounterSign Security Rest API.
         The CounterSign Security Rest API is a digital signature paid service and 
         to achieve it you need buy credits or acquire a license.
*/

package com.cs.sample.security.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.cs.mime.CSMimeBodyPart;
import com.cs.mime.CSMimeContentData;
import com.cs.mime.CSMimeContentDisposition;
import com.cs.mime.CSMimeContentTypeHandler;
import com.cs.mime.CSMimeMultipart;
import com.cs.mime.CSMimeTypeException;
import com.cs.mime.CSMimeTypeOctetStreamHandler;
import com.cs.mime.CSMimeTypeTextPlainHandler;
import com.cs.mime.FormMimeHandler;
import com.cs.mime.ICSMimeHandler;
import com.cs.sample.security.file.HtmlFileHandler;
import com.cs.util.string.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class SignStep0
 */
@WebServlet("/SignStep0")
public class SignStep0 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignStep0() {
        super();
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder path = new StringBuilder(this.getServletContext().getRealPath("/"));
		String html = "";
		try {
			html = HtmlFileHandler.readHtmlFromFile(path + "/pages/signstep1.html");
		} catch(Exception ex) {
			html = HtmlFileHandler.readHtmlFromFile(path + "/pages/error.html");
		}
		
        response.setContentType("text/html;charset=ISO-8859-1");
		response.getWriter().append(html);
		
	}
}
