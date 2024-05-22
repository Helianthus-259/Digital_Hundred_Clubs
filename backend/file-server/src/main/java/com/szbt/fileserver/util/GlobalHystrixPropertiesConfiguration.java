package com.szbt.fileserver.util;


import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//全局熔断配置
@Configuration
public class GlobalHystrixPropertiesConfiguration {

    /**
     * 熔断器配置
     *
     * @return
     */
    @Bean
    public HystrixCommandProperties.Setter commandPropertiesConfig() {
        return HystrixCommandProperties.Setter()
                // 熔断器在整个统计时间内是否开启的阀值
                .withExecutionTimeoutInMilliseconds(10000).withExecutionTimeoutEnabled(true).withExecutionIsolationThreadInterruptOnTimeout(true)
                // 报错比例决定熔断器是否开启
                .withCircuitBreakerErrorThresholdPercentage(10)
                // 熔断器开启必须有超过多少个请求
                .withCircuitBreakerRequestVolumeThreshold(10);
    }

    /**
     * 线程池配置
     *
     * @return
     */
    @Bean
    public HystrixThreadPoolProperties.Setter threadPoolConfig() {
        return HystrixThreadPoolProperties.Setter().withCoreSize(1).withMaxQueueSize(10);
    }

    /**
     * 用来拦截处理HystrixCommand注解
     * @return
     */
//    @Bean
//    public HystrixCommandAspect hystrixCommandAspect() {
//        return new HystrixCommandAspect();
//    }
}

