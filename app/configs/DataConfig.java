package configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import play.Play;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.admin.QueryOptionsManager;
import com.marklogic.client.io.StringHandle;

@Configuration
@EnableTransactionManagement
public class DataConfig {
	@Bean
	@Scope("singleton")
	public DatabaseClient databaseClient() {
		final String host = Play.application().configuration().getString("ml.host");
		final int port = Play.application().configuration().getInt("ml.port");
		final String user = "admin";
		final String password = "********";

		DatabaseClient db = DatabaseClientFactory.newClient(host, port, user, password, Authentication.DIGEST);

		String options = "<search:options " + "xmlns:search='http://marklogic.com/appservices/search'>" + "<search:return-results>true</search:return-results>"
				+ "<search:transform-results apply='raw' />" + "</search:options>";

		QueryOptionsManager optionsMgr = db.newServerConfigManager().newQueryOptionsManager();
		optionsMgr.writeOptions("default", new StringHandle(options));

		return db;
	}

	// @Bean
	// public EntityManagerFactory entityManagerFactory() {
	// HibernateJpaVendorAdapter vendorAdapter = new
	// HibernateJpaVendorAdapter();
	// vendorAdapter.setShowSql(true);
	// vendorAdapter.setGenerateDdl(true);
	// LocalContainerEntityManagerFactoryBean entityManagerFactory = new
	// LocalContainerEntityManagerFactoryBean();
	// entityManagerFactory.setPackagesToScan("models");
	// entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
	// entityManagerFactory.setDataSource(dataSource());
	// entityManagerFactory.afterPropertiesSet();
	// return entityManagerFactory.getObject();
	// }
	//
	// @Bean
	// public PlatformTransactionManager transactionManager() {
	// JpaTransactionManager transactionManager = new
	// JpaTransactionManager(entityManagerFactory());
	// transactionManager.setDataSource(dataSource());
	// transactionManager.setJpaDialect(new HibernateJpaDialect());
	// return transactionManager;
	// }
	//
	// @Bean
	// public DataSource dataSource() {
	// final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	// dataSource.setDriverClassName(Play.application().configuration().getString("db.default.driver"));
	// dataSource.setUrl(Play.application().configuration().getString("db.default.url"));
	// return dataSource;
	// }

}