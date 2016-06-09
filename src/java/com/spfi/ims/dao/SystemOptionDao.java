package com.spfi.ims.dao;

import com.spfi.ims.dto.SystemOption;
import java.util.List;

public interface SystemOptionDao {
    
    void postUpdateValue(int id, String value);
    
    SystemOption findByCode (String code);
    
    List<SystemOption> getEditableOptions();
    
}
