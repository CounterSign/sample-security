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
import java.util.Date;

public class AuthenticationResults extends Results implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2428436253697121781L;
	
	public static final int NOT_VALID = 1;
	public static final int NOT_VALID_EXPIRED = 2;
	public static final int NOT_AN_ICP_BRASIL_CERTIFICATE = 3;
	public static final int NOT_AN_ICP_BRASIL_A3_CERTIFICATE = 4;

	protected String certificateBase64 = "";
	protected X509Certificate x509Certificate;
	
	protected String name = "";
	protected String cpf = "";
	protected String cnpj = "";
	protected String email = "";
	
	protected String serialNumber;
	protected String subjectDn;
	protected String issuerDn;
	protected Date validityNotBefore;
	protected Date validityNotAfter;
	protected String thumbprint;
	protected String certificateType;
	protected String certificateStatus;
	
	private String subjectCn = "";
	private String subjectOrganization = "";
	private String subjectOu1 = "";
	private String subjectOu2 = "";
	private String subjectOu3 = "";
	private String subjectOu4 = "";
	private String subjectOu5 = "";
	private String subjectLocality = "";
	private String subjectState = "";
	private String subjectCountry = "";
	private String subjectDnEmail = "";
	private String rfc822Email = "";
	
	//ICP
	protected String icpCpf = "";
	protected String icpRg = "";
	protected String icpRgOrgaoExpedidor = "";
	protected String icpRgUf = "";
	protected String icpDataDeNascimento = "";
    protected String icpPispasep = "";
	
    protected String icpTituloEleitorNumero = "";
    protected String icpTituloEleitorZona = "";
    protected String icpTituloEleitorSecao = "";
    protected String icpTituloEleitorMunicipio = "";
    protected String icpTituloEleitorUf = "";
	
    protected String icpCei = "";

    protected String icpPjResp = "";

    public AuthenticationResults() {
    	
    }
    
	public String getCertificateBase64() {
		return certificateBase64;
	}
	public void setCertificateBase64(String certificateBase64) {
		this.certificateBase64 = certificateBase64;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getThumbprint() {
		return thumbprint;
	}
	public void setThumbprint(String thumbprint) {
		this.thumbprint = thumbprint;
	}
	public String getCertificateType() {
		return certificateType;
	}
	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	public X509Certificate getX509Certificate() {
		return x509Certificate;
	}
	public void setX509Certificate(X509Certificate x509Certificate) {
		this.x509Certificate = x509Certificate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSubjectDn() {
		return subjectDn;
	}
	public void setSubjectDn(String subjectDn) {
		this.subjectDn = subjectDn;
	}
	public String getIssuerDn() {
		return issuerDn;
	}
	public void setIssuerDn(String issuerDn) {
		this.issuerDn = issuerDn;
	}
	public Date getValidityNotBefore() {
		return validityNotBefore;
	}
	public void setValidityNotBefore(Date validityNotBefore) {
		this.validityNotBefore = validityNotBefore;
	}
	public Date getValidityNotAfter() {
		return validityNotAfter;
	}
	public void setValidityNotAfter(Date validityNotAfter) {
		this.validityNotAfter = validityNotAfter;
	}
	public String getCertificateStatus() {
		return certificateStatus;
	}
	public void setCertificateStatus(String certificateStatus) {
		this.certificateStatus = certificateStatus;
	}
	public String getSubjectCn() {
		return subjectCn;
	}
	public void setSubjectCn(String subjectCn) {
		this.subjectCn = subjectCn;
	}
	public String getSubjectOrganization() {
		return subjectOrganization;
	}
	public void setSubjectOrganization(String subjectOrganization) {
		this.subjectOrganization = subjectOrganization;
	}
	public String getSubjectOu1() {
		return subjectOu1;
	}
	public void setSubjectOu1(String subjectOu1) {
		this.subjectOu1 = subjectOu1;
	}
	public String getSubjectOu2() {
		return subjectOu2;
	}
	public void setSubjectOu2(String subjectOu2) {
		this.subjectOu2 = subjectOu2;
	}
	public String getSubjectOu3() {
		return subjectOu3;
	}
	public void setSubjectOu3(String subjectOu3) {
		this.subjectOu3 = subjectOu3;
	}
	public String getSubjectOu4() {
		return subjectOu4;
	}
	public void setSubjectOu4(String subjectOu4) {
		this.subjectOu4 = subjectOu4;
	}
	public String getSubjectOu5() {
		return subjectOu5;
	}
	public void setSubjectOu5(String subjectOu5) {
		this.subjectOu5 = subjectOu5;
	}
	public String getSubjectLocality() {
		return subjectLocality;
	}
	public void setSubjectLocality(String subjectLocality) {
		this.subjectLocality = subjectLocality;
	}
	public String getSubjectState() {
		return subjectState;
	}
	public void setSubjectState(String subjectState) {
		this.subjectState = subjectState;
	}
	public String getSubjectCountry() {
		return subjectCountry;
	}
	public void setSubjectCountry(String subjectCountry) {
		this.subjectCountry = subjectCountry;
	}
	public String getSubjectDnEmail() {
		return subjectDnEmail;
	}
	public void setSubjectDnEmail(String subjectDnEmail) {
		this.subjectDnEmail = subjectDnEmail;
	}
	public String getRfc822Email() {
		return rfc822Email;
	}
	public void setRfc822Email(String rfc822Email) {
		this.rfc822Email = rfc822Email;
	}
	public String getIcpCpf() {
		return icpCpf;
	}
	public void setIcpCpf(String icpCpf) {
		this.icpCpf = icpCpf;
	}
	public String getIcpRg() {
		return icpRg;
	}
	public void setIcpRg(String icpRg) {
		this.icpRg = icpRg;
	}
	public String getIcpRgOrgaoExpedidor() {
		return icpRgOrgaoExpedidor;
	}
	public void setIcpRgOrgaoExpedidor(String icpRgOrgaoExpedidor) {
		this.icpRgOrgaoExpedidor = icpRgOrgaoExpedidor;
	}
	public String getIcpRgUf() {
		return icpRgUf;
	}
	public void setIcpRgUf(String icpRgUf) {
		this.icpRgUf = icpRgUf;
	}
	public String getIcpDataDeNascimento() {
		return icpDataDeNascimento;
	}
	public void setIcpDataDeNascimento(String icpDataDeNascimento) {
		this.icpDataDeNascimento = icpDataDeNascimento;
	}
	public String getIcpPispasep() {
		return icpPispasep;
	}
	public void setIcpPispasep(String icpPispasep) {
		this.icpPispasep = icpPispasep;
	}
	public String getIcpTituloEleitorNumero() {
		return icpTituloEleitorNumero;
	}
	public void setIcpTituloEleitorNumero(String icpTituloEleitorNumero) {
		this.icpTituloEleitorNumero = icpTituloEleitorNumero;
	}
	public String getIcpTituloEleitorZona() {
		return icpTituloEleitorZona;
	}
	public void setIcpTituloEleitorZona(String icpTituloEleitorZona) {
		this.icpTituloEleitorZona = icpTituloEleitorZona;
	}
	public String getIcpTituloEleitorSecao() {
		return icpTituloEleitorSecao;
	}
	public void setIcpTituloEleitorSecao(String icpTituloEleitorSecao) {
		this.icpTituloEleitorSecao = icpTituloEleitorSecao;
	}
	public String getIcpTituloEleitorMunicipio() {
		return icpTituloEleitorMunicipio;
	}
	public void setIcpTituloEleitorMunicipio(String icpTituloEleitorMunicipio) {
		this.icpTituloEleitorMunicipio = icpTituloEleitorMunicipio;
	}
	public String getIcpTituloEleitorUf() {
		return icpTituloEleitorUf;
	}
	public void setIcpTituloEleitorUf(String icpTituloEleitorUf) {
		this.icpTituloEleitorUf = icpTituloEleitorUf;
	}
	public String getIcpCei() {
		return icpCei;
	}
	public void setIcpCei(String icpCei) {
		this.icpCei = icpCei;
	}
	public String getIcpPjResp() {
		return icpPjResp;
	}
	public void setIcpPjResp(String icpPjResp) {
		this.icpPjResp = icpPjResp;
	}


}
