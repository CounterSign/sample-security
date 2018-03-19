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
 
 NOTICE: this is a part of source code of the Digital Signature Sample using the 
         CounterSign Security Rest API.
         The CounterSign Security Rest API is a digital signature paid service and 
         to achieve it you need buy credits or acquire a license.
*/

package com.cs.sample.security.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implements a HTML reader
 * @author ramir_000
 *
 */
public class HtmlFileHandler {

	/**
	 * Read the HTML file 
	 * @param fileName the HTML to be loaded
	 * @return String the html
	 * @throws IOException
	 */
	public synchronized static String readHtmlFromFile(String fileName) throws IOException {
		String html = "";
		FileInputStream fileIn = null;
		try {
			fileIn  = new FileInputStream(fileName);
			int sizeof = fileIn.available();
			byte[] buffer = new byte[sizeof];
			fileIn.read(buffer);
			html = new String(buffer, "UTF-8");
			return html;
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if(fileIn != null) {
				try {
					fileIn.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
