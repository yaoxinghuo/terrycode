package com.terry.smsbot.util;

import java.io.UnsupportedEncodingException;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: Feb 10, 2010 8:20:13 PM
 */
public class Pinyin4j {

	/**
	 * 获取汉字串拼音首字母，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音首字母
	 */
	public static String cn2FirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i],
							defaultFormat);
					if (_t != null) {
						pybf.append(_t[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 获取汉字串拼音，英文字符不变
	 * 
	 * @param chinese
	 *            汉字串
	 * @return 汉语拼音
	 */
	public static String cn2Spell(String chinese) {
		StringBuffer pybf = new StringBuffer("");
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		boolean lastChinese = false;// 如果上一次是中文，而本次也是中文，就加一个空格
		for (int i = 0; i < arr.length; i++) {
			if (StringUtil.isCommonChinese(arr[i])) {
				try {
					if (lastChinese)
						pybf.append(" ");
					pybf.append(StringUtil
							.firstCharUpperCase(PinyinHelper
									.toHanyuPinyinStringArray(arr[i],
											defaultFormat)[0]));
					lastChinese = true;
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
				lastChinese = false;
			}
		}
		return pybf.toString();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String x = "你好，哈哈";
		System.out.println(cn2FirstSpell(x));
		System.out.println(cn2Spell(x));
	}
}
