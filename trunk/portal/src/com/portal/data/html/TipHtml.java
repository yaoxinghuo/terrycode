package com.portal.data.html;

import java.util.ArrayList;

import com.portal.data.mapping.Mapping;
import com.portal.data.pojo.Tab;

/**
 * Developer: Terry DateTime : 2007-12-17 下午02:55:57
 */

public class TipHtml {
	public static String getCheckNewsHtml(ArrayList<Object[]> list, String tab) {
		StringBuffer sb = new StringBuffer();
		sb.append("<form id='").append(tab).append("_checkNewsForm'>");
		sb.append("<table>");
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 == 0)
				sb.append("<tr align='left'>");
			Object[] o = list.get(i);
			String type3 = String.valueOf(o[0]);
			String name = Mapping.newsMappingType3.get(type3);
			if (name != null)
				name = name + "(" + o[1] + " RSS)";
			else
				name = "其他(" + o[1] + " RSS)";

			sb.append("<td><input type=checkbox name=newscheckbox value='");
			sb.append(type3).append("' ");
			sb.append("");
			sb.append(" checked>");
			sb.append(name).append("</td>");
			if (i % 2 == 1 || (i == list.size() - 1 && i % 2 == 0))
				sb.append("</tr>");

		}
		sb.append("<tr><td>");
		sb.append("<input type='button' value='取消' onclick=\"hideTipDiv();\"");
		sb.append("</td><td>");
		sb.append("<input type='button' value='保存' onclick=\"saveCheckNews('");
		sb.append(tab).append("');\">");
		sb.append("</td></tr>");
		sb.append("</table></form>");
		return sb.toString();
	}

	public static String getModuleCheckNewsHtml(ArrayList<Object[]> list,
			String baseId, String ex_type3) {
		String[] ex_type3Array = ex_type3.split(",");
		StringBuffer sb = new StringBuffer();
		sb.append("<form id='").append(baseId).append("_checkNewsForm'>");
		sb.append("<table>");
		for (int i = 0; i < list.size(); i++) {
			if (i % 2 == 0)
				sb.append("<tr align='left'>");
			Object[] o = list.get(i);
			String type3 = String.valueOf(o[0]);
			String name = Mapping.newsMappingType3.get(type3);
			if (name != null)
				name = name + "(" + o[1] + " RSS)";
			else
				name = "其他(" + o[1] + " RSS)";

			sb.append("<td><input type=checkbox name=newscheckbox value='");
			sb.append(type3).append("' ");
			sb.append("");
			if (!contains(type3, ex_type3Array))
				sb.append(" checked");
			sb.append(">");
			sb.append(name).append("</td>");
			if (i % 2 == 1 || (i == list.size() - 1 && i % 2 == 0))
				sb.append("</tr>");

		}
		sb.append("<tr><td>");
		sb.append("<input type='button' value='取消' onclick=\"hideTipDiv();\"");
		sb.append("</td><td>");
		sb.append("<input type='button' value='保存' onclick=\"saveCheckNews('");
		sb.append(baseId).append("');\">");
		sb.append("</td></tr>");
		sb.append("</table></form>");
		return sb.toString();
	}

	private static boolean contains(String o, String[] os) {
		for (int i = 0; i < os.length; i++) {
			if (o.equals(os[i]))
				return true;
		}
		return false;
	}

	public static String getTabSettingsHtml(int position, Tab tab) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table><tr><td>");
		sb.append("设置标题：</td><td><input type=text value='");
		sb.append(tab.getTitle()).append("' id='tab");
		sb.append(position).append("_settingTitle' size=10></td></tr>");
		sb.append("<tr><td align='right'><input type=checkbox name='tab");
		sb.append(position).append("_settingShare' id='tab");
		sb.append(position);
		sb.append("_settingCheckShare' value=0");
		if (tab.isShare())
			sb.append(" checked");
		sb.append("></td><td align='left'>共享Tab</td></tr>");

		sb.append("<tr><td><input type=button ");
		sb.append("value='取消' onclick=\"hideTipDiv();\"></td>");
		sb.append("<td><input type=button ");
		sb.append("value='保存' onclick=updateTabProfile('");
		sb.append(position).append("');></td><tr></table>");

		return sb.toString();
	}

	public static String getLinkEditHtml(String compid, String id,
			String title, String link, String desc) {
		StringBuffer sb = new StringBuffer();
		sb.append("<form><table><tr><td>");
		sb.append("标题：</td><td><input type=text value='");
		sb.append(title).append("' name='title'></td></tr>");
		sb.append("<tr><td>链接：</td><td><input type=text value='");
		sb.append(link).append("' name='link'></td></tr>");
		sb.append("<tr><td>备注：</td><td><input type=text value='");
		sb.append(desc).append("' name='desc'>");
		sb.append("<tr><td>");
		sb.append("<input type='button' value='取消' ");
		sb.append("onclick=hideTipDiv();></td>");
		sb.append("<td><input type=button ");
		sb.append("value='保存' onclick=link_updateLink('");
		sb.append(compid).append("','").append(id);
		sb.append("',this,'").append(trimDescString(desc));
		sb.append("');></td></tr></table></form>");

		return sb.toString();
	}

	public static String getLinkAddHtml(String baseid, String row_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("<form><table><tr><td>");
		sb.append("标题：</td><td><input type=text name='title'>");
		sb.append("</td></tr><tr><td>链接：</td><td>");
		sb.append("<input type=text name='link' value='http://'></td></tr>");
		sb.append("<tr><td>备注：</td><td><input type=text name='desc'>");
		sb.append("<tr><td>");
		sb.append("<input type=hidden name='row_id' value='");
		sb.append(row_id).append("'>");
		sb.append("<input type='button' value='取消' ");
		sb.append("onclick=hideTipDiv();></td>");
		sb.append("<td><input type=button ");
		sb.append("value='保存' onclick=link_addLink('");
		sb.append(baseid).append("',this);>");
		sb.append("</td></tr></table></form>");

		return sb.toString();
	}
	
	private static String trimDescString(String oStr){
		oStr = oStr.replace("&nbsp;","").replace(" ","");
		if(oStr.length()>16)
			oStr = oStr.substring(0,13)+"...";
		return oStr;
	}
}
