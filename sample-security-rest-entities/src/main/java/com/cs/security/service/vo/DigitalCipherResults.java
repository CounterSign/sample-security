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

public class DigitalCipherResults implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6253931692065707315L;
	
	public static final int SUCCESS = 0;
	public static final int NOT_VALID = 1;
	public static final int NOT_VALID_CERTIFICATE = 2;
	public static final int NOT_VALID_CERTIFICATE_EXPIRED = 3;
	public static final int NOT_VALID_NOT_AN_ICP_BRASIL_CERTIFICATE = 4;
	public static final int NOT_VALID_NOT_AN_ICP_BRASIL_A3_CERTIFICATE = 5;
	public static final int NOT_VALID_SIGNATURE =6;
	
	protected int results = NOT_VALID;
	
	protected String errors = "";
	protected byte[] clearData;
	protected byte[] cipheredData;
	protected String cipheredDataB64 = "";
	
	public DigitalCipherResults() {
		super();
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

	public byte[] getClearData() {
		return clearData;
	}

	public void setClearData(byte[] clearData) {
		this.clearData = clearData;
	}

	public byte[] getCipheredData() {
		return cipheredData;
	}

	public void setCipheredData(byte[] cipheredData) {
		this.cipheredData = cipheredData;
	}

	public String getCipheredDataB64() {
		return cipheredDataB64;
	}

	public void setCipheredDataB64(String cipheredDataB64) {
		this.cipheredDataB64 = cipheredDataB64;
	}


}
