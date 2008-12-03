package cn.edu.jiangnan.lab.data.dao.intf;

import java.util.ArrayList;

import cn.edu.jiangnan.lab.data.pojo.Feedback;

public interface IFeedbackDao {
	public boolean saveFeedback(Feedback feedback);

	public boolean deleteFeedback(Feedback feedback);

	public Feedback getFeedbackById(String id);

	public ArrayList<Feedback> getFeedbacks(int start, int limit, boolean pub);

	public long getCountFeedbacks(boolean pub);
}
