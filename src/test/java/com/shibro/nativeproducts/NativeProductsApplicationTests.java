package com.shibro.nativeproducts;

import com.shibro.nativeproducts.deom.FutureTask;
import com.shibro.nativeproducts.deom.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NativeProductsApplicationTests {

	@Autowired
	private Task task;

	@Autowired
	private FutureTask futureTask;

	@Resource
	private ThreadPoolTaskExecutor baseTaskExecutor;

	@Test
	public void test() throws Exception {
		task.test();
	}

	@Test
	public void test2() throws Exception{
		futureTask.setParam("*******************");
		Future<String> task =baseTaskExecutor.submit(futureTask);
		while (true){
			if(task.isDone()){
				System.out.println(task.get());
				break;
			}
		}

	}

}

