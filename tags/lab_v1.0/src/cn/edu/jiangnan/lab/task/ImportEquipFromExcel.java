package cn.edu.jiangnan.lab.task;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.edu.jiangnan.lab.data.pojo.Equip;

public class ImportEquipFromExcel {
	/*
	 * 导入Excel中d:\\equip.xls的仪器记录到数据库 只是AP，线上不运行
	 */
	public static void main(String[] args) throws Exception {
		execute();
		// e();
	}

	public static ArrayList<Equip> execute() throws Exception {
		ArrayList<Equip> equips = new ArrayList<Equip>();
		SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat s2 = new SimpleDateFormat("d-MMM-yy", Locale.US);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File("d:\\equip.xls"));
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(0);
			int rowNumber = sheet.getLastRowNum();
			for (int i = 1; i < rowNumber; i++) {
				HSSFRow row = sheet.getRow(i);
				int cellNumber = row.getLastCellNum();
				Equip equip = new Equip();
				for (int j = 0; j < cellNumber - 1; j++) {
					HSSFCell cell = row.getCell((short) j);
					String value = null;
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								value = cell.getDateCellValue().toString();
							} else {
								Integer num = new Integer((int) cell
										.getNumericCellValue());
								value = String.valueOf(num);

							}
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getRichStringCellValue().toString();
							break;
						default:
							value = "";
						}
						System.out.print(value + "\t");
						switch (j) {
						case 0:
							equip.setNo(value);
							break;
						case 1:
							if (value.contains("方向管"))
								equip.setType(3);
							else if (value.contains("院管"))
								equip.setType(1);
							else
								equip.setType(2);
							break;
						case 2:
							equip.setName(value);
							break;
						case 3:
							equip.setModel(value);
							break;
						case 4:
							equip.setSpecification(value);
							break;
						case 5:
							equip.setPrice(value);
							break;
						case 6:
							equip.setCountry(value);
							break;
						case 7:
							equip.setCompany(value);
							break;
						case 8:
							equip.setYear1(s1.format(s2.parse(value)));
							break;
						case 9:
							equip.setYear2(s1.format(s2.parse(value)));
							break;
						}
					}

				}
				equips.add(equip);
				System.out.println();
			}
			return equips;

			// HSSFRow row = sheet.getRow(1);
			// HSSFCell cell = row.getCell((short)0);
			// System.out.println(cell.getRichStringCellValue());
		} finally {
			fis.close();
			System.out.println("Closed");
		}
	}

	public static void e() throws Exception {
		SimpleDateFormat sdfd = new SimpleDateFormat("d-MMM-yy", Locale.US);
		String s = "01-Dec-04";
		Date d = sdfd.parse(s);
		System.out.println(d);
	}
}
