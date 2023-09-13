package com.view.utils;
    import java.io.ByteArrayInputStream;
    import java.io.ByteArrayOutputStream;
    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import javax.faces.context.FacesContext;
    import javax.naming.Context;
    import javax.naming.InitialContext;
    import javax.servlet.ServletContext;
    import javax.servlet.http.HttpServletResponse;
    import javax.sql.DataSource;
    import oracle.apps.xdo.template.FOProcessor;
    import oracle.apps.xdo.template.RTFProcessor;
public class RTFUtils {
    public RTFUtils() {
        super();
    }
   

     


        
        

     

        
        
        public static byte[] runReport(String templateFile, BigDecimal ID,String Date, String packageName) {

     
System.out.println("run report");
        System.out.println("templateFile"+templateFile.toString());     
          byte[] dataBytes = null;
        try {

     

             ServletContext context = getContext();

     

      InputStream fs = context.getResourceAsStream(templateFile);

     System.out.println(fs +"---------------");

     //Process RTF template to convert to XSL-FO format
      System.err.println("====1====");
    RTFProcessor rtfp = new RTFProcessor(fs);
     ByteArrayOutputStream xslOutStream = new ByteArrayOutputStream();
    rtfp.setOutput(xslOutStream);
     System.err.println("====before process====");
     rtfp.process();
            System.err.println("====2====");
     //Use XSL Template and Data from the VO to generate report and return the OutputStream of report
            ByteArrayInputStream xslInStream = new ByteArrayInputStream(xslOutStream.toByteArray());
    FOProcessor processor = new FOProcessor();
    ByteArrayInputStream dataStream =new ByteArrayInputStream((getXMLData(ID,Date, packageName).getBytes()));
     processor.setData(dataStream);
     processor.setTemplate(xslInStream);
            
     ByteArrayOutputStream pdfOutStream = new ByteArrayOutputStream();
            System.out.println("111111111");
     processor.setOutput(pdfOutStream);
            System.out.println("222222222222");
            
         byte outFileTypeByte=FOProcessor.FORMAT_PDF;  
    
     processor.setOutputFormat(outFileTypeByte);
            
         System.out.println(pdfOutStream.toByteArray()+"---------->pdfOutStream");
     processor.generate();
            
     dataBytes = pdfOutStream.toByteArray();
     } catch (IOException e) {
     System.out.println("IOException when generating pdf===IO" +e.toString());
    } catch (Exception e) {
     System.out.println("IOException when generating pdf===EXE" +e.toString());
     }
    System.out.println("dataBytes==>"+dataBytes);
    System.out.println("OUT run ");
    return dataBytes;
    }
     public static String getXMLData(BigDecimal ID,String Date, String packageName) throws Exception {
    
    System.out.println("Entered XML data");
     String retStr = "";
    Context ctx;
    Connection con = null;
    ctx = new InitialContext();
    String dataSource = "otl";
    DataSource ds = (DataSource)ctx.lookup(dataSource);
    con = ds.getConnection(); 
     String selectSQL ="SELECT XXSC_OTL_REPORT_PKG."+packageName+"(" + ID +"," +"'"+ Date +"'"+") xml FROM dual";
    PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
     System.err.println("=====OUT XML==" + selectSQL);
     ResultSet rs1 = preparedStatement.executeQuery(selectSQL);
    while (rs1.next()) {
    retStr = rs1.getString("xml");
    }
    System.err.println("=====OUT XML==" + retStr);
    return retStr;
     }
        
        public static ServletContext getContext() {
    return (ServletContext)getFacesContext().getExternalContext().getContext();
        }
        public static FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
        } 
        
        
    
}
