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
 * The information of Signature Policy Qualifier extracted from the CMS:signedData:signerInfo:signedAttributes
 * @author ramir_000
 *
 */
public class DigitalSignatureSigPolicyQualifierResults implements Serializable {

	private static final long serialVersionUID = 4204510744493188205L;
	
	protected String sigPolicyQualifierId = "";
    protected String sigQualifierSPUri = "";
    protected String sigQualifierUserNotice = "";

    public DigitalSignatureSigPolicyQualifierResults() {
    }

    public DigitalSignatureSigPolicyQualifierResults(String sigPolicyQualifierId, String sigQualifierSPUri) {
        this.sigPolicyQualifierId = sigPolicyQualifierId;
        this.sigQualifierSPUri = sigQualifierSPUri;
    }

    public String getSigPolicyQualifierId() {
        return sigPolicyQualifierId;
    }

    public void setSigPolicyQualifierId(String sigPolicyQualifierId) {
        this.sigPolicyQualifierId = sigPolicyQualifierId;
    }

    public String getSigQualifierSPUri() {
        return sigQualifierSPUri;
    }

    public void setSigQualifierSPUri(String sigQualifierSPUri) {
        this.sigQualifierSPUri = sigQualifierSPUri;
    }

    public String getSigQualifierUserNotice() {
        return sigQualifierUserNotice;
    }

    public void setSigQualifierUserNotice(String sigQualifierUserNotice) {
        this.sigQualifierUserNotice = sigQualifierUserNotice;
    }
}
