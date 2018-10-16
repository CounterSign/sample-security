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

import com.cs.security.entities.request.Request;

public class RequestDigitalSignatureSignData extends Request {

	private static final long serialVersionUID = 4167474227428358563L;
	
	protected AuthenticationVo authenticationVo;
	protected String clientDocId;
	protected String contractItemNumber;
	protected long idDocumentType = 0;
	protected boolean flagSaveDocument = false;
	protected String documentName;
	protected String productSku;
	protected DigitalSignatureSignData digitalSignatureSignData;
	//PDF specific fields
	protected byte[] pdfPre = null;
	protected String sigFieldName = null;
	
	public RequestDigitalSignatureSignData() {
	}

	public RequestDigitalSignatureSignData(AuthenticationVo authenticationVo, String clientDocId,
			String contractItemNumber, DigitalSignatureSignData digitalSignatureSignData) {
		super();
		this.authenticationVo = authenticationVo;
		this.clientDocId = clientDocId;
		this.contractItemNumber = contractItemNumber;
		this.digitalSignatureSignData = digitalSignatureSignData;
	}

	public RequestDigitalSignatureSignData(DigitalSignatureSignData digitalSignatureSignData) {
		super();
		this.digitalSignatureSignData = digitalSignatureSignData;
	}

	public AuthenticationVo getAuthenticationVo() {
		return authenticationVo;
	}

	public void setAuthenticationVo(AuthenticationVo authenticationVo) {
		this.authenticationVo = authenticationVo;
	}

	public String getClientDocId() {
		return clientDocId;
	}

	public void setClientDocId(String clientDocId) {
		this.clientDocId = clientDocId;
	}

	public String getContractItemNumber() {
		return contractItemNumber;
	}

	public void setContractItemNumber(String contractItemNumber) {
		this.contractItemNumber = contractItemNumber;
	}

	public DigitalSignatureSignData getDigitalSignatureSignData() {
		return digitalSignatureSignData;
	}

	public void setDigitalSignatureSignData(DigitalSignatureSignData digitalSignatureSignData) {
		this.digitalSignatureSignData = digitalSignatureSignData;
	}

	public long getIdDocumentType() {
		return idDocumentType;
	}

	public void setIdDocumentType(long idDocumentType) {
		this.idDocumentType = idDocumentType;
	}

	public boolean isFlagSaveDocument() {
		return flagSaveDocument;
	}

	public void setFlagSaveDocument(boolean flagSaveDocument) {
		this.flagSaveDocument = flagSaveDocument;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public byte[] getPdfPre() {
		return pdfPre;
	}

	public void setPdfPre(byte[] pdfPre) {
		this.pdfPre = pdfPre;
	}

	public String getSigFieldName() {
		return sigFieldName;
	}

	public void setSigFieldName(String sigFieldName) {
		this.sigFieldName = sigFieldName;
	}

}
