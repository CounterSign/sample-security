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
 * A digital sign sample using the CounterSign:Security Rest API WebService.
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
public class SampleSignDataICP {
	
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
			
			//Sign a data in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, true, DigitalSignaturePrepareSign.DS_ICP_AD_RB, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0001", "require the contract item number from CounterSign", 2);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICP.verify(signedData, data, true, 2);
			
			//CoSign a signedData in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICP.sign(signedData, "windows", "MARCIO ROBERTO", null, null, true, DigitalSignaturePrepareSign.DS_ICP_AD_RB_COSIGN, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0003", "325d5b8d-9ee8-40aa-a45b-52019b8f3226", 2);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICP.verify(signedData, data, true, 2);
			
			//Sign a data in the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			//using the PA AD-RB vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RB_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, false, DigitalSignaturePrepareSign.DS_ICP_AD_RB, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0001", "require the contract item number from CounterSign", 2);
			
			//Verify the signed data from the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICP.verify(signedData, data, false, 2);
			
			//Sign a data in the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			//using the PA AD-RT vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RT_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, true, DigitalSignaturePrepareSign.DS_ICP_AD_RT, "2 16 76 1 7 1 2 2 2", "http://time.certum.pl", null, "SVCCOUNTER0002", "bba6d0d2-36ba-4f0e-b29d-bc0af090b23a", 2);
			
			//Verify a signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			SampleSignDataICP.verify(signedData, data, true, 2);
			
			//Sign a data in the PKI CMS:signedData detached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RT
			//using the PA AD-RT vs2.2 available at http://politicas.icpbrasil.gov.br/PA_AD_RT_v2_2.der
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, false, DigitalSignaturePrepareSign.DS_ICP_AD_RT, "2 16 76 1 7 1 2 2 2", "http://time.certum.pl", null, "SVCCOUNTER0002", "bba6d0d2-36ba-4f0e-b29d-bc0af090b23a", 2);
	
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
			signedData = SampleSignDataICP.sign(data, "windows", "MARCIO ROBERTO", null, null, true, DigitalSignaturePrepareSign.DS_ICP_AD_RB, "2 16 76 1 7 1 1 2 2", "SVCCOUNTER0001", "require the contract item number from CounterSign", 1);
			
			//Verify the signed data from the PKI CMS:signedData attached standard PKCS#7 = CMS:signedData CAdES ICP-Brasil AD-RB
			SampleSignDataICP.verify(signedData, data, true, 1);
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Sign a data in the PKI CMS:signedData standard
	 * @param data the data to be signed
	 * @param repos the Signer큦 Certificate repository
	 * @param cn the Signer큦 Certificate Common Name to filter the results from the List of Certificates found with Private Key in the Repos
	 * @param fileName the Signer큦 Certificate JKS or PFX repository
	 * @param password the Signer큦 Certificate repository password
	 * @param isAttached defines that the data will be enveloped inside the CMS:signedData package
	 * @param signType the Security:API digital signature type
	 * @param signaturePolicyOid the ETSI digital signature Policy OID
	 * @param productSku the SKU of the product
	 * @param contractItemNumber the contract item number
	 * @param idDocumentType the type of document
	 * @throws Exception 
	 */
	public static byte[] sign(byte[] data, String repos, String cn, String fileName, String password, boolean isAttached, int signType, String signaturePolicyOid, String productSku, String contractItemNumber, int idDocumentType) throws Exception {
		return sign(data, repos, cn, fileName, password, isAttached, signType, signaturePolicyOid, null, null, productSku, contractItemNumber, idDocumentType);
	}

	/**
	 * Sign a data in the PKI CMS:signedData standard
	 * @param data the data to be signed
	 * @param repos the Signer큦 Certificate repository
	 * @param cn the Signer큦 Certificate Common Name to filter the results from the List of Certificates found with Private Key in the Repos
	 * @param fileName the Signer큦 Certificate JKS or PFX repository
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
	public static byte[] sign(byte[] data, String repos, String cn, String fileName, String password, boolean isAttached, int signType, String signaturePolicyOid, String tsaUrl, String tsaPolicyOid, String productSku, String contractItemNumber, int idDocumentType) throws Exception {
		
        Certificate signerCertificate = null;
        PrivateKey privateKey = null;
        
        try {
        	
            KeyStore keyStoreForMy = KeyStore.getInstance("Windows-MY");
            keyStoreForMy.load(null, null);
            
            Enumeration<String> aliases = keyStoreForMy.aliases();
            while(aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                X509Certificate certificate = (X509Certificate) keyStoreForMy.getCertificate(alias);
                
                String serialNumber = certificate.getSerialNumber().toString(16);
                String subjectDN = certificate.getSubjectX500Principal().getName(X500Principal.RFC2253);
                
                System.out.println(serialNumber);
                System.out.println(subjectDN);
                
                try {
	                certificate.checkValidity(new Date());
                } catch(Exception ex) {
                    System.out.println("Certificate was expired at: " + certificate.getNotAfter());
                    continue;
                }

                privateKey = (PrivateKey)keyStoreForMy.getKey(alias, null);
                if(privateKey != null) {
                    System.out.println("Certificate has an privateKey associated in the repos");
                    if( (cn == null) || ( (cn != null) && (subjectDN.contains(cn)) ) ) {
                        signerCertificate = certificate;
                        break;
                    }
                }
                
            }
        	
        } catch (KeyStoreException e) {
            System.out.println("KeyStore not found");
            e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm is not available");
            e.printStackTrace();
		} catch (CertificateException e) {
            System.out.println("Error while accessing the Certificate");
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println("Error while accessing the keyStore");
            e.printStackTrace();
		} finally {
        }
		
		if( (privateKey == null) || (signerCertificate == null) ) {
            throw new Exception("No one PrivateKey found in the Certificate Repository: " + repos);
		}
		
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
		
		//encode the signerCertificate to Base64
        BASE64Encoder base64Enc = new BASE64Encoder();
        signerCertificateB64 = base64Enc.encode(signerCertificate.getEncoded());

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
		
		//set the Credential and Authentication
		RequestDigitalSignaturePrepareSign requestDigitalSignaturePrepareSign = new RequestDigitalSignaturePrepareSign();
		requestDigitalSignaturePrepareSign.setDigitalSignaturePrepareSign(digitalSignaturePrepareSign);
		
		String appCode = "require from CounterSign";
		CredentialRequest credentialRequest = createCredential(appCode, "your email", "1234");
		AuthenticationVo authenticationVo = new AuthenticationVo(appCode, "BASIC", credentialRequest);
		requestDigitalSignaturePrepareSign.setAuthenticationVo(authenticationVo);
		requestDigitalSignaturePrepareSign.setClientDocId("your ID");
		requestDigitalSignaturePrepareSign.setContractItemNumber(contractItemNumber);
		requestDigitalSignaturePrepareSign.setDocumentName("Test 1 API");
		requestDigitalSignaturePrepareSign.setFlagSaveDocument(isAttached);
		requestDigitalSignaturePrepareSign.setIdDocumentType(idDocumentType);
		requestDigitalSignaturePrepareSign.setProductSku(productSku);
		//when you use PDF documents, use this fields to make a signature stamp
		requestDigitalSignaturePrepareSign.setPageIndex(1);//adjust with the index of page, or -1 for use the CounterSign default
		requestDigitalSignaturePrepareSign.setPosX(0);//the X position, starting from the left and growing to the right
		requestDigitalSignaturePrepareSign.setPosY(0);//the Y position, starting from the bottom and growing to the top
		requestDigitalSignaturePrepareSign.setQRCodeEnabled(-1);//disable
		
		//the CounterSign:Security API Rest WebService URL
		String svcUrl = "https://www.countersignweb.com/countersign-rest/CounterSignAPIRest";
		
		//call the CounterSign:Security Rest API for signature preparation method
		DigitalSignaturePrepareResponseExt digitalSignaturePrepareResponse = (DigitalSignaturePrepareResponseExt) JsonConnHandler.callSvcREST(svcUrl + "/prepareSign", requestDigitalSignaturePrepareSign, DigitalSignaturePrepareResponseExt.class);
		if(digitalSignaturePrepareResponse == null) {
			System.out.println("Error: Can not get access to Security Rest API WebService");
            throw new Exception();
		}
		
		//retrieve from the results the data prepared to be signed
		String signedAttributesB64 = null;
		if(digitalSignaturePrepareResponse.isSuccess()) {
			//get the data prepared in the Server to be signed for the user
			signedAttributesB64 = digitalSignaturePrepareResponse.getSignedAttributes();
		} else {
			//get the errors returned by the server
			List<ErrorResponse> listOfErrors = digitalSignaturePrepareResponse.getListErrorResponse();
			//show errors
			for(ErrorResponse errorResponse : listOfErrors) {
				System.out.println("Error: " + String.valueOf(errorResponse.getCode()) + " " + errorResponse.getError());
			}
			return null;
		}

		//store the signature produced and encoded as Base64
		String signatureB64 = null;

		//decode the signedAttributes from Base64 to binary
		byte[] dataToBeSignedPrepared = null;
		BASE64Decoder base64Dec = new BASE64Decoder();
		dataToBeSignedPrepared = base64Dec.decodeBuffer(signedAttributesB64);
		
		//sign the data using the user큦 private key
		try {
			Signature signature = Signature.getInstance(signatureAlg);
			
			signature.initSign(privateKey);
			signature.update(dataToBeSignedPrepared);
			byte[] signed = signature.sign();

	        base64Enc = new BASE64Encoder();
			signatureB64 = base64Enc.encode(signed);
			
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
			digitalSignatureSignData.setSignatureType(signatureType);

			//set the Credential and Authentication
			RequestDigitalSignatureSignData requestDigitalSignatureSignData = new RequestDigitalSignatureSignData();
			requestDigitalSignatureSignData.setDigitalSignatureSignData(digitalSignatureSignData);
			requestDigitalSignatureSignData.setAuthenticationVo(authenticationVo);
			requestDigitalSignatureSignData.setClientDocId("your ID");
			requestDigitalSignatureSignData.setContractItemNumber(contractItemNumber);
			requestDigitalSignatureSignData.setDocumentName("Test 1 API");
			requestDigitalSignatureSignData.setFlagSaveDocument(true);
			requestDigitalSignatureSignData.setIdDocumentType(idDocumentType);
			requestDigitalSignatureSignData.setProductSku(productSku);
			//for PDF
			requestDigitalSignatureSignData.setPdfPre(digitalSignaturePrepareResponse.getPdfPre());
			requestDigitalSignatureSignData.setSigFieldName(digitalSignaturePrepareResponse.getSigFieldName());
			
			//call the CounterSign:Security Rest API for envelope the signature in the signedData method
			DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = (DigitalSignatureSignDataResponse) JsonConnHandler.callSvcREST(svcUrl + "/envelopeSignedData", requestDigitalSignatureSignData, DigitalSignatureSignDataResponse.class);
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
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
            throw new Exception(e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
            throw new Exception(e);
		} catch (SignatureException e) {
			e.printStackTrace();
            throw new Exception(e);
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
		String appCode = "9191e963-5b0f-4efc-92cb-0e400c4996a3";
		CredentialRequest credentialRequest = createCredential(appCode, "your email", "your password");
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
	private static CredentialRequest createCredential(String appCode, String userEmail, String userPassword) {
		CredentialRequest credentialRequest = new CredentialRequest(CredentialRequest.CREDENTIAL_TYPE_USER_AND_PASSWORD, appCode);

		String credential = "USER_EMAIL=" + userEmail + "&USER_PASSWORD=" + userPassword + "&TIME=" + new Date().toString();
		
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
        return null;
			
	}
}
