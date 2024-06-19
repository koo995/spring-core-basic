package com.example.corebasic.web;

import com.example.corebasic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    /**
     * 오타수정 domo -> demo
     */
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {

        /**
         * 내가 만든 것이 아니라 CGLIB 가 만든 것이다.
         * 지금은 껍데이이고 setRequest 와 같은 실제 기능을 호출하는 시점에
         * 진짜를 찾아서 동작한다. 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.
         * 요청이 들어올때 request scope 객체가 생성된 것을 찾아온다.
         */
        System.out.println("myLogger = " + myLogger.getClass());
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
