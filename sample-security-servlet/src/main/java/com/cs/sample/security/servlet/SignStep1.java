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

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

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
 * Servlet implementation class SignStep1
 */
@WebServlet("/SignStep1")
public class SignStep1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = Logger.getLogger(SignStep1.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignStep1() {
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
        String error = "";
		byte[] pdf = null;
        try {
	        ServletInputStream in = request.getInputStream();
	        byte[] buffer = new byte[in.available()];
	        in.read(buffer);
	        
	        FormMimeHandler formMime = new FormMimeHandler();
	        formMime.parse(buffer);

	        CSMimeMultipart mimeMultipart = formMime.getMultipart();

	        CSMimeContentDisposition contentDisposition = new CSMimeContentDisposition();
	        CSMimeContentData contentData = new CSMimeContentData();

	        //get the content-type and data
	        List<CSMimeBodyPart> mimeBodyParts = mimeMultipart.getListOfMimeBodyParts();
	        for(CSMimeBodyPart bodyPart : mimeBodyParts) {
	            contentDisposition = bodyPart.getContentDisposition();
	            contentData = bodyPart.getContentData();
	            ICSMimeHandler mimeTypeHandler = null;
	            try {
//	                CSMimeContentTypeHandler.register("application/octet-stream", CSMimeTypeOctetStreamHandler.class.getCanonicalName());
//	                CSMimeContentTypeHandler.register("text/plain", CSMimeTypeTextPlainHandler.class.getCanonicalName());
//	                CSMimeContentTypeHandler.register("form/field", CSMimeTypeTextPlainHandler.class.getCanonicalName());
	                mimeTypeHandler = CSMimeContentTypeHandler.create(contentData.getMimeContentType().getType());
	            } catch (ClassNotFoundException ex) {
	                throw new ServiceException(ex);
	            } catch (InstantiationException ex) {
	                throw new ServiceException(ex);
	            } catch (IllegalAccessException ex) {
	                throw new ServiceException(ex);
	            }
	            try {
	                mimeTypeHandler.execute(contentData);
	            } catch (CSMimeTypeException ex) {
	                //redirect
	                logger.fatal(ex);
	                error = "Error while decoding the MIME or content-disposition type. The form was not filled correctly. ";
	            }

	            if(mimeTypeHandler instanceof CSMimeTypeOctetStreamHandler) {
	            	pdf = ((CSMimeTypeOctetStreamHandler) mimeTypeHandler).getData().get();
	            } else {
	                if(mimeTypeHandler instanceof CSMimeTypeTextPlainHandler) {
	                    if(contentData.getMimeContentType().getType().equalsIgnoreCase("form/field")) {
	                    } else if(contentData.getMimeContentType().getType().equalsIgnoreCase("text/plain")) {
	                    	pdf = contentData.getData().get();
	                    }
	                } else {
	                    logger.error("--- FILE_IS_NOT_RECOGNIZED_ERROR ----" + bodyPart.getContentType().getContentType());
		                error = "File is not recognized. ";
	                }
	            }
	        }
		} catch(Exception ex) {
			logger.fatal(ex);
            error = "Error while decoding multi-part. ";
		}
		
        String html = "";
		if(pdf != null) {
			html = HtmlFileHandler.readHtmlFromFile(path + "/pages/signstep2.html");
	        BASE64Encoder base64Enc = new BASE64Encoder();
			String data = base64Enc.encode(pdf);
			data = StringUtils.removePhrase(data, "\r\n");
			html = StringUtils.replacePhrase(html, "[data]", data);
		} else {
			error += "There is no data to sign. ";
			html = HtmlFileHandler.readHtmlFromFile(path + "/pages/error.html");
			html = StringUtils.replacePhrase(html, "[error]", error);
		}
		
        response.setContentType("text/html;charset=ISO-8859-1");
		response.getWriter().append(html);
		
	}
}
