package com.portal.data.html;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.portal.data.pojo.Row;
import com.portal.data.pojo.Tab;

/**
 * Developer: Terry DateTime : 2007-12-17 下午02:52:31
 */

public class TabHtml {
	public static String getTabHtml(int position, Tab tab,
			HashMap<String, Row> rows) {
		JSONObject jo = new JSONObject();
		switch (tab.getType()) {
		case 1:
			jo.put("type", 1);
			jo.put("position", position);
			jo.put("content", getRssTabHtml(position, tab, rows));
			return jo.toString();
		case 2:
			jo.put("type", 2);
			jo.put("position", position);
			jo.put("content", getLinkTabHtml(position, tab, rows));
			return jo.toString();
		case 3:
			jo.put("type", 3);
			jo.put("position", position);
			jo.put("content", "");
		default:
			return null;
		}
	}

	public static String getRssTabHtml(int position, Tab tab,
			HashMap<String, Row> rows) {
		StringBuffer sb = new StringBuffer();

		sb.append("<table cellpadding=0 cellspacing=0 ");
		sb.append("border=0 width=100% height=100%>\n");

		sb.append("<tr>\n");

		/*
		 * 添加内容
		 */
		sb.append("<td valign=top height=100%>\n");
		sb.append("<table class=mhdr ");
		sb.append("cellspacing='0' cellpadding='0'><tr>\n");
		sb.append("<td id='tab").append(position);
		sb.append("_addContentPanel' style='display:none'>\n<div ");
		sb.append("style='width:14em;border-right:1px solid #3366cc;'>");
		sb.append("<div class=cpromo onClick=\"hideAddContentPanel('tab");
		sb.append(position).append("')\">关闭</div>\n");
		sb.append("<br>\n");
		sb.append("<div>");
		sb.append("<br><font color=green>--添加本站内容--</font>");
		sb.append("<br><input type='checkbox' id='tab").append(position);
		sb.append("_changeTitleDefault' onclick=\"changeTitleDefault('tab");
		sb.append(position).append("')\"> 标题：<input type='text' id='tab");
		sb.append(position).append("_titleText' ");
		sb.append(" size=10 disabled value='' >");
		sb.append("<br>选择大类：<select onchange=\"showSelectOptions2('tab");
		sb.append(position).append("',0);\" id='tab");
		sb.append(position).append("_selectType1' style='width:100px;'>\n");
		sb.append("</select>\n");
		sb.append("<br>选择小类：<select onchange=\"showSelectOptions3('tab");
		sb.append(position).append("');\" id='tab");
		sb.append(position).append("_selectType2' style='width:100px;'>");
		sb.append("<option value=0>--先选择大类--</option>");
		sb.append("</select>\n");
		sb.append("<br>选择来源：<input type='button' id='tab");
		sb.append(position).append("_selectType3' disabled ");
		sb.append("onclick=\"showCheckNewsLayer('tab");
		sb.append(position).append("')\" value='点击(可选)' style='width:100px;'>");
		sb.append("<br><input type='checkbox' id='tab").append(position);
		sb.append("_checkShowFrom'>显示来源");
		sb.append("<input type='checkbox' id='tab").append(position);
		sb.append("_checkShowTime'>显示时间");
		sb.append("<br><input type='checkbox' id='tab").append(position);
		sb.append("_checkShowTip'>浮动摘要");
		sb.append("<br>每页显示：<select id='tab").append(position);
		sb.append("_selectShowNumber'>\n");
		for (int j = 5; j <= 30; j += 5) {
			if (j == 10)
				sb.append("<option value='" + j + "' selected>" + j + "\n");
			else
				sb.append("<option value='" + j + "'>" + j + "\n");
		}
		sb.append("</select> 项 ");
		sb.append("<input type=button value='预览' onclick=\"newsPreview('");
		sb.append(position).append("')\" disabled='true' id='tab");
		sb.append(position).append("_newsPreviousButton'>");
		sb.append("<br>添加位置：<select id='tab").append(position);
		sb.append("_addColPosition'>\n");
		for (int j = 1; j <= 3; j++) {
			if (j == 1)
				sb.append("<option value='" + (j - 1) + "' selected>" + j
						+ "\n");
			else
				sb.append("<option value='" + (j - 1) + "'>" + j + "\n");
		}
		sb.append("</select> 列 ");
		sb.append("<input type='hidden' value='");
		sb.append("' id='tab").append(position).append("_exSelectType3'>");
		sb.append("<input type='button' id='tab");
		sb.append(position).append("_addButton' disabled ");
		sb.append("onclick=\"addModuleToTab('");
		sb.append(position).append("')\" value='添加 &raquo;'>");

		sb.append("<br><br><font color=green>--添加自定义RSS--</font>");
		sb.append("<br>输入标题：<input type='text' id='tab");
		sb.append(position).append("_ctitleText' ");
		sb.append(" size=10 value='' >");
		sb.append("<br>RSS地址：<input type='text' id='tab");
		sb.append(position).append("_crssAddress' ");
		sb.append(" size=10 value='http://' onchange=\"changeValidateButton('");
		sb.append("tab").append(position).append("_addcButton',true)\">");
		sb.append("<br><input type='checkbox' id='tab");
		sb.append(position).append("_checkcShare'>网友共享");
		sb.append("<input type='checkbox' id='tab").append(position);
		sb.append("_checkcShowTime'>显示时间");
		sb.append("<br><input type='checkbox' id='tab").append(position);
		sb.append("_checkcShowTip'>浮动摘要");
		sb.append("<br>每页显示：<select id='tab").append(position);
		sb.append("_selectcShowNumber'>\n");
		for (int j = 5; j <= 30; j += 5) {
			if (j == 10)
				sb.append("<option value='" + j + "' selected>" + j + "\n");
			else
				sb.append("<option value='" + j + "'>" + j + "\n");
		}
		sb.append("</select> 项 ");
		sb.append("<br>添加位置：<select id='tab").append(position);
		sb.append("_addcColPosition'>\n");
		for (int j = 1; j <= 3; j++) {
			if (j == 1)
				sb.append("<option value='" + (j - 1) + "' selected>" + j
						+ "\n");
			else
				sb.append("<option value='" + (j - 1) + "'>" + j + "\n");
		}
		sb.append("</select> 列 ");
		sb.append("<input type='button' id='tab");
		sb.append(position).append("_addcButton' ");
		sb.append("onclick=\"validateAddRss('");
		sb.append(position).append("')\" value='验证'>");

		sb.append("<div><br>新闻预览&nbsp;");
		sb.append("<span id='tab").append(position);
		sb.append("_indicator' style='visibility:hidden'>");
		sb.append("<img src='images/indicator.gif' alt='Loading'>");
		sb.append("<font color='green'>Loading... </font></span>");
		sb.append("<table class='mc'>\n");
		sb.append("<tbody id='tab").append(position).append("_table'>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");

		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</td></tr></table>\n");

		sb.append("</td>\n");

		sb.append("<td valign=top width=100%>\n");
		sb.append("<div>\n");
		sb.append("<table class=panelc width=100% ");
		sb.append("cellspacing=0 cellpadding=0 border=0>\n");
		sb.append("<tr>\n");
		sb.append("<td>\n");
		sb.append("<input id='tab").append(position);
		sb.append("_addContentButton'");
		sb.append(" type='button' value='添加内容' class='apromo' ");
		sb.append("onclick=\"showAddContentPanel('tab").append(position);
		sb.append("');\"/>");
		sb.append("<input type=button class='apromo' id='tab");
		sb.append(position).append("_tabSettingsButton' ");
		sb.append("onclick=\"showTabSettingsLayer(").append(position);
		sb.append(");\" value='页面设置'/>\n");
		sb.append("</td>\n");

		sb.append("<td>");
		sb.append("<div id='marqueeBox_").append(position);
		sb.append("' class='scrollMessage' ");
		sb.append("onmouseover=\"clearInterval(marqueeInterval[0])\" ");
		sb
				.append("onmouseout=\"marqueeInterval[0]=setInterval('startMarquee()',marqueeDelay)\">");
		sb.append("<div><font color='green'>欢迎您访问“").append(tab.getTitle());
		sb.append("”！</font><br></div></div>");
		sb.append("</td>");

		sb.append("<td>\n");
		sb.append("<div>\n");
		sb.append("<table border=0 cellspacing=0 cellpadding=0 width=100%>\n");
		sb.append("<tr>\n");
		sb.append("<td align=right nowrap>");

		sb.append("<input type='button' style='background:white;'");
		sb.append("value='已经保存' id='tab");
		sb.append(position).append("_saveTabButton' disabled ");
		sb.append("onclick=\"saveTabSettings('").append(position);
		sb.append("','您的页面已成功保存！');\"/></td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");

		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</div>");

		sb.append("<noscript>");
		sb.append("<center><br>");
		sb.append("<span style='padding: 2px; background-color: #fad163;'>");
		sb.append("您需要启动JavaScript才能使用本网页</span><br>");
		sb.append("</center>");
		sb.append("</noscript>");

		/*
		 * 正文内容
		 */

		sb.append("<div>");
		sb.append("<table id=tab_").append(position);
		sb.append(" cellspacing=10 border=0 width=100% align=center ");
		sb.append("style='table-layout: fixed;'>");

		sb.append("<tr>");

		sb.append("<td id='tab").append(position).append("_col0' ");
		sb.append("style='width: 32%;vertical-align: top;'>");
		String col0Html = getRssModuleContent(position, 0, tab.getCol0(), rows);
		if (col0Html != null)
			sb.append(col0Html);
		sb.append("</td>");
		sb.append("<td id='tab").append(position).append("_col1' ");
		sb.append("style='width: 32%;vertical-align: top;'>");
		String col1Html = getRssModuleContent(position, 1, tab.getCol1(), rows);
		if (col1Html != null)
			sb.append(col1Html);
		sb.append("</td>");
		sb.append("<td id='tab").append(position).append("_col2' ");
		sb.append("style='width: 32%;vertical-align: top;'>");
		String col2Html = getRssModuleContent(position, 2, tab.getCol2(), rows);
		if (col2Html != null)
			sb.append(col2Html);
		sb.append("</td>");

		sb.append("</tr>");

		sb.append("</table>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");

		return sb.toString();
	}

	public static String getLinkTabHtml(int position, Tab tab,
			HashMap<String, Row> rows) {
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=0 cellspacing=0 ");
		sb.append("border=0 width=100% height=100%>\n");

		sb.append("<tr>\n");

		/*
		 * 添加内容
		 */
		sb.append("<td valign=top height=100%>\n");
		sb.append("<table class=mhdr ");
		sb.append("cellspacing='0' cellpadding='0'><tr>\n");
		sb.append("<td id='tab").append(position);
		sb.append("_addContentPanel' style='display:none'>\n<div ");
		sb.append("style='width:14em;border-right:1px solid #3366cc;'>");
		sb.append("<div class=cpromo onClick=\"hideAddContentPanel('tab");
		sb.append(position).append("')\">关闭</div>\n");
		sb.append("<br>\n");
		sb.append("<div>");
		sb.append("<br><font color=green>--添加本站内容--</font>");
		sb.append("<br><input type='checkbox' id='tab").append(position);
		sb.append("_changeTitleDefault' onclick=\"changeTitleDefault('tab");
		sb.append(position).append("')\"> 标题：<input type='text' id='tab");
		sb.append(position).append("_titleText' ");
		sb.append(" size=10 disabled value='' >");
		sb.append("<br>选择大类：<select onchange=\"link_showSelectOptions2('tab");
		sb.append(position).append("');\" id='tab");
		sb.append(position).append("_selectType1' style='width:100px;'>\n");
		sb.append("</select>\n");
		sb.append("<br>选择小类：<select onchange=\"link_showSelectOptions3('tab");
		sb.append(position).append("');\" id='tab");
		sb.append(position).append("_selectType2' style='width:100px;'>");
		sb.append("<option value=0>--先选择大类--</option>");
		sb.append("</select>\n");
		sb.append("<br>最多显示：<select id='tab").append(position);
		sb.append("_selectShowNumber'>\n");
		for (int j = 5; j <= 30; j += 5) {
			if (j == 10)
				sb.append("<option value='" + j + "' selected>" + j + "\n");
			else
				sb.append("<option value='" + j + "'>" + j + "\n");
		}
		sb.append("</select> 项 ");
		sb.append("<input type=button value='预览' onclick=\"linksPreview('");
		sb.append(position).append("')\" disabled='true' id='tab");
		sb.append(position).append("_linksPreviousButton'>");
		sb.append("<br>添加位置：<select id='tab").append(position);
		sb.append("_addColPosition'>\n");
		for (int j = 1; j <= 3; j++) {
			if (j == 1)
				sb.append("<option value='" + (j - 1) + "' selected>" + j
						+ "\n");
			else
				sb.append("<option value='" + (j - 1) + "'>" + j + "\n");
		}
		sb.append("</select> 列 ");
		sb.append("<input type='button' id='tab");
		sb.append(position).append("_addButton' disabled ");
		sb.append("onclick=\"link_addModuleToTab('");
		sb.append(position).append("')\" value='添加 &raquo;'>");
		
		sb.append("<br><br><font color=green>--添加空的模块--</font>");
		sb.append("<br>输入标题：<input type='text' id='tab");
		sb.append(position).append("_ctitleText' ");
		sb.append(" size=10 value='' >");
		sb.append("<br><input type='checkbox' id='tab");
		sb.append(position).append("_checkcShare'>网友共享");
		sb.append("<input type='checkbox' id='tab").append(position);
		sb.append("_checkcShowTip'>浮动摘要");
		sb.append("<br>添加位置：<select id='tab").append(position);
		sb.append("_addcColPosition'>\n");
		for (int j = 1; j <= 3; j++) {
			if (j == 1)
				sb.append("<option value='" + (j - 1) + "' selected>" + j
						+ "\n");
			else
				sb.append("<option value='" + (j - 1) + "'>" + j + "\n");
		}
		sb.append("</select> 列 ");
		sb.append("<input type='button' id='tab");
		sb.append(position).append("_addcButton' ");
		sb.append("onclick=\"link_addcModuleToTab('");
		sb.append(position).append("')\" value='添加 &raquo;'>");

		sb.append("<div><br>网址预览&nbsp;");
		sb.append("<span id='tab").append(position);
		sb.append("_indicator' style='visibility:hidden'>");
		sb.append("<img src='images/indicator.gif' alt='Loading'>");
		sb.append("<font color='green'>Loading... </font></span>");
		sb.append("<table class='mc'>\n");
		sb.append("<tbody id='tab").append(position).append("_table'>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");

		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</td></tr></table>\n");

		sb.append("</td>\n");

		sb.append("<td valign=top width=100%>\n");
		sb.append("<div>\n");
		sb.append("<table class=panelc width=100% ");
		sb.append("cellspacing=0 cellpadding=0 border=0>\n");
		sb.append("<tr>\n");
		sb.append("<td>\n");
		sb.append("<input id='tab").append(position);
		sb.append("_addContentButton'");
		sb.append(" type='button' value='添加内容' class='apromo' ");
		sb.append("onclick=\"link_showAddContentPanel('tab").append(position);
		sb.append("');\"/>");
		sb.append("<input type=button class='apromo' id='tab");
		sb.append(position).append("_tabSettingsButton' ");
		sb.append("onclick=\"showTabSettingsLayer(").append(position);
		sb.append(");\" value='页面设置'/>\n");
		sb.append("</td>\n");

		sb.append("<td>");
		sb.append("<div id='marqueeBox_").append(position);
		sb.append("' class='scrollMessage' ");
		sb.append("onmouseover=\"clearInterval(marqueeInterval[0])\" ");
		sb
				.append("onmouseout=\"marqueeInterval[0]=setInterval('startMarquee()',marqueeDelay)\">");
		sb.append("<div><font color='green'>欢迎您访问“").append(tab.getTitle());
		sb.append("”！</font><br></div></div>");
		sb.append("</td>");

		sb.append("<td>\n");
		sb.append("<div>\n");
		sb.append("<table border=0 cellspacing=0 cellpadding=0 width=100%>\n");
		sb.append("<tr>\n");
		sb.append("<td align=right nowrap>");

		sb.append("<input type='button' style='background:white;'");
		sb.append("value='已经保存' id='tab");
		sb.append(position).append("_saveTabButton' disabled ");
		sb.append("onclick=\"saveTabSettings('").append(position);
		sb.append("','您的页面已成功保存！');\"/></td>\n");
		sb.append("</tr>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");

		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</div>");

		sb.append("<noscript>");
		sb.append("<center><br>");
		sb.append("<span style='padding: 2px; background-color: #fad163;'>");
		sb.append("您需要启动JavaScript才能使用本网页</span><br>");
		sb.append("</center>");
		sb.append("</noscript>");

		/*
		 * 正文内容
		 */

		sb.append("<div>");
		sb.append("<table id=tab_").append(position);
		sb.append(" cellspacing=10 border=0 width=100% align=center ");
		sb.append("style='table-layout: fixed;'>");

		sb.append("<tr>");

		sb.append("<td id='tab").append(position).append("_col0' ");
		sb.append("style='width: 32%;vertical-align: top;'>");
		String col0Html = getLinkModuleContent(position, 0, tab.getCol0(), rows);
		if (col0Html != null)
			sb.append(col0Html);
		sb.append("</td>");
		sb.append("<td id='tab").append(position).append("_col1' ");
		sb.append("style='width: 32%;vertical-align: top;'>");
		String col1Html = getLinkModuleContent(position, 1, tab.getCol1(), rows);
		if (col1Html != null)
			sb.append(col1Html);
		sb.append("</td>");
		sb.append("<td id='tab").append(position).append("_col2' ");
		sb.append("style='width: 32%;vertical-align: top;'>");
		String col2Html = getLinkModuleContent(position, 2, tab.getCol2(), rows);
		if (col2Html != null)
			sb.append(col2Html);
		sb.append("</td>");

		sb.append("</tr>");

		sb.append("</table>");
		sb.append("</div>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</table>");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private static String getRssModuleContent(int position, int col_number,
			String col_content, HashMap<String, Row> rows) {
		JSONArray colArray = JSONArray.fromObject(col_content);
		int module_number = colArray.size();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < module_number; i++) {
			String id = (String) colArray.get(i);
			Row row = rows.get(id);
			if (row == null)
				return null;
			switch (row.getType()) {
			case 1:

				sb.append(ModuleHtml.getSystemRssModuleHtml(row, position));
				break;
			case 2:
				sb.append(ModuleHtml.getUserDefineRssModuleHtml(row, position));
				break;
			}
		}
		sb.append("<DIV class=dm></DIV>\n");
		return sb.toString();
	}

	private static String getLinkModuleContent(int position, int col_number,
			String col_content, HashMap<String, Row> rows) {
		JSONArray colArray = JSONArray.fromObject(col_content);
		int module_number = colArray.size();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < module_number; i++) {
			String id = (String) colArray.get(i);
			Row row = rows.get(id);
			if (row == null)
				return null;

			sb.append(ModuleHtml.getLinkModuleHtml(row, position));
		}
		sb.append("<DIV class=dm></DIV>\n");
		return sb.toString();
	}
}
