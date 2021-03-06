package spring.springauthority.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import spring.springauthority.jwt.JwtAuthenticationTokenFilter;
import spring.springauthority.service.IUmsAdminService;

//@Order(2)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {//1扩展SpringSecurity配置需继承WebSecurityConfigurerAdapter。

    @Autowired
    IUmsAdminService iUmsAdminService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //自带认证
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return iUmsAdminService.loadUserByUsername(username);
            }
        }).passwordEncoder(NoOpPasswordEncoder.getInstance());//不加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        for (String url : ignoreUrlsConfig().getUrls()) {
            http.authorizeRequests().antMatchers(url).permitAll();
        }

        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll(); //跨域请求会先进行一次options请求

        http.csrf().disable().authorizeRequests()
                .anyRequest()
                .authenticated();

//                .and().authorizeRequests().anyRequest().authenticated();
//                .and()
//                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.formLogin().loginPage("/index");
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        http.rememberMe(); //记住我

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
//        for (String url : ignoreUrlsConfig().getUrls()) {
//            web.ignoring().antMatchers(url);
//        }
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }


}