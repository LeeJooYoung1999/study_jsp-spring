package org.scoula.advice;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect  // 부가적기능이 정의된 클래스임을 명시 => (관점지향프로그래밍 AOP)
@Log4j2
@Component
public class LogAdvice {
    //어떤 타겟이 되는 클래스의 메서드를 호출해서 실행할 때,
    //어떤 부가적인기능을 넣을지 충고해주는 클래스
    //클래스의 이름위에 @Aspect 해줘야함.

    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))")
    public void logBefore(){
        log.info("==========================");
    }

    //부가적인 기능을 넣을 타겟이되는 SampleService클래스 안에 있는
    //조인포인트가 되는 모든 메서드를 호출해서 실행하기 전에(이벤트라고 인식함.)
    //logBefore()메서드를 먼저호출해라! 설정.
    //==> 이벤트가 발생하면 aop프락시 객체가 logBefore()메서드를 먼저 호출함.
    //
    //여태까지 우리가 스프링에서 사용된 설계기법 3가지 ==> 24~26정도로 설계패턴을 정리
    //  1.프락시(대리인) 설계패턴
    //  2.싱글톤
    //  3.팩토리

    //SampleService의 doAdd메서드에 대해 매개변수를 체크하는 Before Advice 추가
    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1:" + str1);
        log.info("str2:" + str2);
    }
}
