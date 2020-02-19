package com.payment.xborder.service.payment;

import com.payment.xborder.model.payment.PaymentRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Pradeep P 12-11-2019
 */
public class PaymentFileExcelParser implements PaymentFileParser {

	public PaymentFileExcelParser() {

	}

	@Override
	public PaymentRecord parseLine(String line) {
		String[] columns = line.split(",");
      PaymentRecord filePojo = new PaymentRecord();
		filePojo.setFirstName(columns[0]);
      filePojo.setMiddleName(columns[1]);
		filePojo.setLastName(columns[2]);
		filePojo.setCompanyName(columns[3]);
		filePojo.setAddressLine1(columns[4]);
		filePojo.setAddressLine2(columns[5]);
      filePojo.setCountry(columns[6]);
      filePojo.setState(columns[7]);
      filePojo.setCityName(columns[8]);
      filePojo.setZipCode(columns[8]);
      filePojo.setLandLineNumber(columns[10]);
      filePojo.setMobileNumber(columns[11]);
      filePojo.setEmailAddress(columns[12]);
      filePojo.setIdType(columns[13]);
      filePojo.setIdNumber(columns[14]);
      filePojo.setiDIssueDate(columns[15]);
      filePojo.setiDExpiryDate(columns[16]);
      filePojo.setiDIssueAuthority(columns[17]);
      filePojo.setDateOfBirth(columns[18]);
      filePojo.setBirthCityName(columns[19]);
      filePojo.setBirthCountry(columns[20]);
      filePojo.setGender(columns[21]);
      filePojo.setOccupation(columns[22]);
      filePojo.setAccountType(columns[23]);
      filePojo.setAccountNo(columns[24]);
      filePojo.setRoutingNumber(columns[25]);
      filePojo.setBankName(columns[26]);
      filePojo.setBranchName(columns[27]);
      filePojo.setSenderKey(columns[28]);

		return filePojo;
	}

	@Override
	public List<String> readLines(String path) {
		List<String> lines = new ArrayList<String>();
		try {
			FileInputStream fis = new FileInputStream(path);
			Workbook workbook = path.toLowerCase().endsWith("xlsx") ? new XSSFWorkbook(fis) : new HSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				StringBuilder line = new StringBuilder();
				while (cellIterator.hasNext()) {
					Cell currentCell = cellIterator.next();

               String cellValue = "";
               switch (currentCell.getCellType()){
                  case 0:
                     cellValue =
                           Double.toString(currentCell.getNumericCellValue());
                     break;

                  case 1:
                     cellValue = currentCell.getStringCellValue();
                     break;

                  case 3:
                     cellValue = "";
                     break;

                  case 4:
                     cellValue =
                           Boolean.toString(currentCell.getBooleanCellValue());
                     break;

                  default:
                     break;
               }
               //System.out.println(cellValue);
					line.append(cellValue).append(",");
				}
				lines.add(line.toString());
				System.out.println(line);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return lines;

	}
}
