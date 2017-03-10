package com.xiaomizhuang.buildcaptain;

import android.test.suitebuilder.annotation.MediumTest;

import com.hbw.library.utils.ArithUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.Assert.assertEquals;

/**
 * 描述: 测试类
 * -
 * 工程:
 * #0000     mwy     创建日期: 2016-07-13 10:11
 */
@MediumTest
@RunWith(JUnit4.class)
public class FooTest {

	@Test
	public void test1(){
		assertEquals("result:", 123, 100 + 23);
	}

	@Test
	public void test2(){
		double b = 2.6;
		double a = 3.0;
		assertEquals("result:", ArithUtil.mul(a,b),7.8);
	}

	@Test
	public void test3(){
		String host = "http://api.xiaomizhuang.com/";
		String url = "http://api.xiaomizhuang.com/abcd";
		String newUrl = url.substring(0,url.indexOf(".com")+5);
		assertEquals("result:", host,newUrl);
	}
}
