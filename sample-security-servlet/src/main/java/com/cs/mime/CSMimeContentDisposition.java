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

import com.cs.util.string.StringUtils;

/**
 * Implements the MIME Content-Disposition info.
 *
 * Content-Disposition: type; name=""; filename=""
 *
 * @author Marcio
 */
public class CSMimeContentDisposition {

    String contentDisposition = "";
    String type = "";
    String name = "";
    String fileName = "";

    public CSMimeContentDisposition() {
    }

    public CSMimeContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
        parse(this.contentDisposition);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    /**
     * Executes a parse over the contentDisposition retrieving the detailed
     * information.
     * Content-Disposition: type; name=""; filename=""
     * @param contentDisposition
     */
    public void parse(String contentDisposition) {
        this.type = StringUtils.extractSubstring(contentDisposition, "Content-Disposition: ", ";");
        this.name = StringUtils.extractSubstring(contentDisposition, "name=\"", "\"");
        this.fileName = StringUtils.extractSubstring(contentDisposition, "filename=\"", "\"");
    }


}
