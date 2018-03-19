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
 * The information of Digital Signature extracted from the CMS:signedData after verification and validation
 * @author ramir_000
 *
 */
public class DigitalSignatureExtResults implements Serializable {
	
	private static final long serialVersionUID = 2870373494097608660L;

	public static final int SUCCESS = 0;
	public static final int NOT_VALID = 1;
	public static final int NOT_VALID_CERTIFICATE = 2;
	public static final int NOT_VALID_CERTIFICATE_EXPIRED = 3;
	public static final int NOT_VALID_NOT_AN_ICP_BRASIL_CERTIFICATE = 4;
	public static final int NOT_VALID_NOT_AN_ICP_BRASIL_A3_CERTIFICATE = 5;
	public static final int NOT_VALID_SIGNATURE =6;
	
	protected int results = NOT_VALID;
	protected String errors = "";
	
	protected int amountOfCerts = 0;
	protected int amountOfCRLs = 0;
	protected int amountOfSignatures = 0;
	
	protected List<DigitalSignatureInformationResults> listOfSignatures = new ArrayList<DigitalSignatureInformationResults>();

    protected List<DigitalSignatureCertificateInformationResults> listOfCertificates = new ArrayList<DigitalSignatureCertificateInformationResults>();
    protected List<DigitalSignatureRevocationInformationResults> listOfRevocations = new ArrayList<DigitalSignatureRevocationInformationResults>();
    protected boolean documentAttached = true;
    
	
	public DigitalSignatureExtResults() {
		super();
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

	public int getAmountOfCerts() {
		return amountOfCerts;
	}

	public void setAmountOfCerts(int amountOfCerts) {
		this.amountOfCerts = amountOfCerts;
	}

	public int getAmountOfCRLs() {
		return amountOfCRLs;
	}

	public void setAmountOfCRLs(int amountOfCRLs) {
		this.amountOfCRLs = amountOfCRLs;
	}

	public int getAmountOfSignatures() {
		return amountOfSignatures;
	}

	public void setAmountOfSignatures(int amountOfSignatures) {
		this.amountOfSignatures = amountOfSignatures;
	}

	public List<DigitalSignatureInformationResults> getListOfSignatures() {
		return listOfSignatures;
	}

	public void setListOfSignatures(List<DigitalSignatureInformationResults> listOfSignatures) {
		this.listOfSignatures = listOfSignatures;
	}

	public void addSignature(DigitalSignatureInformationResults digitalSignatureInformationResults) {
		this.listOfSignatures.add(digitalSignatureInformationResults);
	}

	public List<DigitalSignatureCertificateInformationResults> getListOfCertificates() {
		return listOfCertificates;
	}

	public void setListOfCertificates(List<DigitalSignatureCertificateInformationResults> listOfCertificates) {
		this.listOfCertificates = listOfCertificates;
	}

	public void addCertificate(DigitalSignatureCertificateInformationResults certificate) {
		this.listOfCertificates.add(certificate);
	}
	
	public List<DigitalSignatureRevocationInformationResults> getListOfRevocations() {
		return listOfRevocations;
	}

	public void setListOfRevocations(List<DigitalSignatureRevocationInformationResults> listOfRevocations) {
		this.listOfRevocations = listOfRevocations;
	}

	public void addRevocation(DigitalSignatureRevocationInformationResults revocation) {
		this.listOfRevocations.add(revocation);
	}

	public boolean isDocumentAttached() {
		return documentAttached;
	}

	public void setDocumentAttached(boolean documentAttached) {
		this.documentAttached = documentAttached;
	}
	
}
