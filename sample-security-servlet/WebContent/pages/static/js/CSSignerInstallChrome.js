
var returnToURL = window.location.search.substring(1).split("=")[1];
var hostURL = window.location.origin;
var hostAppURL = '/sample-security-servlet/plugin/setup.exe';
var hostAppName = 'setup.exe';
var extension_url = 'https://chrome.google.com/webstore/detail/ijicjpfkhkogdmhhfecciapdkkhoeaif';

var appVersion;
var countTries = 0;
var isNativeWorking = false;
var neverMoreCall = false;

document.addEventListener("csSignerChromeHandlerPageScriptEvent", showResult);

if (chrome.app.isInstalled) {
	document.getElementById('install-button').style.display = 'none';
}

window.onload = function() {
	checkNative();
};

var installSuccess = function() {
	var link = document.createElement("a");
	link.download = hostAppName;
	link.href = hostURL + hostAppURL;
	link.click();
	window.location.reload(true);

	checkNative();
};

var installFailure = function(error, errorCode) {
	alert(error);
	callInstallFailureDlg();
};

function installSetup() {
	var link = document.createElement("a");
	link.download = hostAppName;
	link.href = hostURL + hostAppURL;
	link.click();
	window.location.reload(true);
}

function reqAppInfoResponse(obj) {
	isNativeWorking = true;
	appVersion = obj.appInfo.version;
}

var checkNative = function() {
	if (neverMoreCall == true) {
		return;
	}
	var limit = (countTries >= 300);
	try {
		if (!isNativeWorking) {
			try {
				reqAppInfo();
			} catch (e) {
			}
		}

		if (limit) {
			alert('Time has exceeded the limit to reach the native application. Starting again.');
			countTries = 0;
		}

		if (isNativeWorking) {
			if (appVersion) {
				neverMoreCall = true;
				callInstallSuccessDlg();
				return;
			}
		}
	} catch (e) {
	}

	countTries++;
	setTimeout(checkNative, 1000);
};

// call the native using the event registered on content-script
function reqAppInfo() {
	var concat = "reqAppInfo";
	var customEvent = new CustomEvent(
			"csSignerChromeHandlerContentScriptEvent", {
				"detail" : {
					"action" : concat,
					"tabId" : "0",
					"reqAppInfo" : {
						"install" : "true"
					}
				}
			});
	document.dispatchEvent(customEvent);
}

// registered event to receive the messages from the content-script
function showResult(contentEvent) {
	var obj = JSON.parse(contentEvent.detail);
	if (obj.success != 0) {
		alert('Ooops! ' + obj.errorMessage);
	} else {
		if (obj.action == 'reqAppInfo') {
			reqAppInfoResponse(obj);
		}
	}
}

function sleep(milliseconds) {
	var start = new Date().getTime();
}

// go to the back page
function callGoBack() {
	var temp = decodeURIComponent(window.location.href.substring(1).split("=")[1]);
	if (temp != "undefined") {
		window.location.assign(temp.split("#")[0]);
	} else {
		window.location.assign(defaultURL);
	}
}

function openChromeWebStore() {
	window.open("https://chrome.google.com/webstore/detail/ijicjpfkhkogdmhhfecciapdkkhoeaif");
}
