//package com.caox.verifyImageMake;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author : nazi
// * @version : 1.0
// * @date : 2019/8/26 9:24
// */
//@RestController
//@Slf4j
//public class CreateVerifyImageController {
//    @Autowired
//    private StringRedisTemplate redisService;
//
//    /**
//     * @desc 图形验证码生成
//     */
//    @RequestMapping("/createImg")
//    public void createImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        try {
//            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
//            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
//            response.setHeader("Cache-Control", "no-cache");
//            response.setDateHeader("Expire", 0);
//            VerifyUtil randomValidateCode = new VerifyUtil();
//            randomValidateCode.getRandcode(request, response);//输出验证码图片
//            //将生成的随机验证码存放到redis中
//            redisService.opsForValue().set("RANDOMVALIDATECODEKEY",(String)request.getSession().getAttribute("RANDOMREDISKEY"),
//                    Long.parseLong("60000"), TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            log.error("获取验证码异常：", e); }
//    }
//
//}
//
