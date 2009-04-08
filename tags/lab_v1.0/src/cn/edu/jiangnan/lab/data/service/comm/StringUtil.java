package cn.edu.jiangnan.lab.data.service.comm;

import java.io.UnsupportedEncodingException;

import org.springframework.web.util.HtmlUtils;

public class StringUtil {

	public static String splitString(String inputStr, int len) {
		if (inputStr == null) {
			return "";
		}
		try {
			String str = HtmlUtils.htmlEscape(inputStr);
			byte[] strByte = str.getBytes("GBK");
			int strLen = strByte.length;
			if (len >= strLen || len < 1) {
				return str;
			}
			if (len - 3 > 0) {
				len = len - 3;
			}
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = (int) strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? len + 1 : len - 1;
			}
			return new String(strByte, 0, len, "GBK") + "...";
		} catch (UnsupportedEncodingException e) {
			if (inputStr.length() > len)
				return inputStr.substring(0, len);
			else
				return inputStr;
		}
	}

	public static String renderTitle(String title, boolean bold, boolean red) {
		if (!bold && !red)
			return title;
		StringBuffer sb = new StringBuffer(title);
		if (bold) {
			sb.insert(0, "<b>");
			sb.append("</b>");
		}
		if (red) {
			sb.insert(0, "<font color=red>");
			sb.append("</font>");
		}
		return sb.toString();
	}
}
