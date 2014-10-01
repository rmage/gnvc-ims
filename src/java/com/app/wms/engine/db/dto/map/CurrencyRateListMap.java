package com.app.wms.engine.db.dto.map;

import com.app.wms.engine.db.dto.CurrencyRate;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class CurrencyRateListMap implements ParameterizedRowMapper<CurrencyRate> {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public CurrencyRate mapRow(ResultSet rs, int row) throws SQLException {
        // TODO Auto-generated method stub
        CurrencyRate cr = new CurrencyRate();
        cr.setRateId(rs.getInt(1));
        cr.setCurrencyType(rs.getString(2));
        cr.setcurrencyCodeFrom(rs.getString(3));
        cr.setCurrencyCodeTo(rs.getString(4));
        /*CONVERT STRING TO BIGINTEGER*/
        double value = Double.parseDouble(rs.getString(5));
        BigDecimal db = BigDecimal.valueOf(value);
        cr.setRateValue(db);
        /*CONVERT STRING TO DATE*/
        Date rateDate = null;
        Date rateWeekStart = null;
        Date rateWeekEnd = null;
        Date rateMonth = null;
        Date createdDate = null;
        try {
            if (rs.getString(6) != null ) {
                rateDate = df.parse(rs.getString(6));
            }
            if (rs.getString(7) != null ) {
                rateWeekStart = df.parse(rs.getString(7));
            }
            if (rs.getString(8) != null ) {
                rateWeekEnd = df.parse(rs.getString(8));
            }
            if (rs.getString(9) != null ) {
                rateMonth = df.parse(rs.getString(9));
            }
            if (rs.getString(11) != null ) {
                createdDate = df.parse(rs.getString(11));
            }
        } catch (ParseException ex) {
            Logger.getLogger(CurrencyRateListMap.class.getName()).log(Level.SEVERE, null, ex);
        }
        cr.setRateDate(rateDate);
        cr.setWeekStartDate(rateWeekStart);
        cr.setWeekEndDate(rateWeekEnd);
        cr.setMonthDate(rateMonth);
        cr.setCreatedBy(rs.getString(10));
        cr.setCreatedDate(createdDate);

        return cr;
    }

}
