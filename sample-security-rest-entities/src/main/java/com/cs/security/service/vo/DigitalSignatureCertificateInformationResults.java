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
 * The information about the Certificate found in the list of Certificates extracted from CMS:signedData package
 * @author ramir_000
 *
 */
public class DigitalSignatureCertificateInformationResults implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5046574091097146621L;
	
	public static final int VALID = 0;
    public static final int NOT_VALID = 1;

    protected int status = VALID;
    protected CertificateResults certificate = null;
    protected AttributeCertificateResults attributeCertificate = null;
    protected String generalStatusInfo = "";
    
    public DigitalSignatureCertificateInformationResults() {
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CertificateResults getCertificate() {
		return certificate;
	}

	public void setCertificate(CertificateResults certificate) {
		this.certificate = certificate;
	}

	public AttributeCertificateResults getAttributeCertificate() {
		return attributeCertificate;
	}

	public void setAttributeCertificate(AttributeCertificateResults attributeCertificate) {
		this.attributeCertificate = attributeCertificate;
	}

	public String getGeneralStatusInfo() {
		return generalStatusInfo;
	}

	public void setGeneralStatusInfo(String generalStatusInfo) {
		this.generalStatusInfo = generalStatusInfo;
	}


}
