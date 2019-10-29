package com.example.demo.session;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/8/20 19:23
 */
@RestController
public class LoginController {
    @RequestMapping("test1")
    public Map getSessionId(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        String session_id=null;
        if(session==null){
            session=request.getSession(true);
        }
        session_id = session.getId();
        System.out.println(session_id);
        Map<String,Object> res=new HashMap<>();
        res.put("sessionid",session_id);
        if(session.getAttribute("boot")==null){
            System.out.println("***********************");
            session.setAttribute("boot","nbbo");
        }else {
            System.out.println(session.getAttribute("boot"));
        }
        return res;
    }
}
