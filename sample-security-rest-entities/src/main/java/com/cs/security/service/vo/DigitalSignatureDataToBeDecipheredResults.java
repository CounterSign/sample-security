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
import java.util.ArrayList;
import java.util.List;


public class DigitalSignatureDataToBeDecipheredResults implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -41436045966115595L;
	
	public static final int SUCCESS = 0;
	public static final int NOT_VALID = 1;
	
	protected int results = NOT_VALID;
	protected String errors = "";

	protected byte[] encryptedData = null;
	protected String contentEncryptionAlgorithmOID = "";
	protected byte[] contentEncryptionAlgorithmParameters = null;
	protected List<RecipientInfoResults> listOfRecipientInfoResults = new ArrayList<RecipientInfoResults>();
	
	public DigitalSignatureDataToBeDecipheredResults() {
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

	public byte[] getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(byte[] encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getContentEncryptionAlgorithmOID() {
		return contentEncryptionAlgorithmOID;
	}

	public void setContentEncryptionAlgorithmOID(String contentEncryptionAlgorithmOID) {
		this.contentEncryptionAlgorithmOID = contentEncryptionAlgorithmOID;
	}

	public byte[] getContentEncryptionAlgorithmParameters() {
		return contentEncryptionAlgorithmParameters;
	}

	public void setContentEncryptionAlgorithmParameters(
			byte[] contentEncryptionAlgorithmParameters) {
		this.contentEncryptionAlgorithmParameters = contentEncryptionAlgorithmParameters;
	}

	public List<RecipientInfoResults> getListOfRecipientInfoResults() {
		return listOfRecipientInfoResults;
	}

	public void setListOfRecipientInfoResults(List<RecipientInfoResults> listOfRecipientInfoResults) {
		this.listOfRecipientInfoResults = listOfRecipientInfoResults;
	}

	public void addRecipientInfoResults(RecipientInfoResults recipientInfoResults) {
		this.listOfRecipientInfoResults.add(recipientInfoResults);
	}

}
