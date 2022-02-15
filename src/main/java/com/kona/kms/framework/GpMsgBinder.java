package com.kona.kms.framework;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.globalplatform.namespaces.systems_profiles._1_1.KeyProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.kona.kms.gpmsg.GPMessage;


public class GpMsgBinder {
		
	private static final Logger logger = LoggerFactory.getLogger(GpMsgBinder.class);

	static GpMsgBinder m_instance;
	private JAXBContext m_jc;
	
	public static GpMsgBinder getInstance() {
		if (m_instance == null) {
			m_instance = new GpMsgBinder();
		}
		return m_instance;
	}
	
	private GpMsgBinder() {
		String contextPath = KeyProfile.class.getPackage().getName(); 
		try {
			m_jc = JAXBContext.newInstance(contextPath);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ScmsException("z00001");
		}
	}


    public <T> T unmarshal(InputStream soapMsgInputStream, String beginTag, Class<T> clazz) {
		XMLInputFactory xif;
		XMLStreamReader xsr;
    	
    	try {
			xif = XMLInputFactory.newFactory();
			xsr = xif.createXMLStreamReader(soapMsgInputStream);
    	}
    	catch (Exception e) {
	        e.printStackTrace();	  
	        throw new ScmsException("z00001");
    	}

    	if (beginTag == null) {
    		beginTag = "GPMessage";
    	}

    	T gpMsg = null;
    	int eventType = 0;

        do {
        	try {
	        	eventType = xsr.nextTag(); 
	        	String tagName = xsr.getLocalName();
	        	
		        if (eventType == XMLStreamConstants.START_ELEMENT ) {
			        // Advance to Envelope tag ("GPMessage")
		        	if (tagName.equals(beginTag) == true) {
		        		//String contextPath = GPMessage.class.getPackage().getName(); 
		        		//JAXBContext jc = JAXBContext.newInstance(contextPath);
		        		synchronized(this) {
			    	        javax.xml.bind.Unmarshaller unmarshaller = m_jc.createUnmarshaller();
			    	        JAXBElement<T> je = unmarshaller.unmarshal(xsr, clazz);
			    	        gpMsg = (T) je.getValue();
		        		}
		        	}
	        	}
        	}
        	catch (XMLStreamException xe) {
    			//logger.info("GpMsgBinder::unmarshal: eventType {}, XMLStreamException = [{}]", eventType, xe.toString());
        		logger.debug("GpMsgBinder::unmarshal: END_DOCUMENT Received!");
        		break;
        	}
        	catch (NoSuchElementException xe) {
        		logger.warn("GpMsgBinder::unmarshal: NoSuchElementException = [{}]", xe.toString());
        		break;
        	}
        	catch (Exception e){
		        e.printStackTrace();	  
		        throw new ScmsException("z00001");
        	}
        } while (eventType != XMLStreamConstants.END_DOCUMENT);
        return gpMsg;
	}
        
    public byte[] marshal(Object gpMsg) {
		//Create OutputStream
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

	    try {
			//String contextPath = gpMsg.class.getPackage().getName(); 
			//JAXBContext jc = JAXBContext.newInstance(contextPath);
			//Create marshaller
    		synchronized(this) {
    			Marshaller marshaller = m_jc.createMarshaller();
    			//Marshal object into file.
    		    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
    		    
    		    //add by shifei 2014-07-11
    		    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
    		    marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    		    
    		    
    		    
    			marshaller.marshal(gpMsg, bos);
    		}
		} catch (JAXBException e) {
			e.printStackTrace();
	        throw new ScmsException("z00001");
		}
		return bos.toByteArray();
    }
    

}

