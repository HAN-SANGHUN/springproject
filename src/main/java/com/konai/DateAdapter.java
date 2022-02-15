package com.konai;

import java.sql.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class DateAdapter extends XmlAdapter<String, Date> {

	@Override
	public String marshal(Date arg0) throws Exception {
		return DateStringConverter.convertString(arg0);
	}

	@Override
	public Date unmarshal(String arg0) throws Exception {
		return DateStringConverter.convertDate(arg0);
	}
}
