
var thumbprint = '';
var functionCallback = 0;
var cn = '';
var certificateB64 = '';
var serialNumber = '';

function selectCertificate() {
	if(jQuery('#selectUserCn option:selected').val()==undefined){
			alert('Selecione 1 certificado');
			return;
	} 
	thumbprint = jQuery('#selectUserCn option:selected').val();
	cn = jQuery('#selectUserCn option:selected').text();
	
	certificateB64 = document.CSSignerApp.findAndGetCertificateB64(thumbprint);
	
    //$('#certDialog').dialog('close');
	
};

function selectCertificateForFaces(thumb) {
	certificateB64 = document.CSSignerApp.findAndGetCertificateB64(thumb);
};

function checkRequiredFilesMicrosoftInstalled(urlToDownload) {
	if(document.CSSignerApp.checkRequiredFilesMicrosoftInstalled("urlToDownload")==true) {
		alert("Arquivos necess�rios para o InternetExplorer instalados com sucesso!");
	} else {
		alert("Arquivos necess�rios para o InternetExplorer n�o est�o instalados na m�quina.");
	}
}

function retrieveCertificatesForFaces() {
	if(thumbprint==undefined || thumbprint==''){
		var text = '';
		if(document.CSSignerApp.selectCertificates("Microsoft")) {
			var i = 0;
			while(document.CSSignerApp.hasNext()) {
				var cn = document.CSSignerApp.getSubjectCN();
				var thumbprintLocal = document.CSSignerApp.getThumbprint();
				var ac = document.CSSignerApp.getIssuerCN();
				var validate = document.CSSignerApp.getNotAfter();
				var serialNumber = document.CSSignerApp.getSerialNumber();
				if(i ==0) {
					text += "CERT=" + thumbprintLocal + ";" + cn + ";" + validate  + ";" + ac + ";" + serialNumber;
				} else {
					text += ";";
					text += "CERT=" + thumbprintLocal + ";" + cn + ";" + validate  + ";" + ac + ";" + serialNumber;
				}
				i++;
			}
			
		    return text;
		    
		} else {
			alert("Erro ao capturar a lista de Certificados.");
			eval(options.onerror);
			return "";
		}
	}
}

function retrieveCertificates() {
	if(thumbprint==undefined || thumbprint==''){
		var text = '';
		if(document.CSSignerApp.selectCertificates("Microsoft")) {

		    var selectId = 'selectUserCn';
			
			var size = document.CSSignerApp.getSizeOfCertificates();
			size = size < 2 ? 2 : size;
			text = "<div id='certDialog'><select class='selectLogin margin10L ' name='selectUserCn' id='selectUserCn' size='" + size + "'>";
			var i = 0;
			while(document.CSSignerApp.hasNext()) {
				var cn = document.CSSignerApp.getSubjectCN();
				var thumbprintLocal = document.CSSignerApp.getThumbprint();
				var ac = document.CSSignerApp.getIssuerCN();
				var validate = document.CSSignerApp.getNotAfter();
				if(i ==0) {
					text += "<option selected='selected' title='/sample-security-servlet/pages/static/css/themes/default/images/certificate.png' value='" + thumbprintLocal + "'>" + cn + ";" + validate  + ";" + ac + "</option>";
				} else {
					text += "<option title='/sample-security-servlet/pages/static/css/themes/default/images/certificate.png' value='" + thumbprintLocal +  "'>" + cn + ";" + validate + ";" + ac + "</option>";
				}
				i++;
			}
			
			text +="</select>";
			text += "</div><br/>";
			
		    return text;
		    
		    //$(document.documentElement).append(text);
		    //$('#certDialog').dialog({
		    //    title: 'Selecione o Certificado',
		    //    width: 650,
		    //    height: 350,
		    //    closed: false,
		    //    cache: false,
		    //    href: '',
		    //    modal: true});
		    
		} else {
			alert("Erro ao capturar a lista de Certificados.");
			eval(options.onerror);
			return "";
		}
	}
}

function sign() {
	functionCallback = 1;
	
	var clearData = 'teste';
	
	if(thumbprint==undefined || thumbprint==''){
		var text = '';
		if(document.CSSignerApp.selectCertificates("Microsoft")) {
			var size = document.CSSignerApp.getSizeOfCertificates();
			size = size < 2 ? 2 : 1;
			text = "<select id='selectUserCn' size='" + size + "'>";
			while(document.CSSignerApp.hasNext()) {
				var cn = document.CSSignerApp.getSubjectCN();
				var thumbprintLocal = document.CSSignerApp.getThumbprint();
				text += "<option value='" + thumbprintLocal + "'>" + cn + "</option>";
			}
		} else {
			alert("Erro ao capturar a lista de Certificados.");
		}
			
			text +="</select><br/>";
			
			text +="<input type='button' onclick='selectCertificate();' value='Selecionar'/>";
			text +="<input type='button' id='fechar' onclick='jQuery.unblockUI()' value='Fechar'/>";
		jQuery('#dialog').html(text);
		jQuery.blockUI({ message: jQuery('#dialog'), css: { width: '275px' } });
		return;
	} else {
	
		var signedData = document.CSSignerApp.sign(clearData, thumbprint);
		if(signedData != '') {
			return signedData;
		} else {
			alert("Erro na assinatura." + "\n" +  document.CSSignerApp.getErrors());
			return '';
		}
	}
}


function signDataB64(clearDataB64) {
	if(clearDataB64 == undefined || clearDataB64 == '') {
		clearDataB64 = 'teste';
	}
	var signedData = document.CSSignerApp.signDataB64(clearDataB64, thumbprint);
	if(signedData != '') {
		return signedData;
	} else {
		alert("Erro na assinatura." + "\n" +  document.CSSignerApp.getErrors());
		return '';
	}
}

function signPkcs1B64(clearDataB64, thumb) {
	if(clearDataB64 == undefined || clearDataB64 == '') {
		alert("Favor informar os dados para a assinatura");
		return '';
	}
	thumbprint = thumb;
	var signedData = document.CSSignerApp.signPkcs1B64(clearDataB64, thumbprint);
	if(signedData != '') {
		return signedData;
	} else {
		alert("Erro na assinatura." + "\n" +  document.CSSignerApp.getErrors());
		return '';
	}
}

function signPkcs1B64Alg(clearDataB64, thumb, messageDigestAlg, signatureAlg) {
	if(clearDataB64 == undefined || clearDataB64 == '') {
		alert("Favor informar os dados para a assinatura");
		return '';
	}
	thumbprint = thumb;
	var signedData = document.CSSignerApp.signPkcs1B64(clearDataB64, thumbprint, messageDigestAlg, signatureAlg);
	if(signedData != '') {
		return signedData;
	} else {
		alert("Erro na assinatura." + "\n" +  document.CSSignerApp.getErrors());
		return '';
	}
}

function signDataCMS(clearData, thumb) {
	if(clearData == undefined || clearData == '') {
		alert("Erro na assinatura: Favor informar os dados para assinatura");
		return;
	}
	thumbprint = thumb;
	if(thumbprint == undefined || thumbprint == '') {
		alert("Erro na assinatura: Favor informar o Certificado");
		return '';
	}
	var signedData = document.CSSignerApp.sign(clearData, thumbprint);
	if(signedData != '') {
		return signedData;
	} else {
		alert("Erro na assinatura." + "\n" +  document.CSSignerApp.getErrors());
		return '';
	}
}

function decipherPkcs1B64(cipheredData, thumb) {
	thumbprint = thumb;
	if(thumbprint == undefined || thumbprint == '') {
		alert("Erro na decriptografia: Certificado n�o foi selecionado");
		return '';
	}
	var data = document.CSSignerApp.decipherPkcs1B64(cipheredData, thumbprint);
	return data;
}


function getThumbprint() {
	return thumbprint;
}

function getCn() {
	return cn;
}

function setThumbprint(thumb) {
	thumbprint = thumb;
}

function getSerialNumber() {
	return serialNumber;
}

