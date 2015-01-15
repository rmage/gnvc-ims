package com.spfi.ims.dao;

import java.util.Date;

public interface DBLoggingDao {
    
    public void logError(Date logDate, String logModule, String logSubmodule, String logText, String loguser, int logStatus);
    
}
