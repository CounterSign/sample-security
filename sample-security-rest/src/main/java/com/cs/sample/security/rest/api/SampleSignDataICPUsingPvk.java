package com.cs.sample.security.rest.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

import org.apache.commons.codec.binary.Hex;

import com.cs.sample.security.rest.json.JsonConnHandler;
import com.cs.security.entities.response.DigitalSignaturePrepareResponse;
import com.cs.security.entities.response.DigitalSignaturePrepareResponseExt;
import com.cs.security.entities.response.DigitalSignatureSignDataResponse;
import com.cs.security.entities.response.ErrorResponse;
import com.cs.security.entities.response.VerifyDigitalSignatureResponse;
import com.cs.security.service.vo.AuthenticationVo;
import com.cs.security.service.vo.CredentialRequest;
import com.cs.security.service.vo.DigitalSignatureExtResults;
import com.cs.security.service.vo.DigitalSignaturePrepareSign;
import com.cs.security.service.vo.DigitalSignatureSignData;
import com.cs.security.service.vo.RequestDigitalSignaturePrepareSign;
import com.cs.security.service.vo.RequestDigitalSignatureSignData;
import com.cs.security.service.vo.RequestVerifyDigitalSignatureData;
import com.cs.security.service.vo.VerifyDigitalSignatureData;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * A digital sign sample using the CounterSign:Security Rest API WebService with the Private Key hosted in the CounterSign.
 * Provides a digital signature over a simple data enveloping in the PKCS#7/CMS signedData CAdES ICP-Brasil AD-XX standard.
 * 
 * The CounterSign:Security Rest is an API to use PKI digital signatures without SDK in the client and in the easy and rapidly way to implement PKI in your project.
 * 
 * <pre>
      The CounterSign:Security API has 1 methods to be used in the digital signature process:
      1) sign:
         which receives the data in the raw to be signed and the private key code. The method will sign the data in server side
 * </pre>
 * 
 * @author countersign.com.br
 * 
 */
public class SampleSignDataICPUsingPvk {
	
	public static void main(String[] args) {
		
		byte[] data = null;
		try {
			data = extractDataFromFile("c:\\temp\\test.txt");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			byte[] signedData = null;
			String pvkCode = "require from CounterSign";
			String password = "require from CounterSign";
			
			//Sign a data in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData
			signedData = SampleSignDataICPUsingPvk.sign(data, pvkCode, password, true, DigitalSignaturePrepareSign.DS_CMS, null, "SVCCOUNTER0004", "require the contract item number from CounterSign", 2);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData
			SampleSignDataICPUsingPvk.verify(signedData, data, true, 2);
			
			//Sign a data in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICPUsingPvk.sign(data, pvkCode, password, true, DigitalSignaturePrepareSign.DS_ICP_AD_RB, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0001", "require the contract item number from CounterSign", 2);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICPUsingPvk.verify(signedData, data, true, 2);
			
			//CoSign a signedData in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICP.sign(signedData, "windows", "MARCIO ROBERTO", null, null, true, DigitalSignaturePrepareSign.DS_ICP_AD_RB_COSIGN, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0003", "require the contract item number from CounterSign", 2);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICP.verify(signedData, data, true, 2);
			
			//Sign a data in the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, false, DigitalSignaturePrepareSign.DS_ICP_AD_RB, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0001", "require the contract item number from CounterSign", 2);
			
			//Verify the signed data from the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICP.verify(signedData, data, false, 2);
			
			//Sign a data in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			//using the PA AD-RT vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RT_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, true, DigitalSignaturePrepareSign.DS_ICP_AD_RT, "2 16 76 1 7 1 2 2 2", "http://time.certum.pl", null, "SVCCOUNTER0002", "require the contract item number from CounterSign", 2);
			
			//Verify a signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			SampleSignDataICP.verify(signedData, data, true, 2);
			
			//Sign a data in the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			//using the PA AD-RT vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RT_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, false, DigitalSignaturePrepareSign.DS_ICP_AD_RT, "2 16 76 1 7 1 2 2 2", "http://time.certum.pl", null, "SVCCOUNTER0002", "require the contract item number from CounterSign", 2);
	
			//Verify a signed data from the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			SampleSignDataICP.verify(signedData, data, false, 2);
			
			try {
				data = extractDataFromFile("c:\\temp\\test.pdf");
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			//Sign a data in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICPUsingPvk.sign(data, pvkCode, password, true, DigitalSignaturePrepareSign.DS_ICP_AD_RB, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0001", "require the contract item number from CounterSign", 1);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICPUsingPvk.verify(signedData, data, true, 1);
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Sign a data in the PKI CMS:signedData standard
	 * @param data the data to be signed
	 * @param pvkCode the Signer큦 Private Key Code
	 * @param password the Signer큦 Certificate repository password
	 * @param isAttached defines that the data will be enveloped inside the CMS:signedData package
	 * @param signType the Security:API digital signature type
	 * @param signaturePolicyOid the ETSI digital signature Policy OID
	 * @param productSku the SKU of the product
	 * @param contractItemNumber the contract item number
	 * @param idDocumentType the type of document
	 * @throws Exception 
	 */
	public static byte[] sign(byte[] data, String pvkCode, String password, boolean isAttached, int signType, String signaturePolicyOid, String productSku, String contractItemNumber, int idDocumentType) throws Exception {
		return sign(data, pvkCode, password, isAttached, signType, signaturePolicyOid, null, null, productSku, contractItemNumber, idDocumentType);
	}

	/**
	 * Sign a data in the PKI CMS:signedData standard
	 * @param data the data to be signed
	 * @param pvkCode the Signer큦 Private Key Code
	 * @param password the Signer큦 Certificate repository password
	 * @param isAttached defines that the data will be enveloped inside the CMS:signedData package
	 * @param signType the Security:API digital signature type
	 * @param signaturePolicyOid the ETSI digital signature Policy OID
	 * @param tsaUrl the TSA RFC3161 URL address
	 * @param tsaPolicyOid the TSA Policy OID
	 * @param productSku the SKU of the product
	 * @param contractItemNumber the contract item number
	 * @param idDocumentType the type of document
	 * @throws Exception 
	 */
	public static byte[] sign(byte[] data, String pvkCode, String password, boolean isAttached, int signType, String signaturePolicyOid, String tsaUrl, String tsaPolicyOid, String productSku, String contractItemNumber, int idDocumentType) throws Exception {
		
		//the data to be signed
		byte[] dataToBeSigned = data;
		//the signer certificate encoded as Base64
		String signerCertificateB64 = "";
		//the message digest algorithm (SHA1 or SHA-256)
		String messageDigestAlg = "SHA-256";
		//the signature algorithm
		String signatureAlg = "SHA256WithRSA";
		//the type of signature to be encoded
		int signatureType = signType;
		//the message digest for detached mode
		String messageDigestInHex = null;
		
		//creates the JSON structure to request a signature
		DigitalSignaturePrepareSign digitalSignaturePrepareSign = new DigitalSignaturePrepareSign();
		if(isAttached) {
			digitalSignaturePrepareSign.setDataToBeSigned(dataToBeSigned);
		} else {
			//when using in the detached mode, is required to hash the data in the client side and encoded it in Hexadecimal
			try {
				MessageDigest md = MessageDigest.getInstance(messageDigestAlg);
				byte[] hashed = md.digest(dataToBeSigned);
				
				messageDigestInHex = Hex.encodeHexString(hashed);
				digitalSignaturePrepareSign.setMessageDigestInHex(messageDigestInHex);
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
	            throw new Exception(e);
			}
		}
		digitalSignaturePrepareSign.setMessageDigestAlg(messageDigestAlg);
		digitalSignaturePrepareSign.setSignerCertificate(signerCertificateB64);
		digitalSignaturePrepareSign.setSignatureType(signatureType);
		digitalSignaturePrepareSign.setSignaturePolicyOid(signaturePolicyOid);
		digitalSignaturePrepareSign.setSignerCertificate(pvkCode);
		
		//set the Credential and Authentication
		RequestDigitalSignaturePrepareSign requestDigitalSignaturePrepareSign = new RequestDigitalSignaturePrepareSign();
		requestDigitalSignaturePrepareSign.setDigitalSignaturePrepareSign(digitalSignaturePrepareSign);
		
		String appCode = "require from CounterSign";
		CredentialRequest credentialRequest = createCredential(CredentialRequest.CREDENTIAL_TYPE_IP, appCode, "your email", "your password", password);
		AuthenticationVo authenticationVo = new AuthenticationVo(appCode, "BASIC", credentialRequest);
		requestDigitalSignaturePrepareSign.setAuthenticationVo(authenticationVo);
		requestDigitalSignaturePrepareSign.setClientDocId("your ID");
		requestDigitalSignaturePrepareSign.setContractItemNumber(contractItemNumber);
		requestDigitalSignaturePrepareSign.setDocumentName("Test 1 API");
		requestDigitalSignaturePrepareSign.setFlagSaveDocument(false);
		requestDigitalSignaturePrepareSign.setIdDocumentType(idDocumentType);
		requestDigitalSignaturePrepareSign.setProductSku(productSku);
		//when you use PDF documents, use this fields to make a signature stamp
		requestDigitalSignaturePrepareSign.setPageIndex(1);//adjust with the index of page, or -1 for use the CounterSign default
		requestDigitalSignaturePrepareSign.setPosX(0);//the X position, starting from the left and growing to the right
		requestDigitalSignaturePrepareSign.setPosY(0);//the Y position, starting from the bottom and growing to the top
		requestDigitalSignaturePrepareSign.setQRCodeEnabled(-1);//disable
		
		//the CounterSign:Security API Rest WebService URL
		String svcUrl = "https://www.countersignweb.com/countersign-rest/CounterSignAPIRest";
		
		//call the CounterSign:Security Rest API for envelope the signature in the signedData method
		DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = (DigitalSignatureSignDataResponse) JsonConnHandler.callSvcREST(svcUrl + "/sign", requestDigitalSignaturePrepareSign, DigitalSignatureSignDataResponse.class);
		if(digitalSignatureSignDataResponse == null) {
			System.out.println("Error: Can not get access to the Security Rest API WebService");
            throw new Exception("Error: Can not get access to the Security Rest API WebService");
		}
			
		//check the Security API Rest WebService signature results
		if(digitalSignatureSignDataResponse.isSuccess()) {
			//retrieve the signed data from the results
			byte[] cmsSignedData = digitalSignatureSignDataResponse.getSignedData();
			
			System.out.println("CMS signedData was generated with success. " + (isAttached==true ? "Attached" : "Detached") + " mode");
			
			FileOutputStream fileOut = null;
			try {
				fileOut = new FileOutputStream("c:\\temp\\signedCAdES_ICP_AD_" + String.valueOf(signType) + "_" + (isAttached==true ? "Attached" : "Detached") + ".p7s");
				fileOut.write(cmsSignedData);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return cmsSignedData;
			
		} else {
			//get the errors returned by the server
			List<ErrorResponse> listOfErrors = digitalSignatureSignDataResponse.getListErrorResponse();
			//show errors
			for(ErrorResponse errorResponse : listOfErrors) {
				System.out.println("Error: " + String.valueOf(errorResponse.getCode()) + " " + errorResponse.getError());
			}
			return null;
		}
	}
	
	/**
	 * Verify a signed data from the PKI CMS:signedData standard
	 * @param signedData the CMS package encoded as signedData
	 * @param dataToBeSigned the data to be hashed and compared when is in detached
	 * @param isAttached defines that the data will be enveloped inside the CMS:signedData package
	 * @param idDocumentType the type of document
	 */
	public static void verify(byte[] signedData, byte[] dataToBeSigned, boolean isAttached, int idDocumentType) {
		
		//the message digest algorithm (SHA1 or SHA-256)
		String messageDigestAlg = "SHA-256";
		//the message digest for detached mode
		String messageDigestInHex = null;
		
		//creates the JSON structure to request a signature verification
		VerifyDigitalSignatureData verifyDigitalSignatureData = new VerifyDigitalSignatureData();
		if(! isAttached) {
			//when using in the detached mode, is required to hash the data in the client side and encoded it in Hexadecimal
			try {
				MessageDigest md = MessageDigest.getInstance(messageDigestAlg);
				byte[] hashed = md.digest(dataToBeSigned);
				
				messageDigestInHex = Hex.encodeHexString(hashed);
				verifyDigitalSignatureData.setMessageDigestInHex(messageDigestInHex);
				
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return;
			}
		}
		verifyDigitalSignatureData.setSignedData(signedData);
		
		//set the Credential and Authentication
		String appCode = "require from CounterSign";
		CredentialRequest credentialRequest = createCredential(CredentialRequest.CREDENTIAL_TYPE_IP, appCode, "your email", "your password", null);
		AuthenticationVo authenticationVo = new AuthenticationVo(appCode, "BASIC", credentialRequest);
		RequestVerifyDigitalSignatureData requestVerifyDigitalSignatureData = new RequestVerifyDigitalSignatureData();
		requestVerifyDigitalSignatureData.setVerifyDigitalSignatureData(verifyDigitalSignatureData);
		requestVerifyDigitalSignatureData.setAuthenticationVo(authenticationVo);
		requestVerifyDigitalSignatureData.setClientDocId("your ID");
		requestVerifyDigitalSignatureData.setContractItemNumber("require the contract item number from CounterSign");
		requestVerifyDigitalSignatureData.setDocumentName("Test 1 API");
		requestVerifyDigitalSignatureData.setIdDocumentType(idDocumentType);
		requestVerifyDigitalSignatureData.setProductSku("SVCCOUNTER2002");
		
		//the CounterSign:Security API Rest WebService URL
		String svcUrl = "https://www.countersignweb.com/countersign-rest/CounterSignAPIRest";
		
		//call the CounterSign:Security Rest API for signature verification method
		VerifyDigitalSignatureResponse verifyDigitalSignatureResponse = (VerifyDigitalSignatureResponse) JsonConnHandler.callSvcREST(svcUrl + "/verifySignature", requestVerifyDigitalSignatureData, VerifyDigitalSignatureResponse.class);
		if(verifyDigitalSignatureResponse == null) {
			System.out.println("Error: Can not get access to Security Rest API WebService");
			return;
		}
		
		//retrieve from the results the data prepared to be signed
		DigitalSignatureExtResults digitalSignatureResults= null;
		if(verifyDigitalSignatureResponse.isSuccess()) {
			//get the data prepared in the Server to be signed for the user
			digitalSignatureResults = verifyDigitalSignatureResponse.getDigitalSignatureExtResults();
		} else {
			//get the errors returned by the server
			List<ErrorResponse> listOfErrors = verifyDigitalSignatureResponse.getListErrorResponse();
			//show errors
			for(ErrorResponse errorResponse : listOfErrors) {
				System.out.println("Error: " + String.valueOf(errorResponse.getCode()) + " " + errorResponse.getError());
			}
			return;
		}
		
		System.out.println("Digital Signature Verification was complete with success");
	}
	
	private static byte[] extractDataFromFile(String file) throws IOException {
		FileInputStream fileIn = null;
		fileIn = new FileInputStream(file);
		byte[] data = new byte[fileIn.available()];
		fileIn.read(data);
		fileIn.close();
		
		return data;
	}

	/**
	 * Create a new TOKEN access
	 * @param credentialType the type of credential
	 * @param appCode the application code which was registered in the CounterSign
	 * @param userEmail the user큦 email
	 * @param userPassword the user큦 password
	 * @param pvkPassword the Private Key TOKEN access code (provided by CounterSign)
	 * @return
	 */
	private static CredentialRequest createCredential(long credentialType, String appCode, String userEmail, String userPassword, String pvkPassword) {
		CredentialRequest credentialRequest = new CredentialRequest(credentialType, appCode);

		if(credentialType == CredentialRequest.CREDENTIAL_TYPE_IP) {
			credentialRequest.setUserEmail(userEmail);
			credentialRequest.setUserCertificate(pvkPassword);
            return credentialRequest;
			
		} else if(credentialType == CredentialRequest.CREDENTIAL_TYPE_USER_AND_PASSWORD) {
			String credential = "USER_EMAIL=" + userEmail + "&USER_PASSWORD=" + userPassword + "&TIME=" + new Date().toString();
			
			if(pvkPassword != null) {
				credential = credential + "&PVK_PASSWORD=" + pvkPassword;
			}
			
	        try {
	            SecretKeySpec symmetricKey = new SecretKeySpec(appCode.substring(0, 16).getBytes(), "AES");
	            AlgorithmParameterSpec algParamSpec = (AlgorithmParameterSpec) new javax.crypto.spec.IvParameterSpec(appCode.substring(16, 32).getBytes());
	
	            //cipher the data
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, symmetricKey, algParamSpec);
	            byte[] ciphered = cipher.doFinal(credential.getBytes());
	            
	            credentialRequest.setUserCode(Hex.encodeHexString(ciphered));
	            return credentialRequest;
	
	        } catch (InvalidKeyException ex) {
	        	ex.printStackTrace();
	        } catch (NoSuchAlgorithmException ex) {
	        	ex.printStackTrace();
	        } catch (NoSuchPaddingException ex) {
	        	ex.printStackTrace();
	        } catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return null;
			
	}
}
