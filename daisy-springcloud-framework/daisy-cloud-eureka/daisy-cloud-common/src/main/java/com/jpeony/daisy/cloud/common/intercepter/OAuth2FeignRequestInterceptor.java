//package com.bootdo.clouddocommon.intercepter;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//
//public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
//
//    private static final String AUTHORIZATION_HEADER = "Authorization";
//
//    private static final String BEARER_TOKEN_TYPE = "bearer";
//
//    private final OAuth2RestTemplate oAuth2RestTemplate;
//
//
//    /**
//     * Instantiates a new O auth 2 feign request interceptor.
//     *
//     * @param oAuth2RestTemplate the o auth 2 rest template
//     */
//    OAuth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
//        this.oAuth2RestTemplate = oAuth2RestTemplate;
//    }
//
//    /**
//     * Apply.
//     *
//     * @param template the template
//     */
//    @Override
//    public void apply(RequestTemplate template) {
//        template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE,  oAuth2RestTemplate.getAccessToken().toString()));
//
//    }
//}