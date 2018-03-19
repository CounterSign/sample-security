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

public class DigitalSignatureSignData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6035006910593600662L;
	
	protected byte[] data;
	protected String signedAttributes;
	protected String signerCertificate;
	protected String signature;
	protected String tsaUrl;
	protected String tsaPolicyOid;
	protected String messageDigestAlg = "SHA1";
	protected String messageDigestInHex = null;
	
	protected String signatureAlg = "RSA";
	protected String sigLocation = "";
	protected String sigLocCountry = "";
	protected String commitmentTypeIndication = "";
	
	protected int signatureType = 1;
	protected String signaturePolicyOid = null;
	
	public DigitalSignatureSignData() {
	}

	public DigitalSignatureSignData(byte[] data, String signedAttributes, String signerCertificate, String signature) {
		this.data = data;
		this.signedAttributes = signedAttributes;
		this.signerCertificate = signerCertificate;
		this.signature = signature;
	}

	public DigitalSignatureSignData(byte[] data, String signedAttributes, String signerCertificate, String signature, String messageDigestAlg) {
		this.data = data;
		this.signedAttributes = signedAttributes;
		this.signerCertificate = signerCertificate;
		this.signature = signature;
		this.messageDigestAlg = messageDigestAlg;
	}

	public DigitalSignatureSignData(byte[] data, String signedAttributes, String signerCertificate, String signature, String tsaUrl, String tsaPolicyOid) {
		this.data = data;
		this.signedAttributes = signedAttributes;
		this.signerCertificate = signerCertificate;
		this.signature = signature;
		this.tsaUrl = tsaUrl;
		this.tsaPolicyOid = tsaPolicyOid;
	}
	
	public DigitalSignatureSignData(byte[] data, String signedAttributes, String signerCertificate, String signature, String tsaUrl, String tsaPolicyOid, String messageDigestAlg) {
		this.data = data;
		this.signedAttributes = signedAttributes;
		this.signerCertificate = signerCertificate;
		this.signature = signature;
		this.tsaUrl = tsaUrl;
		this.tsaPolicyOid = tsaPolicyOid;
		this.messageDigestAlg = messageDigestAlg;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getSignedAttributes() {
		return signedAttributes;
	}

	public void setSignedAttributes(String signedAttributes) {
		this.signedAttributes = signedAttributes;
	}

	public String getTsaUrl() {
		return tsaUrl;
	}

	public void setTsaUrl(String tsaUrl) {
		this.tsaUrl = tsaUrl;
	}

	public String getTsaPolicyOid() {
		return tsaPolicyOid;
	}

	public void setTsaPolicyOid(String tsaPolicyOid) {
		this.tsaPolicyOid = tsaPolicyOid;
	}

	public String getSignerCertificate() {
		return signerCertificate;
	}

	public void setSignerCertificate(String signerCertificate) {
		this.signerCertificate = signerCertificate;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getSignatureType() {
		return signatureType;
	}

	public void setSignatureType(int signatureType) {
		this.signatureType = signatureType;
	}

	public String getMessageDigestAlg() {
		return messageDigestAlg;
	}

	public void setMessageDigestAlg(String messageDigestAlg) {
		this.messageDigestAlg = messageDigestAlg;
	}

	public String getSignatureAlg() {
		return signatureAlg;
	}

	public void setSignatureAlg(String signatureAlg) {
		this.signatureAlg = signatureAlg;
	}

	public String getSigLocation() {
		return sigLocation;
	}

	public void setSigLocation(String sigLocation) {
		this.sigLocation = sigLocation;
	}

	public String getSigLocCountry() {
		return sigLocCountry;
	}

	public void setSigLocCountry(String sigLocCountry) {
		this.sigLocCountry = sigLocCountry;
	}

	public String getCommitmentTypeIndication() {
		return commitmentTypeIndication;
	}

	public void setCommitmentTypeIndication(String commitmentTypeIndication) {
		this.commitmentTypeIndication = commitmentTypeIndication;
	}

	public String getMessageDigestInHex() {
		return messageDigestInHex;
	}

	public void setMessageDigestInHex(String messageDigestInHex) {
		this.messageDigestInHex = messageDigestInHex;
	}

	public String getSignaturePolicyOid() {
		return signaturePolicyOid;
	}

	public void setSignaturePolicyOid(String signaturePolicyOid) {
		this.signaturePolicyOid = signaturePolicyOid;
	}
	
	
}
