package com.cs.security.service.vo;

import com.cs.security.entities.request.Request;
import com.cs.security.service.vo.CertificateParseData;

public class RequestCertificateParseData extends Request {
	
	private static final long serialVersionUID = -7162073966781687344L;
	
	protected AuthenticationVo authenticationVo;
	protected String clientDocId;
	protected String contractItemNumber;
	protected long idDocumentType = 0;
	protected String documentName;
	protected String productSku;
	protected CertificateParseData certificateParseData;
	
	public RequestCertificateParseData() {
	}

	public RequestCertificateParseData(AuthenticationVo authenticationVo, String clientDocId,
			String contractItemNumber, CertificateParseData certificateParseData) {
		super();
		this.authenticationVo = authenticationVo;
		this.clientDocId = clientDocId;
		this.contractItemNumber = contractItemNumber;
		this.certificateParseData = certificateParseData;
	}

	public RequestCertificateParseData(CertificateParseData certificateParseData) {
		super();
		this.certificateParseData = certificateParseData;
	}

	public AuthenticationVo getAuthenticationVo() {
		return authenticationVo;
	}

	public void setAuthenticationVo(AuthenticationVo authenticationVo) {
		this.authenticationVo = authenticationVo;
	}

	public String getClientDocId() {
		return clientDocId;
	}

	public void setClientDocId(String clientDocId) {
		this.clientDocId = clientDocId;
	}

	public String getContractItemNumber() {
		return contractItemNumber;
	}

	public void setContractItemNumber(String contractItemNumber) {
		this.contractItemNumber = contractItemNumber;
	}

	public CertificateParseData getCertificateParseData() {
		return certificateParseData;
	}

	public void setCertificateParseData(CertificateParseData certificateParseData) {
		this.certificateParseData = certificateParseData;
	}

	public long getIdDocumentType() {
		return idDocumentType;
	}

	public void setIdDocumentType(long idDocumentType) {
		this.idDocumentType = idDocumentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

}
