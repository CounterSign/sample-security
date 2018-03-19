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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.security.auth.x500.X500Principal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

import com.cs.sample.security.file.HtmlFileHandler;
import com.cs.sample.security.rest.client.SecuritySignDataRestClient;
import com.cs.sample.security.rest.json.JsonConnHandler;
import com.cs.security.entities.response.DigitalSignaturePrepareResponse;
import com.cs.security.entities.response.DigitalSignatureSignDataResponse;
import com.cs.security.entities.response.ErrorResponse;
import com.cs.security.service.enumtypes.EnumDigitalSignatureType;
import com.cs.security.service.vo.DigitalSignaturePrepareSign;
import com.cs.security.service.vo.DigitalSignatureSignData;
import com.cs.util.string.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Servlet implementation class SignStep3
 */
@WebServlet("/SignStep3")
public class SignStep3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = Logger.getLogger(SignStep3.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignStep3() {
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

		FileInputStream fileIn = null;
		String html = "";
		String error = "";
		
		String thumbprint = request.getParameter("selCertificate");
		String signerCertificateB64 = request.getParameter("signerCertificate");
		String dataToBeSignedB64 = request.getParameter("data");
		String signedAttributesB64 = request.getParameter("hash");
		String signatureB64 = request.getParameter("signedData");
		String messageDigestAlg = request.getParameter("messageDigestAlg");
		String signatureAlg = request.getParameter("signatureAlg");

		//envelopeSignedData
		DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = SecuritySignDataRestClient.envelopeSignedData(EnumDigitalSignatureType.DS_ICP_AD_RB.value(), 
					signerCertificateB64, 
					true, 
					dataToBeSignedB64, 
					null, 
					messageDigestAlg, 
					signatureAlg, 
					signedAttributesB64, 
					signatureB64, 
					"2 16 76 1 7 1 1 2 1", 
					null, 
					null);
		
		if(digitalSignatureSignDataResponse != null) {
			//check the Security API Rest WebService signature results
			if(digitalSignatureSignDataResponse.isSuccess()) {
				
				html = HtmlFileHandler.readHtmlFromFile(path + "/pages/signstep4.html");
				
				//retrieve the signed data from the results
				byte[] cmsSignedData = digitalSignatureSignDataResponse.getSignedData();
				//encode the signedData to Base64
		        BASE64Encoder base64Enc = new BASE64Encoder();
		        String signedDataB64 = base64Enc.encode(cmsSignedData);
		        signedDataB64 = StringUtils.removePhrase(signedDataB64, "\r\n");
				
				//replace
				if( (signedDataB64 != null) && (signedDataB64.isEmpty()==false) ) {
					html = StringUtils.replacePhrase(html, "[signedData]", signedDataB64);
				}
				
			} else {
				html = HtmlFileHandler.readHtmlFromFile(path + "/pages/error.html");
				
				//get the errors returned by the server
				List<ErrorResponse> listOfErrors = digitalSignatureSignDataResponse.getListErrorResponse();
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
