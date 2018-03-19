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

package com.cs.security.service.vo;

import java.io.Serializable;

public class HsmResults implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2796881368245824357L;
	
	public static final int SUCCESS = 0;
	public static final int FAILURE = 1;
	public static final int ERROR_ON_LOAD = 2;
	public static final int ERROR_ON_LOGIN = 3;
	public static final int ERROR_ON_INSTALL = 4;
	public static final int ERROR_ON_SIGNATURE = 5;
	public static final int ERROR_ON_SIGN_PRIVATEKEY_NOT_FOUND = 6;
	public static final int ERROR_ON_KEYPAIR_GENERATOR = 7;
	public static final int ERROR_ON_CSR_GENERATOR = 8;
	
	public static final int HSM_NOT_FOUND = 10;
	
	public static final int STATUS_NONE = 0;
	public static final int STATUS_ACTIVE = 1;
	public static final int STATUS_INACTIVE = 2;

	protected int results = FAILURE;
	protected int status = STATUS_NONE;
	protected String errors = "";
	
	public HsmResults() {
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}
	
}
