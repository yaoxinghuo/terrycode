package com.terry.robot.logic;

import java.util.ArrayList;
import java.util.Random;

import net.sf.json.JSONObject;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：2009-6-20 上午09:04:10
 */
public class FortunetellingLogic extends ReplyLogicBase {
	private String ahit = "擲筊";
	private String qhit = "離開";

	private String aresponse = "請在心中默念三次想問的事情，再誠心誠意輸入想問的事情" + RETURN + "0.離開";
	private String qresponse = "感謝您的參加！";

	private ArrayList<Fortune> fortunes = new ArrayList<Fortune>();

	public String getAhit() {
		return ahit;
	}

	public void setAhit(String ahit) {
		this.ahit = ahit;
	}

	public String getQhit() {
		return qhit;
	}

	public void setQhit(String qhit) {
		this.qhit = qhit;
	}

	public String getAresponse() {
		return aresponse;
	}

	public void setAresponse(String aresponse) {
		this.aresponse = aresponse;
	}

	public String getQresponse() {
		return qresponse;
	}

	public void setQresponse(String qresponse) {
		this.qresponse = qresponse;
	}

	@Override
	public JSONObject reply(String message) {
		return null;
	}

	@Override
	public JSONObject reply(int action) {
		return null;
	}

	/*
	 * action:
	 * 
	 * 1：使用者可以问问题 2：系统答应抽签，使用者可以输入抽签数字
	 */
	public JSONObject reply(String message, int nextStep) {
		if (fortunes == null || fortunes.size() == 0)
			return null;
		JSONObject jo = new JSONObject();
		jo.put("response", "");
		jo.put("type", 1);
		if (message.equals(qhit) || message.equals("0")) {
			jo.put("type", 1);
			jo.put("response", qresponse);
			jo.put("step", 0);
			return jo;
		}
		switch (nextStep) {
		case 1:
			int action = new Random().nextInt(3);
			if (action == 1) {
				jo.put("type", 1);
				jo.put("response", "聖筊" + RETURN + "請輸入一個任意的數字來取籤" + RETURN
						+ "0.離開");
				jo.put("step", 2);
			} else if (action == 0) {
				jo.put("type", 1);
				jo.put("response", "陰筊" + RETURN + "神明不認同，行事會不順，可以重新再擲筊請示"
						+ RETURN + "0.離開" + RETURN + "1.繼續");
				jo.put("step", 10);
			} else {
				jo.put("type", 1);
				jo.put("response", "笑筊" + RETURN
						+ "神明還未決定要不要認同，行事狀況不明，可以重新再擲筊請示神明，或再次說清楚自己的祈求" + RETURN
						+ "0.離開" + RETURN + "1.繼續");
				jo.put("step", 10);
			}
			break;
		case 10:
			if (!message.equals("1")) {
				jo.put("type", 1);
				jo.put("response", "重新再擲筊請示神明" + RETURN + "0.離開" + RETURN
						+ "1.繼續");
				jo.put("step", 10);
			} else {
				int a = new Random().nextInt(3);
				if (a == 1) {
					jo.put("type", 1);
					jo.put("response", "聖筊" + RETURN + "請輸入一個任意的數字來取籤" + RETURN
							+ "0.離開");
					jo.put("step", 2);
				} else {
					jo.put("type", 1);
					jo.put("response", (a == 0 ? "陰筊" : "笑筊") + RETURN
							+ "神明還未決定要不要認同，行事狀況不明，可以重新再擲筊請示神明" + RETURN
							+ "0.離開" + RETURN + "1.繼續");
					jo.put("step", 10);
				}
			}
			break;
		case 2:
			try {
				int seed = Integer.parseInt(message);
				int i = new Random(seed).nextInt(fortunes.size());
				Fortune fortune = fortunes.get(i);
				jo.put("type", 1);
				StringBuffer response = new StringBuffer();
				response.append("1.籤序").append(RETURN)
						.append(fortune.getCol0());
				response.append(RETURN).append("2.籤文").append(RETURN).append(
						fortune.getCol1());
				response.append(RETURN).append("3.籤性").append(RETURN).append(
						fortune.getCol2());
				response.append(RETURN).append("4.相對問題").append(RETURN).append(
						fortune.getCol6());
				response.append(RETURN).append(RETURN).append(
						"籤文解" + RETURN + fortune.getCol3());
				response.append(RETURN).append(RETURN).append(
						"你要看解籤嗎" + RETURN + "0.離開" + RETURN + "1.解籤" + RETURN
								+ "2.再抽一次");
				jo.put("response", response.toString());
				jo.put("step", 2000 + i);
			} catch (NumberFormatException e) {
				jo.put("type", 1);
				jo.put("response", "請輸入一個任意的數字來取籤\n0.離開");
				jo.put("step", nextStep);
			}
			break;
		case 0:
			if (message.equals(ahit)) {
				jo.put("type", 1);
				jo.put("response", aresponse);
				jo.put("step", nextStep + 1);
			} else
				return null;
			break;
		}

		if (nextStep > 2000) {
			if (message.equals("1")) {
				int i = nextStep % 2000;
				Fortune fortune = fortunes.get(i);
				jo.put("type", 1);
				jo.put("response", "講解" + RETURN + fortune.getCol7() + RETURN
						+ RETURN + "四句淺釋" + RETURN + fortune.getCol4() + RETURN
						+ RETURN + "靈籤之曰" + RETURN + fortune.getCol5());
				jo.put("r", 3);
				jo.put("step", 0);
			} else if (message.equals("2")) {
				jo.put("type", 1);
				jo.put("response", "請輸入一個任意的數字來取籤" + RETURN + "0.離開");
				jo.put("step", 2);
			} else {
				jo.put("type", 1);
				jo.put("response", "你要看解籤嗎" + RETURN + "0.離開" + RETURN + "1.解籤"
						+ RETURN + "2.再抽一次");
				jo.put("step", nextStep);
			}
		}

		if (jo.getString("response").equals(""))
			return null;
		else
			return jo;
	}

	public FortunetellingLogic() {
		super();
		this.name = "擲筊";
	}

	public void setFortunes(ArrayList<Fortune> fortunes) {
		this.fortunes = fortunes;
	}

	public ArrayList<Fortune> getFortunes() {
		return fortunes;
	}
}
