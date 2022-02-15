package com.kona.kms.web.ss.company.view;

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

import com.kona.kms.web.ss.company.model.CompanyModel;
import com.kona.kms.web.utils.ExcelUtil;

@Service
public class CompanyExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<CompanyModel> companys = (List<CompanyModel>)model.get("list");
		
		Map<String, HSSFCellStyle> style = ExcelUtil.getStyles(workbook);
		
		HSSFSheet sheet = workbook.createSheet("Company List");
		
		
		sheet.setColumnWidth(0, 7000); // companyId
		sheet.setColumnWidth(1, 7000); // companyName
		sheet.setColumnWidth(2, 7000); // companyTypeCode
		sheet.setColumnWidth(3, 7000); // tokenLabel
		sheet.setColumnWidth(4, 7000); // fax
		sheet.setColumnWidth(5, 7000); // email
		sheet.setColumnWidth(6, 10000); // homepageUrl
		sheet.setColumnWidth(7, 10000); // address
		sheet.setColumnWidth(8, 7000); // bizLicenseNo
		sheet.setColumnWidth(9, 7000); // officeTelNum		
		sheet.setColumnWidth(10, 7000); // companyDescription
		sheet.setColumnWidth(11, 7000); // companyStatusCode		
				
		int rownum = 0;

		HSSFRow row = sheet.createRow(rownum++);
		
		row.setHeightInPoints(20);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("CompanyID"));

		cell = row.createCell(1);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Company Name"));
		
		cell = row.createCell(2);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Company Type"));

		cell = row.createCell(3);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Token Label"));

		cell = row.createCell(4);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("fax"));
		
		cell = row.createCell(5);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Email"));
		
		cell = row.createCell(6);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Homepage"));		

		cell = row.createCell(7);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Address"));
		
		cell = row.createCell(8);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Biz License"));
		
		cell = row.createCell(9);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Office No."));
		
		cell = row.createCell(10);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Company Desc."));

		cell = row.createCell(11);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Status"));
		
		for(CompanyModel entry : companys){
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(18);

			cell = row.createCell(0);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyId()));

			cell = row.createCell(1);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyName()));

			cell = row.createCell(2);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyTypeCode()));

			cell = row.createCell(3);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getTokenLabel()));

			cell = row.createCell(4);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getFax()));

			cell = row.createCell(5);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getEmail()));

			cell = row.createCell(6);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getHomepageUrl()));

			cell = row.createCell(7);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getAddress()));

			cell = row.createCell(8);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getBizLicenseNo()));

			cell = row.createCell(9);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getOfficeTelNum()));

			cell = row.createCell(10);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyDescription()));
			
			cell = row.createCell(11);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getCompanyStatusCode()));
			
		}

	
		String fileName = "company_list.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		res.setHeader("Content-Disposition", "attachment; fileName=\""
				+ fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		
	}
}
