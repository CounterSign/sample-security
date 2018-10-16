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

public class RequestVerifyDigitalSignatureData extends Request {

	private static final long serialVersionUID = 2586012745366573755L;
	
	protected AuthenticationVo authenticationVo;
	protected String clientDocId;
	protected String contractItemNumber;
	protected long idDocumentType = 0;
	protected String documentName;
	protected String productSku;
	protected VerifyDigitalSignatureData verifyDigitalSignatureData;
	
	public RequestVerifyDigitalSignatureData() {
	}

	public RequestVerifyDigitalSignatureData(AuthenticationVo authenticationVo, String clientDocId,
			String contractItemNumber, VerifyDigitalSignatureData verifyDigitalSignatureData) {
		super();
		this.authenticationVo = authenticationVo;
		this.clientDocId = clientDocId;
		this.contractItemNumber = contractItemNumber;
		this.verifyDigitalSignatureData = verifyDigitalSignatureData;
	}

	public RequestVerifyDigitalSignatureData(VerifyDigitalSignatureData verifyDigitalSignatureData) {
		super();
		this.verifyDigitalSignatureData = verifyDigitalSignatureData;
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

	public VerifyDigitalSignatureData getVerifyDigitalSignatureData() {
		return verifyDigitalSignatureData;
	}

	public void setVerifyDigitalSignatureData(VerifyDigitalSignatureData verifyDigitalSignatureData) {
		this.verifyDigitalSignatureData = verifyDigitalSignatureData;
	}

	public long getIdDocumentType() {
		return idDocumentType;
	}

	public void setIdDocumentType(long idDocumentType) {
		this.idDocumentType = idDocumentType;
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

}
