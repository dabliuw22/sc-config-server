package com.leysoft.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.config.monitor.BitbucketPropertyPathNotificationExtractor;
import org.springframework.cloud.config.monitor.GiteePropertyPathNotificationExtractor;
import org.springframework.cloud.config.monitor.GithubPropertyPathNotificationExtractor;
import org.springframework.cloud.config.monitor.GitlabPropertyPathNotificationExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoExtractorConfiguration {
	
	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.github.enabled", havingValue="true", matchIfMissing=true)
	public GithubPropertyPathNotificationExtractor githubPropertyPathNotificationExtractor() {
		return new GithubPropertyPathNotificationExtractor();
	}
	
	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.gitlab.enabled", havingValue="true", matchIfMissing=true)
	public GitlabPropertyPathNotificationExtractor gitlabPropertyPathNotificationExtractor() {
		return new GitlabPropertyPathNotificationExtractor();
	}

	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.bitbucket.enabled", havingValue="true", matchIfMissing=true)
	public BitbucketPropertyPathNotificationExtractor bitbucketPropertyPathNotificationExtractor() {
		return new BitbucketPropertyPathNotificationExtractor();
	}

	@Bean
	@ConditionalOnProperty(value="spring.cloud.config.server.monitor.gitee.enabled", havingValue="true", matchIfMissing=true)
	public GiteePropertyPathNotificationExtractor giteePropertyPathNotificationExtractor() {
		return new GiteePropertyPathNotificationExtractor();
	}
}
