package CDProject.vfmarket.global.config;

import CDProject.vfmarket.global.jwt.Service.JwtService;
import CDProject.vfmarket.global.jwt.filter.JwtAuthenticationProcessingFilter;
import CDProject.vfmarket.global.login.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import CDProject.vfmarket.global.login.handler.LoginFailureHandler;
import CDProject.vfmarket.global.login.handler.LoginSuccessHandler;
import CDProject.vfmarket.global.login.service.LoginService;
import CDProject.vfmarket.global.oauth2.handler.OAuth2LoginFailureHandler;
import CDProject.vfmarket.global.oauth2.handler.OAuth2LoginSuccessHandler;
import CDProject.vfmarket.global.oauth2.service.CustomOAuth2UserService;
import CDProject.vfmarket.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final LoginService loginService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .formLogin(formLogin -> {
                            formLogin
                                    .loginPage("/account/login")
                                    .loginProcessingUrl("/login")
                                    .successHandler(new LoginSuccessHandler(jwtService, userRepository))
                                    .failureHandler(new LoginFailureHandler());
                        }
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)

                .headers(headers -> headers
                        .frameOptions(FrameOptionsConfig::disable) // X-Frame-Options 비활성화
                )
                // 세션 사용하지 않으므로 STATELESS로 설정
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 기본 페이지, css, image, js 하위 폴더에 있는 자료들은 모두 접근 가능, h2-console에 접근 가능
//                .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**").permitAll()
//                .requestMatchers("/sign-up").permitAll() // 회원가입 접근 가능
//                .anyRequest().authenticated() // 위의 경로 이외에는 모두 인증된 사용자만 접근 가능
//                .and()
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        new AntPathRequestMatcher("/**"),
                                        new AntPathRequestMatcher("/css/**"),
                                        new AntPathRequestMatcher("/images/**"),
                                        new AntPathRequestMatcher("/js/**"),
                                        new AntPathRequestMatcher("/h2-console/**"),
                                        new AntPathRequestMatcher("/profile/**"),
                                        new AntPathRequestMatcher("/static/**")
                                ).permitAll()
//                                .requestMatchers(
//                                        new AntPathRequestMatcher("/controller")
//                                ).hasRole(String.valueOf(Role.MEMBER))
//                        ).hasRole("ADMIN")
                                .anyRequest().authenticated()
                )//유저 권한 있는 놈들만
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                new AntPathRequestMatcher("/controller")
//                        ).hasRole("ADIMN")
//                        .anyRequest().authenticated()
//                )
                .oauth2Login(oauth2Login -> {
                    oauth2Login
                            .loginPage("/account/login")
                            .successHandler(oAuth2LoginSuccessHandler)
                            .failureHandler(oAuth2LoginFailureHandler)
                            .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(
                                    customOAuth2UserService)); // customUserService 설정
                });


        http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
        http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtService, userRepository);
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }


    @Bean
    public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
        CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
                = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
        customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return customJsonUsernamePasswordLoginFilter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        return new JwtAuthenticationProcessingFilter(jwtService,
                userRepository);
    }

}
