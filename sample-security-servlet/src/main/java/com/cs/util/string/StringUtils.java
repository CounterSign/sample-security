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


package com.cs.util.string;

/**
 * An utility class to handle strings.
 * @author Marcio
 */
public final class StringUtils {

    public static final short LEFT = 0;
    public static final short RIGTH = 0;
    
    /**
     * Erase 'n' chars until the final index position from string, starting at pos: iniPos.
     * @param in_text - The string to erase.
     * @param iniPos - The initial position to start.
     * @return The new string erasured.
     */
    public static final String erase(String in_text, int iniPos) {
        return erase(in_text, in_text.length());
    }

    /**
     * Erase 'n' chars until the final index position or length from string, starting at pos: iniPos.
     * @param in_text - The string to erase.
     * @param iniPos - The initial position to start.
     * @param length - The amount of chars to erase.
     * @return The new string erasured.
     */
    public static final String erase(String in_text, int iniPos, int length) {
        String ret = in_text;
        int finalPos = length;
        if((iniPos + length) > ret.length()) {
            finalPos = ret.length();
        } else {
            finalPos = (iniPos + length);
        }
        String ret1 = ret.substring(0, iniPos);
        if(finalPos >= ret.length()) {
            ret = ret1;
        } else {
            ret = ret1 + ret.substring(finalPos, ret.length());
        }
        return ret;
    }

    /**
     * Removes a substring from string.
     * @param in_text - The original string.
     * @param in_char - The substring to remove from string
     * @return The new string.
     */
    public static final String removePhrase(String in_text, String in_char) {
        String ret = in_text;
        int l_pos_type = 0;
        while( (l_pos_type = ret.indexOf(in_char)) >= 0 ) {
            ret = StringUtils.erase(ret, l_pos_type, in_char.length());
        }
        return ret;
	}

    /**
     * Remove alphabetic chars from string.
     * @param in_text - The string to be replaced.
     * @return The new string without alphabetic chars.
     */
	public static final String removeAlfa(String in_text) {
        String ret = in_text;
        StringBuffer l_tmp = new StringBuffer();
        for(int i=0; i < in_text.length(); i++) {
            char cit = in_text.charAt(i);
            if(((cit-48 > -2) && (cit-48 < 10)) || (cit == ' ')) {
                l_tmp.append(cit);
            }
        }
        ret = l_tmp.toString();
        return ret;
	}

    /**
     * Replaces a substring from string to another substring.
     * @param in_text - The original string.
     * @param in_charToFind - The substring to find.
     * @param in_charToReplace - The substring to replace the substring found.
     * @return The new string replaced.
     */
    public static final String replacePhrase(String in_text, String in_charToFind, String in_charToReplace) {
        String ret = in_text;
        int pos = 0;
        while((pos = ret.indexOf(in_charToFind)) >= 0) {
            if(in_charToFind.equals("+")) {
                ret = ret.substring(0, pos) + in_charToReplace + ret.substring(pos+1);
            } else {
                StringBuffer tmp = new StringBuffer(ret);
                ret = tmp.replace(pos, pos + in_charToFind.length(), in_charToReplace).toString();
            }
        }
        return ret;
    }

    /**
     * Executes a leading trim.
     * @param in_text - The original string.
     * @return The new string without leading spaces
     */
    public static final String removeLeadingSpaces(String in_text) {
        StringBuffer ret = new StringBuffer(in_text);
        for(int i=0; i<in_text.length(); i++) {
            char cit = in_text.charAt(i);
            if(cit != ' ') {
                return ret.toString();
            } else {
                ret.deleteCharAt(i);
            }
        }
        return ret.toString();
    }

    /**
     * Executes a trailing trim.
     * @param in_text - The original string.
     * @return The new string without trailing spaces
     */
    public static final String removeTrailingSpaces(String in_text) {
        StringBuffer ret = new StringBuffer("");
        boolean l_flag = false;
        for(int i=0; i<in_text.length(); i++) {
            char cit = in_text.charAt(i);
            if( (cit == ' ') && (l_flag==false) ) {
                ret.append(cit);
            } else if ((cit != ' ')) {
                l_flag = true;
                ret.append(cit);
            }
        }
        return ret.toString();
    }

    /**
     * Extract a substring from the string without remove it from the original string.
     * The seacr begins with the begin string and ends with the end string.
     * @param content The original string
     * @param begin The initial word to search the substring
     * @param end The final word to get the substring
     * @return The substring
     */
    public static final String extractSubstring(String content, String begin, String end) {
        String out = "";
        int initPos = 0;
        initPos = content.indexOf(begin);
        if(initPos >= 0) {
            initPos += begin.length();
            String tmp = content.substring(initPos);
            int pos = tmp.indexOf(end);
            if(pos < 0) {
                pos = tmp.length();
            }
            out = tmp.substring(0, pos);
        }
        return out;
    }

    /**
     * Extract a substring from the string removing it from the original string.
     * The seacr begins with the begin string and ends with the end string or until
     * the end of string.
     * @param content The original string
     * @param begin The initial word to search the substring
     * @param end The final word to get the substring
     * @return The string without the substring
     */
    public static final String removeSubstring(String content, String begin, String end) {
        StringBuffer out = new StringBuffer(content);
        int initPos = 0;
        initPos = content.indexOf(begin);
        if(initPos >= 0) {
            int pos = content.indexOf(end, initPos);
            if(pos < 0) {
                pos = content.length();
            } else {
                pos++; //inclusive
            }
            out.delete(initPos, pos);
        }
        return out.toString();
    }

    public static String getMask(String text, String mask, int repetitionToComplete, short type) {
        if(text.length() > repetitionToComplete) {
            return text;
        }
        StringBuilder sb = new StringBuilder(text);
        if(type == StringUtils.LEFT) {
            for(int i=0; i < repetitionToComplete - text.length(); i++) {
                sb.insert(0, mask);
            }
        } else {
            for(int i=0; i < repetitionToComplete - text.length(); i++) {
                sb.append(mask);
            }
        }
        return sb.toString();
    }

    public static String getMask(String text, String mask, int maskSize, short type, boolean stop) {
        if(text.length() >= maskSize) {
            return text.substring(0, maskSize);
        }
        if(stop) {
            return text;
        }
        StringBuilder sb = new StringBuilder(text);
        if(type == StringUtils.LEFT) {
            for(int i=0; i < maskSize - text.length(); i++) {
                sb.insert(0, mask);
            }
        } else {
            for(int i=0; i < maskSize - text.length(); i++) {
                sb.append(mask);
            }
        }
        return sb.toString();
    }
}
