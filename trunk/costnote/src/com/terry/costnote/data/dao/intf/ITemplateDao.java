package com.terry.costnote.data.dao.intf;

import java.util.List;

import com.terry.costnote.data.model.Template;

/**
 * @author xinghuo.yao Email: yaoxinghuo at 126 dot com
 * @version create: Jul 16, 2009 11:42:43 AM
 */
public interface ITemplateDao {
	public boolean addTemplate(Template template, int limit);

	public List<Template> getTemplatesByEmail(String email, int limit);
}
