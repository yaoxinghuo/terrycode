package com.microblog.logic;

import com.microblog.process.Commands;

public class Test {
	public static void main(String[] args) throws Exception {
		String s = "MSG1";
		Commands c = Commands.valueOf(s);
		System.out.println(c.toString());
	}
}
