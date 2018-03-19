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
import java.util.Date;
import java.util.List;

/**
 * The information about Time Stamp Token extracted from the Digital Signature
 * @author ramir_000
 *
 */
public class DigitalSignatureTSTInfoResults implements Serializable {

	private static final long serialVersionUID = 4351603414620782681L;

    //validation message code
    public static final int TST_TOKEN_VALID = 0;
    public static final int TST_TOKEN_NOT_VALID_HASH = 1;
    public static final int TST_TOKEN_SIGNER_CERTIFICATE_IS_NOT_VALID = 2;
    public static final int TST_TOKEN_SIGNER_INFO_CERTIFICATE_IS_NOT_VALID_EXPIRED = 3;
    public static final int TST_TOKEN_SIGNER_INFO_CERTIFICATE_IS_NOT_VALID_YET = 4;
    public static final int TST_TOKEN_SIGNER_INFO_CERTIFICATE_IS_NOT_VALID_SUBJECT_IS_NOT_EQUAL = 5;
    public static final int TST_TOKEN_SIGNER_INFO_IS_NOT_VALID = 6;
    public static final int TST_TOKEN_INVALID_SIGNATURE = 7;
    public static final int TST_TOKEN_SIGNER_CERTIFICATE_NOT_HAVE_TIMESTAMPING_EXT_KEY_USAGE_ERROR = 8;
    
	protected long version = 0;
    protected String serialNumber = "";
    protected String nonce = "";
    protected boolean ordering = false;
    protected String policyOid = "";
    protected Date genTime = new Date();
    protected long seconds = 0;
    protected long millis = 0;
    protected long micros = 0;
    protected String tsaGenNames = "";
    protected String algorithmIdentifierOid = "";
    protected String hashedMessage = "";

    protected boolean status = false;

    protected DigitalSignatureInformationResults signatureInformation = new DigitalSignatureInformationResults();
    protected List<DigitalSignatureCertificateChoiceResults> listOfCertificateChoices = null;
    protected List<DigitalSignatureRevocationChoiceResults> listOfRevocationChoices = null;

    protected byte[] signature = null;
    protected byte[] encoded = null;

    protected int validationStatus = TST_TOKEN_VALID;

	public DigitalSignatureTSTInfoResults() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public boolean isOrdering() {
		return ordering;
	}

	public void setOrdering(boolean ordering) {
		this.ordering = ordering;
	}

	public String getPolicyOid() {
		return policyOid;
	}

	public void setPolicyOid(String policyOid) {
		this.policyOid = policyOid;
	}

	public Date getGenTime() {
		return genTime;
	}

	public void setGenTime(Date genTime) {
		this.genTime = genTime;
	}

	public long getSeconds() {
		return seconds;
	}

	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
	}

	public long getMicros() {
		return micros;
	}

	public void setMicros(long micros) {
		this.micros = micros;
	}

	public String getTsaGenNames() {
		return tsaGenNames;
	}

	public void setTsaGenNames(String tsaGenNames) {
		this.tsaGenNames = tsaGenNames;
	}

	public String getAlgorithmIdentifierOid() {
		return algorithmIdentifierOid;
	}

	public void setAlgorithmIdentifierOid(String algorithmIdentifierOid) {
		this.algorithmIdentifierOid = algorithmIdentifierOid;
	}

	public String getHashedMessage() {
		return hashedMessage;
	}

	public void setHashedMessage(String hashedMessage) {
		this.hashedMessage = hashedMessage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	public DigitalSignatureInformationResults getSignatureInformation() {
		return signatureInformation;
	}

	public void setSignatureInformation(DigitalSignatureInformationResults signatureInformation) {
		this.signatureInformation = signatureInformation;
	}

	public List<DigitalSignatureCertificateChoiceResults> getListOfCertificateChoices() {
		return listOfCertificateChoices;
	}

	public void setListOfCertificateChoices(List<DigitalSignatureCertificateChoiceResults> listOfCertificateChoices) {
		this.listOfCertificateChoices = listOfCertificateChoices;
	}

	public List<DigitalSignatureRevocationChoiceResults> getListOfRevocationChoices() {
		return listOfRevocationChoices;
	}

	public void setListOfRevocationChoices(List<DigitalSignatureRevocationChoiceResults> listOfRevocationChoices) {
		this.listOfRevocationChoices = listOfRevocationChoices;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public byte[] getEncoded() {
		return encoded;
	}

	public void setEncoded(byte[] encoded) {
		this.encoded = encoded;
	}

	public int getValidationStatus() {
		return validationStatus;
	}

	public void setValidationStatus(int validationStatus) {
		this.validationStatus = validationStatus;
	}

}
