package com.mtlckj.base.system.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import fr.opensagres.poi.xwpf.converter.core.BasicURIResolver;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;




public class Doc2html {
	public static String docToHtml(InputStream file) throws Exception {
	    File path = new File(ResourceUtils.getURL("classpath:").getPath());
	    String imagePathStr = path.getAbsolutePath() + "\\static\\image\\";
	    String sourceFileName = path.getAbsolutePath() + "\\static\\test.doc";
	    String targetFileName = path.getAbsolutePath() + "\\static\\test2.html";
	    String htmlString="";
	    File imageFilePath = new File(imagePathStr);
	    if(!imageFilePath.exists()) {
	    	imageFilePath.mkdirs();
	    }
	    HWPFDocument wordDocument = new HWPFDocument(file);
	    org.w3c.dom.Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
	    WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
	    //保存图片，并返回图片的相对路径
	    wordToHtmlConverter.setPicturesManager((content, pictureType, name, width, height) -> {
	        try (FileOutputStream out = new FileOutputStream(imagePathStr + name)) {
	            out.write(content);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "image/" + name;
	    });
	    wordToHtmlConverter.processDocument(wordDocument);
	    org.w3c.dom.Document htmlDocument = wordToHtmlConverter.getDocument();
	    DOMSource domSource = new DOMSource(htmlDocument);
	    StringWriter stringWriter=new StringWriter();
	    StreamResult streamResult = new StreamResult(stringWriter);
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer serializer = tf.newTransformer();
	    serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	    serializer.setOutputProperty(OutputKeys.METHOD, "html");
	    serializer.transform(domSource, streamResult);
	    System.out.println(streamResult.getWriter().toString());
	    return targetFileName;
	}
	
	public static String docxToHtml(InputStream file) throws Exception {
	    File path = new File(ResourceUtils.getURL("classpath:").getPath());
	    String imagePath = path.getAbsolutePath() + "\\static\\image";
	    String sourceFileName = path.getAbsolutePath() + "\\static\\test.docx";
	    String targetFileName = "D:\\text.html";
	    String htmlString="";
	    
	    StringWriter stringWriter = null;
	    FileOutputStream outp=null;
	    try {
	        XWPFDocument document = new XWPFDocument(file);
	        XHTMLOptions options = XHTMLOptions.create().indent(4);
	        // 存放图片的文件夹
	        options.setExtractor(new FileImageExtractor(new File(imagePath)));
	        options.setIgnoreStylesIfUnused(false);
	        options.setFragment(true);
	        // html中图片的路径
	        options.URIResolver(new BasicURIResolver("image"));
	        stringWriter = new StringWriter();
	        outp=new FileOutputStream(targetFileName);
	        XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
	        xhtmlConverter.convert(document, outp, options);
	    } finally {
	        if (stringWriter != null) {
	        	stringWriter.close();
	        	outp.close();
	        }
	    }
	    System.out.println(stringWriter.getBuffer().toString());
	    return targetFileName;
	}
}
