package com.terry.botmail.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.terry.botmail.model.Schedule;
import com.terry.botmail.util.EMF;
import com.terry.botmail.util.MailSender;
import com.terry.botmail.util.StringUtil;
import com.terry.botmail.util.XMPPSender;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:35:50
 */
public class SendMailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -843840894946959108L;

	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
			Locale.CHINA);

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
		Key key = KeyFactory.stringToKey(id);
		if (key == null || !key.isComplete())
			return;

		EntityManager em = EMF.get().createEntityManager();

		Schedule schedule = em.find(Schedule.class, key);
		if (schedule == null)
			return;
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			MailSender.sendMail(schedule.getEmail(), schedule.getAccount(),
					schedule.getSubject(), schedule.getContent());
			String report = "主题为：" + schedule.getSubject() + "的邮件已发送至："
					+ schedule.getEmail();
			if (schedule.getType() == 2)
				em.remove(schedule);
			else {
				Calendar c_sdate = Calendar.getInstance();
				c_sdate.setTime(schedule.getSdate());
				switch (schedule.getType()) {
				case 3:
					c_sdate.add(Calendar.DAY_OF_YEAR, 1);
					break;
				case 4:
					c_sdate.add(Calendar.WEEK_OF_YEAR, 1);
					break;
				case 5:
					c_sdate.add(Calendar.MONTH, 1);
					break;
				default:
					c_sdate.add(Calendar.YEAR, 1);
					break;
				}
				schedule.setSdate(c_sdate.getTime());
				schedule.setAdate(new Date());
				em.persist(schedule);
				report = report + "，下次发送日期为：" + sdf2.format(c_sdate.getTime());
			}
			tx.commit();
			XMPPSender.sendXMPP(schedule.getAccount(), report);
		} catch (Exception e) {
		}
	}
}
