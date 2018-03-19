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


package com.cs.util.io;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Buffer class that wraps an array of bytes.
 * @author Marcio
 */
public class Buffer implements Serializable {

    Vector<Byte> buffer = new Vector<Byte>();
    
    public Buffer() {
    }

    /**
     * Creates a new Buffer instance and add one byte.
     * @param in_byte - The byte to add to the new buffer.
     */
    public Buffer(byte in_byte) {
        buffer.add(in_byte);
    }

    /**
     * Creates a new Buffer instance and fill it with the bytes.
     * @param in_buffer - The bytes to fill the buffer.
     */
    public Buffer(Vector<Byte> in_buffer) {
        buffer.addAll(in_buffer);
    }

    /**
     * Creates a new Buffer instance and fill it with the bytes.
     * @param aBytes - The array of Byte to fill the buffer.
     */
    public Buffer(Byte[] aByte) {
        List<Byte> collection = Arrays.asList(aByte);
        buffer.addAll(collection);
    }

    /**
     * Creates a new Buffer instance and fill it with the bytes.
     * @param bytes - The bytes to fill the buffer.
     */
    public Buffer(byte[] bytes) {
        add(bytes);
    }

    public Buffer(char[] chars) {
        add(chars);
    }

    /**
     * Creates a new Buffer instance copying the old Buffer´s contents values.
     * @param contents - The contents to copy.
     */
    public Buffer(Buffer contents) {
        copy(contents);
    }

    public Buffer(String string) {
        add(string.getBytes());
    }

    /**
     * Creates a new Buffer instance and fill it with the byte 'c' n times.
     * @param n - The number of size to fill the buffer with 'c'
     * @param c - The byte to fill.
     */
    public Buffer(int n, byte c) {
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, c);
        this.add(bytes);
    }

    public synchronized boolean add(char c) {
        return buffer.add(new Byte((byte)c));
    }

    public synchronized boolean add(byte c) {
        return buffer.add(new Byte((byte)c));
    }

    /**
     * Add the buffer into the current buffer final position.
     * @param in_value - The buffer to add.
     * @deprecated
     */
    public synchronized boolean add(Buffer in_value) {
        return addAll(in_value);
    }

    public synchronized boolean add(String in_value) {
        return add(in_value.getBytes());
    }

    public synchronized void add(byte b, int i) {
        buffer.add(i, b);
    }

    public synchronized boolean addAll(Buffer in_value) {
        return addAll(in_value.getElements());
    }

    /**
     * Gets the elements as byte array.
     * @return The array of bytes.
     */
    public synchronized byte[] get() {
        byte[] bytes = new byte[buffer.size()];
        for(int i=0; i<buffer.size(); i++) {
            bytes[i] = buffer.get(i);
        }
        return bytes;
    }

    /**
     * Gets an single byte at position of the index.
     * @param index - The index position.
     * @return The single byte.
     */
    public synchronized byte get(int index) {
        return buffer.get(index).byteValue();
    }

    /**
     * Gerts the elements from the range starting at in_ini until in_final (excluded)
     *  as byte array.
     * @param in_ini - The initial position to get.
     * @param in_final - The final position.
     * @return The array of bytes.
     */
    public synchronized byte[] get(int in_ini, int in_final) {
        //byte[] bytes = Arrays.copyOfRange(get(), in_ini, in_final);

        int size = in_final - in_ini;
        byte[] bytes = new byte[size];

        int index = in_ini;
        for(int i=0; i<size; i++) {
            bytes[i] = buffer.get(index++);
        }
        return bytes;
    }

    public synchronized void insert(int pos, Buffer in_src) {
        buffer.addAll(pos, in_src.getElements());
    }

    public synchronized Object[] getAll() {
        return buffer.toArray();
    }

    public synchronized boolean add(byte[] bytes) {
        boolean ret = true;
        for(int i=0; i<bytes.length; i++) {
            ret = buffer.add(bytes[i]);
        }
        return ret;
    }

    public synchronized boolean add(char[] chars) {
        boolean ret = true;
        for(int i=0; i<chars.length; i++) {
            ret = buffer.add((byte)chars[i]);
        }
        return ret;
    }

    public synchronized Vector<Byte> getElementsStartingAt(int pos) {
        Buffer l_buffer = new Buffer();
        if(pos >= buffer.size()) {
            System.out.println("error on pos");
        }
        l_buffer.putAll(buffer.subList(pos, buffer.size()));
        return l_buffer.getElements();
    }

    public synchronized Vector<Byte> getElementsStartingAt(int pos, int finalPos) {
        Buffer l_buffer = new Buffer();
        l_buffer.putAll(buffer.subList(pos, finalPos));
        return l_buffer.getElements();
    }

    public synchronized boolean putAll(byte[] in_buffer) {
        buffer.clear();
        return add(in_buffer);
    }

    public synchronized boolean putAll(Buffer contents) {
        return putAll(contents.getElements());
    }

    public synchronized void setFromString(String in_value) {
        buffer.clear();
        byte[] bytes = in_value.getBytes();
        for(int i=0; i<bytes.length; i++) {
            buffer.add(bytes[i]);
        }
    }

    public synchronized void trimToSize() {
        buffer.trimToSize();
    }

    public synchronized String toString() {
        return buffer.toString();
    }

    public synchronized <T> T[] toArray(T[] a) {
        return buffer.toArray(a);
    }

    public synchronized Object[] toArray() {
        return buffer.toArray();
    }

    public synchronized List<Byte> subList(int fromIndex, int toIndex) {
        return buffer.subList(fromIndex, toIndex);
    }

    public synchronized int size() {
        return buffer.size();
    }

    public synchronized void setSize(int newSize) {
        buffer.setSize(newSize);
    }

    public synchronized void setElementAt(Byte obj, int index) {
        buffer.setElementAt(obj, index);
    }

    public synchronized Byte set(int index, Byte element) {
        return buffer.set(index, element);
    }

    public synchronized boolean retainAll(Collection<?> c) {
        return buffer.retainAll(c);
    }

    public synchronized void removeElementAt(int index) {
        buffer.removeElementAt(index);
    }

    public synchronized boolean removeElement(Object obj) {
        return buffer.removeElement(obj);
    }

    public synchronized void removeAllElements() {
        buffer.removeAllElements();
    }

    public synchronized boolean removeAll(Collection<?> c) {
        return buffer.removeAll(c);
    }

    public synchronized Byte remove(int index) {
        return buffer.remove(index);
    }

    public synchronized boolean remove(Object o) {
        return buffer.remove(o);
    }

    public synchronized int lastIndexOf(Object o, int index) {
        return buffer.lastIndexOf(o, index);
    }

    public synchronized int lastIndexOf(Object o) {
        return buffer.lastIndexOf(o);
    }

    public synchronized Byte lastElement() {
        return buffer.lastElement();
    }

    public synchronized boolean isEmpty() {
        return buffer.isEmpty();
    }

    public synchronized void insertElementAt(Byte obj, int index) {
        buffer.insertElementAt(obj, index);
    }

    public synchronized int indexOf(Object o, int index) {
        return buffer.indexOf(o, index);
    }

    public int indexOf(Object o) {
        return buffer.indexOf(o);
    }

    public synchronized Byte firstElement() {
        return buffer.firstElement();
    }

    @Override
    public synchronized boolean equals(Object o) {
        if(o instanceof Buffer) {
            Buffer l_buffer = (Buffer)o;
            if(Arrays.equals(l_buffer.get(), get())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.buffer != null ? this.buffer.hashCode() : 0);
        return hash;
    }

    public synchronized void ensureCapacity(int minCapacity) {
        buffer.ensureCapacity(minCapacity);
    }

    public Enumeration<Byte> elements() {
        return buffer.elements();
    }

    public synchronized Byte elementAt(int index) {
        return buffer.elementAt(index);
    }

    public synchronized void copyInto(Object[] anArray) {
        buffer.copyInto(anArray);
    }

    public synchronized boolean containsAll(Collection<?> c) {
        return buffer.containsAll(c);
    }

    public synchronized Object clone() {
        return buffer.clone();
    }

    public void clear() {
        buffer.clear();
    }

    public synchronized int capacity() {
        return buffer.capacity();
    }

    public synchronized void addElement(Byte obj) {
        buffer.addElement(obj);
    }

    public synchronized boolean addAll(int index, Collection<? extends Byte> c) {
        return buffer.addAll(index, c);
    }

    public synchronized boolean addAll(Collection<? extends Byte> c) {
        return buffer.addAll(c);
    }

    public synchronized void add(int index, Byte element) {
        buffer.add(index, element);
    }

    public synchronized boolean add(Byte e) {
        return buffer.add(e);
    }

    public synchronized boolean putAll(Collection<? extends Byte> c) {
        buffer.clear();
        return buffer.addAll(c);
    }

    public synchronized void copy(Buffer contents) {
        this.buffer.clear();
        this.buffer.addAll(contents.buffer);
    }

    public Vector<Byte> getElements() {
        return buffer;
    }

    public synchronized String getAsString() {
        StringBuffer ret = new StringBuffer();
        for(Byte c : buffer) {
            char retChar = (char)c.byteValue();
            ret.append(retChar);
        }
        return ret.toString();
    }

    public synchronized String getAsString(String charSet) throws UnsupportedEncodingException {
        StringBuffer ret = new StringBuffer();
        for(Byte c : buffer) {
            char retChar = (char)c.byteValue();
            ret.append(new String(String.valueOf(retChar).getBytes(charSet)));
        }
        return ret.toString();
    }

    public synchronized boolean add(int b) {
        return buffer.add(new Byte((byte)b));
    }


}
