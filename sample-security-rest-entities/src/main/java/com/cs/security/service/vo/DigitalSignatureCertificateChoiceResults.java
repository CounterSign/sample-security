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

public class DigitalSignatureCertificateChoiceResults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2054857653675872791L;

	protected CertificateResults certificate = null;
	protected AttributeCertificateResults attributeCertificate = null;
	
	public DigitalSignatureCertificateChoiceResults() {
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

	
}