package com.kona.kms;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.security.pkcs11.wrapper.Functions;

import com.kona.kms.crypto.CryptoManager;
import com.kona.kms.crypto.bo.CryptoBO;
import com.kona.kms.crypto.bo.CryptoBOImpl;
import com.kona.kms.crypto.bo.CryptoRequest;
import com.kona.kms.crypto.bo.CryptoResponse;
import com.kona.kms.crypto.ws.KmsCryptoService;
import com.kona.kms.crypto.ws.KmsCryptoServiceImpl;
import com.kona.kms.token.KeyManager;
import com.kona.kms.token.KeyMap;
import com.kona.kms.token.TokenMap;
import com.kona.kms.utils.Util;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/crypto")
public class KmsController {
	
	private static final Logger logger = LoggerFactory.getLogger(KmsController.class);
	
	@Inject
	@Named("cryptoClient")
	private KmsCryptoService client;
	
	@Autowired
	MessageSourceAccessor messageSourceAccessor;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	CryptoBO cryptoBo;
	
	@PostConstruct
	public void init() {
		logger.debug("KmsController::init: Working Directory : {}",System.getProperty("user.dir"));
		
		/* init KeyManager */
		KeyManager.getInstance().init();
		KeyMap.getInstance().load();
		CryptoManager.getInstance().init();
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("start test ------------------------ The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		String sText = "01234567";
		byte[] data = sText.getBytes();
		
		CryptoRequest request = new CryptoRequest();
		request.keyLabel = "des3-test-1111";
		request.mechanism = "DES3_ECB";
		request.mechanismParameters = null;
		request.data = data;
		
		CryptoResponse response = new CryptoResponse();

		try {
			// String mechanism, String[] mechanismParameters, String keyLabel, byte[] data
			response = cryptoBo.encrypt(request);
		} catch (KmsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		String encrypted = Util.byteArray2hexString(response.dataEncrypted);
		logger.info("CryptoBOImpl::encrypt: dataEncrypted [{}]", encrypted);
		
		return "home";
	}

	
}

