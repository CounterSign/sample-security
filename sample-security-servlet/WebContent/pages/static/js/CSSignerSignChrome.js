
var returnToURL = '';
var hostURL = window.location.origin;
var hostAppURL = '/sample-security-servlet/plugin/setup.exe';
var hostAppName = 'setup.exe';
var extension_url = 'https://chrome.google.com/webstore/detail/ijicjpfkhkogdmhhfecciapdkkhoeaif';

var appVersion;
var countTries = 0;
var isNativeWorking = false;
var neverMoreCall = false;

window.onload=function() {
	checkNative();
};

function setReturnUrl(returnUrl) {
	returnToURL = returnUrl;
}

var checkNative = function() {
	if(neverMoreCall == true) {
		return;
	}
	var limit = (countTries >= 10);
	try {
		if (! isNativeWorking) {
			try {
				reqAppInfo();
			}catch (e) {
			}
		
			if (limit) {
				alert('Chrome Extension is not installed');
				
				if(returnToURL == '') {
					window.location.assign(hostURL + '/ipm-web/pages/public/installCSSignerChrome.jsf?returnUrl=' + hostURL + 'ipm-web');
				} else {
					window.location.assign(hostURL + '/ipm-web/pages/public/installCSSignerChrome.jsf?returnUrl=' + returnToURL);
				}
				window.location.reload;
			}
		}
		
	}catch (e) {
	}

	countTries++;
	setTimeout(checkNative, 1000);
};

function sleep(milliseconds) {
	var start = new Date().getTime();
}

//call the native using the event registered on content-script
function reqAppInfo() {
	var concat = "reqAppInfo";
	var customEvent = new CustomEvent("csSignerChromeHandlerContentScriptEvent", {"detail":{"action":concat, "tabId":"0", "reqAppInfo":{"install":"true"} }});
	document.dispatchEvent(customEvent);
}

function signDataProc() {
	var hash = document.getElementById('hash').value;
	if(hash == "") {
		reqSignCMS();
	} else {
		reqSignPkcs1();
	}
}

function selectAndSubmitCertificate() {
	var concat = "getCertificate";
	var repos = "windows";
	var thumbprint = document.getElementById('selCertificate').value;
	var customEvent = new CustomEvent("csSignerChromeHandlerContentScriptEvent", {"detail":{"action":concat, "tabId":"0", "findCertificate":{"thumbprint":thumbprint, "repos":repos} }});
	document.dispatchEvent(customEvent);
}

function prepareCertificates() {
	var concat = "reqListCertificates";
	var customEvent = new CustomEvent("csSignerChromeHandlerContentScriptEvent", {"detail":{"action":concat, "tabId":"0"}});
	document.dispatchEvent(customEvent);
}

function reqSignCMS() {
	var concat = "reqSignCMS";
	var thumbprint = document.getElementById('selCertificate').value;
	var dataB64 = document.getElementById("signedData").value;
	var messageDigestAlg = "SHA256";
	var customEvent = new CustomEvent("csSignerChromeHandlerContentScriptEvent", {"detail":{"action":concat, "tabId":"0", "reqSign":{"thumbprint":thumbprint, "dataB64":dataB64, "messageDigestAlg":messageDigestAlg} }});
	document.dispatchEvent(customEvent);
}

function reqSignPkcs1() {
	var concat = "reqSignPkcs1";
	var thumbprint = document.getElementById('selCertificate').value;
	var hash = document.getElementById('hash').value;
	var messageDigestAlg = document.getElementById("messageDigestAlg").value;
	var signatureAlg = document.getElementById("signatureAlg").value;
	var customEvent = new CustomEvent("csSignerChromeHandlerContentScriptEvent", {"detail":{"action":concat, "tabId":"0", "reqSign":{"thumbprint":thumbprint, "dataB64":hash, "messageDigestAlg":messageDigestAlg, "signatureAlg":signatureAlg} }});
	document.dispatchEvent(customEvent);
}

function showResult(contentEvent) {
	var obj = JSON.parse(contentEvent.detail);
	if(obj.success != 0) {
		alert('ERRO: ' + obj.errorResponse.errorMessage);
	} else {
		if(obj.action == 'reqListCertificates') {
			retrieveCertificatesForFaces(obj);
		}
		if(obj.action == 'getCertificate') {
			selectCertificate(obj);
		}
		if(obj.action == 'reqSignCMS') {
			signCMS(obj);
		}
		if(obj.action == 'reqSignPkcs1') {
			signPkcs1(obj);
		}
		if(obj.action == 'reqAppInfo') {
			reqAppInfoResponse(obj);
		}
	}
}

function retrieveCertificatesForFaces(obj) {
	var text = "";
	var i=0;
	while(true) {
		var cn = obj.certificateInfos[i].subjectDn;
		var thumbprintLocal = obj.certificateInfos[i].thumbprint;
		var ac = obj.certificateInfos[i].issuerDn;
		var validate = obj.certificateInfos[i].notAfter;
		var serialNumber = obj.certificateInfos[i].serialNumber;
		
		if(i ==0) {
			text += "CERT=" + thumbprintLocal + ";" + cn + ";" + validate  + ";" + ac + ";" + serialNumber;
		} else {
			text += ";";
			text += "CERT=" + thumbprintLocal + ";" + cn + ";" + validate  + ";" + ac + ";" + serialNumber;
		}
		i++;
		if(i >= obj.certificateInfos.length) {
			break;
		}
	}
	document.getElementById('signerCertificate').value = text;
	submitCerts();
}

function selectCertificate(obj) {
	var certificateB64 = "";
	var text = "";
	var i=0;
	while(true) {
		certificateB64 = obj.certificateInfo.certificateB64;
		if(certificateB64 == undefined || certificateB64 == '') {
			alert('Favor selecionar o Certificado Digital');
		} else {
			document.getElementById('signerCertificate').value = certificateB64;
			submitCertificate();
			break;
		}
		i++;
		if(i >= obj.certificateInfos.length) {
			break;
		}
	}
}

function signCMS(obj) {
	var signedData = obj.signature;
	if(signedData != null) {
		document.getElementById('signedData').value = signedData;
		submitSignature();
	} else {
		alert('Ocorreu um erro ao realizar a Assinatura Digital');
		submitSignatureWithError();
	}
}

function signPkcs1(obj) {
	var signedData = obj.signature;
	if(signedData != null) {
		document.getElementById('signedData').value = signedData;
		submitSignature();
	} else {
		alert('Ocorreu um erro ao realizar a Assinatura Digital');
		submitSignatureWithError();
	}
}

function reqAppInfoResponse(obj) {
	isNativeWorking = true;
	appVersion = obj.appInfo.version;
}

document.addEventListener("csSignerChromeHandlerPageScriptEvent", showResult);

