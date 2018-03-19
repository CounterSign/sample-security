
function embedAppletOnHtml() {
	
	var obj = document.getElementById('signerApplet');
	obj.innerHTML = 
	"<object id='CSSignerApp'" +
		"classid='clsid:8AD9C840-044E-11D1-B3E9-00805F499D93' " +
		"width='0' height='0' style='width: 0px; height: 0px; float: left;'>" +

		"<param name='code' value='com.cs.security.applet.CSSignerApp.class' />" +
		"<param name='codebase' value='/sample-security-servlet/pages/static/applet' />" +
		"<param name='type' value='application/x-java-applet' />" +

		"<param name='archive' value='CSSignerApp.jar' />" +
		"<param name='cache_version' value='1.0.3.1' />" +
		"<param name='separate_jvm' value='true' />" +
		"<param name='setError' value='setError' />" +
		"<param name='showException' value='showException' />" +
		"<param name='activateDialog' value='false' />" +

		"<param name='mayscript' value='true' />" +
		"<param name='scriptable' value='true' />" +
		"<param name='appDialogTitle' value='Security Sample' />" +
		"<param name='chooseCertificateTitle' value='Choose a Certificate' />" +
		"<param name='chooseCertificateSubTitle' value='SubTitle' />" +

		"<embed name='CSSignerApp' type='application/x-java-applet'" +
			" width='0' height='0'" +
			" code='com.cs.security.applet.CSSignerApp.class'" +
			" codebase='/sample-security-servlet/pages/static/applet'" +
			" archive='CSSignerApp.jar'" +
			" cache_version='1.0.3.1'" +
			" mayscript='true' scriptable='true' " +
			" separate_jvm='true'" +
			" setError='setError' " +
			" showException='showException'" +
			" activateDialog='false'" +
			" appDialogTitle='Security Sample'" +
			" chooseCertificateTitle='Choose a Certificate" +
			" chooseCertificateSubTitle='SubTitle'>" +
			" </embed>" +
	"</object>";
}

