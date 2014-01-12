/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.servlet;

import com.app.wms.engine.db.dao.spring.PoCSVDao;
import com.app.wms.hbm.bean.*;
import com.app.wms.web.util.FileUploadListener;
import com.app.wms.web.util.Utility;
import com.app.wms.hbm.bean.Pocsv;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @gnv solution
 */
public class UploadServlet extends HttpServlet {
    
    private Logger logger = Logger.getLogger(UploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        FileUploadListener listener = null;
        StringBuffer buffy = new StringBuffer();
        long bytesRead = 0, contentLength = 0;
        
        String hash = "";
        
        if (session == null) {
            return;
        } else if (session != null) {
            listener = (FileUploadListener) session.getAttribute("LISTENER");
            hash = session.getAttribute("hash")+"";
                    
            if (listener == null) {
                return;
            } else {
                bytesRead = listener.getBytesRead();
                contentLength = listener.getContentLength();
            }
        }

        response.setContentType("text/xml");

        buffy.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
        buffy.append("<response>\n");
        buffy.append("\t<bytes_read>" + bytesRead + "</bytes_read>\n");
        buffy.append("\t<content_length>"
                + contentLength + "</content_length>\n");

        if (bytesRead == contentLength) {
            buffy.append("\t<finished />\n");
            buffy.append("\t<hash>"+hash+"</hash>\n");
            session.setAttribute("LISTENER", null);
        } else {
            long percentComplete = ((100 * bytesRead) / contentLength);
            buffy.append("\t<percent_complete>"
                    + percentComplete + "</percent_complete>\n");
        }
        buffy.append("</response>\n");
        out.println(buffy.toString());
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String hash = Utility.generateRandomHash();
        
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        FileUploadListener listener = new FileUploadListener();
        HttpSession session = request.getSession();
        session.setAttribute("LISTENER", listener);
        session.setAttribute("hash", hash);
        upload.setProgressListener(listener);
        List uploadedItems = null;
        FileItem fileItem = null;
                
        String filePath = getServletContext().getRealPath("/") + "upload/";
        
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        PoCSVDao poCSVDao = (PoCSVDao)context.getBean("poCSVDao");

        try {
            File baseFile = new File(filePath);
            if(!baseFile.exists())
                baseFile.mkdir();
            
            uploadedItems = upload.parseRequest(request);
            Iterator i = uploadedItems.iterator();

            while (i.hasNext()) {
                fileItem = (FileItem) i.next();
                if (fileItem.isFormField() == false) {
                    if (fileItem.getSize() > 0) {
                        File uploadedFile = null;
                        String myFullFileName = fileItem.getName(),
                                myFileName = "", slashType =
                                (myFullFileName.lastIndexOf("\\") > 0) ? "\\" : "/";
                        int startIndex = myFullFileName.lastIndexOf(slashType);
                        myFileName = myFullFileName.substring(startIndex
                                + 1, myFullFileName.length());
                        uploadedFile = new File(filePath, hash+"."+myFileName);
                        fileItem.write(uploadedFile);
                        System.out.println("writing to "+uploadedFile.getAbsolutePath());
                        System.out.println("uuid is "+hash);
                        
                        Scanner scanner = new Scanner(uploadedFile);
                        List<Pocsv> pocsvs = new ArrayList<Pocsv>();                        
                        while (scanner.hasNextLine()) {                                  
                            String nextLine = scanner.nextLine(); // CO-LSGLLB,Liquid Lipstick Blind Date,10
                            String[] pos = nextLine.split(",", -1);
                            
                            Pocsv pocsv = new Pocsv(); 
                            pocsv.setHash(hash);
                            pocsv.setProductCode(pos[0]);
                            pocsv.setProductName(pos[1]);
                            pocsv.setQty(Integer.parseInt(pos[2]));                            
                            
                            pocsvs.add(pocsv);
                        }
                        
                        poCSVDao.save(pocsvs);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }

    }
}
