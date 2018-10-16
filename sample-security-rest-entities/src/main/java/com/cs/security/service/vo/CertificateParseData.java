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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A request structure for the verify a digital signature process
 * @author ramir_000
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificateParseData implements Serializable {
	
	private static final long serialVersionUID = -303376481148256292L;
	
	//the certificate
	protected byte[] certificate = null;
	protected boolean isICPRequiredOnly = false;
	protected boolean isA3RequiredOnly = false;
	protected boolean checkAgainstCrl = true;
	protected List<byte[]> chain = null;
	
	public CertificateParseData() {
	}

	public CertificateParseData(byte[] certificate) {
		this.certificate = certificate;
	}
	
	public CertificateParseData(byte[] certificate, List<byte[]> chain) {
		this.certificate = certificate;
		this.chain = chain;
	}
	
	public byte[] getCertificate() {
		return certificate;
	}

	public void setCertificate(byte[] certificate) {
		this.certificate = certificate;
	}

	public boolean isICPRequiredOnly() {
		return isICPRequiredOnly;
	}

	public void setICPRequiredOnly(boolean isICPRequiredOnly) {
		this.isICPRequiredOnly = isICPRequiredOnly;
	}

	public boolean isA3RequiredOnly() {
		return isA3RequiredOnly;
	}

	public void setA3RequiredOnly(boolean isA3RequiredOnly) {
		this.isA3RequiredOnly = isA3RequiredOnly;
	}

	public boolean isCheckAgainstCrl() {
		return checkAgainstCrl;
	}

	public void setCheckAgainstCrl(boolean checkAgainstCrl) {
		this.checkAgainstCrl = checkAgainstCrl;
	}

	public List<byte[]> getChain() {
		return chain;
	}

	public void setChain(List<byte[]> chain) {
		this.chain = chain;
	}

	public void addChain(byte[] cert) {
		if(this.chain == null) {
			this.chain = new ArrayList<byte[]>();
		}
		this.chain.add(cert);
	}
	
}
