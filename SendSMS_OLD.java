package com.util;

/*import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

public class SendSMS {

      public static void main(String[] args) throws Exception {
          AuthMethod auth = new TokenAuthMethod("48af4766","e19a53f58ff19151");
          NexmoClient client = new NexmoClient(auth);

          TextMessage message = new TextMessage("+919884723870", "+919884723870", "Hello from Binil!");

          //There may be more than one response if the SMS sent is more than 160 characters.
          SmsSubmissionResult[] responses = client.getSmsClient().submitMessage(message);
            for (SmsSubmissionResult response : responses) {
            System.out.println(response);
          }
      }
  }*/


import java.net.*;
import java.util.Date;

public class SendSMS_OLD {

        public static void main(String[] args) {
                try {
                     
                 String recipient = "9207614823";
                 String message = "From SBI ATM Service,Your OTP Code 1234";
                 String username = "ish8ishwarya@gmail.com:achu12597";
                 String senderid = "TEST SMS";
                // String originator = "06201234567";
                 //String url = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=biniljmg@gmail.com:111111&senderID=TEST SMS&receipientno=9884723870&dcs=0&msgtxt=This is Test message&state=4 ";
                 String shifanaurl = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=shifaant95@gmail.com:123456&senderID=TEST SMS&receipientno=9846337695&dcs=0&msgtxt=This is Test message&state=4"; 
                String requestUrl  = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=" + 
                		URLEncoder.encode(username, "UTF-8") + "&senderID=" +
                		URLEncoder.encode(senderid, "UTF-8") + "&receipientno="+
                		URLEncoder.encode(recipient, "UTF-8") + "&dcs=0&msgtxt='" +
                		URLEncoder.encode(message, "UTF-8") + "'&state=4";
                		

                		URL url = new URL(requestUrl);
                       
                        System.out.println(url);
                        HttpURLConnection uc = (HttpURLConnection)url.openConnection();

                        System.out.println(uc.getResponseMessage());

                        uc.disconnect();

                } catch(Exception ex) {
                        System.out.println(ex.getMessage());

                }
        }
        public static boolean sendOTP(String reciepeintno,String OTP){
        	boolean status= false;
        	System.out.println("Reciept no is:"+reciepeintno);
        	System.out.println("OTP is:"+OTP);
        	//Uncomment below code before delivery the code
          /* 
        	try {
         // String  requestUrl= "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=ish8ishwarya@gmail.com:achu12597&senderID=TEST SMS&receipientno=9884723870&dcs=0&msgtxt='From SBI ATM Service,Your OTP Code 1234'&state=4";
          String recipient = reciepeintno;
          String message = "From ATM Service,Your OTP Code is: "+OTP;
          String username = "ish8ishwarya@gmail.com:achu12597";
          String senderid = "TEST SMS";
          String requestUrl  = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=" + 
         		URLEncoder.encode(username, "UTF-8") + "&senderID=" +
         		URLEncoder.encode(senderid, "UTF-8") + "&receipientno="+
         		URLEncoder.encode(recipient, "UTF-8") + "&dcs=0&msgtxt='" +
         		URLEncoder.encode(message, "UTF-8") + "'&state=4";
         		

         		URL url = new URL(requestUrl);
                
                 System.out.println(url);
                 HttpURLConnection uc = (HttpURLConnection)url.openConnection();

                 System.out.println(uc.getResponseMessage());
                 if("OK".equalsIgnoreCase(uc.getResponseMessage())){
                	 status = true; 
                 }
                 uc.disconnect();
                // status = 
         } catch(Exception ex) {
                 System.out.println(ex.getMessage());

         }

        	return status;*/
        		return true;
        }

}
