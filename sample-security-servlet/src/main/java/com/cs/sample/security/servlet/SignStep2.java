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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cs.sample.security.file.HtmlFileHandler;
import com.cs.sample.security.rest.client.SecuritySignDataRestClient;
import com.cs.security.entities.response.DigitalSignaturePrepareResponse;
import com.cs.security.entities.response.ErrorResponse;
import com.cs.security.service.enumtypes.EnumDigitalSignatureType;
import com.cs.util.string.StringUtils;

/**
 * Servlet implementation class SignStep2
 */
@WebServlet(description = "Prepare the Data to be Signed", urlPatterns = { "/SignStep2" })
public class SignStep2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(SignStep2.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignStep2() {
        super();
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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
		String error = "";
		
		String thumbprint = request.getParameter("selCertificate");
		String signerCertificateB64 = request.getParameter("signerCertificate");
		String dataB64 = request.getParameter("data");
		String messageDigestAlg = request.getParameter("messageDigestAlg");
		String signatureAlg = request.getParameter("signatureAlg");
		
		//prepareSign
		DigitalSignaturePrepareResponse digitalSignaturePrepareResponse = SecuritySignDataRestClient.prepareSign(EnumDigitalSignatureType.DS_ICP_AD_RB.value(), 
				signerCertificateB64, true, dataB64, null, messageDigestAlg, signatureAlg, "2 16 76 1 7 1 1 2 1");
		
		if(digitalSignaturePrepareResponse != null) {
			//retrieve from the results the data prepared to be signed
			String signedAttributesB64 = null;
			if(digitalSignaturePrepareResponse.isSuccess()) {
				html = HtmlFileHandler.readHtmlFromFile(path + "/pages/signstep3.html");
				
				//get the data prepared in the Server to be signed for the user
				signedAttributesB64 = digitalSignaturePrepareResponse.getSignedAttributes();
				
				//replace
				if( (signedAttributesB64 != null) && (signedAttributesB64.isEmpty()==false) && (! signedAttributesB64.equals("[signedAttributes]")) ) {
					html = StringUtils.replacePhrase(html, "[signedAttributes]", signedAttributesB64);
				}
				//send back again
				if( (thumbprint != null) && (thumbprint.isEmpty()==false) && (! thumbprint.equals("[thumbprint]")) ) {
					html = StringUtils.replacePhrase(html, "[thumbprint]", thumbprint);
				}
				if( (signerCertificateB64 != null) && (signerCertificateB64.isEmpty()==false) && (! signerCertificateB64.equals("[signerCertificate]")) ) {
					html = StringUtils.replacePhrase(html, "[signerCertificate]", StringUtils.removePhrase(signerCertificateB64, "\r\n"));
				}
				if( (dataB64 != null) && (dataB64.isEmpty()==false) && (! dataB64.equals("[data]")) ) {
					html = StringUtils.replacePhrase(html, "[data]", dataB64);
				}
				
			} else {
				html = HtmlFileHandler.readHtmlFromFile(path + "/pages/error.html");
				
				//get the errors returned by the server
				List<ErrorResponse> listOfErrors = digitalSignaturePrepareResponse.getListErrorResponse();
				//show errors
				for(ErrorResponse errorResponse : listOfErrors) {
					logger.error("Error: " + String.valueOf(errorResponse.getCode()) + " " + errorResponse.getError());
					error += "Error: " + String.valueOf(errorResponse.getCode()) + " " + errorResponse.getError() + "<br/>";
				}

				html = StringUtils.replacePhrase(html, "[error]", error);
			}
		} else {
			html = HtmlFileHandler.readHtmlFromFile(path + "/pages/error.html");
			
			error = "Service unavailable. Can not get access to Security Rest API.";

			html = HtmlFileHandler.readHtmlFromFile(path + "/pages/error.html");
			html = StringUtils.replacePhrase(html, "[error]", error);
		}
		
        response.setContentType("text/html;charset=ISO-8859-1");
		response.getWriter().append(html);
		
	}
	
}
