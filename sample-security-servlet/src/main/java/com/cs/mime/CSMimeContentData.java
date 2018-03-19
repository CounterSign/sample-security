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

import com.cs.util.io.Buffer;

/**
 * Implements the MIME data, inside a Content-Type
 * @author Marcio
 */
public class CSMimeContentData {

    protected CSMimeContentType mimeContentType = null;
    protected Buffer data = new Buffer();

    public CSMimeContentData() {
    }

    public CSMimeContentData(CSMimeContentType mimeContentType, Buffer data) {
        this.mimeContentType = mimeContentType;
        this.data = data;
    }

    public Buffer getData() {
        return data;
    }

    public void setData(Buffer data) {
        this.data = data;
    }

    public CSMimeContentType getMimeContentType() {
        return mimeContentType;
    }

    public void setMimeContentType(CSMimeContentType mimeContentType) {
        this.mimeContentType = mimeContentType;
    }


}
