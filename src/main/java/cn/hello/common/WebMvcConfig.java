//package cn.hello.common;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author
// *
// * 跨域访问配置
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
//////                .allowedOrigins("*")
////                .allowedOriginPatterns("*")
////
////                .allowCredentials(true)
////                .allowedMethods("GET", "POST", "DELETE", "PUT")
////                .maxAge(3600);
////    }
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .allowedOriginPatterns("*")
//                .allowCredentials(true);
//    }
//
//
//}
//
