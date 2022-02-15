package com.kona.kms.web.ss.hsm.view;

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

import com.kona.kms.web.ss.hsm.model.HsmMetaModel;
import com.kona.kms.web.utils.ExcelUtil;

@Service
public class HsmMetaExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("unchecked")
		List<HsmMetaModel> hsm = (List<HsmMetaModel>)model.get("list");
		
		Map<String, HSSFCellStyle> style = ExcelUtil.getStyles(workbook);
		
		HSSFSheet sheet = workbook.createSheet("HSM Meta List");
		
		
		sheet.setColumnWidth(0, 3000); // hsmNo
		sheet.setColumnWidth(1, 7000); // hsmLabel
		sheet.setColumnWidth(2, 7000); // slotCount
		sheet.setColumnWidth(3, 7000); // slotStartNum
		sheet.setColumnWidth(4, 7000); // slotEndNum
		sheet.setColumnWidth(5, 7000); // ipAddress
		sheet.setColumnWidth(6, 3000); // port
		sheet.setColumnWidth(7, 7000); // statusCode
		sheet.setColumnWidth(8, 10000); // descritpion		
		
		int rownum = 0;

		HSSFRow row = sheet.createRow(rownum++);
		
		row.setHeightInPoints(20);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Hsm No"));

		cell = row.createCell(1);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Hsm Label"));
		
		cell = row.createCell(2);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Slot Count"));

		cell = row.createCell(3);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Slot Start Num"));

		cell = row.createCell(4);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Slot End Num"));

		cell = row.createCell(5);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Ip Address"));

		cell = row.createCell(6);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Port"));

		cell = row.createCell(7);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Status"));

		cell = row.createCell(8);
		cell.setCellStyle(style.get("title"));
		cell.setCellValue(new HSSFRichTextString("Description"));
		
		
		for(HsmMetaModel entry : hsm){
			row = sheet.createRow(rownum++);
			row.setHeightInPoints(13);

			cell = row.createCell(0);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getHsmNo())));

			cell = row.createCell(1);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getHsmLabel()));

			cell = row.createCell(2);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getSlotCount())));

			cell = row.createCell(3);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getSlotStartNum())));

			cell = row.createCell(4);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(String.valueOf(entry.getSlotEndNum())));

			cell = row.createCell(5);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getIpAddress()));

			cell = row.createCell(6);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getPort()));

			cell = row.createCell(7);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getStatusCode()));

			cell = row.createCell(8);
			cell.setCellStyle(style.get("text"));
			cell.setCellValue(new HSSFRichTextString(entry.getDescription()));
			
		}
	
		String fileName = "hsm_info_list.xls";
		fileName = new String(fileName.getBytes("euc-kr"), "8859_1");
		res.setHeader("Content-Disposition", "attachment; fileName=\""
				+ fileName + "\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
	}
	
}
