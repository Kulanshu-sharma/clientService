package com.voteroid.clientService.configurations;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voteroid.clientService.dtos.Reply;

@Aspect
@Component
public class clientAspect {
	
	@Autowired
	HttpServletResponse response;

	@AfterReturning(pointcut="@annotation(org.springframework.web.bind.annotation.PostMapping) || "
			               + "@annotation(org.springframework.web.bind.annotation.GetMapping) ||"
			               + "@annotation(org.springframework.web.bind.annotation.PutMapping) ||"
			               + "@annotation(org.springframework.web.bind.annotation.DeleteMapping)",returning = "reply")
	public void afterReturningAdvice(JoinPoint joinPoint, Reply reply)  
	{  
		response.setHeader("userData",reply.getjsonStringFromMap());
		System.out.println("After Returing method:"+joinPoint.getSignature());  

	}  
	
}
