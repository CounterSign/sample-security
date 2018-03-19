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
import com.cs.util.string.StringUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * The handler for Multipart MIME type in HTML posted forms.
 * 
 * @author Marcio
 */
public class FormMimeHandler {

    protected CSMimeMultipart multipart = new CSMimeMultipart();
    protected CSMimeTypeEventHandler mimeTypeHandler = null;

    public FormMimeHandler() {
    }

    public CSMimeMultipart getMultipart() {
        return this.multipart;
    }

    /**
     * Executes a parse from the Form data formatted as MIME
     * @param buffer
     */
    public void parse(byte[] buffer) {
        Buffer allData = new Buffer(buffer);
        int step = 0;
        Buffer boundary = new Buffer();
        Buffer contentType = new Buffer();
        Buffer contentTypeData = new Buffer();
        Buffer contentTypeFooter = new Buffer();
        Buffer contentDisposition = new Buffer();

        String teste = new String(allData.get());

        CSMimeBodyPart bodyPart = new CSMimeBodyPart();

        Buffer tmp = new Buffer();
        for(int i=0; i < buffer.length; i++) {
            tmp.add(buffer[i]);
            if(buffer[i] == '\n') {
                if(step == 0) {
                    boundary.putAll(tmp.getElementsStartingAt(0, tmp.size()-2));
                    tmp.clear();
                    step++;
                    multipart.setBoundary(boundary.getAsString());
                    bodyPart = new CSMimeBodyPart();

                } else if(step == 1) {
                    //parser the content
                    String header = tmp.getAsString();
                    if(header.contains("Content-Disposition")) {
                        //it is a content-disposition
                        contentDisposition.putAll(tmp.getElementsStartingAt(0, tmp.size()-2));
                        tmp.clear();

                        CSMimeContentDisposition mimeContentDisposition = new CSMimeContentDisposition(contentDisposition.getAsString());
                        bodyPart = new CSMimeBodyPart();
                        bodyPart.setContentDisposition(mimeContentDisposition);

                    } else if(header.contains("Content-Type")) {
                        //content type header
                        contentType.putAll(tmp.getElementsStartingAt(0, tmp.size()-2));
                        tmp.clear();

                        CSMimeContentType mimeContentType = new CSMimeContentType(contentType.getAsString());
                        bodyPart.setContentType(mimeContentType);

                        int start = i;
                        //skip the first \n
                        for(int j=start; j < buffer.length; j++) {
                            if( (buffer[j] != '\r') && ((buffer[j] != '\n')) ) {
                                break;
                            }
                            i++;
                        }

                        //read all contentType data
                        boolean foundEndOfBoundary = false;
                        start = i;
                        for(int f=start; f < buffer.length; f++) {
                            Buffer content = new Buffer(allData.getElementsStartingAt(f, (f+boundary.size()) > allData.size() ? allData.size() : (f+boundary.size()) ));
                            if(content.equals(boundary)) {
                                //end of content-type data
	                            int tail = 0;
	                            if( (tmp.get(tmp.size()-2) == '\r') && ((tmp.get(tmp.size()-1) == '\n')) ) {
	                                tail = 2;
	                            }
	                            contentTypeData = new Buffer(tmp.getElementsStartingAt(0, tmp.size()-tail));
	                            tmp.clear();
	
	                            CSMimeContentData mimeContentData = new CSMimeContentData(mimeContentType, contentTypeData);
	                            bodyPart.setContentData(mimeContentData);
	                            this.multipart.addMimeBodyPart(bodyPart);
	                            foundEndOfBoundary = true;
	                            
                                //skip the boundary
                                i = i + (boundary.size() + 2);
                                break;
                            } else if( (buffer.length > f+1) && ( (buffer[f] == '\r') && ((buffer[f+1] == '\n')) ) ) {
                                if(mimeContentType.getType().equalsIgnoreCase("text/html")) {
                                    //do not add
                                } else {
                                    tmp.add(buffer[f]);
                                }
                            } else {
                                tmp.add(buffer[f]);
                            }
                            i++;
                        }
                        if(! foundEndOfBoundary) {
                            contentTypeData = new Buffer(tmp.getElementsStartingAt(0, tmp.size()));
                            tmp.clear();

                            CSMimeContentData mimeContentData = new CSMimeContentData(mimeContentType, contentTypeData);
                            bodyPart.setContentData(mimeContentData);
                            this.multipart.addMimeBodyPart(bodyPart);
                        }
                    } else {
	                    if( (buffer.length > i+1) && ( (buffer[i] == '\r') && ((buffer[i+1] == '\n')) ) ) {
                            //do nothing
                        } else {
                            String line = tmp.getAsString();
                            line = StringUtils.removePhrase(line, "\r");
                            line = StringUtils.removePhrase(line, "\n");
                            line = StringUtils.removeLeadingSpaces(line);
                            line = StringUtils.removeTrailingSpaces(line);
                            if(line.length() > 0) {
                                contentTypeData = new Buffer(line);
                                tmp.clear();

                                CSMimeContentType mimeContentType = new CSMimeContentType("Content-Type: form/field");
                                CSMimeContentData mimeContentData = new CSMimeContentData(mimeContentType, contentTypeData);
                                bodyPart.setContentType(mimeContentType);
                                bodyPart.setContentData(mimeContentData);
                                this.multipart.addMimeBodyPart(bodyPart);

                                step--;
                            }
                        }
                    }
                }
            }
        }
    }
}
