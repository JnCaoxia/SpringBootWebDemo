package com.caox;

import org.joda.time.format.FormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerFeignApplicationTests {

	@Test
	public void contextLoads() {
		Date now=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String tablename=dateFormat.format(now);

		System.out.println(tablename);
		System.out.println(tablename + createTransactionSuffix());
		System.out.println(tablename + (int) ((Math.random()*9+1)*100000));
	}

	// 先加1再取值
	private String createTransactionSuffix(){
		AtomicLong tomicLong = new AtomicLong();
		long i = tomicLong.incrementAndGet();
		System.out.println(i);
		if (i > 9999999999L) {
			// 当大于 9999999999L
			// 重新开始
			AtomicLong atomicLong = new AtomicLong();
			// 先加1再取值
			i = atomicLong.incrementAndGet();
		}
		return String.format(String.format("%010d", i));
	}



}
