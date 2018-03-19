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
import java.util.List;

/**
 * A request structure for the verify a digital signature process
 * @author ramir_000
 *
 */
public class VerifyDigitalSignatureData implements Serializable {
	
	private static final long serialVersionUID = -4051739732190302412L;
	
	//the CMS:signedData package
	protected byte[] signedData = null;
	//the CMS:signedData package in Base64
	protected byte[] signedDataB64 = null;
	//the hash of data encoded as Hexadecimal to be compared with message digest of the CMS package. For use in detached mode only.
	protected String messageDigestInHex = null;
	//the list of certificates to be builded as a certificate chain required only when is not present inside the CMS package it self
	protected List<String> certificateChain;
	
	public VerifyDigitalSignatureData() {
	}

	public VerifyDigitalSignatureData(byte[] signedData) {
		this.signedData = signedData;
	}

	public VerifyDigitalSignatureData(byte[] signedData, List<String> certificateChain) {
		this.signedData = signedData;
		this.certificateChain = certificateChain;
	}

	public VerifyDigitalSignatureData(byte[] signedData, String messageDigestInHex, List<String> certificateChain) {
		this.signedData = signedData;
		this.messageDigestInHex = messageDigestInHex;
		this.certificateChain = certificateChain;
	}


	public byte[] getSignedData() {
		return signedData;
	}

	public void setSignedData(byte[] signedData) {
		this.signedData = signedData;
	}

	public List<String> getCertificateChain() {
		return certificateChain;
	}

	public void setCertificateChain(List<String> certificateChain) {
		this.certificateChain = certificateChain;
	}

	public String getMessageDigestInHex() {
		return messageDigestInHex;
	}

	public void setMessageDigestInHex(String messageDigestInHex) {
		this.messageDigestInHex = messageDigestInHex;
	}

	
}
