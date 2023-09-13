package view;

import com.view.utils.JSFUtils;
import com.oschrenk.io.Base64;

import oracle.adf.share.ADFContext;

//import com.sun.jersey.api.client.Client;




import org.json.JSONException;
import org.json.JSONObject;


import oracle.adf.share.ADFContext;

public class PayrollHome {
    public PayrollHome() {
        super();
    }
    public String checkUser() throws JSONException {
        
        //     ADFContext.getCurrent().getSessionScope().put("userName","4iapps");
             // ADFContext.getCurrent().getSessionScope().put("userName","Benoy");
              //
              //                                     //    code to be included here for data restriction.
              ////                                         ADFContext.getCurrent().getSessionScope().put("userName","Benoy");
              ////
              ////
        //      String  outputString = "validUser";
               //ADFContext.getCurrent().getPageFlowScope().put("val","validUser");
               
               
              
//                    Client client =  Client.create();

       
    //
    //
    //               System.err.println("JWT tokens:=====>>> "+ADFContext.getCurrent().getPageFlowScope().get("tokens").toString());
    //                URL url;
    //                URLConnection connection;
    //                HttpURLConnection httpConn;
               /**
                * PROD
//                */
                String outputString = "";
                    String jwt =
                        ADFContext.getCurrent().getPageFlowScope().get("tokens").toString();
                System.out.println("Enter check");
 //               String jwt="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6Ik1OM2phUU1TZTNVQ3F4R1hSX2NZdHVTaWtEUSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1NzM3MjkwMzcsInN1YiI6IjRpYXBwcyIsImlzcyI6Ind3dy5vcmFjbGUuY29tIiwicHJuIjoiNGlhcHBzIiwiaWF0IjoxNTczNzE0NjM3fQ.UzkAH6q5-_KqgLU0NYaAL09m8VFcEz87tpGAJSwnv_VOZjHVz4Fn3sPa9mp8l2LSvBHng_LYLMT_Ogve8YLLFI7yMxrw_nWMNJTNw4aLlXftQ2Yv43ZaDa5Wx6GS6Al9gUqNl_hJYGwBepacTvBggrCZPX0qht-qC1O9IfKdxYVUw_peO9qBidufqmtf9JnemllDzCtm0kLmqhOGA3ZPCAzFnX5WvYSbdwCO_IioQxGc0VerPcG7ZSqHvUZHhmJHEIhEtCMf9h3KGQl7VFmxKeqJnROs7MDUIlyUs2nZz0JfU51Xyp_-5MuOTSgTHVS7nnNoOOidwovha9daVkRWHQ";
  //              String jwt="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsIng1dCI6Ik1OM2phUU1TZTNVQ3F4R1hSX2NZdHVTaWtEUSIsImtpZCI6InRydXN0c2VydmljZSJ9.eyJleHAiOjE1NzQwNzIyNzksInN1YiI6IjRpYXBwcyIsImlzcyI6Ind3dy5vcmFjbGUuY29tIiwicHJuIjoiNGlhcHBzIiwiaWF0IjoxNTc0MDU3ODc5fQ.HwdeHrBYw_yWvBW1xJ_e6y8UvSRYLwPdzo5dE7ROXuY7ABwI9zEZBLJDJqVJujx_mdsITsBpTFDmgGYqxpNbymil1xcQJaZelWILVutHlX53G3HRa0twbCEtSQVPagL2xSRbb83q6TUeseOkSpnnF5noap88VM_FQQ2DsuXK2ijJMwJDbBcDejmlWtAXdT5d0LnPnnJYFLHVJ10rCmvDlFJyGntVYi7ud2ncn-9OZU-GgH_gJgkpGhJv7pxBNvc3-J2CIMZG-6Rei6DLkl4mN_blWSwgbLZTybGa2Q6lqyZQaBDfLn8Babs3wTRG_WuagohwMUp7C2_aYltsng4JLA";

              System.err.println("JWT---- " + jwt);
              String inputEncodedText =jwt;
//                  String inputEncodedText =
//                                 ADFContext.getCurrent().getPageFlowScope().get("tokens").toString();
               ADFContext.getCurrent().getPageFlowScope().put("val",jwt);
                          try {    
                  
                        outputString = "validUser";
                        ADFContext.getCurrent().getPageFlowScope().put("val","validUser"); 
             
                    if (outputString.equalsIgnoreCase("validUser")) {
              
                        String[] xIn = inputEncodedText.split("\\.");
                        byte c[] = Base64.decode(xIn[1]);
                        String tempPass = new String(c);
                        int chkOccurance = tempPass.lastIndexOf("}");
                        if (chkOccurance < 1) {
                          tempPass += "}";
                        }
                        JSONObject jo = new JSONObject(tempPass);
                    
                        long millis = System.currentTimeMillis();
                        long iatVal = Long.parseLong(jo.get("iat").toString()) * 1000;
                        long expVal = Long.parseLong(jo.get("exp").toString()) * 1000;
                        if (millis >= iatVal && millis <= expVal) {
              

                          ADFContext.getCurrent().getSessionScope().put("userName", jo.getString("prn"));
                          //code to be included here for data restriction.
                            outputString = "validUser";
                            ADFContext.getCurrent().getPageFlowScope().put("val","validUser"); 
                 
                        }
                          else {
                                            outputString = "invalidUser";
                                            ADFContext.getCurrent().getPageFlowScope().put("val","invalidUser");
                                        }
                      }
                           else {
                               outputString = "invalidUser";
                               ADFContext.getCurrent().getPageFlowScope().put("val","invalidUser");
                       }
               
                       }catch (Exception e) {
                   JSFUtils.addFacesInformationMessage("Token Error: "+e.toString());
//                               
                }
 //        System.err.println("output string "+outputString);
         
              //ADFContext.getCurrent().getSessionScope().put("userName","4iapps");
            // return "validUser";
                return outputString;
          }
}
