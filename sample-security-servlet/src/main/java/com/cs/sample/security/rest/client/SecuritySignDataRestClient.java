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
 
 NOTICE: this is a part of source code of the Digital Signature Sample using the 
         CounterSign Security Rest API.
         The CounterSign Security Rest API is a digital signature paid service and 
         to achieve it you need buy credits or acquire a license.
*/

package com.cs.sample.security.rest.client;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.cs.sample.security.rest.json.JsonConnHandler;
import com.cs.security.entities.response.DigitalSignaturePrepareResponse;
import com.cs.security.entities.response.DigitalSignatureSignDataResponse;
import com.cs.security.service.vo.DigitalSignaturePrepareSign;
import com.cs.security.service.vo.DigitalSignatureSignData;

import sun.misc.BASE64Decoder;

/**
 * A digital signature using the CounterSign:Security Rest API WebService.
 * Provides a digital signature over a simple data enveloping in the PKCS#7/CMS signedData CAdES ICP-Brasil AD-RB standard.
 * 
 * The CounterSign:Security Rest is an API to use PKI digital signatures without SDK in the client and in the easy and rapidly way to implement PKI in your project.
 * 
 * <pre>
      The CounterSign:Security API has 2 methods to be used in the digital signature process:
      1) prepareSign:
         which receives the data in the raw to be signed and prepare the data to be signed by the user in the client side
      2) envelopeSignedData:
      	 which receives the signature, the data in the raw and the the data prepared again and produces the CMS:signedData package
 * </pre>
 * 
 * @author countersign.com.br
 * 
 */
@SuppressWarnings("restriction")
public class SecuritySignDataRestClient {
	
	private static final Logger logger = Logger.getLogger(SecuritySignDataRestClient.class);

	/**
	 * Prepare the data to be signed
	 * @param signatureType the Security:API digital signature type
	 * @param signerCertificateB64 the signer´s certificate as base64 encoded
	 * @param isAttached defines that the data will be enveloped inside the CMS:signedData package
	 * @param dataToBeSignedB64 the data to be signed as base64 encoded
	 * @param messageDigestInHex the message digest of the data when is detached or null for attached
	 * @param messageDigestAlg the message digest algorithm to be used
	 * @param signatureAlg the signature algorithm to be used in the signature process
	 * @param signaturePolicyOid the ETSI digital signature Policy OID
	 * @return DigitalSignaturePrepareResponse the data prepared to be signed
	 * @throws Exception 
	 */
	public synchronized static DigitalSignaturePrepareResponse prepareSign(int signatureType, String signerCertificateB64, boolean isAttached, String dataToBeSignedB64, String messageDigestInHex, String messageDigestAlg, String signatureAlg, String signaturePolicyOid) throws IOException {

		byte[] dataToBeSigned = null;
		BASE64Decoder base64Dec = new BASE64Decoder();
		dataToBeSigned = base64Dec.decodeBuffer(dataToBeSignedB64);
		
		//creates the JSON structure to request a signature
		DigitalSignaturePrepareSign digitalSignaturePrepareSign = new DigitalSignaturePrepareSign();
		if(isAttached) {
			digitalSignaturePrepareSign.setDataToBeSigned(dataToBeSigned);
		} else {
			//when using in the detached mode, is required to hash the data in the client side and encoded it in Hexadecimal
			digitalSignaturePrepareSign.setMessageDigestInHex(messageDigestInHex);
		}
		digitalSignaturePrepareSign.setMessageDigestAlg(messageDigestAlg);
		digitalSignaturePrepareSign.setSignerCertificate(signerCertificateB64);
		digitalSignaturePrepareSign.setSignatureType(signatureType);
		digitalSignaturePrepareSign.setSignaturePolicyOid(signaturePolicyOid);
		
		//the CounterSign:Security API Rest WebService URL
		String svcUrl = "http://localhost:8080/security-rest/SecurityAPIRest";
		
		//call the CounterSign:Security Rest API for signature preparation method
		DigitalSignaturePrepareResponse digitalSignaturePrepareResponse = (DigitalSignaturePrepareResponse) JsonConnHandler.callSvcREST(svcUrl + "/prepareSign", digitalSignaturePrepareSign, DigitalSignaturePrepareResponse.class);
		if(digitalSignaturePrepareResponse == null) {
			logger.error("Error: Can not get access to Security Rest API WebService");
			return null;
		}
		
		return digitalSignaturePrepareResponse;
	}
	
	/**
	 * Envelope the data which was signed by the client in the CMS standard
	 * @param signatureType the Security:API digital signature type
	 * @param signerCertificateB64 the signer´s certificate as base64 encoded
	 * @param isAttached defines that the data will be enveloped inside the CMS:signedData package
	 * @param dataToBeSignedB64 the data to be signed as base64 encoded
	 * @param messageDigestInHex the message digest of the data when is detached or null for attached
	 * @param messageDigestAlg the message digest algorithm to be used
	 * @param signatureAlg the signature algorithm to be used in the signature process
	 * @param signaturePolicyOid the ETSI digital signature Policy OID
	 * @param tsaUrl the TSA RFC3161 URL address
	 * @param tsaPolicyOid the TSA Policy OID
	 * @return DigitalSignatureSignDataResponse the CMS
	 * @throws Exception 
	 */
	public synchronized static DigitalSignatureSignDataResponse envelopeSignedData(int signatureType, String signerCertificateB64, boolean isAttached, String dataToBeSignedB64, String messageDigestInHex, String messageDigestAlg, String signatureAlg, String signedAttributesB64, String signatureB64, String signaturePolicyOid, String tsaUrl, String tsaPolicyOid) throws IOException {

		byte[] dataToBeSigned = null;
		BASE64Decoder base64Dec = new BASE64Decoder();
		dataToBeSigned = base64Dec.decodeBuffer(dataToBeSignedB64);
		
		//call the CounterSign:Security API Rest WebService to envelope the signature
		DigitalSignatureSignData digitalSignatureSignData = new DigitalSignatureSignData();
		if(isAttached) {
			digitalSignatureSignData.setData(dataToBeSigned);
		} else {
			digitalSignatureSignData.setMessageDigestInHex(messageDigestInHex);
		}
		digitalSignatureSignData.setSignedAttributes(signedAttributesB64);
		digitalSignatureSignData.setSignerCertificate(signerCertificateB64);
		digitalSignatureSignData.setSignature(signatureB64);
		digitalSignatureSignData.setMessageDigestAlg(messageDigestAlg);
		
		digitalSignatureSignData.setSignaturePolicyOid(signaturePolicyOid);
		digitalSignatureSignData.setCommitmentTypeIndication(null);
		digitalSignatureSignData.setSigLocation(null);
		digitalSignatureSignData.setSigLocCountry(null);
		
		if( (tsaUrl != null) && (tsaUrl.isEmpty()==false) ) {
			digitalSignatureSignData.setTsaUrl(tsaUrl);
		}
		if( (tsaPolicyOid != null) && (tsaPolicyOid.isEmpty()==false) ) {
			digitalSignatureSignData.setTsaPolicyOid(tsaPolicyOid);
		}

		//the CounterSign:Security API Rest WebService URL
		String svcUrl = "http://localhost:8080/security-rest/SecurityAPIRest";
		
		//call the CounterSign:Security Rest API for envelope the signature in the signedData method
		DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = (DigitalSignatureSignDataResponse) JsonConnHandler.callSvcREST(svcUrl + "/envelopeSignedData", digitalSignatureSignData, DigitalSignatureSignDataResponse.class);
		if(digitalSignatureSignDataResponse == null) {
			logger.error("Error: Can not get access to Security Rest API WebService");
			return null;
		}
		return digitalSignatureSignDataResponse;
	}
}
