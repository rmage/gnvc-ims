package com.app.wms.engine.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author JECO
 * @since July 08, 2010
 */
public class XMLUtils {

    public static final String CONTENT_TYPE = "text/xml; charset=UTF-8";
    public static final String CONNECTION_MODE = "keep-alive";
    public static final String URI_PROTOCOL = "http";
    public static final String URI_AUTH = null;
    public static final String URI_FRAGMENT = null;

    public Document createDocument() {

        Document dom = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
        } catch (ParserConfigurationException pce) {
            System.out.println("Error while trying to instantiate DocumentBuilder: \n" + pce.getMessage());
        }
        return dom;
    }

    public InputStream getInputStreamFromUrl(String url) throws Exception {
        InputStream result = null;
        HttpURLConnection http = null;
        URL u = null;
        try {
            u = new URL(url);
            http = (HttpURLConnection) u.openConnection();
            result = http.getInputStream();
        } catch (Exception ex) {
            throw new Exception("Error while trying to get InputStream from URL(" + url + "): \n" + ex.getMessage());
        } finally {
            if (http != null) {
                http.disconnect();
            }
            http = null;
            u = null;
        }
        return result;
    }

    public InputStream getInputStreamFromFile(String filePath) throws Exception {
        InputStream result = null;
        try {
            result = new FileInputStream(new File(filePath));
        } catch (Exception ex) {
            throw new Exception("Error while trying to get InputStream from Local URI(" + filePath + "): \n" + ex.getMessage());
        }
        return result;
    }

    /**
     * XMLString: <?xml version.....> XML String to be sent to the url
     * url: http://www.example.com/url-for-xml-rpc
     * httpMethod: POST, GET
     * encodeType: UTF-8
     */
    public String sendXMLRPCRequest(String XMLString, String url, String httpMethod, String encodeType) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        OutputStreamWriter wout = null;
        URL u = null;
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(httpMethod);
            out = connection.getOutputStream();
            wout = new OutputStreamWriter(out, encodeType);
            wout.write(XMLString);
            wout.flush();
            in = connection.getInputStream();
            if (in != null) {
                String line;
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, encodeType));
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } finally {
                }
            } else {
                System.out.println("InputStream = null");
            }

        } catch (Exception ex) {
            throw new Exception("Error while trying to sendXMLRPCRequest[url=" + url + ", httpMethod=" + httpMethod + ", encodeType=" + encodeType + "]: \n" + ex.getMessage());
        } finally {
            if (wout != null) {
                wout.close();
            }
            if (out != null) {
                out.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
            u = null;
        }
        return sb.toString();
    }

    /**
     * requestParameter: XML=<?xml version.....>
     * url: //10.64.1.62:4848/  <--- REMEMBER, URL should not contain "http:"
     * httpMethod: POST, GET
     * encodeType: UTF-8
     */
    public String sendRequestParameter(String requestParameter, String url, String httpMethod, String encodeType) throws Exception {
        InputStream in = null;
        URI uri = null;
        URL u = null;
        HttpURLConnection con = null;
        StringBuilder sb = new StringBuilder();
        try {
            uri = new URI(URI_PROTOCOL, URI_AUTH, url, requestParameter, URI_FRAGMENT);
            u = uri.toURL();
            System.out.println("url sent:" + uri.getQuery());
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod(httpMethod);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", CONTENT_TYPE);
            con.setRequestProperty("Connection", CONNECTION_MODE);
            System.out.println("SEND REQUEST - URL:" + u.toString());
            System.out.println("RESPONSE: " + con.getResponseCode() + " - " + con.getResponseMessage());
            in = con.getInputStream();
            if (in != null) {
                String line;

                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, encodeType));
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } finally {
                }
            } else {
                System.out.println("InputStream = null");
            }
        } catch (Exception ex) {
            throw new Exception("Error while trying to sendRequestParameter[url=" + url + ", requestParameter=" + requestParameter + ", httpMethod=" + httpMethod + ", encodeType=" + encodeType + "]: \n" + ex.getMessage());
        } finally {
            if (con != null) {
                con.disconnect();
            }
            u = null;
            if (in != null) {
                in.close();
            }
            in = null;
        }
        return sb.toString();
    }

    public Document parseXmlToDocument(InputStream input) throws Exception {

        Document result = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            result = db.parse(input);
        } catch (ParserConfigurationException pce) {
            throw new Exception("ParserConfigurationException while trying to parse XML file to Document from given InputStream: \n" + pce.getMessage());
        } catch (SAXException se) {
            throw new Exception("SAXException while trying to parse XML file to Document from given InputStream: \n" + se.getMessage());
        } catch (IOException ioe) {
            throw new Exception("IOException while trying to parse XML file to Document from given InputStream: \n" + ioe.getMessage());
        }
        return result;
    }

    public Properties createProperties(InputStream input) throws Exception {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(input);
        } catch (Exception ex) {
            throw new Exception("Error while trying to parse XML InputStream to Properties from given InputStream: \n" + ex.getMessage());
        }
        return properties;
    }

    public String parseDocumentToXMLString(Document xmlDocument) throws Exception {

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        //create string from xml tree
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(xmlDocument);
        transformer.transform(source, result);
        return sw.toString();
    }

    public Document parseStringToXMLDocument(String xmlString) throws Exception {

        // Prepare Document Builder
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //  Parse XML String to XML Document
        try {
            System.out.println("START PARSING STRING TO XML DOCUMENT");
            doc = builder.parse(new InputSource(new StringReader(xmlString)));
            System.out.println("FINISH PARSING STRING TO XML DOCUMENT");
        } catch (Exception ex) {
            throw new Exception("Error while trying to parse XML String to XML Document using DOcumentBuilder: \n" + ex.getMessage());
        }
        return doc;
    }
}
