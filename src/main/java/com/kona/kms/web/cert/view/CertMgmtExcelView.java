package com.kona.kms.web.cert.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.kona.kms.web.cert.model.CertificateModel;
import com.kona.kms.web.utils.ExcelUtil;

@Service
public class CertMgmtExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		@SuppressWarnings("unchecked")
		List<CertificateModel> models = (List<CertificateModel>)model.get("list");
		
		Map<String, HSSFCellStyle> style = ExcelUtil.getStyles(workbook);
		
		HSSFSheet sheet = workbook.createSheet("Certificate Mgmt List");
		
		sheet.setColumnWidth(0, 7000); // company
		sheet.setColumnWidth(1, 10000); // certificate name
		sheet.setColumnWidth(2, 7000); // brand type
		sheet.setColumnWidth(3, 7000); // bin number
		sheet.setColumnWidth(4, 7000); // tracking number
		sheet.setColumnWidth(5, 7000); // pair key subject(ID)
		
		sheet.setColumnWidth(6, 7000); // public key profile id
		sheet.setColumnWidth(7, 7000); // public key profile version
		sheet.setColumnWidth(8, 10000); // public key profile name
		sheet.setColumnWidth(9, 4000); // issuer pk index
		sheet.setColumnWidth(10, 4000); // issuer pk size	
		
		sheet.setColumnWidth(11, 4000); // issuer pk exponent value
		sheet.setColumnWidth(12, 10000); // issuer hash value
		sheet.setColumnWidth(13, 10000); // request file name
		sheet.setColumnWidth(14, 15000); // request file content
		sheet.setColumnWidth(15, 10000); // response file name
		
		sheet.setColumnWidth(16, 15000); // response file content
		sheet.setColumnWidth(17, 7000); // certi serial number	
		sheet.setColumnWidth(18, 7000); // ca pk index
		sheet.setColumnWidth(19, 7000); // ca pk size		
		sheet.setColumnWidth(20, 7000); // expire date	
		
		sheet.setColumnWidth(21, 7000); // request date
		sheet.setColumnWidth(22, 7000); // registration date
		sheet.setColumnWidth(23, 7000); // registration user
		
		int rownum = 0;

		HSSFRow row = sheet.createRow(rownum++);
		
		row.setHeightInPoints(20);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Company"));

		cell = row.createCell(1);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Certificate Name"));
		
		cell = row.createCell(2);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Brand Type"));

		cell = row.createCell(3);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("BIN No."));

		cell = row.createCell(4);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Tracking No."));

		cell = row.createCell(5);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Pair Key ID"));

		cell = row.createCell(6);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("PK Profile ID"));

		cell = row.createCell(7);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("PK Profile Ver."));

		cell = row.createCell(8);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("PK Profile Name"));

		cell = row.createCell(9);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("IPK Index"));

		cell = row.createCell(10);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("IPK Size"));

		cell = row.createCell(11);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("IPK Exponent Value"));

		cell = row.createCell(12);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Hash Value"));

		cell = row.createCell(13);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Request File Name"));
		
		cell = row.createCell(14);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Request File"));
		
		cell = row.createCell(15);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Response File Name"));
		
		cell = row.createCell(16);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Response File"));
		
		cell = row.createCell(17);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("CA Serial No."));

		cell = row.createCell(18);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("CA PK Index"));
		
		cell = row.createCell(19);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("CA PK Size"));

		cell = row.createCell(20);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Expire Date"));

		cell = row.createCell(21);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Request Date"));
		
		cell = row.createCell(22);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Registration Date"));

		cell = row.createCell(23);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Registration User"));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		for(CertificateModel entry : models){
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(18);

			cell = row.createCell(0);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyName()));

			cell = row.createCell(1);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCertificateName()));

			cell = row.createCell(2);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(entry.getBrandTypeCode()));

			cell = row.createCell(3);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(entry.getBinNumber()));

			cell = row.createCell(4);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry
					.getTrackingNumber()));

			cell = row.createCell(5);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeySubject()));

			cell = row.createCell(6);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyProfileID()));
			
			cell = row.createCell(7);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyProfileVersion()));
			
			cell = row.createCell(8);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyProfileName()));
			
			
			cell = row.createCell(9);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getIpkIndex())));

			cell = row.createCell(10);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getIpkSize())));

			cell = row.createCell(11);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getExponentValue()));

			cell = row.createCell(12);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getHashValue()));

			cell = row.createCell(13);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCertiReqFileName()));

			cell = row.createCell(14);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCertiReqBinaryFileStr()));
			
			cell = row.createCell(15);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCertiResFileName()));

			cell = row.createCell(16);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCertiResBinaryFileStr()));

			cell = row.createCell(17);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getCaSerialNumber())));

			cell = row.createCell(18);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getCaPublicKeyIndex())));

			cell = row.createCell(19);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCaPublicKeyLength()));

			
			cell = row.createCell(20);
			cell.setCellStyle(style.get("text"));
			if(entry.getExpireDate() != null){
				cell.setCellValue(new HSSFRichTextString(formatter2.format(entry.getExpireDate())));
			}
			
			cell = row.createCell(21);
			cell.setCellStyle(style.get("text"));
			if(entry.getRequestDate() != null){
				cell.setCellValue(new HSSFRichTextString(formatter2.format(entry.getRequestDate())));
			}
			
			cell = row.createCell(22);
			cell.setCellStyle(style.get("text"));
			if(entry.getUpdateDate() != null){
				cell.setCellValue(new HSSFRichTextString(formatter.format(entry.getUpdateDate())));
			}

			cell = row.createCell(23);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getUpdateUserID()));

		}
		
		String fileName = "certificate_mgmt_list.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		res.setHeader("Content-Disposition", "attachment; fileName=\""
				+ fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");

	}

}
