package peaksoft.springbootsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import peaksoft.springbootsecurity.serviceImple.UserDetailServicImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServicImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /**Company's page*/
                .antMatchers("/companies").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers("/companies/addCompany").hasAuthority("ADMIN")
                .antMatchers("/companies/update/**").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/companies/**/updateCompany").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/companies/**").hasAnyAuthority("ADMIN")
                /**Course's page*/
                .antMatchers("/courses/courses/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers(HttpMethod.POST,"/courses/**/**/saveAssign").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/courses/**/addCourse").hasAuthority("ADMIN")
                .antMatchers("/courses/update/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR")
                .antMatchers("/courses/**/**/saveUpdateCourse").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR")
                .antMatchers("/courses/**/**").hasAnyAuthority("ADMIN")
                /**Instructor's page*/
                .antMatchers("/instructors/instructors/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers("/instructors/**/addInstructor").hasAnyAuthority("ADMIN")
                .antMatchers("/instructors/updateInstructor/**").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/instructors/**/**/delete").hasAnyAuthority("ADMIN")
                /**Student's page*/
                .antMatchers("/students/students/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers(HttpMethod.POST,"/students/**/**/saveAssign").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/students/**/addStudent").hasAnyAuthority("ADMIN","MANAGER")
                .antMatchers("/students/updateStudent/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR")
                .antMatchers("/students/**/**/delete").hasAnyAuthority("ADMIN")
                /**Lesson's page*/
                .antMatchers("/lessons/allLessons/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers("/lessons/**/addLesson").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .antMatchers("/lessons/updateLesson/**").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .antMatchers("/lessons/**/**/delete").hasAnyAuthority("ADMIN","INSTRUCTOR")
                /**Task's page*/
                .antMatchers("/tasks/allTasks/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers("/tasks/**/newTask").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .antMatchers("/tasks/updateTask/**").hasAnyAuthority("ADMIN","INSTRUCTOR","STUDENT")
                .antMatchers("/tasks/**/**/deleteTask").hasAnyAuthority("ADMIN","INSTRUCTOR")
                /**Video's page*/
                .antMatchers("/videos/allVideos/**").hasAnyAuthority("ADMIN","MANAGER","INSTRUCTOR","STUDENT")
                .antMatchers("/videos/**/newVideo").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .antMatchers("/videos/updateVideo/**").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .antMatchers("/videos/**/**/deleteVideo").hasAnyAuthority("ADMIN","INSTRUCTOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/companies")
                .permitAll()
                .and()
                .logout().permitAll();
    }
}
