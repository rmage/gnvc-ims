/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.wms.web.servlet;

import com.app.wms.engine.db.dao.ProductDao;
import com.app.wms.engine.db.dao.spring.ProductDaoImpl;
import com.app.wms.engine.db.dto.Product;
import com.app.wms.engine.db.dto.map.LoginUser;
import com.app.wms.engine.db.factory.DaoFactory;
import com.app.wms.web.util.FileUploadListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
 *  untuk mengupload product via csv
 *  format : 
 *      PR001, Dodol, Makanan
 *      PR002, Keong, Binatang
 *  TANPA HEADER
 * 
 * @gnv solution
 */
public class UploadProductServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        FileUploadListener listener = null;
        StringBuffer buffy = new StringBuffer();
        long bytesRead = 0, contentLength = 0;

        if (session == null) {
            return;
        } else if (session != null) {
            listener = (FileUploadListener) session.getAttribute("LISTENER");

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

        LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
        
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        FileUploadListener listener = new FileUploadListener();
        HttpSession session = request.getSession();
        session.setAttribute("LISTENER", listener);
        upload.setProgressListener(listener);
        List uploadedItems = null;
        FileItem fileItem = null;

        String filePath = getServletContext().getRealPath("/") + "productUpload/";

        ProductDao productDaoImpl = DaoFactory.createProductDao();

        try {
            File baseFile = new File(filePath);
            if (!baseFile.exists()) {
                baseFile.mkdir();
            }

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
                        uploadedFile = new File(filePath, new Date().getTime() + "." + myFileName);
                        fileItem.write(uploadedFile);
                        logger.debug("writing to " + uploadedFile.getAbsolutePath());

                        Scanner scanner = new Scanner(uploadedFile);
                        Product product = null;
                        while (scanner.hasNextLine()) {
                            String nextLine = scanner.nextLine(); 
                            String[] pos = nextLine.split(",", -1);
                            
                            product = new Product();
                            
                            // barcode,productcode,productname,productdescription,productcategory,brandname,producttype,
                            //productcolor,volweight,unitweight,volmetrix,unitmetrix,unit_piece, unit_box, unit_carton, unit_pallete
                            
                            product.setBarCode(pos[0].trim());
                            product.setProductCode(pos[1].trim());
                            product.setProductName(pos[2].trim());
                            product.setProductDescription(pos[3].trim());
                            product.setProductCategory(pos[4].trim());
                            product.setBrandName(pos[5].trim());
                            product.setProductType(pos[6].trim());
                            product.setProductColor(pos[7].trim());
                            product.setVolumeWeight(pos[8].trim());
                            product.setUnitWeight(pos[9].trim());
                            product.setVolumeMatrix(pos[10].trim());
                            product.setUnitMatrix(pos[11].trim());
                            product.setUnitPiece(Integer.parseInt(pos[12].trim()));
                            product.setUnitBox(Integer.parseInt(pos[13].trim()));
                            product.setUnitCartoon(Integer.parseInt(pos[14].trim()));
                            product.setUnitPallete(Integer.parseInt(pos[15].trim()));
                            product.setCreatedBy(lu.getUsername());
                            product.setUserId(lu.getUserId());
                            product.setCorpId(lu.getCorpId());
                            product.setWhCode(lu.getWhCode());
                            product.setUpdatedDate(new Date());
                            product.setCreatedDate(new Date());

                            productDaoImpl.insert(product);
                        }                        
                    }
                }
            }
        } catch (FileUploadException e) {
            logger.error(e,e);
        } catch (Exception e) {
            logger.error(e,e);
        }

    }
}
