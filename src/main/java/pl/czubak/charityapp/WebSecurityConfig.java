package pl.czubak.charityapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .passwordParameter("password")
                .usernameParameter("email")
                .defaultSuccessUrl("/donation")
                .and().logout().logoutSuccessUrl("/")
                .and()
                .httpBasic();

    }



    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/charity-donation?serverTimezone=UTC");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {



        auth
                .inMemoryAuthentication()
                .withUser("admin@gmail.com")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .roles("ADMIN");
        auth

                .jdbcAuthentication()
                .dataSource(dataSource())
                .usersByUsernameQuery(
                        "select email, password, enabled from users where email=?")
                .authoritiesByUsernameQuery(
                        "select u.email, r.role from users u inner join user_role ur on(u.id=ur.user_id) inner join roles r on(ur.role_id=r.id) where u.email=?")
                .passwordEncoder(passwordEncoder());

    }




}
