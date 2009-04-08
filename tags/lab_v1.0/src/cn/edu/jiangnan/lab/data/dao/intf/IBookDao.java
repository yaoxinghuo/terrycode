package cn.edu.jiangnan.lab.data.dao.intf;

import java.util.ArrayList;
import java.util.Date;

import cn.edu.jiangnan.lab.data.pojo.Book;

public interface IBookDao {
	public boolean saveBook(Book book);

	public boolean deleteBook(Book book);

	public Book getBookById(String id);

	public ArrayList<Book> getBooksByEquipId(int start, int limit, String id);

	public long getCountBooksByEquipId(String id);

	public ArrayList<Book> getMyUnfonfirmedBooksByEquipId(String user_id,
			String equip_id, Date start);

	public ArrayList<Book> getConfirmedBooksByEquipId(String id, Date start);

	public ArrayList<Book> getBooksByUserId(int start, int limit, String id);

	public long getCountBooksByUserId(String id);

	public ArrayList<Book> getBooksLog(int start, int limit, int action,
			Date startDate, Date endDate, String keyword, String column);

	public long getCountBooksLog(int action, Date startDate, Date endDate,
			String keyword, String column);

	// 得到待批准预约
	public ArrayList<Book> getPrBooksByAdminType(int start, int limit,
			int type, String adminName);

	public long getCountPrBooksByAdminType(int type, String adminName);

	// 得到已批准预约
	public ArrayList<Book> getAfBooksByAdminType(int start, int limit,
			int type, String adminName);

	public long getCountAfBooksByAdminType(int type, String adminName);

	public int bookClean();

}
