package cn.edu.jiangnan.lab.data.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.edu.jiangnan.lab.data.dao.intf.IBookDao;
import cn.edu.jiangnan.lab.data.dao.intf.ILogDao;
import cn.edu.jiangnan.lab.data.pojo.Book;
import cn.edu.jiangnan.lab.data.pojo.Log;
import cn.edu.jiangnan.lab.data.service.intf.IMessageService;

public class MessageServiceImpl implements IMessageService {
	private IBookDao bookDao;
	private ILogDao logDao;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public ILogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	@Override
	public String getMessages() {
		JSONObject jo = new JSONObject();
		jo.put("type0", getType0Messages());
		jo.put("type1", getType1Messages());
		return jo.toString();
	}

	@Override
	public String getMessagesByType(int type) {
		if (type == 0)
			return getType0Messages().toString();
		else
			return getType1Messages().toString();

	}

	private JSONArray getType0Messages() {
		Calendar c = Calendar.getInstance();
		Date endDate = c.getTime();
		c.add(Calendar.MONTH, -1);
		ArrayList<Book> books = bookDao.getBooksLog(0, 30, -1, c.getTime(),
				endDate, "", "id");
		JSONArray ja = new JSONArray();
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			String color = "green";
			String action = "批准了";
			switch (book.getAction()) {
			case 0:
				color = "blue";
				action = "-尚未批准";
				break;
			case 1:
				color = "green";
				action = "-已经批准";
				break;
			case 2:
				color = "red";
				action = "-已被删除";
				break;
			case 3:
				color = "black";
				action = "-已改费用";
				break;
			default:
			}
			StringBuffer message = new StringBuffer();
			message.append("<font color=").append(color).append(">");
			message.append(sdf.format(book.getInput())).append(":&nbsp;[");
			message.append(book.getUser_name()).append("]");
			message.append("登记预约'");
			message.append(book.getEquip().getName());
			message.append("'(");
			message.append(sdf.format(book.getStart())).append("-");
			message.append(sdf.format(book.getEnd())).append(")");
			message.append(action);
			message.append("</font>");
			ja.add(message.toString());
		}
		return ja;
	}

	private JSONArray getType1Messages() {
		JSONArray ja = new JSONArray();
		Calendar c = Calendar.getInstance();
		Date endDate = c.getTime();
		c.add(Calendar.MONTH, -1);
		ArrayList<Log> logs = logDao.getLogs(0, 50, -1, c.getTime(), endDate,
				"", "id");
		for (int i = 0; i < logs.size(); i++) {
			Log log = logs.get(i);
			String color = "green";
			String action = "批准了";
			switch (log.getAction()) {
			case 0:
				color = "blue";
				action = "未批准";
				break;
			case 1:
				color = "green";
				action = "批准了";
				break;
			case 2:
				color = "red";
				action = "删除了";
				break;
			case 3:
				color = "black";
				action = "改费用";
				break;
			default:
			}
			StringBuffer message = new StringBuffer();
			message.append("<font color=");
			message.append(color).append(">");
			message.append(sdf.format(log.getInput())).append(":&nbsp;[");
			message.append(log.getAdmin_name()).append("]");
			message.append(action);
			message.append("'").append(log.getUser_name()).append("'");
			message.append("的预约申请('<b>");
			message.append(log.getEquip_name()).append("</b>'");
			message.append(sdf.format(log.getStart())).append("-");
			message.append(sdf.format(log.getEnd())).append(")");
			message.append("</font>");

			ja.add(message.toString());
		}
		return ja;
	}

	@Override
	public String getIntrodoceMessagesByType(int type) {
		StringBuffer sb = new StringBuffer("");
		String img = "<img src='resources/icons/note.png'>";
		if (type == 0) {
			sb.append("<br/>");
			sb
					.append("<h2 style='font-size:small' align='center'>江南大学食品学院仪器预约系统 使用简要说明</h2><br/>");
			sb.append(img).append(
					"用户使用本系统登记预约想要使用的设备，经该设备所在组别的负责人批准后方可使用；<br/><br/>");
			sb.append(img).append(
					"点击标签栏的“常用设备”、“分类设备”、“搜索设备”选择到要预约的设备；<br/><br/>");
			sb
					.append("&nbsp;“常用设备”列出最长被预约的设备，“搜索设备”输入关键词后需要指定搜索的字段，默认搜索字段为“设备名称”；<br/><br/>");
			sb
					.append("&nbsp;找到需要使用的设备后，点击记录的右键，选择“预约该设备”或双击设备记录进行登记预约操作，点击“设备预约列表”查看该设备当前的预约情况；<br/><br/>");
			sb
					.append(img)
					.append(
							"单击工具栏“我的预约信息”查看自己的预约状态，在这里可以进行“修改预约信息”、“撤销预约”等操作；<br/><br/>");
			sb.append(img).append(
					"单击工具栏“我的审批信息”查看自己的审批状态，在这里可以进行“查看负责人留言”的操作；<br/><br/>");
			sb.append(img).append("两个欢迎界面显示的是最近所有学生的预约列表和审批信息列表；<br/><br/>");
			sb.append(img).append(
					"<b>若使用中画面出现错误，请尝试清空浏览器缓存，并刷新整个页面；</b><br/><br/>");
			sb
					.append(img)
					.append(
							"如有任何意见和建议请到首页<a href='form_feedback.jsp'>投诉和反馈</a>，或<a href='http://sites.google.com/site/it/'>联系程序设计</a>，谢谢！");
		} else if (type == 1) {
			sb.append("<br/>");
			sb
					.append("<h2 style='font-size:small' align='center'>江南大学食品学院设备预约系统后台 使用简要说明</h2><br/>");
			sb.append(img).append("权限说明：<br/><br/>");
			sb
					.append("&nbsp;身份为“设备管理员”的用户，只能进行该用户所在组的设备的审批、留言、取消等操作，以及查看该用户所在组的设备的查询和设备预约状态的开关，不能查看和管理用户信息和公告信息；<br/><br/>");
			sb
					.append("&nbsp;身份为“管理员”的用户具有该用户所在组的设备的审批、留言、取消等操作，以及设备的查询和一起预约状态的开关，管理用户和公告反馈等；<br/><br/>");
			sb
					.append("&nbsp;需要修改设备其他的信息，需要到<a href='equip.action'>设备管理系统</a>进行相关的操作；<br/><br/>");
			sb.append(img).append("预约审批说明：<br/><br/>");
			sb
					.append("&nbsp;只有“是否过期”显示为“否”的预约记录才能批准，如果为用户留言，默认则为未批准该预约，但学生可以在前台看到该留言，并可对预约的细节做修改，可重新通过批准；<br/><br/>");
			sb.append("&nbsp;如果选择“删除预约”，则前台学生无法修改该次预约；<br/><br/>");
			sb
					.append("&nbsp;可以选择多条预约记录进行批量操作，注意如果多个预约是同一台设备且时间上有冲突，系统会将排在前面的预约批准；<br/><br/>");
			sb.append(img).append("公告反馈说明：<br/><br/>");
			sb
					.append("&nbsp;默认显示的是前台主页显示的公告，如要查看非前台显示的公告，请钩掉工具栏的“前台显示的记录”选框；<br/><br/>");
			sb
					.append("&nbsp;“公告管理”的编辑使用的是HTML编辑器，可以对公告进行格式化、超链接等处理；<br/><br/>");
			sb.append("&nbsp;“反馈管理”没有HTML编辑器，其他操作同“公告管理”。<br/><br/>");
			sb.append(img).append("<b>若使用中画面出现错误，请尝试清空浏览器缓存，并刷新整个页面；也可<a href='http://sites.google.com/site/it/'>联系程序设计</a>，</b>");
		}
		return sb.toString();
	}

}
