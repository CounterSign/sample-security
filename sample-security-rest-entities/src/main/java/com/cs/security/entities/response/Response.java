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
 
 NOTICE: this code was extracted from the CSPKI Library to simplify the 
 		 Digital Signature Sample using the CounterSign Security Rest API.
*/

package com.cs.security.entities.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {

	private static final long serialVersionUID = 5685459730328647840L;

	public static final long GENERIC_ERROR_CODE = -9999;

	public static final boolean SUCCESS = true;
	public static final boolean FAILED = false;

	protected boolean success = FAILED;
	
	protected List<ErrorResponse> listErrorResponse = new ArrayList<ErrorResponse>();

	public Response() {
	}

	public Response(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void addError(ErrorResponse errorResponse) {
		if (this.listErrorResponse == null) {
			this.listErrorResponse = new ArrayList<ErrorResponse>();
		}
		this.listErrorResponse.add(errorResponse);
	}

	public List<ErrorResponse> getListErrorResponse() {
		return listErrorResponse;
	}

	public void setListErrorResponse(List<ErrorResponse> listErrorResponse) {
		this.listErrorResponse = listErrorResponse;
	}

	public void addError(String error) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setError(error);
		errorResponse.setCode(GENERIC_ERROR_CODE);
		this.listErrorResponse.add(errorResponse);
	}

}
