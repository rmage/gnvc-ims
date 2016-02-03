package com.spfi.ims.dao;

import com.spfi.ims.dto.BackDateProfile;
import java.util.List;
import java.util.Map;

public interface BackDateProfileDao {
    
    void create(BackDateProfile backDateProfile);
    
    void delete(int id, String updatedBy);
    
    List<Map<String, Object>> findByAppMenu(String appMenuCode);
    
    List<Map<String, Object>> findUserByName(String name, String appMenuCode);
    
    /**
     * @param pathName  menu url that used for the request
     * @param userId    user_id from database value
     * @return          validity status 0 (invalid) or 1 (valid)
     */
    int checkIsValid(String pathName, String userId);
    
}
