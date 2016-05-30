package com.app.wms.engine.util;

import com.app.wms.engine.db.factory.DaoFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ctrlKoneksiDB {
    public ctrlKoneksiDB() {}

    public Connection openConnection() {
        Connection connect = null;
        try {
            //  XXX : FYA | Database Connection Setting
            connect = DaoFactory.getReportDataSource().getConnection();
            return connect;
        } catch (SQLException se) {
            System.out.println("Connection Failed! Error @ : " + se.getMessage());
            return null;
        } catch (Exception ex) {
            System.out.println("Failed to Connect Database! Error @ : " + ex.getMessage());
            return null;
        }
    }
}
