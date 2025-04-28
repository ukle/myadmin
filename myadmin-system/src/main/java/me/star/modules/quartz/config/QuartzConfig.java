package me.star.modules.quartz.config;

import lombok.extern.slf4j.Slf4j;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务配置
 * @author /
 * @date 2019-01-07
 */
@Slf4j
@Configuration
@Scope("singleton")
public class QuartzConfig {

	/**
	 * 解决Job中注入Spring Bean为null的问题
	 */
	@Component("quartzJobFactory")
	public static class QuartzJobFactory extends AdaptableJobFactory {

		private final AutowireCapableBeanFactory capableBeanFactory;

		@Autowired
		public QuartzJobFactory(AutowireCapableBeanFactory capableBeanFactory) {
			this.capableBeanFactory = capableBeanFactory;
		}

		@NonNull
		@Override
		protected Object createJobInstance(@NonNull TriggerFiredBundle bundle) throws Exception {
			try {
				// 调用父类的方法，把Job注入到spring中
				Object jobInstance = super.createJobInstance(bundle);
				capableBeanFactory.autowireBean(jobInstance);
				log.debug("Job instance created and autowired: {}", jobInstance.getClass().getName());
				return jobInstance;
			} catch (Exception e) {
				log.error("Error creating job instance for bundle: {}", bundle, e);
				throw e;
			}
		}
	}
}
