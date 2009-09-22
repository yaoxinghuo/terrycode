package org.ictclas4j.utility;

/**
 * 編輯距離的兩字符串相似度
 */
public class Similarity {
	private int min(int one, int two, int three) {
		int min = one;
		if (two < min) {
			min = two;
		}
		if (three < min) {
			min = three;
		}
		return min;
	}

	public int ld(String str1, String str2) {
		int d[][]; // 矩陣
		int n = str1.length();
		int m = str2.length();
		int i; // for str1
		int j; // for str2
		char ch1; // str1的
		char ch2; // str2的
		int temp; // 記錄相同字符,在某個矩陣位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) { // 遍歷str1
			ch1 = str1.charAt(i - 1);
			// 去匹配str2
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 左邊+1,上邊+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}
		return d[n][m];
	}

	public double sim(String str1, String str2) {
		int ld = ld(str1, str2);
		return 1 - (double) ld / Math.max(str1.length(), str2.length());
	}

	public static void main(String[] args) {
		Similarity s = new Similarity();
		String str1 = "chenlb.blogjava.net";
		String str2 = "chenlb.javaeye.com";
		System.out.println("ld=" + s.ld(str1, str2));
		System.out.println("sim=" + s.sim(str1, str2));
	}

}
