package com.cloud.kjetboy.server.common.utils;

import com.github.pagehelper.PageHelper;


public class GetPage {
	/**
	 * 功能描述：返回分页数据
	 * @param pageNum
	 * @param pageSize
	 * @return PageInfo
	 */
	public static void getPage(Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
	}
}
