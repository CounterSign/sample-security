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

public class RequestDigitalSignaturePrepareSign extends Request {

	private static final long serialVersionUID = 6635418648717703112L;
	
	protected AuthenticationVo authenticationVo;
	protected String clientDocId;
	protected String contractItemNumber;
	protected long idDocumentType = 0;
	protected boolean flagSaveDocument = false;
	protected String documentName;
	protected String productSku;
	protected DigitalSignaturePrepareSign digitalSignaturePrepareSign;
	//PDF specific fields 
	//signature stamp seal position
	protected int pageIndex = -1;//for use the CounterSign default which is in the last page
	protected int posX = -1;//for use the CounterSign default
	protected int posY = -1;//for use the CounterSign default
	protected int QRCodeEnabled = 0;//include by default
	
	public RequestDigitalSignaturePrepareSign() {
	}

	public RequestDigitalSignaturePrepareSign(AuthenticationVo authenticationVo, DigitalSignaturePrepareSign digitalSignaturePrepareSign) {
		super();
		this.authenticationVo = authenticationVo;
		this.digitalSignaturePrepareSign = digitalSignaturePrepareSign;
	}

	public AuthenticationVo getAuthenticationVo() {
		return authenticationVo;
	}

	public void setAuthenticationVo(AuthenticationVo authenticationVo) {
		this.authenticationVo = authenticationVo;
	}

	public DigitalSignaturePrepareSign getDigitalSignaturePrepareSign() {
		return digitalSignaturePrepareSign;
	}

	public void setDigitalSignaturePrepareSign(DigitalSignaturePrepareSign digitalSignaturePrepareSign) {
		this.digitalSignaturePrepareSign = digitalSignaturePrepareSign;
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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getQRCodeEnabled() {
		return QRCodeEnabled;
	}

	public void setQRCodeEnabled(int qRCodeEnabled) {
		QRCodeEnabled = qRCodeEnabled;
	}


}
