package br.com.codein.mobiagecore.integration.generic;

import io.gumga.core.GumgaThreadScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gelatti on 03/05/17.
 */
@Component
public abstract class AbstractClientNoClass<T> {

    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private HttpEntity requestEntity;
    protected String url;

    public AbstractClientNoClass() {
    }

    public AbstractClientNoClass(String url) {
        this.url = url;
    }

    protected ResponseEntity get(String url, Object stringObjectMap) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.GET, (HttpEntity<?>) this.requestEntity, Object.class, stringObjectMap);
    }

    protected ResponseEntity get(String url) {
        return this.get(url, new HashMap<>());
    }

    protected ResponseEntity post(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, (HttpEntity<?>) this.requestEntity, Object.class);
    }

    protected ResponseEntity postFile(String url, File file) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Resource resource = new FileSystemResource(file);
        map.add("file", resource);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
                map, headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, (HttpEntity<?>) requestEntity, Object.class);
    }

    protected ResponseEntity postXml(String url, File file) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Resource resource = new FileSystemResource(file);
        map.add("file", resource);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, requestEntity, Object.class);
    }

    protected ResponseEntity delete(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.DELETE, (HttpEntity<?>) this.requestEntity, Object.class);
    }
}
