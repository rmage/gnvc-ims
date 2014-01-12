package com.app.wms.engine.db.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppMenu implements Serializable {

    /**
     * This attribute maps to the column MENU_CODE in the APP_MENU table.
     */
    protected String menuCode;
    /**
     * This attribute maps to the column GROUP_CODE in the APP_MENU table.
     */
    protected String groupCode;
    protected String sortNo;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * This attribute maps to the column NAME in the APP_MENU table.
     */
    protected String name;
    /**
     * This attribute maps to the column URL in the APP_MENU table.
     */
    protected String url;
    /**
     * This attribute maps to the column CREATED_BY in the APP_MENU table.
     */
    protected String is_delete;
    
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
    public AppMenu() {
    }

    public String getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
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

	public String getSortNo() {
		return sortNo;
	}

	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	/**
     * Method 'getMenuCode'
     *
     * @return String
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * Method 'setMenuCode'
     *
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
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
     * Method 'getUrl'
     *
     * @return String
     */
    public String getUrl() {
        return url;
    }

    /**
     * Method 'setUrl'
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
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

        if (!(_other instanceof AppMenu)) {
            return false;
        }

        final AppMenu _cast = (AppMenu) _other;
        if (menuCode == null ? (_cast.menuCode == null ? menuCode != null : !_cast.menuCode.equals(menuCode)) : !menuCode.equals(_cast.menuCode)) {
            return false;
        }
        if (groupCode == null ? (_cast.groupCode == null ? groupCode != null : !_cast.groupCode.equals(groupCode)) : !groupCode.equals(_cast.groupCode)) {
            return false;
        }
        if (name == null ? (_cast.name == null ? name != null : !_cast.name.equals(name)) : !name.equals(_cast.name)) {
            return false;
        }

        if (url == null ? (_cast.url == null ? url != null : !_cast.url.equals(url)) : !url.equals(_cast.url)) {
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
        if (menuCode != null) {
            _hashCode = 29 * _hashCode + menuCode.hashCode();
        }
        if (groupCode != null) {
            _hashCode = 29 * _hashCode + groupCode.hashCode();
        }
        if (name != null) {
            _hashCode = 29 * _hashCode + name.hashCode();
        }

        if (url != null) {
            _hashCode = 29 * _hashCode + url.hashCode();
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
    public AppMenuPk createPk() {
        return new AppMenuPk(menuCode);
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
        ret.append("menuCode=" + menuCode);
        ret.append(", groupCode=" + groupCode);
        ret.append(", name=" + name);
        ret.append(", url=" + url);
        ret.append(", createdBy=" + createdBy);
        ret.append(", createdDate=" + createdDate);
        ret.append(", updatedBy=" + updatedBy);
        ret.append(", updatedDate=" + updatedDate);
        return ret.toString();
    }
}
