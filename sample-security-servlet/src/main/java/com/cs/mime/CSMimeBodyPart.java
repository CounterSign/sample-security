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

/**
 * Implements the MIME BodyPart.
 * The MIME BodyPart may contains: Content-Disposition, Content-Type and/or Data.
 * @author Marcio
 */
public class CSMimeBodyPart {

    protected CSMimeContentDisposition contentDisposition;
    protected CSMimeContentType contentType;
    protected CSMimeContentData contentData;
    protected CSMimeContentTransferEncoding contentTransferEncoding;

    public CSMimeBodyPart() {
    }

    public CSMimeContentData getContentData() {
        return contentData;
    }

    public void setContentData(CSMimeContentData contentData) {
        this.contentData = contentData;
    }

    public CSMimeContentDisposition getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(CSMimeContentDisposition contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public CSMimeContentType getContentType() {
        return contentType;
    }

    public void setContentType(CSMimeContentType contentType) {
        this.contentType = contentType;
    }

    public CSMimeContentTransferEncoding getContentTransferEncoding() {
        return contentTransferEncoding;
    }

    public void setContentTransferEncoding(CSMimeContentTransferEncoding contentTransferEncoding) {
        this.contentTransferEncoding = contentTransferEncoding;
    }


}
