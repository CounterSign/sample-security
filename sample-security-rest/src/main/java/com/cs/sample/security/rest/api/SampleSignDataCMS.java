package com.cs.sample.security.rest.api;

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
import com.cs.security.entities.response.DigitalSignatureSignDataResponse;
import com.cs.security.entities.response.ErrorResponse;
import com.cs.security.service.vo.AuthenticationVo;
import com.cs.security.service.vo.CredentialRequest;
import com.cs.security.service.vo.DigitalSignaturePrepareSign;
import com.cs.security.service.vo.DigitalSignatureSignData;
import com.cs.security.service.vo.RequestDigitalSignaturePrepareSign;
import com.cs.security.service.vo.RequestDigitalSignatureSignData;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * A digital signature sample using the CounterSign:Security Rest API WebService.
 * Provides a digital signature over a simple data enveloping in the PKCS#7/CMS signedData standard.
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
public class SampleSignDataCMS {
	
	public static void sign(String repos, String cn, String fileName, String password, boolean isAttached, String productSku, String contractItemNumber) throws Exception {

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
		byte[] dataToBeSigned = "Test".getBytes();
		//the signer certificate encoded as Base64
		String signerCertificateB64 = "";
		//the message digest algorithm (SHA1 or SHA-256)
		String messageDigestAlg = "SHA-256";
		//the signature algorithm
		String signatureAlg = "SHA256WithRSA";
		//the type of signature to be encoded
		int signatureType = DigitalSignaturePrepareSign.DS_CMS;//PKCS#7 = CMS:signedData
		//the message digest for detached mode
		String messageDigestInHex = null;
		
		//encode the signerCertificate to Base64
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
				return;
			}
		}
		digitalSignaturePrepareSign.setMessageDigestAlg(messageDigestAlg);
		digitalSignaturePrepareSign.setSignerCertificate(signerCertificateB64);
		digitalSignaturePrepareSign.setSignatureType(signatureType);

		//set the Credential and Authentication
		RequestDigitalSignaturePrepareSign requestDigitalSignaturePrepareSign = new RequestDigitalSignaturePrepareSign();
		requestDigitalSignaturePrepareSign.setDigitalSignaturePrepareSign(digitalSignaturePrepareSign);
		
		String appCode = "require from CounterSign";
		CredentialRequest credentialRequest = createCredential(appCode, "your email", "your password");
		AuthenticationVo authenticationVo = new AuthenticationVo(appCode, "BASIC", credentialRequest);
		requestDigitalSignaturePrepareSign.setAuthenticationVo(authenticationVo);
		requestDigitalSignaturePrepareSign.setClientDocId("your ID");
		requestDigitalSignaturePrepareSign.setContractItemNumber(contractItemNumber);
		requestDigitalSignaturePrepareSign.setDocumentName("Test 1 API");
		requestDigitalSignaturePrepareSign.setFlagSaveDocument(isAttached);
		requestDigitalSignaturePrepareSign.setIdDocumentType(2);
		requestDigitalSignaturePrepareSign.setProductSku(productSku);
		
		//the CounterSign:Security API Rest WebService URL
		String svcUrl = "https://www.countersignweb.com/countersign-rest/CounterSignAPIRest";
		
		//call the CounterSign:Security Rest API for signature preparation method
		DigitalSignaturePrepareResponse digitalSignaturePrepareResponse = (DigitalSignaturePrepareResponse) JsonConnHandler.callSvcREST(svcUrl + "/prepareSign", requestDigitalSignaturePrepareSign, DigitalSignaturePrepareResponse.class);
		if(digitalSignaturePrepareResponse == null) {
			System.out.println("Error: Can not get access to Security Rest API WebService");
			return;
		}
		
		//the data prepared to be signed
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
			return;
		}

		//store the signature produced and encoded as Base64
		String signatureB64 = null;

		//decode the signedAttributes from Base64 to binary
		byte[] dataToBeSignedPrepared = null;
		BASE64Decoder base64Dec = new BASE64Decoder();
		dataToBeSignedPrepared = base64Dec.decodeBuffer(signedAttributesB64);
		
		//sign the data prepared using the user´s private key
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
			
			//set the Credential and Authentication
			RequestDigitalSignatureSignData requestDigitalSignatureSignData = new RequestDigitalSignatureSignData();
			requestDigitalSignatureSignData.setDigitalSignatureSignData(digitalSignatureSignData);
			requestDigitalSignatureSignData.setAuthenticationVo(authenticationVo);
			requestDigitalSignatureSignData.setClientDocId("your ID");
			requestDigitalSignatureSignData.setContractItemNumber(contractItemNumber);
			requestDigitalSignatureSignData.setDocumentName("Test 1 API");
			requestDigitalSignatureSignData.setFlagSaveDocument(isAttached);
			requestDigitalSignatureSignData.setIdDocumentType(2);
			requestDigitalSignatureSignData.setProductSku(productSku);
			
			//call the CounterSign:Security Rest API for envelope the signature in the signedData method
			DigitalSignatureSignDataResponse digitalSignatureSignDataResponse = (DigitalSignatureSignDataResponse) JsonConnHandler.callSvcREST(svcUrl + "/envelopeSignedData", requestDigitalSignatureSignData, DigitalSignatureSignDataResponse.class);
			if(digitalSignatureSignDataResponse == null) {
				System.out.println("Error: Can not get access to Security Rest API WebService");
				return;
			}
			
			//check the Security API Rest WebService signature results
			if(digitalSignatureSignDataResponse.isSuccess()) {
				//retrieve the signed data from the results
				byte[] cmsSignedData = digitalSignatureSignDataResponse.getSignedData();
				
				System.out.println("CMS signedData was generated with success. " + (isAttached==true ? "Attached" : "Detached") + " mode");
				
				FileOutputStream fileOut = null;
				try {
					fileOut = new FileOutputStream("signed" + (isAttached==true ? "Attached" : "Detached") + ".p7s");
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
				
			} else {
				//get the errors returned by the server
				List<ErrorResponse> listOfErrors = digitalSignatureSignDataResponse.getListErrorResponse();
				//show errors
				for(ErrorResponse errorResponse : listOfErrors) {
					System.out.println("Error: " + String.valueOf(errorResponse.getCode()) + " " + errorResponse.getError());
				}
				return;
			}
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return;
		} catch (SignatureException e) {
			e.printStackTrace();
			return;
		}
	}
	
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
	
	public static void main(String[] args) {
		
		try {
			//Sign a data in the PKI CMS:signedData attached standard
			SampleSignDataCMS.sign("windows", "MARCIO ROBERTO", null, null, true, "SVCCOUNTER0004", "require the contract item number from CounterSign");
			
			//Sign a data in the PKI CMS:signedData detached standard
			SampleSignDataCMS.sign("windows", "MARCIO ROBERTO", null, null, false, "SVCCOUNTER0004", "require the contract item number from CounterSign");
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
