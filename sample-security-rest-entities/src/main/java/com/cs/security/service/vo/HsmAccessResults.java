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

package com.cs.security.service.vo;


public class HsmAccessResults extends HsmResults {

	/**
	 * 
	 */
	private static final long serialVersionUID = 214151915901910361L;
	
	protected String name = "";

	//smartcardInfo
	protected String smartCardName = "";
	//deviceInfo
    protected long slotId = 0;
    protected String serialNumber = "";
    protected String atr = "";
    protected String deviceName = "";
    protected String libFileName = "";
    protected String manufacturer = "";
    protected String manufacturerAdditionalInfo = "";
    protected int so = 1;
    protected String label = "";
	
	
	public HsmAccessResults() {
	}
	
	
	public int getResults() {
		return results;
	}
	public void setResults(int results) {
		this.results = results;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public long getSlotId() {
		return slotId;
	}


	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}


	public String getSerialNumber() {
		return serialNumber;
	}


	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}


	public String getAtr() {
		return atr;
	}


	public void setAtr(String atr) {
		this.atr = atr;
	}


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public String getLibFileName() {
		return libFileName;
	}


	public void setLibFileName(String libFileName) {
		this.libFileName = libFileName;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getManufacturerAdditionalInfo() {
		return manufacturerAdditionalInfo;
	}


	public void setManufacturerAdditionalInfo(String manufacturerAdditionalInfo) {
		this.manufacturerAdditionalInfo = manufacturerAdditionalInfo;
	}


	public int getSo() {
		return so;
	}


	public void setSo(int so) {
		this.so = so;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getSmartCardName() {
		return smartCardName;
	}


	public void setSmartCardName(String smartCardName) {
		this.smartCardName = smartCardName;
	}
	
	
}
