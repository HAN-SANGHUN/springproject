package com.kona.kms.crypto;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kona.kms.crypto.mech.MechPkcs11;


public class MechMgmt {

	private static final Logger logger = LoggerFactory.getLogger(MechMgmt.class);

	static MechMgmt m_instance;
	Map<String, MechPkcs11> mechanisms;

	public static MechMgmt getInstance() {

		logger.debug("MechMgmt::getInstance: invoked....");

		if (m_instance == null) {
			m_instance = new MechMgmt();
		}
		return m_instance;
	}

	private MechMgmt() {
		logger.debug("MechMgmt::MechMgmt: invoked....");
	}

	public void setMechanisms(Map<String,MechPkcs11> mechas) {

		mechanisms = mechas;
		showMapKeyInfo(mechanisms);
	}
	
	public MechPkcs11 getMechanism(String mecha) {
		return (MechPkcs11) mechanisms.get(mecha);
	}
	
//shhan append 함수 Debug함수
	void showMapKeyInfo(Map<String,MechPkcs11> mechas){
 		Iterator iterator = mechanisms.keySet().iterator();
		logger.debug("MechMgmt::MechMgmt: setMechanisms.");
		while(iterator.hasNext()){
			String key = (String)iterator.next();
			logger.debug("MechMgmt::MechMgmt: setMechanisms.  key : [{}]", key);
		}
	}
}
