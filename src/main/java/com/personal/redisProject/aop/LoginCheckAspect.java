package com.personal.redisProject.aop;

import com.personal.redisProject.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect //실제 AOP 동작을 위한 어노테이션
@Order(Ordered.LOWEST_PRECEDENCE) //AOP의 우선순위를 지정
@Log4j2
@Component //Bean 등록
public class LoginCheckAspect {
    @Around("@annotaion(com.personal.redisProject.aop.LoginCheck) && @ annotation(loginCheck") // interface 에서 만든 LoginCheck 어노테이션을 사용하기 위해
    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable{ //PointCut 지정된 시점의 내용들을 한번에 모아줘 Controller에 넘기기 전에 실행
        HttpSession session = (HttpSession) ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession(); //ServletAttribute 에서 Type을 지정해야만 Session을 가져올 수 있음
        String id = null;
        int idIndex = 0; //몇번째 Argument를 넘길지 지정

        String userType = loginCheck.type().toString(); //어떤 타입의 유저인지 확인
        switch (userType){
            case "ADMIN": {
                id = SessionUtil.getLoginAdminId(session); //Admin 일 경우
                break;
            }
            case "USER": {
                id = SessionUtil.getLoginMemberId(session); //User 일 경우
                break;
            }
        }
        if(id == null) {
            log.debug(proceedingJoinPoint.toString() + "accountName :" + id);
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한 id값을 확인해주세요.") {};

        }
        Object[] modifiedArgs = proceedingJoinPoint.getArgs(); //Argument를 가져옴

        if(proceedingJoinPoint.getArgs() != null)
            modifiedArgs[idIndex] = id; //Argument에 id값을 넣어줌
        return proceedingJoinPoint.proceed(modifiedArgs); //Argument를 넣어준 후 Controller로 넘김

    }
}
