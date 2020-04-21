package com.form;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;


public class FCommon  {
	
	public void createCell(Row row,short column, String value)
	  {	 
		Cell cell = row.createCell(column);
	    cell.setCellValue(value);	 
	  }
	
	public void cellStyleBoderBold(Row row, Workbook workbook, short column) {
		Cell cell = row.getCell(column);
		CellStyle cellStyle = workbook.createCellStyle();
		cell.setCellStyle(cellStyle);
		 
		XSSFCellStyle style = (XSSFCellStyle) cell.getCellStyle();
		XSSFFont font = (XSSFFont) workbook.createFont(); 
		font.setFontName("Times New Roman");
	    font.setBold(true);
	    style.setFont(font);     		
		style.setWrapText(true);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
	}
	
	public void cellStyleBoder(Row row, Workbook workbook, short column) {
		Cell cell = row.getCell(column);
		CellStyle cellStyle = workbook.createCellStyle();
		cell.setCellStyle(cellStyle);		 
		XSSFCellStyle style = (XSSFCellStyle) cell.getCellStyle();	
		XSSFFont font = (XSSFFont) workbook.createFont(); 
		font.setFontName("Times New Roman");	
	    style.setFont(font);     
		style.setWrapText(true);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
	}
	
	public void cellStyleBoderMarge(int rowf, int rowt,int columnf, int columnt, Sheet sheet, Workbook workbook, Row row) {
		 CellRangeAddress cellRangeAddress= new CellRangeAddress(rowf,rowt,columnf,columnt);
		 sheet.addMergedRegion(cellRangeAddress);
	     RegionUtil.setBorderTop(BorderStyle.THIN, cellRangeAddress, sheet);
	     RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangeAddress, sheet);
	     RegionUtil.setBorderRight(BorderStyle.THIN, cellRangeAddress, sheet);
	     RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangeAddress, sheet);
	     
	     CellStyle cellStyle = workbook.createCellStyle();
	     Cell cell = row.getCell(columnf);
	     XSSFCellStyle style = (XSSFCellStyle) cell.getCellStyle();	
	     XSSFFont font = (XSSFFont) workbook.createFont(); 
		 font.setFontName("Times New Roman");
	     font.setBold(true);
	     style.setFont(font);     		
		 style.setWrapText(true);
	}

    public String dateToString(java.sql.Date date, String dateFormat) {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            if (date != null)
                result = sdf.format(date);
        } catch (Exception ex) {
            result = null;
        } finally {
        }
        return result;
    }
    
    
    public String dateToStringUtil(java.util.Date date, String dateFormat) {
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            if (date != null)
                result = sdf.format(date);
        } catch (Exception ex) {
            result = null;
        } finally {
        }
        return result;
    }
    
    public java.util.Date stringToDate(String date, String format) {
        java.util.Date result = null;
        DateFormat df = new SimpleDateFormat(format); 
        try {
            result = df.parse(date);
        } catch (Exception ex) {
            result = null;
        } finally {
        }
        return result;
    }
    
    
    public String changeToHHMMSS(int sec)
    {
      int hour = sec / 3600;
      int min = sec % 3600 / 60;
      int s = sec % 3600 % 60;
      return new StringBuilder().append(hour < 10 ? new StringBuilder().append("0").append(hour).toString() : hour+"").append(":").append(min < 10 ? new StringBuilder().append("0").append(min).toString() : min+"").append(":").append(s < 10 ? new StringBuilder().append("0").append(s).toString() : s+"").toString();
    }
  
    public int changeToSecond(String time)
    {
      if (!time.equalsIgnoreCase("null")) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);
        int sec = Integer.parseInt(time.split(":")[2]);
        int t = hour * 3600 + min * 60 + sec;
        return hour * 3600 + min * 60 + sec;
      }
      return 0;
    }
  
  
  public int getMonth(java.sql.Date date) {
      Calendar cld = Calendar.getInstance();
      cld.setTime(date);

      return cld.get(2) + 1;
  }

  public int getYear(java.sql.Date date) {
      Calendar cld = Calendar.getInstance();
      cld.setTime(date);
      return cld.get(1);
  }

  public int getDaysOfMonth(java.sql.Date date) {
      return getDaysOfMonth(getMonth(date), getYear(date));
  }

  public int getDaysOfMonth(int month, int year) {
      int[] days = 
      { 31, 28 + (isLeapYear(year) ? 1 : 0), 31, 30, 31, 30, 31, 31, 30, 31, 
        30, 31 };
      return (month > 0) && (month < 13) ? days[(month - 1)] : 0;
  }

  public boolean isLeapYear(int year) {
      return (year%400==0?true:false) | (year % 4 == 0 ? true : false) & (year % 100 != 0 ? true : false);
  }

  public int getDays(java.util.Date fromDate, java.util.Date toDate) {
      int DAY_OF_YEAR = 365;
      int result = 0;
      Calendar fd = Calendar.getInstance();
      Calendar td = Calendar.getInstance();
      if (fromDate.before(toDate)) {
          fd.setTime(fromDate);
          td.setTime(toDate);
      } else {
          td.setTime(fromDate);
          fd.setTime(toDate);
      }
      while (fd.get(1) != td.get(1)) {
          result += 365;
          fd.add(6, 365);
      }
      result += td.get(6) - fd.get(6);

      return (fromDate.before(toDate) ? 1 : -1) * result;
  }

  public java.sql.Date addDays(java.util.Date date, int days) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(6, days);

      return DateToSqlDate(cal.getTime());
  }
    
  public java.sql.Date DateToSqlDate(java.util.Date date) {
      return date == null ? null : new java.sql.Date(date.getTime());
  }

  public java.sql.Date getCurrentSqlDate() {
      return new java.sql.Date(System.currentTimeMillis());
  }

  public java.util.Date getCurrentDate() {
      return new java.util.Date();
  }
}
