
var flagLoad = false;

function includeJavaScript(js) {
	var newScript = document.createElement('script');
	newScript.type = 'text/javascript';
	newScript.src = js;
	document.getElementsByTagName('head')[0].appendChild(newScript);
}

function addSignerScriptForChrome() {
	if(flagLoad == false) {
		includeJavaScript('/sample-security-servlet/pages/static/js/CSSignerSignChrome.js');
		flagLoad = true;
	}
}

function addSignerScriptForIE() {
	if(flagLoad == false) {
		includeJavaScript('/sample-security-servlet/pages/static/js/CSSignerAppletEmbeded.js');
		includeJavaScript('/sample-security-servlet/pages/static/js/CSSignerApplet.js');
		includeJavaScript('/sample-security-servlet/pages/static/js/CSSignerSignApplet.js');
		flagLoad = true;
	}
}


window.onload=function() {
	try {
		if(window.chrome) {
			addSignerScriptForChrome();
			checkNative();
		} else {
			addSignerScriptForIE();
			embedAppletOnHtml();
		}
	} catch(Exception) {
	}
};

