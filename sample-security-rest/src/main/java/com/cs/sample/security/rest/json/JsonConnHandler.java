package com.cs.sample.security.rest.json;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implements a handler for connection to a RESTFul WebService
 * @author countersign.com.br
 *
 */
public class JsonConnHandler {

	private static final Logger logger = Logger.getLogger(JsonConnHandler.class);
	
	public static String getJSONString(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		mapper.writeValue(bout, obj);
		return new String(bout.toByteArray());
	}

	public static Object getObject(String json, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, clazz);
	}

	public static Object callSvcREST(String svcUrl, Object objReq, Class<?> clazz) {

		HttpURLConnection conn = null;
		boolean success = false;
		try {
			logger.debug("calling the Service REST at URL: " + svcUrl);

			URL url = new URL(svcUrl);
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json;charset=utf-8");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setConnectTimeout(3000);
			conn.setDoOutput(true);

			String sendData = getJSONString(objReq);
			System.out.println("sending: " + sendData);
			logger.info("sending: " + sendData);

			OutputStream os = conn.getOutputStream();
			os.write(sendData.getBytes());
			os.flush();

			BufferedReader br;
			if (conn.getResponseCode() == 200) {
				logger.info("Service REST returned OK = 200");
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				success = true;
			} else {
				logger.info("Service REST returned ERROR = is not equal 200");
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}

			String read = "";
			String outputData = "";
			while ((read = br.readLine()) != null) {
				outputData += new String(read.getBytes(), "UTF-8");
			}
			logger.info("receiving data: " + outputData);
			System.out.println("receiving data: " + outputData);

			if (!success) {
				logger.error("received error");
				return null;
			}

			logger.trace("parser the JSON object");
			return getObject(outputData, clazz);

		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
	}

}
