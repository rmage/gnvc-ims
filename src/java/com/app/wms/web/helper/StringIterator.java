/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.web.helper;

import java.util.Iterator;

/**
 * A String iterator that works as expected when two succesive delimeters are
 * found (as opposed to java.util.StringTokenizer, it returns null as the
 * String in that case).  Only a single character delimeter is allowed.
 */
public class StringIterator implements Iterator
{

private String str;
private char delim;
private int prevPos;
private int pos;

/**
 * @param str
 * @param delim
 */
public StringIterator(String str, char delim)
{
        this.str = str;
        this.delim = delim;
        prevPos = 0;
        pos = str.indexOf(delim);
}

/* (non-Javadoc)
 * @see java.util.Iterator#hasNext()
 */
public boolean hasNext()
{
        return prevPos != -1;
}

/* (non-Javadoc)
 * @see java.util.Iterator#next()
 */
public Object next()
{
        return nextString();
}

/**
 * @return String
 */
public String nextString()
{
        if (pos == -1) {
                String token = str.substring(prevPos);
                prevPos = -1;
                return token;
        }
        String token = str.substring(prevPos, pos);
        prevPos = pos + 1;
        pos = str.indexOf(delim, prevPos);
        return token;
}

/* (non-Javadoc)
 * @see java.util.Iterator#remove()
 */
public void remove()
{
        throw new UnsupportedOperationException();
}

}