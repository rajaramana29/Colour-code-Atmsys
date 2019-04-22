package com.digitalpersona.onetouch.ui.swing.Enrollment;

import com.atm.ChooseTransactionType;
import com.atm.FingerPrintVerification;
import com.atm.User;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import com.util.ATMSession;

import java.awt.*;

public class VerificationForm extends CaptureForm
{
	private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
	private DPFPTemplate template;
	private int count=0;
	private int tcount=10;
	public VerificationForm(Frame owner, DPFPTemplate t) {
		
		super(owner);
		this.template = t;
	}
	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Fingerprint Enrollment");
		updateStatus(0);
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);

		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

		// Check quality of the sample and start verification if it's good
		if (features != null)
		{
			// Compare the feature set with our template
			DPFPVerificationResult result = 
				verificator.verify(features, this.template);
			updateStatus(result.getFalseAcceptRate());
			if (result.isVerified()){
				makeReport("The fingerprint was VERIFIED.");
				stop();
				//ATMSession.getInstance().setItem("logedinusernamefpverified","yes");
				ChooseTransactionType log=new ChooseTransactionType();
				
				Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
				log.setLocation(0,0);
				log.setSize(dim.width,dim.height);
				log.setTitle("Choose Transaction type");
				log.setResizable(false);
				log.setVisible(true);
				dispose();
			}
			else{
				++count;
				
				makeReport("The fingerprint was NOT VERIFIED.Attempt Left is:"+(tcount-count));
				if(count==tcount){
					ATMSession.getInstance().cleanUp();
					stop();
					User log=new User();
					Dimension dim= Toolkit.getDefaultToolkit().getScreenSize();
					log.setLocation(0,0);
					log.setSize(dim.width,dim.height);
					log.setTitle("User-Login");
					log.setResizable(false);
					log.setVisible(true);
					dispose();
				}
			}
		}
	}
	
	private void updateStatus(int FAR)
	{
		// Show "False accept rate" value
		setStatus(String.format("False Accept Rate (FAR) = %1$s", FAR));
	}

}
