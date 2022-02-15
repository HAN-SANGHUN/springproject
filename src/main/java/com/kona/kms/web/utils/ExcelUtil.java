
package com.kona.kms.web.utils;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class ExcelUtil
{

    public static Map<String, HSSFCellStyle> getStyles(HSSFWorkbook wb)
    {
    	ConcurrentHashMap<String, HSSFCellStyle> styles = new ConcurrentHashMap<String, HSSFCellStyle>();
        HSSFDataFormat df = wb.createDataFormat();
        HSSFCellStyle style;

        HSSFFont headerFont = wb.createFont();
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerFont.setColor(new HSSFColor.WHITE().getIndex());
        headerFont.setFontHeightInPoints((short) 15);
        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.AQUA().getIndex());
        style.setFont(headerFont);
        styles.put("header", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styles.put("text", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styles.put("text-center", style);

        HSSFFont textFont = wb.createFont();
        textFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        textFont.setColor(new HSSFColor.BLACK().getIndex());
        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
        style.setFont(textFont);
        styles.put("text-question", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.PALE_BLUE().getIndex());
        style.setFont(textFont);
        styles.put("text-seq", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styles.put("subtitle", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styles.put("result", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.PALE_BLUE().getIndex());
        style.setFont(textFont);
        styles.put("title", style);

        style = createBorderedStyle(wb);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(textFont);
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styles.put("total", style);

        style = createBorderedStyle(wb);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(textFont);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styles.put("subtotal", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.ROSE().getIndex());
        style.setFont(textFont);
        styles.put("error", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.ROSE().getIndex());
        style.setFont(textFont);
        styles.put("error1", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
        style.setFont(textFont);
        styles.put("error2", style);

        style = createBorderedStyle(wb);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
        styles.put("date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setDataFormat(df.getFormat("#,##0.##"));
        styles.put("number", style);

        return styles;
    }

    private static HSSFCellStyle createBorderedStyle(HSSFWorkbook wb)
    {
        HSSFCellStyle style = wb.createCellStyle();

        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(new HSSFColor.BLACK().getIndex());

        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(new HSSFColor.BLACK().getIndex());

        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(new HSSFColor.BLACK().getIndex());

        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setTopBorderColor(new HSSFColor.BLACK().getIndex());

        return style;
    }

    public static void breakRow(HSSFSheet sheet, int index)
    {
        @SuppressWarnings("unused")
        HSSFRow row = sheet.createRow((short) index);
    }

    public static void applyCellMerge(HSSFCellStyle style)
    {
        // style.
    }

    public static void applyRowHeight(HSSFRow row, float height)
    {
        row.setHeightInPoints(height);
    }
}
