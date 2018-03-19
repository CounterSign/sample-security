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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcio
 */
public class CSMimeMultipart {

    protected String boundary = "";
    protected List<CSMimeBodyPart> listOfMimeBodyParts = new ArrayList<CSMimeBodyPart>();

    public CSMimeMultipart() {

    }

    public String getBoundary() {
        return boundary;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public List<CSMimeBodyPart> getListOfMimeBodyParts() {
        return listOfMimeBodyParts;
    }

    public void setListOfMimeBodyParts(List<CSMimeBodyPart> listOfMimeBodyParts) {
        this.listOfMimeBodyParts = listOfMimeBodyParts;
    }

    public void addMimeBodyPart(CSMimeBodyPart mimeBodyPart) {
        this.listOfMimeBodyParts.add(mimeBodyPart);
    }

}
