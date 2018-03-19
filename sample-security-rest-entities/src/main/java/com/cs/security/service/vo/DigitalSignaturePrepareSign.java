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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DigitalSignaturePrepareSign implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2547706230484592842L;
	
	public static final int DS_CMS = 1;
	public static final int DS_CMS_COSIGN = 2;
	public static final int DS_CMS_COUNTERSIGN = 3;
	public static final int DS_CMS_DETACHED = 4;
	public static final int DS_CMS_COSIGN_DETACHED = 5;
	public static final int DS_CMS_COUNTERSIGN_DETACHED = 6;
	public static final int DS_CMS_TS = 11;
	public static final int DS_CMS_TS_COSIGN = 12;
	public static final int DS_CMS_TS_COUNTERSIGN = 13;
	public static final int DS_CMS_TS_DETACHED = 14;
	public static final int DS_CMS_TS_COSIGN_DETACHED = 15;
	public static final int DS_CMS_TS_COUNTERSIGN_DETACHED = 16;
	
	public static final int DS_ICP_AD_RB = 101;
	public static final int DS_ICP_AD_RB_COSIGN = 102;
	public static final int DS_ICP_AD_RB_COUNTERSIGN = 103;
	
	public static final int DS_ICP_AD_RT = 201;
	public static final int DS_ICP_AD_RT_COSIGN = 202;
	public static final int DS_ICP_AD_RT_COUNTERSIGN = 202;

	protected byte[] dataToBeSigned;
	protected String messageDigestInHex;
	protected String messageDigestAlg = null;
	protected String signatureAlg = null;
	protected String signerCertificate;
	
	protected int signatureType = DS_CMS;
	protected String signaturePolicyOid = null;
	protected boolean isICPRequiredOnly = false;
	protected boolean isA3RequiredOnly = false;
	protected boolean checkAgainstCrl = false;
	
	public DigitalSignaturePrepareSign() {
	}

	public DigitalSignaturePrepareSign(byte[] dataToBeSigned, String messageDigestAlg, String signerCertificate, int signatureType) {
		this.dataToBeSigned = dataToBeSigned;
		this.messageDigestAlg = messageDigestAlg;
		this.signerCertificate = signerCertificate;
		this.signatureType = signatureType;
	}
	
	public DigitalSignaturePrepareSign(byte[] dataToBeSigned, String messageDigestInHex, String messageDigestAlg, String signerCertificate, int signatureType) {
		this.dataToBeSigned = dataToBeSigned;
		this.messageDigestInHex = messageDigestInHex;
		this.messageDigestAlg = messageDigestAlg;
		this.signerCertificate = signerCertificate;
		this.signatureType = signatureType;
	}

	public String getMessageDigestInHex() {
		return messageDigestInHex;
	}

	public void setMessageDigestInHex(String messageDigestInHex) {
		this.messageDigestInHex = messageDigestInHex;
	}

	public int getSignatureType() {
		return signatureType;
	}

	public void setSignatureType(int signatureType) {
		this.signatureType = signatureType;
	}


	public byte[] getDataToBeSigned() {
		return dataToBeSigned;
	}

	public void setDataToBeSigned(byte[] dataToBeSigned) {
		this.dataToBeSigned = dataToBeSigned;
	}

	public String getSignerCertificate() {
		return signerCertificate;
	}

	public void setSignerCertificate(String signerCertificate) {
		this.signerCertificate = signerCertificate;
	}

	public boolean isCheckAgainstCrl() {
		return checkAgainstCrl;
	}

	public void setCheckAgainstCrl(boolean checkAgainstCrl) {
		this.checkAgainstCrl = checkAgainstCrl;
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

	public String getMessageDigestAlg() {
		return messageDigestAlg;
	}

	public void setMessageDigestAlg(String messageDigestAlg) {
		this.messageDigestAlg = messageDigestAlg;
	}

	public String getSignaturePolicyOid() {
		return signaturePolicyOid;
	}

	public void setSignaturePolicyOid(String signaturePolicyOid) {
		this.signaturePolicyOid = signaturePolicyOid;
	}

	public String getSignatureAlg() {
		return signatureAlg;
	}

	public void setSignatureAlg(String signatureAlg) {
		this.signatureAlg = signatureAlg;
	}
	
	
}
