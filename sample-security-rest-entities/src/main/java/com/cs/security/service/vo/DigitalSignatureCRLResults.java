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
import java.util.Date;

public class DigitalSignatureCRLResults implements Serializable {

	private static final long serialVersionUID = 3418165692023960550L;

	public static final int VALID = 0;
	public static final int NOT_VALID = 1;
	public static final int NOT_VALID_EXPIRED = 2;
	public static final int PEM_IS_INVALID_ERROR = 5;
	
	protected int results = NOT_VALID;
	protected String errors = "";
	protected String crlBase64 = "";
	
	protected String serialNumber;
	protected String issuerDn;
	protected Date validityThisUpdate;
	protected Date validityNextUpdate;
	
	public DigitalSignatureCRLResults() {
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public String getCrlBase64() {
		return crlBase64;
	}

	public void setCrlBase64(String crlBase64) {
		this.crlBase64 = crlBase64;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getIssuerDn() {
		return issuerDn;
	}

	public void setIssuerDn(String issuerDn) {
		this.issuerDn = issuerDn;
	}

	public Date getValidityThisUpdate() {
		return validityThisUpdate;
	}

	public void setValidityThisUpdate(Date validityThisUpdate) {
		this.validityThisUpdate = validityThisUpdate;
	}

	public Date getValidityNextUpdate() {
		return validityNextUpdate;
	}

	public void setValidityNextUpdate(Date validityNextUpdate) {
		this.validityNextUpdate = validityNextUpdate;
	}

	
}
