//package com.caox.elasticJob;
//
//import com.dangdang.ddframe.job.executor.ShardingContexts;
//import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author : nazi
// * @version : 1.0
// * @date : 2019/7/17 10:19
// */
//public class MyElasticJobListener implements ElasticJobListener {
//    private static final Logger logger = LoggerFactory.getLogger(MyElasticJobListener.class);
//
//    private long beginTime = 0;
//    @Override
//    public void beforeJobExecuted(ShardingContexts shardingContexts) {
//        beginTime = System.currentTimeMillis();
//
//        logger.info("===>{} JOB BEGIN TIME: {} <===",shardingContexts.getJobName(), beginTime);
//    }
//
//    @Override
//    public void afterJobExecuted(ShardingContexts shardingContexts) {
//        long endTime = System.currentTimeMillis();
//        logger.info("===>{} JOB END TIME: {},TOTAL CAST: {} <===",shardingContexts.getJobName(),endTime, endTime - beginTime);
//    }
//}
