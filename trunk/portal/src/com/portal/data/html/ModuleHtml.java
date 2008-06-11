package com.portal.data.html;

import net.sf.json.JSONObject;

import com.portal.data.mapping.Mapping;
import com.portal.data.pojo.Row;

/**
 * Developer: Terry DateTime : 2007-12-17 下午02:46:29
 */

public class ModuleHtml {
	public static String getSystemRssModuleHtml(Row row, int position) {
		row.setTotalResult(-1);
		StringBuffer sb = new StringBuffer();

		String baseId = row.getId();
		String title = row.getTitle();
		int limit = row.getShowNumber();
		sb.append("<div id='").append(baseId);
		sb.append("' class='modbox' style='position: relative'>\n");
		sb.append("<h2 class='modtitle'>\n");
		sb.append("<table class='mhdr' ");
		sb.append("cellspacing='0' cellpadding='0'>\n");
		sb.append("<tr>\n");
		sb.append("<td id='").append(baseId);
		sb.append("_title' class='mttl'>");
		sb.append("<span class='mttli' id='");
		sb.append(baseId).append("_titleContent'>");
		sb.append(title).append("</span></td>\n");
		sb.append("<td class=medit>");
		sb.append("<a href='#' onClick=\"module_refresh('");
		sb.append(baseId).append("');\"> ");
		sb.append("<img src='images/view-refresh.gif' ");
		sb.append("style='cursor:hand' alt='刷新' class=mdel></a></td>\n");
		sb.append("<td class='medit'>");
		sb.append("<a href='#' src='images/arrow.gif' id='").append(baseId);
		sb.append("_editText'").append(" onClick=\"return module_edit('");
		sb.append(baseId).append("');\">编辑</a></td>\n");
		sb.append("<td class='medit'>");
		sb.append("<a href='#' onClick=\"return module_del('");
		sb.append(position).append("','");
		sb.append(baseId).append("'");
		sb.append(")\" id='").append(baseId);
		sb.append("_del' class='medit'><img alt='删除' src='images/x.gif' ");
		sb.append("class=mdel></a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td colspan='5' bgcolor='#e5ecf9'>\n");
		sb.append("<div id='").append(baseId);
		sb.append("_editContent' style='padding: 5px; display: none;'>\n");
		sb.append("<form id='").append(baseId).append("_editForm'>");
		sb.append("<input type='hidden' value='").append(row.getEx_type3());
		sb.append("' id='").append(baseId).append("_exSelectType3'>");
		JSONObject settings = JSONObject.fromObject(row);
		sb.append("<input type='hidden' value='").append(settings.toString());
		sb.append("' id='").append(baseId).append("_settings'>");
		sb.append("编辑标题： <input type='text' value='");
		sb.append(title).append("' id='").append(baseId);
		sb.append("_titleText' size=10>");
		String type1 = Mapping.newsMappingType1.get(row.getType1());
		String type2 = Mapping.newsMappingType0.get(row.getType1()).get(
				row.getType2());
		sb.append("<br>订阅类别： <span id='");
		sb.append(baseId).append("_currentSubcribeType'>");
		sb.append(type1).append("--").append(type2);
		sb.append("</span>&nbsp;<a href='#' onclick=\"showResubscribeDiv('");
		sb.append(baseId).append("')\" id='").append(baseId);
		sb.append("_resubscribeText'>修改</a>");
		sb.append("<div style='display: none' id='").append(baseId);
		sb.append("_resubscribeDiv' >");
		sb.append("选择大类： <select style='width:100px;' id='").append(baseId);
		sb.append("_selectType1' onchange=\"showSelectOptions2('");
		sb.append(baseId).append("',1);\"></select><br>\n");
		sb.append("选择小类： <select style='width:100px;' id='").append(baseId);
		sb.append("_selectType2' onchange=\"resetSelectOption2('");
		sb.append(baseId).append("');\"><option value=0>");
		sb.append("--先选择大类--</option></select></div>\n");
		sb.append("<div>新闻来源： <input type='button' id='");
		sb.append(baseId).append("_selectType3' ");
		sb.append("onclick=\"showModuleCheckNewsLayer('");
		sb.append(baseId).append("')\" ");
		sb.append("value='点击(可选)' style='width:100px;'></div>");
		sb.append("<input type='checkbox' id='").append(baseId);
		sb.append("_checkShowFrom' ");
		if (row.isShow_from())
			sb.append("checked ");
		sb.append(">显示来源");
		sb.append("<input type='checkbox' id='").append(baseId);
		sb.append("_checkShowTime' ");
		if (row.isShow_time())
			sb.append("checked ");
		sb.append(">显示时间");
		sb.append("<input type='checkbox' id='").append(baseId);
		sb.append("_checkShowTip' ");
		if (row.isShow_tip())
			sb.append("checked ");
		sb.append(">浮动摘要");
		sb.append("<br>每页显示： <select id='").append(baseId);
		sb.append("_selectShowNumber'>\n");
		for (int j = 5; j <= 30; j += 5) {
			if (j == limit)
				sb.append("<option value='" + j + "' selected>" + j + "\n");
			else
				sb.append("<option value='" + j + "'>" + j + "\n");
		}
		sb.append("</select> 项 ");
		sb.append("<div id='").append(baseId);
		sb.append("_saveSettingsButtonDiv' style='display:block'>");
		sb.append("<input class='submitbtn' type='button' value='保存设置' id='");
		sb.append(baseId).append("_saveModuleButton' ");
		sb.append("onclick=\"saveModuleSettings('").append(position);
		sb.append("','").append(baseId).append("',1,'您的设置已成功保存！'");
		sb.append(");\"></div>");
		sb.append("<div id='").append(baseId);
		sb.append("_resubscribeButtonDiv' style='display:none'>");
		sb.append("<input type=button value='预览' onclick=\"newsPreview('");
		sb.append(position).append("','").append(baseId);
		sb.append("');\" disabled='true' id='");
		sb.append(baseId).append("_newsPreviousButton'>");
		sb.append("<input class='submitbtn' id='").append(baseId);
		sb.append("_resubscribeButton' type='button' value='重新订阅' ");
		sb.append("onclick=\"module_resubscribe('").append(baseId);
		sb.append("','").append(position).append("');\" disabled></div>\n");

		sb.append("</form>\n").append("</div></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr align='center'><td colspan=5>");
		sb.append("<span id='").append(baseId);
		sb.append("_message' style='display: none'>");
		sb.append("Message<span></td></tr>");
		sb.append("<tr><td colspan=5>\n");
		sb.append("<table class='mc'>\n");
		sb.append("<tbody id='").append(baseId).append("_table'>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</td></tr>\n");
		sb.append("<tr align='right'><td>");
		sb.append("<span id='").append(baseId);
		sb.append("_indicator' style='visibility:hidden'>");
		sb.append("<img src='images/indicator.gif' alt='Loading'>");
		sb.append("<font color='green'>Loading... </font></span>&nbsp;");
		sb.append("<span id='").append(baseId).append("_pageInfo'>");
		sb.append("</span>");
		sb.append("</td><td colspan=4>");
		sb.append("<img src='images/button_arrow_left.gif' ");
		sb.append("width=10 height=10 ");
		sb.append("style='visibility:hidden;cursor:pointer' ");
		sb.append("onclick=\"module_pageSplit('");
		sb.append(baseId).append("',-1)\" id='").append(baseId);
		sb.append("_pagerSplit_p' alt='上一页'>&nbsp;");
		sb.append("<img src='images/button_arrow_right.gif' ");
		sb.append("width=10 height=10 ");
		sb.append("style='visibility:hidden;cursor:pointer' ");
		sb.append("onclick=\"module_pageSplit('");
		sb.append(baseId).append("',1)\" id='").append(baseId);
		sb.append("_pagerSplit_n' alt='下一页'>");
		sb.append("</td></tr>");
		sb.append("</table>\n");
		sb.append("</h2>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public static String getUserDefineRssModuleHtml(Row row, int position) {
		row.setTotalResult(-1);
		StringBuffer sb = new StringBuffer();

		String baseId = row.getId();
		String title = row.getTitle();
		String rssAddress = row.getRssAddress();
		int limit = row.getShowNumber();
		sb.append("<div id='").append(baseId);
		sb.append("' class='modbox' style='position: relative'>\n");
		sb.append("<h2 class='modtitle'>\n");
		sb.append("<table class='mhdr' ");
		sb.append("cellspacing='0' cellpadding='0'>\n");
		sb.append("<tr>\n");
		sb.append("<td id='").append(baseId);
		sb.append("_title' class='mttl'>");
		sb.append("<span class='mttli' id='");
		sb.append(baseId).append("_titleContent'>");
		sb.append(title).append("</span></td>\n");
		sb.append("<td class=medit>");
		sb.append("<a href='#' onClick=\"module_refresh('");
		sb.append(baseId).append("');\"> ");
		sb.append("<img src='images/view-refresh.gif' ");
		sb.append("style='cursor:hand' alt='刷新' class=mdel></a></td>\n");
		sb.append("<td class='medit'>");
		sb.append("<a href='#' src='images/arrow.gif' id='").append(baseId);
		sb.append("_editText'").append(" onClick=\"return module_edit('");
		sb.append(baseId).append("');\">编辑</a></td>\n");
		sb.append("<td class='medit'>");
		sb.append("<a href='#' onClick=\"return module_del('");
		sb.append(position).append("','");
		sb.append(baseId).append("'");
		sb.append(")\" id='").append(baseId);
		sb.append("_del' class='medit'><img alt='删除' src='images/x.gif' ");
		sb.append("class=mdel></a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td colspan='5' bgcolor='#e5ecf9'>\n");
		sb.append("<div id='").append(baseId);
		sb.append("_editContent' style='padding: 5px; display: none;'>\n");
		sb.append("<form id='").append(baseId).append("_editForm'>");
		JSONObject settings = JSONObject.fromObject(row);
		sb.append("<input type='hidden' value='").append(settings.toString());
		sb.append("' id='").append(baseId).append("_settings'>");
		sb.append("这是您自定义的RSS<br>");
		sb.append("编辑标题： <input type='text' value='");
		sb.append(title).append("' id='").append(baseId);
		sb.append("_titleText' size=10>");
		sb.append("<br>RSS地址： <input type='text' value='");
		sb.append(rssAddress).append("' id='").append(baseId);
		sb.append("_rssAddress' size=20 onchange=\"changeValidateButton('");
		sb.append(baseId).append("_saveModuleButton')\">");
		sb.append("<br><input type='checkbox' id='");
		sb.append(baseId).append("_checkShare' ");
		if (row.isShare())
			sb.append("checked ");
		sb.append(">网友共享");
		sb.append("<input type='checkbox' id='").append(baseId);
		sb.append("_checkShowTime' ");
		if (row.isShow_time())
			sb.append("checked ");
		sb.append(">显示时间");
		sb.append("<input type='checkbox' id='").append(baseId);
		sb.append("_checkShowTip' ");
		if (row.isShow_tip())
			sb.append("checked ");
		sb.append(">浮动摘要");
		sb.append("<br>每页显示： <select id='").append(baseId);
		sb.append("_selectShowNumber'>\n");
		for (int j = 5; j <= 30; j += 5) {
			if (j == limit)
				sb.append("<option value='" + j + "' selected>" + j + "\n");
			else
				sb.append("<option value='" + j + "'>" + j + "\n");
		}
		sb.append("</select> 项 ");
		sb.append("<input class='submitbtn' type='button' value='保存设置' id='");
		sb.append(baseId).append("_saveModuleButton' ");
		sb.append("onclick=\"validateModuleRss('").append(position);
		sb.append("','").append(baseId);
		sb.append("');\"></form>\n").append("</div></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr align='center'><td colspan=5>");
		sb.append("<span id='").append(baseId);
		sb.append("_message' style='display: none'>");
		sb.append("Message<span></td></tr>");
		sb.append("<tr><td colspan=5>\n");
		sb.append("<table class='mc'>\n");
		sb.append("<tbody id='").append(baseId).append("_table'>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</td></tr>\n");
		sb.append("<tr align='right'><td>");
		sb.append("<span id='").append(baseId);
		sb.append("_indicator' style='visibility:hidden'>");
		sb.append("<img src='images/indicator.gif' alt='Loading'>");
		sb.append("<font color='green'>Loading... </font></span>&nbsp;");
		sb.append("<span id='").append(baseId).append("_pageInfo'>");
		sb.append("</span>");
		sb.append("</td><td colspan=4>");
		sb.append("<img src='images/button_arrow_left.gif' ");
		sb.append("width=10 height=10 ");
		sb.append("style='visibility:hidden;cursor:pointer' ");
		sb.append("onclick=\"module_pageSplit('");
		sb.append(baseId).append("',-1)\" id='").append(baseId);
		sb.append("_pagerSplit_p' alt='上一页'>&nbsp;");
		sb.append("<img src='images/button_arrow_right.gif' ");
		sb.append("width=10 height=10 ");
		sb.append("style='visibility:hidden;cursor:pointer' ");
		sb.append("onclick=\"module_pageSplit('");
		sb.append(baseId).append("',1)\" id='").append(baseId);
		sb.append("_pagerSplit_n' alt='下一页'>");
		sb.append("</td></tr>");
		sb.append("</table>\n");
		sb.append("</h2>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public static String getLinkModuleHtml(Row row, int position) {
		StringBuffer sb = new StringBuffer();

		String baseId = row.getId();
		String title = row.getTitle();
		sb.append("<div id='").append(baseId);
		sb.append("' class='modbox' style='position: relative'>\n");
		sb.append("<h2 class='modtitle'>\n");
		sb.append("<table class='mhdr' ");
		sb.append("cellspacing='0' cellpadding='0'>\n");
		sb.append("<tr>\n");
		sb.append("<td id='").append(baseId);
		sb.append("_title' class='mttl'>");
		sb.append("<span class='mttli' id='");
		sb.append(baseId).append("_titleContent'>");
		sb.append(title).append("</span></td>\n");
		sb.append("<td class='medit'>");
		sb.append("<a href='#' onclick=link_add('").append(baseId);
		sb.append("','").append(row.getId()).append("');");
		sb.append(" id='link_add_").append(baseId).append("'>添加</a>&nbsp;");
		sb.append("<a href='#' src='images/arrow.gif' id='").append(baseId);
		sb.append("_editText'").append(" onClick=\"return module_edit('");
		sb.append(baseId).append("');\">编辑</a></td>\n");
		sb.append("<td class='medit'>");
		sb.append("<a href='#' onClick=\"return module_del('");
		sb.append(position).append("','");
		sb.append(baseId).append("'");
		sb.append(")\" id='").append(baseId);
		sb.append("_del' class='medit'><img alt='删除' src='images/x.gif' ");
		sb.append("class=mdel></a></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td colspan='4' bgcolor='#e5ecf9'>\n");
		sb.append("<div id='").append(baseId);
		sb.append("_editContent' style='padding: 5px; display: none;'>\n");
		sb.append("<form id='").append(baseId).append("_editForm'>");
		JSONObject settings = JSONObject.fromObject(row);
		settings.put("total", 0);
		settings.put("order", "");
		sb.append("<input type='hidden' value='").append(settings.toString());
		sb.append("' id='").append(baseId).append("_settings'>");
		sb.append("编辑标题： <input type='text' value='");
		sb.append(title).append("' id='").append(baseId);
		sb.append("_titleText' size=10>");
		sb.append("<br><input type='checkbox' id='");
		sb.append(baseId).append("_checkShare' ");
		if (row.isShare())
			sb.append("checked ");
		sb.append(">网友共享");
		sb.append("<input type='checkbox' id='").append(baseId);
		sb.append("_checkShowTip' ");
		if (row.isShow_tip())
			sb.append("checked ");
		sb.append(">浮动摘要");
		sb.append("<input class='submitbtn' type='button' value='保存设置' id='");
		sb.append(baseId).append("_saveModuleButton' ");
		sb.append("onclick=\"saveModuleSettings('").append(position);
		sb.append("','").append(baseId);
		sb.append("',").append(row.getType());
		sb.append(",'您的设置已成功保存！');\"></form>\n");
		sb.append("</div></td>\n");
		sb.append("</tr>\n");
		sb.append("<tr align='center'><td colspan=5>");
		sb.append("<span id='").append(baseId);
		sb.append("_message' style='display: none'>");
		sb.append("Message<span></td></tr>");
		sb.append("<tr><td colspan=5>\n");
		sb.append("<div class='mc'");
		sb.append(" id='").append(baseId).append("_table'>\n");
		sb.append("<span id='").append(baseId);
		sb.append("_indicator'>");
		sb.append("<img src='images/indicator.gif' alt='Loading'>");
		sb.append("<font color='green'>Loading... </font></span>");
		sb.append("</div>\n");
		sb.append("</td></tr>");
		sb.append("</table>\n");
		sb.append("</h2>\n");
		sb.append("</div>\n");
		return sb.toString();
	}
}
