package com.dinsaren.hrmanagementsystemapplication.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Enable DataTables Repository Factory for creating jpa repositories.
 *
 * @author Djamel Eddine Korei
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class, basePackages = "com.dinsaren.hrmanagementsystemapplication")
public class DataTablesConfiguration {
}
