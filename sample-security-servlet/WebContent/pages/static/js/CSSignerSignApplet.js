
function reqSignPkcs1() {
	signDataProc();
}

function signDataProc() {
	var hash = document.getElementById('hash').value;
	var messageDigestAlg = document.getElementById('messageDigestAlg').value;
	var signatureAlg = document.getElementById('signatureAlg').value;
	
	var signedData = signPkcs1B64Alg(hash, document.getElementById('selCertificate').value, messageDigestAlg, signatureAlg);
	if(signedData != null) {
		document.getElementById('signedData').value = signedData;
		submitSignature();
	} else {
		alert('Ocorreu um erro ao realizar a Assinatura Digital');
		submitSignatureWithError();
	}
}

function prepareCertificates() {
	try {
		document.CSSignerApp.selectCertificates("Microsoft");
	} catch(exception) {
		embedAppletOnHtml();
	}
	
    //var newDiv=document.createElement('div');
    //newDiv.innerHTML= retrieveCertificates();
    //document.getElementById("selectCertificates").appendChild(newDiv);
	var text = retrieveCertificatesForFaces();
	document.getElementById('signerCertificate').value = text;
	submitCerts();
}

function setCertificate() {
	selectCertificateForFaces(document.getElementById('selCertificate').value);
	if(certificateB64 == undefined || certificateB64 == '') {
		alert('Favor selecionar o Certificado Digital');
		PF('statusDialog').hide();
	}
}
function selectAndSubmitCertificate() {
	selectCertificateForFaces(document.getElementById('selCertificate').value);
	if(certificateB64 == undefined || certificateB64 == '') {
		alert('Favor selecionar o Certificado Digital');
		PF('statusDialog').hide();
	} else {
		document.getElementById('signerCertificate').value = certificateB64;
		submitCertificate();
	}
}

