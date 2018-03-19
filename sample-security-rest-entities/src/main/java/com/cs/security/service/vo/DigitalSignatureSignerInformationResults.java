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

/**
 * The Information about the Signer extracted from the Digital Signature
 * @author ramir_000
 *
 */
public class DigitalSignatureSignerInformationResults implements Serializable {

	private static final long serialVersionUID = -3330903626266214142L;
	
	protected String alias = "";
    protected CertificateResults userCertificate = new CertificateResults();

    protected String issuerDN = "";
    protected String serialNumber = "";
    protected String subjectPublicKeyIdentifier = "";
    
    public DigitalSignatureSignerInformationResults() {
    }


    public DigitalSignatureSignerInformationResults(String alias, CertificateResults userCertificate,String subjectPublicKeyIdentifier, String issuerDN, String serialNumber) {
		super();
		this.alias = alias;
		this.userCertificate = userCertificate;
		this.issuerDN = issuerDN;
		this.serialNumber = serialNumber;
		this.subjectPublicKeyIdentifier = subjectPublicKeyIdentifier;
	}


	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}


	public CertificateResults getUserCertificate() {
		return userCertificate;
	}


	public void setUserCertificate(CertificateResults userCertificate) {
		this.userCertificate = userCertificate;
	}


	public String getIssuerDN() {
		return issuerDN;
	}

	public void setIssuerDN(String issuerDN) {
		this.issuerDN = issuerDN;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getSubjectPublicKeyIdentifier() {
		return subjectPublicKeyIdentifier;
	}


	public void setSubjectPublicKeyIdentifier(String subjectPublicKeyIdentifier) {
		this.subjectPublicKeyIdentifier = subjectPublicKeyIdentifier;
	}

}
