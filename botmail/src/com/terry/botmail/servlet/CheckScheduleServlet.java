package com.terry.botmail.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions;
import com.terry.botmail.data.impl.ScheduleDaoImpl;
import com.terry.botmail.data.intf.IScheduleDao;
import com.terry.botmail.model.Schedule;

/**
 * @author xinghuo.yao E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-2-3 下午04:42:38
 */
public class CheckScheduleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -302297776402317937L;

	private IScheduleDao scheduleDao = new ScheduleDaoImpl();

	private Queue queue = QueueFactory.getDefaultQueue();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		List<Schedule> schedules = scheduleDao.getReadyToToSchedules();
		if (schedules == null || schedules.size() == 0)
			return;
		for (Schedule schedule : schedules) {
			queue.add(TaskOptions.Builder.url("/cron/send").param("id",
					schedule.getId()));
		}
	}

}
