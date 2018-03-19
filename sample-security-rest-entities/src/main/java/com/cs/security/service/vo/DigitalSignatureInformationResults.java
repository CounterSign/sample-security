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

/**
 * The Information about the Digital Signature extracted from the CMS:signedData package
 * @author ramir_000
 *
 */
public class DigitalSignatureInformationResults implements Serializable {

	private static final long serialVersionUID = -3098964085729807475L;
	
	public static final int SUCCESS = 0;
	public static final int VALID = 0;
	public static final int NOT_VALID = 1;
	public static final int NOT_VALID_CERTIFICATE = 2;
	public static final int NOT_VALID_CERTIFICATE_EXPIRED = 3;
	public static final int NOT_VALID_NOT_AN_ICP_BRASIL_CERTIFICATE = 4;
	public static final int NOT_VALID_NOT_AN_ICP_BRASIL_A3_CERTIFICATE = 5;
	public static final int NOT_VALID_SIGNATURE =6;
	
	protected int results = NOT_VALID;
	protected String errors = "";
	
	protected boolean digitalSignatureValid = false;
    protected DigitalSignatureSignerInformationResults signerInfo = new DigitalSignatureSignerInformationResults();

    protected String digestAlgorithm = "";
    protected List<DigitalSignatureAttributeInformationResults> signedAttributes = new ArrayList<DigitalSignatureAttributeInformationResults>();
    protected String signatureAlgorithm = "";
    protected List<DigitalSignatureAttributeInformationResults> unsignedAttrs =  new ArrayList<DigitalSignatureAttributeInformationResults>();
    protected String signatureThumbprint = "";
    protected byte[] signature = null;

    protected byte[] signerInfoEncoded = null;
    
    //known signed attributes
    protected String signingTime = "";
    protected String messageDigest = "";
    protected String content = "";
    protected DigitalSignaturePolicyIdentifierResults signaturePolicyIdentifier = null;

    //known unsigned attributes
    protected DigitalSignatureTSTInfoResults timestampingTokenInfo = null;
    protected DigitalSignatureInformationResults counterSignInfo = null;
    protected DigitalSignatureSignerInformationResults cmsSignerInfo = null;
    
    public DigitalSignatureInformationResults() {
    }

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public boolean isDigitalSignatureValid() {
		return digitalSignatureValid;
	}

	public void setDigitalSignatureValid(boolean digitalSignatureValid) {
		this.digitalSignatureValid = digitalSignatureValid;
	}

	public DigitalSignatureSignerInformationResults getSignerInfo() {
		return signerInfo;
	}

	public void setSignerInfo(DigitalSignatureSignerInformationResults signerInfo) {
		this.signerInfo = signerInfo;
	}

	public String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	public void setDigestAlgorithm(String digestAlgorithm) {
		this.digestAlgorithm = digestAlgorithm;
	}

	public List<DigitalSignatureAttributeInformationResults> getSignedAttributes() {
		return signedAttributes;
	}

	public void setSignedAttributes(List<DigitalSignatureAttributeInformationResults> signedAttributes) {
		this.signedAttributes = signedAttributes;
	}

	public void addSignedAttributes(DigitalSignatureAttributeInformationResults signedAttribute) {
		this.signedAttributes.add(signedAttribute);
	}
	
	public String getSignatureAlgorithm() {
		return signatureAlgorithm;
	}

	public void setSignatureAlgorithm(String signatureAlgorithm) {
		this.signatureAlgorithm = signatureAlgorithm;
	}

	public List<DigitalSignatureAttributeInformationResults> getUnsignedAttrs() {
		return unsignedAttrs;
	}

	public void setUnsignedAttrs(List<DigitalSignatureAttributeInformationResults> unsignedAttrs) {
		this.unsignedAttrs = unsignedAttrs;
	}

	public void addUnsignedAttrs(DigitalSignatureAttributeInformationResults unsignedAttr) {
		this.unsignedAttrs.add(unsignedAttr);
	}

	public String getSignatureThumbprint() {
		return signatureThumbprint;
	}

	public void setSignatureThumbprint(String signatureThumbprint) {
		this.signatureThumbprint = signatureThumbprint;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public byte[] getSignerInfoEncoded() {
		return signerInfoEncoded;
	}

	public void setSignerInfoEncoded(byte[] signerInfoEncoded) {
		this.signerInfoEncoded = signerInfoEncoded;
	}

	public DigitalSignatureTSTInfoResults getTimestampingTokenInfo() {
		return timestampingTokenInfo;
	}

	public void setTimestampingTokenInfo(DigitalSignatureTSTInfoResults timestampingTokenInfo) {
		this.timestampingTokenInfo = timestampingTokenInfo;
	}

	public DigitalSignaturePolicyIdentifierResults getSignaturePolicyIdentifier() {
		return signaturePolicyIdentifier;
	}

	public void setSignaturePolicyIdentifier(DigitalSignaturePolicyIdentifierResults signaturePolicyIdentifier) {
		this.signaturePolicyIdentifier = signaturePolicyIdentifier;
	}


	public DigitalSignatureInformationResults getCounterSignInfo() {
		return counterSignInfo;
	}

	public void setCounterSignInfo(DigitalSignatureInformationResults counterSignInfo) {
		this.counterSignInfo = counterSignInfo;
	}

	public DigitalSignatureSignerInformationResults getCmsSignerInfo() {
		return cmsSignerInfo;
	}

	public void setCmsSignerInfo(DigitalSignatureSignerInformationResults cmsSignerInfo) {
		this.cmsSignerInfo = cmsSignerInfo;
	}

	public String getSigningTime() {
		return signingTime;
	}

	public void setSigningTime(String signingTime) {
		this.signingTime = signingTime;
	}

	public String getMessageDigest() {
		return messageDigest;
	}

	public void setMessageDigest(String messageDigest) {
		this.messageDigest = messageDigest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    
}
