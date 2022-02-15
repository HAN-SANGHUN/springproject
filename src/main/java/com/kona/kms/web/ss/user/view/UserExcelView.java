package com.kona.kms.web.ss.user.view;

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

import com.kona.kms.web.ss.user.model.KmsUserModel;
import com.kona.kms.web.utils.ExcelUtil;

@Service
public class UserExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		
		@SuppressWarnings("unchecked")
		List<KmsUserModel> users = (List<KmsUserModel>)model.get("list");
				
		Map<String, HSSFCellStyle> style = ExcelUtil.getStyles(workbook);
		
		HSSFSheet sheet = workbook.createSheet("KMS User List");
		
		
		sheet.setColumnWidth(0, 7000); // userId
		sheet.setColumnWidth(1, 7000); // userName
		sheet.setColumnWidth(2, 7000); // role
		sheet.setColumnWidth(3, 7000); // status
		sheet.setColumnWidth(4, 7000); // companyId
		sheet.setColumnWidth(5, 7000); // companyName
		sheet.setColumnWidth(6, 7000); // userEmail

		sheet.setColumnWidth(7, 7000); // cellphoneNum
		sheet.setColumnWidth(8, 7000); // officeNum
		sheet.setColumnWidth(9, 7000); // department
		sheet.setColumnWidth(10, 7000); // designation
		sheet.setColumnWidth(11, 10); // ipaddress

		sheet.setColumnWidth(12, 10000); // description
		
		int rownum = 0;

		HSSFRow row = sheet.createRow(rownum++);
		
		row.setHeightInPoints(20);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("UserID"));

		cell = row.createCell(1);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("User Name"));
		
		cell = row.createCell(2);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Role"));

		
		cell = row.createCell(3);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Status"));

		cell = row.createCell(4);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("CompanyID"));

		cell = row.createCell(5);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("CompanyName"));

		cell = row.createCell(6);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Email"));

		cell = row.createCell(7);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Cell No."));

		cell = row.createCell(8);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Office No."));

		cell = row.createCell(9);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Department"));

		cell = row.createCell(10);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Designation"));

		cell = row.createCell(11);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("IP Address"));

		cell = row.createCell(12);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Description"));
		
		for(KmsUserModel entry : users){
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(18);

			cell = row.createCell(0);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getUserId()));

			cell = row.createCell(1);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getUserName()));

			cell = row.createCell(2);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getUserRoleCode()));

			cell = row.createCell(3);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getActiveStatusCode()));

			cell = row.createCell(4);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyId()));

			cell = row.createCell(5);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry
					.getCompanyName()));

			cell = row.createCell(6);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getEmail()));

			cell = row.createCell(7);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCellphoneNum()));

			cell = row.createCell(8);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getOfficeTelNum()));

			cell = row.createCell(9);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getDepartment()));

			cell = row.createCell(10);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getDesignation()));

			cell = row.createCell(11);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getLoginIPAddress()));

			cell = row.createCell(12);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getDescription()));
		}

	
		String fileName = "kms_user_list.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		res.setHeader("Content-Disposition", "attachment; fileName=\""
				+ fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
	}
}
