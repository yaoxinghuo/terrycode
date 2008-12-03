package cn.edu.jiangnan.lab.dwr;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.directwebremoting.WebContextFactory;

import cn.edu.jiangnan.lab.data.service.comm.Constants;
import cn.edu.jiangnan.lab.data.service.intf.IAccountService;
import cn.edu.jiangnan.lab.data.service.intf.IBookService;

public class Book {
	private IBookService bookService;
	private IAccountService accountService;
	private SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private String purciewErrorMessage = Constants.NO_ADMIN_DO_ERROR_MESSAGE;

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	private String getSessionId(boolean adminId) {
		if (adminId)
			return (String) WebContextFactory.get().getSession().getAttribute(
					Constants.SESSION_ADMIN_ID);
		else
			return (String) WebContextFactory.get().getSession().getAttribute(
					Constants.SESSION_ID);
	}

	public String saveBook(String equip_id, String startDate, String endDate,
			String sample_name, int sample_mount, float compute_fee,
			String content) {
		String id = getSessionId(false);
		if (id == null)
			return "{result:false,message:'您没有登录或长时间未操作，已退出登录，请重新登录后再试！'}";
		else
			try {
				return bookService.saveBook(equip_id, id, sdf.parse(startDate),
						sdf.parse(endDate), sample_name, sample_mount,
						compute_fee, content);
			} catch (Exception e) {
				return "{result:false,message:'对不起，程序出现错误！'}";
			}

	}

	public String updateBook(String bookid, String startDate, String endDate,
			String sample_name, int sample_mount, float compute_fee,
			String content) {
		String id = getSessionId(false);
		if (id == null) {
			return "{result:false,message:'您没有登录或长时间未操作，已退出登录，请重新登录后再试！'}";
		} else
			try {
				return bookService.updateBook(bookid, id, sdf.parse(startDate),
						sdf.parse(endDate), sample_name, sample_mount,
						compute_fee, content);
			} catch (Exception e) {
				return "{result:false,message:'对不起，程序出现错误！'}";
			}
	}

	public String getPrBooksInfo(int start, int limit) {
		String adminId = getSessionId(true);
		if (adminId != null)
			return bookService.getPrBooksInfo(start, limit, adminId);
		return null;
	}

	public String getAfBooksInfo(int start, int limit) {
		String adminId = getSessionId(true);
		if (adminId != null)
			return bookService.getAfBooksInfo(start, limit, adminId);
		return null;
	}

	public String getBooksInfoByEquipId(int start, int limit, String book_id) {
		return bookService.getBooksInfoByEquipId(start, limit, book_id);
	}

	public String getBookDetailById(String book_id) {
		return bookService.getBookDetailById(book_id);
	}

	public String getBooksInfoByUserId(int start, int limit) {
		String id = getSessionId(false);
		if (id != null)
			return bookService.getBooksInfoByUserId(start, limit, id);
		return null;
	}

	public String getBooksLogInfo(int start, int limit, int action,
			String startDate, String endDate, String keyword, String column)
			throws ParseException {
		return bookService.getBooksLogs(start, limit, action, sdfd
				.parse(startDate), sdfd.parse(endDate), keyword, column);
	}

	public String getLogsInfo(int start, int limit, int action,
			String startDate, String endDate, String keyword, String column)
			throws ParseException {
		return bookService.getLogs(start, limit, action, sdfd.parse(startDate),
				sdfd.parse(endDate), keyword, column);
	}

	public String messageBook(String book_id) {
		return bookService.messageBook(book_id);
	}

	public String adminMessageBook(String log_id) {
		return bookService.adminMessageBook(log_id);
	}

	public String confirmBook(String book_id, String message) {
		String admin_id = getSessionId(true);
		return bookService.confirmBook(book_id, admin_id, message);
	}

	public String batchConfirmBook(String[] book_ids) {
		String admin_id = getSessionId(true);
		return bookService.batchConfirmBook(book_ids, admin_id);
	}

	public String removeBook(String book_id, String message) {
		String admin_id = getSessionId(true);
		return bookService.removeBook(book_id, admin_id, message);
	}

	public String deleteBook(String book_id) {
		String id = getSessionId(false);
		if (id == null) {
			return "{result:false,message:'您没有登录或长时间未操作，已退出登录，请重新登录后再试！'}";
		}

		return bookService.deleteBook(book_id);

	}

	public String deleteBookNoLog(String book_id) {
		if (!isSuperAdmin()) {
			return purciewErrorMessage;
		}
		return bookService.deleteBook(book_id);
	}

	public String batchDeleteBookNoLog(String[] book_ids) {
		if (!isSuperAdmin()) {
			return purciewErrorMessage;
		}
		return bookService.batchDeleteBook(book_ids);
	}

	public String batchRemoveBook(String[] book_ids) {
		String admin_id = getSessionId(true);
		return bookService.batchRemoveBook(book_ids, admin_id);
	}

	public String cancelBook(String book_id, String message) {
		String admin_id = getSessionId(true);
		if (!isSuperAdmin()) // 设备管理员权限也不能撤销预约
			admin_id = null;
		return bookService.cancelBook(book_id, admin_id, message);
	}

	public String remarkBook(String book_id, String message) {
		String admin_id = getSessionId(true);
		return bookService.remarkBook(book_id, admin_id, message);
	}

	public String batchCancelBook(String[] book_ids) {
		String admin_id = getSessionId(true);
		if (!isSuperAdmin())// 设备管理员权限也不能撤销预约
			admin_id = null;
		return bookService.batchCancelBook(book_ids, admin_id);
	}

	public String getLogsByUserId(int start, int limit) {
		String id = getSessionId(false);
		if (id != null)
			return bookService.getLogsByUserId(start, limit, id);
		return null;
	}

	public String deleteLog(String id) {
		if (isSuperAdmin() && bookService.deleteLogById(id)) {
			return "{result:true,message:'您已成功删除审批记录！'}";
		} else {
			return purciewErrorMessage;
		}
	}

	public String batchDeleteLogs(String[] ids) {
		return bookService.batchDeleteLogs(isSuperAdmin(), ids);
	}

	public String changeFee(String bookid, float fee, String remark) {
		String admin_id = getSessionId(true);
		return bookService.changeFee(bookid, fee, remark, admin_id);
	}

	public String computerEndDateAndFee(String equipid, String startDate,
			int sample_mount) {
		return bookService.computerEndDateAndFee(equipid, startDate,
				sample_mount);
	}

	private boolean isSuperAdmin() {
		if (WebContextFactory.get().getSession().getAttribute(
				Constants.SESSION_ADMIN_ID) == null)
			return false;
		cn.edu.jiangnan.lab.data.pojo.Account account = accountService
				.getAccountById((String) WebContextFactory.get().getSession()
						.getAttribute(Constants.SESSION_ADMIN_ID));
		return (account.getAdmin() == 0 || account.getAdmin() == 3) ? true
				: false;
	}

	public void invalidateSession() {
		WebContextFactory.get().getSession().invalidate();
		// System.out.println("Session Invalidate");
	}
}
