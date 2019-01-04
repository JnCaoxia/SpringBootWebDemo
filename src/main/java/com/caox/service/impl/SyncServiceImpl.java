package com.caox.service.impl;

import com.caox.annotions.RedisSync;
import com.caox.service.SyncService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/1/3 13:22
 */
@Service
public class SyncServiceImpl implements SyncService{

    private static int COUNT = 100;
    private static final Random RANDOM = new Random();

    @Override
    @RedisSync(waitTime = 0)
    public int getIndex() {
        try {
            //随机获取等待时间（该时间仅供参考，准确时间还需加上代码执行时间）
            long time = 500 + RANDOM.nextInt(3000);
            System.out.println("COUNT(" + COUNT + ")，sleep:" + time);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (COUNT <= 0) {
            return 0;
        }
        return COUNT--;
    }
}
