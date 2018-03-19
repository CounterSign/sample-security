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

public class RecipientInfoResults implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -169233142793547974L;
	
	protected String keyEncryptionAlgOID = "";
	protected String keyEncryptedB64 = "";
	protected String issuerCN = "";
	protected String serialNumber = "";
	
	public RecipientInfoResults() {
		super();
	}
	
	public RecipientInfoResults(String keyEncryptionAlgOID, String keyEncryptedB64, String issuerCN, String serialNumber) {
		super();
		this.keyEncryptionAlgOID = keyEncryptionAlgOID;
		this.keyEncryptedB64 = keyEncryptedB64;
		this.issuerCN = issuerCN;
		this.serialNumber = serialNumber;
	}

	
	public String getKeyEncryptionAlgOID() {
		return keyEncryptionAlgOID;
	}

	public void setKeyEncryptionAlgOID(String keyEncryptionAlgOID) {
		this.keyEncryptionAlgOID = keyEncryptionAlgOID;
	}

	public String getKeyEncryptedB64() {
		return keyEncryptedB64;
	}
	public void setKeyEncryptedB64(String keyEncryptedB64) {
		this.keyEncryptedB64 = keyEncryptedB64;
	}
	public String getIssuerCN() {
		return issuerCN;
	}
	public void setIssuerCN(String issuerCN) {
		this.issuerCN = issuerCN;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	
	
}
