package com.atguigu.jxc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JxcApplicationTests {

	@Test
	public void contextLoads() {
		int i = 200;
		byte b = (byte)i;//溢出
		System.out.println("b = " + b);
	}

}
