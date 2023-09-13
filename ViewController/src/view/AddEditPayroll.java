package view;

import com.view.utils.ADFUtils;
import com.view.utils.JSFUtils;

import com.view.utils.RTFUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.OperationBinding;



import oracle.jbo.ViewObject;

public class AddEditPayroll {
    public AddEditPayroll() {
    }

    public void onClickSave(ActionEvent actionEvent) {
        ViewObject HdrVO = ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        String Status =
            HdrVO.getCurrentRow().getAttribute("ApprovalStatus") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("ApprovalStatus").toString();
        if(Status.equals("DRAFT")){
            HdrVO.getCurrentRow().setAttribute("ApprovalStatus","Draft");
        }
        
        ADFUtils.findOperation("Commit").execute();
                    JSFUtils.addFacesInformationMessage("Information Saved Successfully");
    }

    public void onClickCancel(ActionEvent actionEvent) {
        ViewObject HdrVO = ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        HdrVO.applyViewCriteria(null);
        HdrVO.executeQuery();
        OperationBinding binding = (OperationBinding) ADFUtils.findOperation("Rollback").execute();
    }

    public void onClickRefresh(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void onClickExportExcel(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void onClickSubmit(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject HdrVO =
                   ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        HdrVO.getCurrentRow().setAttribute("ApprovalStatus","PROCESSED");
        ADFUtils.findOperation("Commit").execute();
    }

    public void onClickGenerate(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject HdrVO =
                   ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        BigDecimal bu_id =(BigDecimal)HdrVO.getCurrentRow().getAttribute("BusinessUnitId");
           
        String date=    
            HdrVO.getCurrentRow().getAttribute("PeriodName").toString().toUpperCase();
        OperationBinding binding = (OperationBinding) ADFUtils.findOperation("callingReportPkg");
        binding.getParamsMap().put("buid", bu_id);
        binding.getParamsMap().put("date", date);
       String returnVal = (String) binding.execute();
        if(returnVal.equalsIgnoreCase("success"))
        {
                JSFUtils.addFacesInformationMessage("Data submitted to SaaS");

//                ViewObject HdrVO =
//                           ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
                HdrVO.getCurrentRow().setAttribute("ApprovalStatus","Submitted to SaaS");
                ADFUtils.findOperation("Commit").execute();
            }
        else {
//            JSFUtils.addFacesInformationMessage("E");
            System.err.println("Error calling report"
                               );

        }



    }

    public void onClickPayrollReport(FacesContext facesContext, OutputStream outputStream) throws ParseException {
        try {
                  ViewObject HdrVO =
                             ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        BigDecimal bu_id =(BigDecimal)HdrVO.getCurrentRow().getAttribute("BusinessUnitId"); 
        String date=    
            HdrVO.getCurrentRow().getAttribute("PeriodName").toString().toUpperCase();
        System.err.println(date +"--date PeriodName");
        Object result=RTFUtils.runReport("//reports//OTL_Payroll_Report.rtf", bu_id,date, "OTL_PAYROLL");
        outputStream.write((byte[])result);
        } 
 catch (IOException e) {
        System.out.println("Exception " + e);
        } 
        }

    public void onClickEmployeeBasedPayrollReport(FacesContext facesContext, OutputStream outputStream) throws ParseException {
        try {
                  ViewObject HdrVO =
                             ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        BigDecimal bu_id =(BigDecimal)HdrVO.getCurrentRow().getAttribute("BusinessUnitId"); 
        String date=    
            HdrVO.getCurrentRow().getAttribute("PeriodName").toString().toUpperCase();
        System.err.println(date +"--date PeriodName");
        Object result=RTFUtils.runReport("//reports//OTL_Attendance_Report.rtf", bu_id,date, "OTL_ATTENDANCE");
        outputStream.write((byte[])result);
        } 
    catch (IOException e) {
        System.out.println("Exception " + e);
        } 
        }

}
