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


package com.cs.exception;

/**
 * Extends the Exception Class to handle exceptions
 *
 * @author Marcio
 */
public abstract class BaseException extends Exception {
    protected long errorCode = 0;
    protected String className = "";
    protected String localizedMessage = "";

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.localizedMessage = cause.getLocalizedMessage();
    }

    public BaseException(long in_code, String in_class_name, String in_description) {
        super(in_description);
        this.errorCode = in_code;
        this.className = in_class_name;
        this.localizedMessage = in_description;
    }

    public BaseException(long in_code, String in_class_name, String in_description, Throwable in_exception) {
        super(in_description, in_exception);
        this.errorCode = in_code;
        this.className = in_class_name;
        this.localizedMessage = super.getLocalizedMessage();
    }

    @Override
    public String getLocalizedMessage() {
        return this.localizedMessage;
    }

    public long getErrorCode() {
        return this.errorCode;
    }
}
