package cn.edu.jiangnan.lab.task;

import cn.edu.jiangnan.lab.data.service.intf.IBookService;

public class LogCleanTask {

	private IBookService bookService;

	public IBookService getBookService() {
		return bookService;
	}

	public void setBookService(IBookService bookService) {
		this.bookService = bookService;
	}

	public void logChean() {
		bookService.logClean();
	}

}
