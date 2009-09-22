package org.ictclas4j.utility;

/**
 * ��݋���x�ă��ַ������ƶ�
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
		int d[][]; // ���
		int n = str1.length();
		int m = str2.length();
		int i; // for str1
		int j; // for str2
		char ch1; // str1��
		char ch2; // str2��
		int temp; // ӛ���ͬ�ַ�,��ĳ�����λ��ֵ������,����0����1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // ��ʼ����һ��
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) { // ��ʼ����һ��
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) { // ��vstr1
			ch1 = str1.charAt(i - 1);
			// ȥƥ��str2
			for (j = 1; j <= m; j++) {
				ch2 = str2.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// ��߅+1,��߅+1, ���Ͻ�+tempȡ��С
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
