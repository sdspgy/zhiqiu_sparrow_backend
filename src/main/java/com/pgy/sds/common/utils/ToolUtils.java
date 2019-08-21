package com.pgy.sds.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Author:   taoyuzhu
 * Date:     2019-08-21 10:24
 * Description:
 */
@Slf4j
public class ToolUtils {

	private static Random random = new Random(System.currentTimeMillis());

	private static Random getRandom() {
		return random;
	}

	public static void main(String[] args) {

	}

	public static String decimalFormat(int molecule, int denominator) {
		String format = new DecimalFormat("0.00").format((float) molecule / denominator);
		return format;
	}

	public static String simpleDateFormat() {
		String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(System.currentTimeMillis());
		return format;
	}

	/*类型转换*/
	public static String typeToString(Object value) {
		String s = String.valueOf(value);
		return s;
	}

	public static byte stringToByte(String s) {
		byte b = Byte.parseByte(s);
		return b;
	}

	public static int stringToInt(String s) {
		int i = Integer.parseInt(s);
		return i;
	}

	/*随机数*/
	public static int random(int v) {
		int r = getRandom().nextInt(v);
		return r;
	}

	public static boolean randomBoolean() {
		return getRandom().nextBoolean();
	}

	public static float randomFloat() {
		return getRandom().nextFloat();
	}

	public static int randomBySection(int start, int end) {
		int r = Math.abs(getRandom().nextInt()) % (end - start + 1) + start;
		return r;
	}

	public static int randomOpenNum(int min, int max) {
		int result = getRandom().nextInt((max - min) << 5);
		return (result >> 5) + min;
	}

	public static int randomCloseNum(int max) {
		return randomCloseNum(0, max);
	}

	public static int randomCloseNum(int min, int max) {
		int result = getRandom().nextInt((max - min + 1) << 5);
		return (result >> 5) + min;
	}

	public static float randomOpenNum(float min, float max) {
		return getRandom().nextFloat() * (max - min) + min;
	}

	public static int randomByChoices(int[] choices) {
		int sum = 0;
		for (int i : choices) {
			sum += i;
		}
		int r = randomCloseNum(1, sum);
		int total = 0;
		for (int i = 0; i < choices.length; i++) {
			total += choices[i];
			if (r <= total) {
				return i;
			}
		}
		throw new IllegalArgumentException("It can't be here!");
	}

	public static int randomByList(List<Integer> probability) {
		if (CollectionUtils.isEmpty(probability)) {
			throw new RuntimeException("List is 0");
		}
		int sum = 0;
		int length = probability.size();
		for (int i = 0; i < length; i++) {
			sum += probability.get(i);
		}
		if (sum == 0) {
			return 0;
		}
		int result = getRandom().nextInt(sum) + 1;
		for (int i = 0; i < length; i++) {
			result -= probability.get(i);
			if (result <= 0) {
				return i;
			}
		}
		throw new RuntimeException("dead code");
	}

	/*数组转集合*/
	public static List<Integer> arrayToListByStream(int[] array) {
		List<Integer> collect = Arrays.stream(array).boxed().collect(Collectors.toList());
		return collect;
	}

	public static List<Integer> arrayToListByArrayList(int[] array) {
		List ints = Arrays.asList(array);
		return ints;
	}
}
