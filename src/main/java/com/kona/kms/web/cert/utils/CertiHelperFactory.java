package com.kona.kms.web.cert.utils;

import com.kona.kms.web.cert.BrandType;
import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.cert.utils.formatter.AmexFormatter;
import com.kona.kms.web.cert.utils.formatter.DinersFormatter;
import com.kona.kms.web.cert.utils.formatter.JcbFormatter;
import com.kona.kms.web.cert.utils.formatter.MasterFormatter;
import com.kona.kms.web.cert.utils.formatter.MasterLocalFormatter;
import com.kona.kms.web.cert.utils.formatter.PbocFormatter;
import com.kona.kms.web.cert.utils.formatter.VisaFormatter;
import com.kona.kms.web.cert.utils.formatter.VisaLocalFormatter;
import com.kona.kms.web.cert.utils.parser.AmexParser;
import com.kona.kms.web.cert.utils.parser.DinersParser;
import com.kona.kms.web.cert.utils.parser.JcbParser;
import com.kona.kms.web.cert.utils.parser.MasterLocalParser;
import com.kona.kms.web.cert.utils.parser.MasterParser;
import com.kona.kms.web.cert.utils.parser.PbocParser;
import com.kona.kms.web.cert.utils.parser.VisaLocalParser;
import com.kona.kms.web.cert.utils.parser.VisaParser;


public class CertiHelperFactory {

	private static CertiHelperFactory m_instance;
	
	public static CertiHelperFactory getInstance() {
		if (m_instance == null) {
			m_instance = new CertiHelperFactory();
		}
		return m_instance;
	}
	
	private CertiHelperFactory() {
		
	}
	
	public ICertiReqFormatter getCertiFormatter(CertificateModel model){
		
		switch(model.getBrandType()){
		case VISA: return new VisaFormatter(model);
		case MASTER: return new MasterFormatter(model);
		case JCB: return new JcbFormatter(model);
		case VISA_LOCAL: return new VisaLocalFormatter(model);
		case MASTER_LOCAL: return new MasterLocalFormatter(model);
		case AMEX: return new AmexFormatter(model);
		case DINERS: return new DinersFormatter(model);
		case PBOC: return new PbocFormatter(model);
		
		default: return null;
		}	
	}
	
	public ICertiParser getCertiParser(CertificateModel model){
		
		switch(model.getBrandType()){
		case VISA: return new VisaParser(model);
		case MASTER: return new MasterParser(model);
		case JCB: return new JcbParser(model);
		case VISA_LOCAL: return new VisaLocalParser(model);
		case MASTER_LOCAL: return new MasterLocalParser(model);
		case AMEX: return new AmexParser(model);
		case DINERS: return new DinersParser(model);
		case PBOC: return new PbocParser(model);
		
		default: return null;
		}	
	}
}
