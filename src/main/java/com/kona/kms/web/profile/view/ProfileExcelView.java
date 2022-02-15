package com.kona.kms.web.profile.view;

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

import com.kona.kms.web.profile.model.KeyProfileModel;
import com.kona.kms.web.utils.ExcelUtil;

@Service
public class ProfileExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		@SuppressWarnings("unchecked")
		List<KeyProfileModel> profiles = (List<KeyProfileModel>) model.get("list");
		
		Map<String, HSSFCellStyle> style = ExcelUtil.getStyles(workbook);
		
		HSSFSheet sheet = workbook.createSheet("Key Profile List");
		
		
		sheet.setColumnWidth(0, 7000); // company
		sheet.setColumnWidth(1, 7000); // tokenlabel
		sheet.setColumnWidth(2, 7000); // profileID
		sheet.setColumnWidth(3, 4000); // profileVersion
		sheet.setColumnWidth(4, 7000); // profileName
		sheet.setColumnWidth(5, 10000); // profileDescirption

		sheet.setColumnWidth(6, 4000); // startDate
		sheet.setColumnWidth(7, 4000); // endDate
		sheet.setColumnWidth(8, 4000); // RevocationDate
		sheet.setColumnWidth(9, 7000); // owner
		sheet.setColumnWidth(10, 4000); // mode

		sheet.setColumnWidth(11, 4000); // keyType
		sheet.setColumnWidth(12, 4000); // keySubtype
		sheet.setColumnWidth(13, 7000); // keyRole
		sheet.setColumnWidth(14, 4000); // keyVersion
		sheet.setColumnWidth(15, 4000); // keyIdentifier
		sheet.setColumnWidth(16, 7000); // keyDefinition
		
		sheet.setColumnWidth(17, 4000); // keySize
		sheet.setColumnWidth(18, 4000); // KCV algorithm
		sheet.setColumnWidth(19, 4000); // usage
		sheet.setColumnWidth(20, 15000); // xml
		sheet.setColumnWidth(21, 7000); // registrationDate
		
		int rownum = 0;

		HSSFRow row = sheet.createRow(rownum++);
		
		row.setHeightInPoints(20);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Company"));

		cell = row.createCell(1);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Token Label"));
		
		cell = row.createCell(2);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Profile ID"));

		cell = row.createCell(3);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Profile Version"));

		cell = row.createCell(4);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Profile Name"));

		cell = row.createCell(5);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Description"));

		cell = row.createCell(6);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Start Date"));

		cell = row.createCell(7);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("End Date"));

		cell = row.createCell(8);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Revocation Date"));

		cell = row.createCell(9);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Owner"));

		cell = row.createCell(10);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Mode"));

		cell = row.createCell(11);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Type"));

		cell = row.createCell(12);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Subtype"));

		cell = row.createCell(13);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Role"));
		
		cell = row.createCell(14);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Version"));

		cell = row.createCell(15);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Identifier"));

		cell = row.createCell(16);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Definition"));
		
		cell = row.createCell(17);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Key Size"));

		cell = row.createCell(18);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("KCV Algorithm"));

		cell = row.createCell(19);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Usage"));

		cell = row.createCell(20);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Profile XML"));

		cell = row.createCell(21);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Registration Date"));
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");

		
		for(KeyProfileModel entry : profiles){
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(18);

			cell = row.createCell(0);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyName()));

			cell = row.createCell(1);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getTokenLabel()));

			cell = row.createCell(2);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyProfileID()));

			cell = row.createCell(3);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyProfileVersion()));

			cell = row.createCell(4);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry
					.getKeyProfileName()));

			cell = row.createCell(5);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getDescription()));

			cell = row.createCell(6);
			cell.setCellStyle(style.get("text-center"));
			
			if(entry.getEffectiveStartDate() != null){
				cell.setCellValue(new HSSFRichTextString(formatter2.format(entry.getEffectiveStartDate())));
			}

			cell = row.createCell(7);
			cell.setCellStyle(style.get("text-center"));
			
			if(entry.getEffectiveEndDate() != null){
				cell.setCellValue(new HSSFRichTextString(formatter2.format(entry.getEffectiveEndDate())));
			}

			cell = row.createCell(8);
			cell.setCellStyle(style.get("text-center"));
			
			if(entry.getRevocationDate() != null){
				cell.setCellValue(new HSSFRichTextString(formatter2.format(entry.getRevocationDate())));
			}
			
			cell = row.createCell(9);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyOwner()));

			cell = row.createCell(10);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getTestMode()));

			cell = row.createCell(11);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyTypeCode()));

			cell = row.createCell(12);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeySubtypeCode()));

			cell = row.createCell(13);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyRoleCode()));

			cell = row.createCell(14);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyVersion()));

			cell = row.createCell(15);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyIdentifier()));
			
			cell = row.createCell(16);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyDefinition()));
			
			cell = row.createCell(17);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getKeySize())));

			cell = row.createCell(18);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyAlgorithm()));

			cell = row.createCell(19);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyUsageIndicatorValue()));

			cell = row.createCell(20);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getKeyProfileXMLFile()));
			
			cell = row.createCell(21);
			cell.setCellStyle(style.get("text-center"));
			cell.setCellValue(new HSSFRichTextString(formatter.format(entry.getRegistrationDate())));
		}
		
		String fileName = "key_profile_list.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		res.setHeader("Content-Disposition", "attachment; fileName=\""
				+ fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
	}
}
