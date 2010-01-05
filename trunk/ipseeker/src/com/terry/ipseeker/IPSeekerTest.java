package com.terry.ipseeker;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author 孙钰佳
 * @mail sunyujia@yahoo.cn
 * @date 2007-9-24 下午12:06:19
 */
public class IPSeekerTest {
	public static void main(String[] args) throws Exception {
		IPSeeker ipSeeker = IPSeeker.getInstance();
		System.out.println(ipSeeker.getCountry("211.144.197.18"));
		System.out.println(ipSeeker.getArea("211.144.197.18"));
		System.out.println(ipSeeker.getDetailedIpInfo("211.144.197.18"));
	}
}
