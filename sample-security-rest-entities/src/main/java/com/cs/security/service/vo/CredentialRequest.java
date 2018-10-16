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
import java.security.cert.X509Certificate;
import java.util.List;

public class CredentialRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8717990510490110323L;

	public static final long CREDENTIAL_TYPE_USER_AND_PASSWORD = 1;
	public static final long CREDENTIAL_TYPE_CERTIFICATE = 2;
	public static final long CREDENTIAL_TYPE_IP = 14;
	
	protected String userCode = "";
	protected String userEmail = "";
	protected String userCertificate = "";
	protected String userPassword = "";
	protected long credentialType = 0;
	protected String ip = "";
	
	protected X509Certificate clientCertificate = null;
	protected List<X509Certificate> listOfCertificates = null;
	
	public CredentialRequest() {
	}

	public CredentialRequest(long credentialType, String token) {
		this.credentialType = credentialType;
		if(credentialType == CREDENTIAL_TYPE_IP) {
			this.ip = token;
		} else if(credentialType == CREDENTIAL_TYPE_USER_AND_PASSWORD) {
			this.userCode = token;
		} else {
			this.userCertificate = token;
		}
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserCertificate() {
		return userCertificate;
	}

	public void setUserCertificate(String userCertificate) {
		this.userCertificate = userCertificate;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public long getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(long credentialType) {
		this.credentialType = credentialType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public X509Certificate getClientCertificate() {
		return clientCertificate;
	}

	public void setClientCertificate(X509Certificate clientCertificate) {
		this.clientCertificate = clientCertificate;
	}

	public List<X509Certificate> getListOfCertificates() {
		return listOfCertificates;
	}

	public void setListOfCertificates(List<X509Certificate> listOfCertificates) {
		this.listOfCertificates = listOfCertificates;
	}


}
