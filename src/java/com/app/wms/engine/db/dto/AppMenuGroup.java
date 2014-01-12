package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppMenuGroup implements Serializable {

    /**
     * This attribute maps to the column MENU_CODE in the APP_MENU table.
     */
    protected String groupCode;
    protected String sortNo;
    /**
     * This attribute maps to the column NAME in the APP_MENU table.
     */
    protected String name;
    /**
     * This attribute maps to the column CREATED_BY in the APP_MENU table.
     */
    protected String createdBy;
    /**
     * This attribute maps to the column CREATED_DATE in the APP_MENU table.
     */
    protected Date createdDate;
    /**
     * This attribute maps to the column UPDATED_BY in the APP_MENU table.
     */
    protected String updatedBy;
    /**
     * This attribute maps to the column UPDATED_DATE in the APP_MENU table.
     */
    protected Date updatedDate;

    /**
     * Method 'AppMenu'
     *
     */
    protected String isDelete;
    
    public AppMenuGroup() {
    }

    public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


    /**
     * Method 'getGroupCode'
     *
     * @return String
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * Method 'setGroupCode'
     *
     * @param groupCode
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * Method 'getName'
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method 'setName'
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * Method 'getCreatedDate'
     *
     * @return Date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Method 'setCreatedDate'
     *
     * @param createdDate
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Method 'getUpdatedDate'
     *
     * @return Date
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * Method 'setUpdatedDate'
     *
     * @param updatedDate
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * Method 'equals'
     *
     * @param _other
     * @return boolean
     */
    @Override
    public boolean equals(Object _other) {
        if (_other == null) {
            return false;
        }

        if (_other == this) {
            return true;
        }

        if (!(_other instanceof AppMenuGroup)) {
            return false;
        }

        final AppMenuGroup _cast = (AppMenuGroup) _other;
        if (groupCode == null ? (_cast.groupCode == null ? groupCode != null : !_cast.groupCode.equals(groupCode)) : !groupCode.equals(_cast.groupCode)) {
            return false;
        }

        if (name == null ? (_cast.name == null ? name != null : !_cast.name.equals(name)) : !name.equals(_cast.name)) {
            return false;
        }



        if (createdBy == null ? _cast.createdBy != createdBy : !createdBy.equals(_cast.createdBy)) {
            return false;
        }

        if (createdDate == null ? _cast.createdDate != createdDate : !createdDate.equals(_cast.createdDate)) {
            return false;
        }

        if (updatedBy == null ? _cast.updatedBy != updatedBy : !updatedBy.equals(_cast.updatedBy)) {
            return false;
        }

        if (updatedDate == null ? _cast.updatedDate != updatedDate : !updatedDate.equals(_cast.updatedDate)) {
            return false;
        }

        return true;
    }

    /**
     * Method 'hashCode'
     *
     * @return int
     */
    @Override
    public int hashCode() {
        int _hashCode = 0;
        if (groupCode != null) {
            _hashCode = 29 * _hashCode + groupCode.hashCode();
        }

        if (name != null) {
            _hashCode = 29 * _hashCode + name.hashCode();
        }

        if (createdBy != null) {
            _hashCode = 29 * _hashCode + createdBy.hashCode();
        }

        if (createdDate != null) {
            _hashCode = 29 * _hashCode + createdDate.hashCode();
        }

        if (updatedBy != null) {
            _hashCode = 29 * _hashCode + updatedBy.hashCode();
        }

        if (updatedDate != null) {
            _hashCode = 29 * _hashCode + updatedDate.hashCode();
        }

        return _hashCode;
    }

    /**
     * Method 'createPk'
     *
     * @return AppMenuPk
     */
    public AppMenuGroupPk createPk() {
        return new AppMenuGroupPk(groupCode);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("com.app.wms.engine.db.dto.AppMenu: ");
        ret.append("groupCode=" + groupCode);
        ret.append(", name=" + name);
        ret.append(", createdBy=" + createdBy);
        ret.append(", createdDate=" + createdDate);
        ret.append(", updatedBy=" + updatedBy);
        ret.append(", updatedDate=" + updatedDate);
        return ret.toString();
    }
}
