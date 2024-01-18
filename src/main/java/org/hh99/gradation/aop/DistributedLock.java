package org.hh99.gradation.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

	// 락의 이름
	String key();

	// 락의 시간 단위
	TimeUnit timeUnit() default TimeUnit.SECONDS;

	// 락을 획득하기 위해 대기하는 시간
	long waitTime() default 5L;

	// 락 임대시간, 획득한 후 leaseTime 이 지나면 락을 해제
	long leaseTime() default 3L;
}