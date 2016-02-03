package com.spfi.ims.dao;

import com.spfi.ims.dto.BackDateProfile;
import java.util.List;
import java.util.Map;

public interface BackDateProfileDao {
    
    void create(BackDateProfile backDateProfile);
    
    void delete(int id, String updatedBy);
    
    List<Map<String, Object>> findByAppMenu(String appMenuCode);
    
    List<Map<String, Object>> findUserByName(String name, String appMenuCode);
    
    int checkIsValid(String pathName, String userId);
    
}
