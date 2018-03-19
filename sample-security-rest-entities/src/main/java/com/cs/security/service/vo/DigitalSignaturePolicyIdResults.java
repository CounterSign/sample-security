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
 * The information of Signature Policy Identifier extracted from the CMS:signedData:signerInfo:signedAttributes
 * @author ramir_000
 *
 */
public class DigitalSignaturePolicyIdResults implements Serializable {

	private static final long serialVersionUID = -8947972414783278009L;
	
	protected String sigPolicyId = "";
    protected String signPolicyHashAlgorithm = "";
    protected String sigPolicyHash = "";
    protected List<DigitalSignatureSigPolicyQualifierResults> listOfSigPolicyQualifiers = new ArrayList<DigitalSignatureSigPolicyQualifierResults>();

    public DigitalSignaturePolicyIdResults(){
    }

    public DigitalSignaturePolicyIdResults(String sigPolicyId, String signPolicyHashAlgorithm, String sigPolicyHash, List<DigitalSignatureSigPolicyQualifierResults> listOfSigPolicyQualifiers) {
        this.sigPolicyId = sigPolicyId;
        this.signPolicyHashAlgorithm = signPolicyHashAlgorithm;
        this.sigPolicyHash = sigPolicyHash;
        this.listOfSigPolicyQualifiers = listOfSigPolicyQualifiers;
    }

	public String getSigPolicyId() {
		return sigPolicyId;
	}

	public void setSigPolicyId(String sigPolicyId) {
		this.sigPolicyId = sigPolicyId;
	}

	public String getSignPolicyHashAlgorithm() {
		return signPolicyHashAlgorithm;
	}

	public void setSignPolicyHashAlgorithm(String signPolicyHashAlgorithm) {
		this.signPolicyHashAlgorithm = signPolicyHashAlgorithm;
	}

	public String getSigPolicyHash() {
		return sigPolicyHash;
	}

	public void setSigPolicyHash(String sigPolicyHash) {
		this.sigPolicyHash = sigPolicyHash;
	}

	public List<DigitalSignatureSigPolicyQualifierResults> getListOfSigPolicyQualifiers() {
		return listOfSigPolicyQualifiers;
	}

	public void setListOfSigPolicyQualifiers(List<DigitalSignatureSigPolicyQualifierResults> listOfSigPolicyQualifiers) {
		this.listOfSigPolicyQualifiers = listOfSigPolicyQualifiers;
	}

	public void addSigPolicyQualifier(DigitalSignatureSigPolicyQualifierResults sigPolicyQualifier) {
		this.listOfSigPolicyQualifiers.add(sigPolicyQualifier);
	}

}
