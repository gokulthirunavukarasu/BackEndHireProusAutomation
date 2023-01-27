package com.Hireprous.Engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParserUtils {
	
	private static final int BOOLEAN = 0;
	private static final int NUMERIC = 0;
	public  String path;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static Sheet ws;
	public static Row row;
	public static Cell cell;
	public static CellStyle style;
	//public static XSSFSheet sheet;
	
	public ExcelParserUtils(String path) 
	{
		
		this.path=path;
		try 
		{
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			ws = wb.getSheetAt(0);
			fi.close();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	
	public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data;
		try 
		{
			data=cell.getStringCellValue();
		} catch (Exception e) 
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void setIntCellData(String xlfile,String xlsheet,int rownum,int colnum,int data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		cell.setCellValue((Integer)data); 
		fo=new FileOutputStream(xlfile);
		wb.write(fo);		
		wb.close();
		fi.close();
		fo.close();
	}
	
	public boolean setCellDataByColName(String xlsheet,String colName,int rowNum, String data)
	{
		try
		{
			fi = new FileInputStream(path); 
			wb = new XSSFWorkbook(fi);
			
			if(rowNum<=0)
				return false;
			
			int index = wb.getSheetIndex(xlsheet);
			
			int colNum=-1;
			if(index==-1)
				return false;
			
			
			ws = wb.getSheetAt(index);
			

		row=ws.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
			
		}
		if(colNum==-1)
			return false;

		ws.autoSizeColumn(colNum); 
		row = ws.getRow(rowNum-1);
		if (row == null)
			row = ws.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    
	    cell.setCellValue(data);
	   

	    fo = new FileOutputStream(path);

		wb.write(fo);

	    fo.close();	

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	//give total row minus 1 in this
	//readRandomCellData
	public static String readRandomCellData(String xlfile,String sheetName, String colName, int totalRow) throws IOException
    {
        try
        {
            int col_Num = -1;

			fi = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(fi);
			 ws = wb.getSheet(sheetName);
            row = ws.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
            Random rand = new Random();
            int a = rand.nextInt(totalRow);
            row = ws.getRow(a + 1);
            cell = row.getCell(col_Num);
 
            if(cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA)
            {
            	// Only need one of these
            	DataFormatter fmt = new DataFormatter();

            	// Once per cell
            	String valueAsSeenInExcel = fmt.formatCellValue(cell);
                String cellValue = valueAsSeenInExcel;
                if(HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }else if(cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "row  or column "+ colName +" does not exist  in Excel";
        }
        finally
        {
        	wb.close();
    		fi.close();
        }
    }
	
	public int getRowCountbyColName(String sheetName,String colName,int ColNameRowIndex) throws IOException
	{
		int col_Num=0;
		String data;
		int[] dataCount = null;
	    try {
	    	int index = wb.getSheetIndex(sheetName);
	    	ws = wb.getSheetAt(index);
	    	row=ws.getRow(ColNameRowIndex);
	    	for(int i=0;i<row.getLastCellNum();i++)
			{
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				{
					col_Num=i;
				}
			}
	        Iterator rowIter = ws.rowIterator();
	        Row r = (Row)rowIter.next();
	        short lastCellNum = r.getLastCellNum();
	        dataCount = new int[lastCellNum];
	        int col = 0;
	        rowIter = ws.rowIterator();
	        while(rowIter.hasNext()) {
	            Iterator cellIter = ((Row)rowIter.next()).cellIterator();
	            while(cellIter.hasNext()) {
	                Cell cell = (Cell)cellIter.next();
	                col = cell.getColumnIndex();
	                if(col==col_Num){
	                dataCount[col] += 1;
	                DataFormatter df = new DataFormatter();
	                data = df.formatCellValue(cell);
	                //System.out.println("Data: " + data);
	            }
	            }
	        }
	        fi.close();
	       
	            //System.out.println("col " + col_Num + ": " + dataCount[col_Num]);
	        
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	       
	    }
		return dataCount[col_Num];
	}
	
	public boolean setCellDataByColRowNum(String sheetName,String colName,int rowNum, String data, int ColRowNum)
	{
		try
		{
			
		fi = new FileInputStream(path); 
		wb = new XSSFWorkbook(fi);
		

		if(rowNum<=0)
			return false;
		
		int index = wb.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		ws = wb.getSheetAt(index);
		

		row=ws.getRow(1);
		for(int i=0;i<row.getLastCellNum();i++)
		{
			
			if(row.getCell(i).getStringCellValue().trim().equals(colName)) {
				colNum=i;
			}
		}
		if(colNum==-1)
			return false;

		ws.autoSizeColumn(colNum); 
		row = ws.getRow(rowNum-1);
		if (row == null)
			row = ws.createRow(rowNum-1);
		
		
		cell = row.getCell(colNum);	
		//XSSFCellStyle CellStyle= cell.getRow().getRowStyle();
		if (cell == null)
	        cell = row.createCell(colNum);
		
		XSSFFont wbFont = wb.createFont();
		wbFont.setCharSet(XSSFFont.ANSI_CHARSET); //Your Character encoding goes in the parameter
		//Establish cell styles
		XSSFCellStyle cellStyle =wb.createCellStyle();
		cellStyle.setFont(wbFont);

		cell.setCellStyle(cellStyle);
	    cell.setCellValue(data);
	   // removeEmptyRows(sheet);
	    //fis.close();
	    
	    IOUtils.closeQuietly(fi);
	   // XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

	    fo = new FileOutputStream(path);
	    ws.setActiveCell(new CellAddress(0, 0));
	   
    

		wb.write(fo);
		
				
		
	
		/*fileOut.close();
		workbook.close();*/
		
		//IOUtils.closeQuietly(fileOut);   
	   

		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		 finally {
	            // close the streams using close method
	            try {
	                if (fo != null) {
	                	fo.flush();
	                	fo.close();
	                	wb.close();
	                }
	            }
	            catch (IOException ioe) {
	                System.out.println("Error while closing stream: " + ioe);
	            }
	 
	        }
	 
		return true;
	}
	
	public static String getSingleCellData(String xlfile,String sheetName, String colName, int rowNum) throws IOException
    {
		//FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        try
        {
            int col_Num = -1;

			fi = new FileInputStream(xlfile);
			wb = new XSSFWorkbook(fi);
			 ws = wb.getSheet(sheetName);
            row = ws.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
 
            row = ws.getRow(rowNum - 1);
            cell = row.getCell(col_Num);
 
            if(cell.getCellTypeEnum() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellTypeEnum() == CellType.NUMERIC)
            {
            	// Only need one of these
            	DataFormatter fmt = new DataFormatter();

            	// Once per cell
            	String valueAsSeenInExcel = fmt.formatCellValue(cell);
                String cellValue = valueAsSeenInExcel;
                if(HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
                
                
               }
            else if(cell.getCellTypeEnum() == CellType.FORMULA) {
            	 FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            	 DataFormatter dataFormatter = new DataFormatter(new java.util.Locale("en", "US"));
            	 String value = dataFormatter.formatCellValue(cell, evaluator);
            	 System.out.println(value);
            	 return value;
            	 /*
					 * CellValue cellValue = evaluator.evaluate(cell);
					 * 
					 * switch (cellValue.getCellTypeEnum()) { case BOOLEAN:
					 * System.out.println(cellValue.getBooleanValue()); break; case NUMERIC:
					 * System.out.println(cellValue.getNumberValue()); break; case STRING:
					 * System.out.println(cellValue.getStringValue()); break; } return
					 * String.valueOf(cellValue.getNumberValue());
					 */
            }
            
            
            else if(cell.getCellTypeEnum() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rowNum+" or column "+ colName +" does not exist  in Excel";
        }
        finally
        {
        	wb.close();
    		fi.close();
        }
    }

	

	


}
