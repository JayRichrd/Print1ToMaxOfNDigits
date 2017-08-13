package com.jy;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 获取输入
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入N的值:");
		int n = scanner.nextInt();

		// 打印
		System.out.print(n + "位数:");
		print1ToMaxOfNDigits1(n);

		System.out.println();

		// 打印
		System.out.print(n + "位数:");
		print1ToMaxOfNDigits2(n);

		// 释放资源
		scanner.close();
	}

	/**
	 * 常规从数字低位开始逐次加1打印数字
	 * 
	 * @param n
	 *            总共的位数
	 */
	private static void print1ToMaxOfNDigits1(int n) {
		if (n <= 0)
			throw new RuntimeException("Invalid Input!");
		// 数组默认每位上的数字为0
		int[] number = new int[n];
		// 循环打印出每一个数字，直到溢出为止
		while (!increasment(number))
			printNumber(number);
		// 释放资源
		number = null;

	}

	/**
	 * 对数组表示的数字加1
	 * 
	 * @param number
	 *            用来表示数字的数组
	 * @return 是否已经超过了数组能表示的最大范围 当数字的最高位(即数组的第0位向前进1时，表示数组表示的数字溢出)
	 */
	private static boolean increasment(int[] number) {
		// 是否已经溢出n位
		boolean isOverflow = false;
		// 满10后向前1位进数
		int nTakeOver = 0;
		// 数组的长度
		int nLength = number.length;

		for (int i = nLength - 1; i >= 0; i--) {
			// 当前位的数字
			int nSum = number[i] - 0 + nTakeOver;
			if (i == nLength - 1) // 始终是数字的最低位(数组的最后1位)加1
				nSum++;

			if (nSum >= 10) { // 满10进1
				if (0 == i) // 数字的最高位(数组的第0位)进1，则溢出
					isOverflow = true;
				else {
					nSum -= 10;
					// 向前一位进1
					nTakeOver = 1;
					number[i] = nSum;
				}
			} else { // 不满10，则加1后退出
				number[i] = nSum;
				break;
			}
		}

		return isOverflow;
	}

	/**
	 * 递归的方式打印数字
	 * 
	 * @param n
	 *            总共的位数
	 */
	private static void print1ToMaxOfNDigits2(int n) {
		if (n <= 0)
			throw new RuntimeException("Invalid Input!");
		// 数组默认每位上的数字为0
		int[] number = new int[n];
		for (int i = 0; i < 10; i++) { // 从数字的最高位(即数组的第0位开始)
			number[0] = i;
			print1ToMaxOfNDigitsRecursively(number, 1);
		}
		// 释放资源
		number = null;
	}

	/**
	 * 
	 * @param number
	 * @param index
	 */
	private static void print1ToMaxOfNDigitsRecursively(int[] number, int index) {
		// 当数字的最后一位也已经赋值后，就打印出当前的数字
		if (index == number.length) {
			printNumber(number);
			return;
		}
		for (int i = 0; i < 10; i++) {
			number[index] = i;
			print1ToMaxOfNDigitsRecursively(number, index + 1);
		}
	}

	/**
	 * 打印数组表示的数字 为了符合使用习惯，将不会打印数字高位补齐的0
	 * 
	 * @param number
	 *            用来表示数字的数组
	 */
	private static void printNumber(int[] number) {
		// 是否开始打印
		boolean isBeginning = false;
		// 从数字的最高位(数组的前面)开始打印
		for (int i = 0; i < number.length; i++) {
			if (!isBeginning && number[i] != 0)
				isBeginning = true;
			if (isBeginning)
				System.out.print(number[i]);
		}
		System.out.print(", ");
	}

}
