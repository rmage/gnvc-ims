package com.app.wms.engine.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;

public class ctrlIDGenerator {
	
	private ctrlKoneksiDB koneksi;
	private Connection conn;
	private Statement st;
	private int i, h;
	private String kd, j;
	
	public ctrlIDGenerator (){
		koneksi = new ctrlKoneksiDB();
	}
	
	String year =  new SimpleDateFormat("yyyy").format(new Date());
	
	public String getIDCorporate() {
		try{
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select corp_id from corp order by corp_id desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(4);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
					case 1  : kd = "CY-000" + j; break;
					case 2  : kd = "CY-00" + j; break;
					case 3  : kd = "CY-0" + j; break;
					case 4  : kd = "CY-" + j; break;
				}
			}else{
				kd = "CY-0001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	public String getIDPicking() {
		try{
			String head = "PI-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select picking_id from picking order by picking_id desc";
			//String SQL = "select picking_id from picking order by picking_id desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	public String getIDSalesOrder() {
		try{
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select so_number from salesorder order by so_number desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(1);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){
					case 1  : kd = "S0000000000" + j; break;
					case 2  : kd = "S000000000" + j; break;
					case 3  : kd = "S00000000" + j; break;
					case 4  : kd = "S0000000" + j; break;
					case 5  : kd = "S000000" + j; break;
					case 6  : kd = "S00000" + j; break;
					case 7  : kd = "S0000" + j; break;
					case 8  : kd = "S000" + j; break;
					case 9  : kd = "S00" + j; break;
					case 10 : kd = "S0" + j; break;
					case 11 : kd = "S" + j; break;
				}
			}else{
				kd = "S00000000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	
	public String getIDKitting() {
		try{
			String head = "KI-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select kitting_no from kitting order by kitting_no desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	public String getIDBundling() {
		try{
			String head = "BU-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select bundle_code from bundle order by bundle_code desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	public String getIDPacking() {
		try{
			String head = "PA-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select packing_no from packing order by packing_no desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	
	public String getIDConsolidate() {
		try{
			String head = "CO-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select consolidate_no from consolidate order by consolidate_no desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	
	public String getIDDelivery() {
		try{
			String head = "DO-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select delivery_no from deliveryorder order by delivery_no desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
	
	public String getIDReplenish() {
		try{
			String head = "RE-";
			conn = koneksi.openConnection();
			st = conn.createStatement();
			String SQL = "select replenish_no from replenishment order by replenish_no desc";
			ResultSet rs = st.executeQuery(SQL);
			if(rs.next()){
				kd = rs.getString(1);
				kd = kd.substring(7);
				i  = Integer.parseInt(kd)+1;
				j  = String.valueOf(i);
				h  = j.length();
				
				switch (h){          
				
					case 1  : kd = head+year+"0000000" + j; break;
					case 2  : kd = head+year+"000000" + j; break;
					case 3  : kd = head+year+"00000" + j; break;
					case 4  : kd = head+year+"0000" + j; break;
					case 5  : kd = head+year+"000" + j; break;
					case 6  : kd = head+year+"00" + j; break;
					case 7  : kd = head+year+"0" + j; break;
				}
			}else{
				kd = head+year+"00000001";
			}
			
			rs.close(); st.close(); conn.close();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
		return kd;
	}
        
        public String getCDCode(String head, String whCode) {
            try {
                HashMap map = new HashMap();
                map.put("GRCR-", "grcd,gr_code");
                map.put("PUCR-", "pacd,pa_code");
                map.put("CDCR-", "crossdock,cd_code");
                map.put("PICR-", "pcd,p_code");
                map.put("DOCR-", "docd,do_code");
                map.put("QRCR-", "quarantine,q_code");
                
                String[] table = map.get(head).toString().split(",");
                conn = koneksi.openConnection();
                st = conn.createStatement();
                String SQL = "SELECT " + table[1]  + " FROM " + table[0] + " ORDER BY " + table[1] + " DESC";
                ResultSet rs = st.executeQuery(SQL);
                
                if(rs.next()){
                    String code = rs.getString(table[1]).substring(13);
                    int num = Integer.parseInt(code) + 1;
                    code = String.valueOf(num);
                    while(code.length() != 7) {
                        code = "0" + code;
                    }
                    return head + whCode.substring(3) + year + code;
                } else {
                    return head + whCode.substring(3) + year +  "0000001";
                }
            } catch(Exception ex) {
                System.out.println(ex);
                return null;
            }
        }

}
