package com.terry.weatherlib.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
import com.terry.weatherlib.data.impl.ScheduleDaoImpl;
import com.terry.weatherlib.data.intf.IScheduleDao;
import com.terry.weatherlib.model.Schedule;

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

	private Cache cache;

	private static final String KEY = "check-status";

	public static final String SCHEDULE_ID_KEY = "schedule-id";

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws ServletException {
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		try {
			cache = CacheManager.getInstance().getCacheFactory().createCache(
					props);
		} catch (CacheException e) {
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		doPost(req, res);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		if (req.getParameter("recheck") != null) {
			if (cache.get(KEY) == null)
				return;
		}
		cache.put(KEY, Boolean.TRUE);
		List<Schedule> schedules = scheduleDao.getReadyToToSchedules();
		if (schedules == null || schedules.size() == 0)
			return;
		for (Schedule schedule : schedules) {
			String id = schedule.getId();
			// 防止还没执行完的Schedule继续放到Queue中
			if (cache.get(SCHEDULE_ID_KEY + "-" + id) == null) {
				queue
						.add(TaskOptions.Builder.url("/cron/send").param("id",
								id));
				cache.put(SCHEDULE_ID_KEY + "-" + id, Boolean.TRUE);
			}
		}
		cache.remove(KEY);
	}
}
