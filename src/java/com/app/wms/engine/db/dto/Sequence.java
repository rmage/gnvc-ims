/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author zyrex
 */
public class Sequence implements Serializable{
    private BigDecimal nextSequence;

    /**
     * @return the nextSequence
     */
    public BigDecimal getNextSequence() {
        return nextSequence;
    }

    /**
     * @param nextSequence the nextSequence to set
     */
    public void setNextSequence(BigDecimal nextSequence) {
        this.nextSequence = nextSequence;
    }

    /**
	 * Method 'equals'
	 *
	 * @param _other
	 * @return boolean
	 */
	public boolean equals(Object _other)
	{
		if (_other == null) {
			return false;
		}

		if (_other == this) {
			return true;
		}

		if (!(_other instanceof Sequence)) {
			return false;
		}

		final Sequence _cast = (Sequence) _other;
		if (nextSequence == null ? _cast.nextSequence != nextSequence : false) {
			return false;
		}

		return true;
	}

	/**
	 * Method 'hashCode'
	 *
	 * @return int
	 */
	public int hashCode()
	{
		int _hashCode = 0;
		if (nextSequence != null) {
			_hashCode = 29 * _hashCode + nextSequence.hashCode();
		}

		return _hashCode;
	}

	/**
	 * Method 'toString'
	 *
	 * @return String
	 */
	public String toString()
	{
		StringBuffer ret = new StringBuffer();
		ret.append( "com.app.wms.engine.db.dto.SequenceId: " );
		ret.append( "nextSequence=" + nextSequence.toPlainString() );
		return ret.toString();
	}

}
