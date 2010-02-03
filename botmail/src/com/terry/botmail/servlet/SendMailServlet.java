package com.terry.botmail.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terry.botmail.data.impl.ScheduleDaoImpl;
import com.terry.botmail.data.intf.IScheduleDao;
import com.terry.botmail.model.Schedule;
import com.terry.botmail.util.MailSender;
import com.terry.botmail.util.StringUtil;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:35:50
 */
public class SendMailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -843840894946959108L;

	private IScheduleDao scheduleDao = new ScheduleDaoImpl();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		String id = req.getParameter("id");
		if (StringUtil.isEmptyOrWhitespace(id))
			return;
		Schedule schedule = scheduleDao.getScheduleById(id);
		if (schedule == null)
			return;
		try {
			MailSender.sendMail(schedule.getMobile() + "@139.com", schedule
					.getSubject(), schedule.getContent());
			if (schedule.getType() == 0)
				scheduleDao.deleteSchedule(schedule);
			else {
				Calendar c_sdate = Calendar.getInstance();
				c_sdate.setTime(schedule.getSdate());
				switch (schedule.getType()) {
				case 1:
					c_sdate.add(Calendar.YEAR, 1);
					break;
				case 2:
					c_sdate.add(Calendar.MONTH, 1);
					break;
				case 3:
					c_sdate.add(Calendar.WEEK_OF_YEAR, 1);
					break;
				case 4:
					c_sdate.add(Calendar.DAY_OF_YEAR, 1);
					break;
				}
				schedule.setSdate(c_sdate.getTime());
				schedule.setAdate(new Date());
				scheduleDao.saveSchedule(schedule);
			}
		} catch (Exception e) {
		}
	}
}
