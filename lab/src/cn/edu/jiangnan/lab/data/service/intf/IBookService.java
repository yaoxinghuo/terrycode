package cn.edu.jiangnan.lab.data.service.intf;

import java.util.ArrayList;
import java.util.Date;

import cn.edu.jiangnan.lab.data.pojo.Book;
import cn.edu.jiangnan.lab.data.pojo.Log;

public interface IBookService {
	public String getBooksInfoByEquipId(int start, int limit, String id);

	public String getBooksInfoByUserId(int start, int limit, String id);

	public String getBookDetailById(String id);

	public String saveBook(String equip_id, String user_id, Date start,
			Date end, String sample_name, int sample_mount, float compute_fee,
			String content);

	public String updateBook(String bookid, String user_id, Date start,
			Date end, String sample_name, int sample_mount, float compute_fee,
			String content);

	public String messageBook(String book_id);

	public String adminMessageBook(String log_id);

	public String confirmBook(String book_id, String admin_id, String message);

	public String batchConfirmBook(String[] book_ids, String admin_id);

	public String removeBook(String book_id, String admin_id, String message);

	public String deleteBook(String book_id);

	public String batchDeleteBook(String[] book_ids);

	public String batchRemoveBook(String[] book_ids, String admin_id);

	public String cancelBook(String book_id, String admin_id, String message);

	public String remarkBook(String book_id, String admin_id, String message);

	public String batchCancelBook(String[] book_ids, String admin_id);

	public String changeFee(String bookid, float fee, String remark,
			String admin_id);

	public String getPrBooksInfo(int start, int limit, String admin_no);

	public String getAfBooksInfo(int start, int limit, String admin_no);

	public String getBooksLogs(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column);

	public String getLogs(int start, int limit, int action, Date startDate,
			Date endDate, String keyword, String column);

	public ArrayList<Log> getLogsArray(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column);

	public ArrayList<Book> getBooksArray(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column);

	public String getLogsByUserId(int start, int limit, String user_id);

	public String getLogDetailById(String id);

	public boolean deleteLogById(String id);

	public Log getLogById(String id);

	public String batchDeleteLogs(boolean admin, String[] ids);

	public String computerEndDateAndFee(String equipid, String startDate,
			int sample_mount);

	public int logClean();

}
