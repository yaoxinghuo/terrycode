package com.terry.costnote.client;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 31, 2009 11:41:17 PM
 */
public class VTypeValidator implements Validator {

	private VType type;

	public VTypeValidator(VType type) {
		this.type = type;
	}

	@Override
	public String validate(Field<?> field, String value) {
		String res = null;
		if (!value.matches(type.regex)) {
			res = value + "不是一个有效的 " + type.name;
		}
		return res;
	}

}
