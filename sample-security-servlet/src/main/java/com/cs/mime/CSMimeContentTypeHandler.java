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


package com.cs.mime;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Marcio
 */
public class CSMimeContentTypeHandler {

    protected static Map<String, String> registeredClasses = new HashMap<String, String>();

    public static void register(String contentType, String className) {
        registeredClasses.put(contentType, className);
    }

    public static ICSMimeHandler create(String contentType) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        String className = registeredClasses.get(contentType);
        if(className != null) {
            System.out.print("--- handling with ");
            System.out.print(className);
            System.out.println(" ----");

            Class cl = Class.forName(className);
            ICSMimeHandler theInstance = (ICSMimeHandler) cl.newInstance();
            return theInstance;
        } else {
            //the default
            if(contentType.equals("text/plain")) {
                System.out.println("--- TEXT/PLAIN received ----");
                return new CSMimeTypeTextPlainHandler();
            } else if(contentType.equals("application/octet-stream")) {
                System.out.println("--- application/octet-stream received ----");
                return new CSMimeTypeOctetStreamHandler();
            } else if(contentType.equals("application/pdf")) {
                System.out.println("--- application/pdf received ----");
                return new CSMimeTypeOctetStreamHandler();
            } else if(contentType.equals("application/x-x509-ca-cert")) {
                System.out.println("--- application/x-x509-ca-cert received ----");
                throw new ClassNotFoundException("CSMimeTypeX509CertHandler not implemented");
            } else {
                System.out.println("--- UNKNOW MIME received ----");
                return new CSMimeTypeOctetStreamHandler();
            }
        }
    }

}
