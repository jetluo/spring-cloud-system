package com.cloud.kjetboy.server.common.utils;

import java.util.UUID;

/**
 * 功能描述：UUID生成器
 * @author jet
 * @date 2017-04-09 16:08:32
 * @version 1.0
 */
public class UUIDGenerator {

	/**
	 * 功能描述：生成32位的UUID
	 * @return 返回值：返回32位的UUID
	 * @author jet
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
