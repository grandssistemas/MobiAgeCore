package br.com.codein.mobiagecore.integration.generic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gelatti on 03/05/17.
 */
@Component
public abstract class AbstractClient<T> {

    private final Class<T> objectClass;
    private HttpHeaders headers;
    private RestTemplate restTemplate;
    private HttpEntity requestEntity;
    protected String url;

    private ObjectMapper mapper;

    public AbstractClient() {
        mapper = new ObjectMapper();
        this.objectClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public AbstractClient(String url) {
        this.url = url;
        this.objectClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected ResponseEntity<T> get(String url, Map<String, Object> stringObjectMap) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.GET, (HttpEntity<?>) this.requestEntity, objectClass, stringObjectMap);
    }

    protected ResponseEntity<T> get(String url) {
        return this.get(url, new HashMap<>());
    }

    protected ResponseEntity getGeneric(String url, Map<String, Object> stringObjectMap) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.GET, this.requestEntity, Object.class, stringObjectMap);
    }

    protected ResponseEntity getGeneric(String url) {
        return this.getGeneric(url, new HashMap<>());
    }

    protected ResponseEntity<List<T>> getList(String url) {
        return this.getList(url, new HashMap<>());
    }


    protected ResponseEntity<List<T>> getList(String url, Map<String, Object> stringObjectMap) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(this.headers);
        ResponseEntity<JsonNode> nodeResponse = this.restTemplate.exchange(this.url.concat(url), HttpMethod.GET, (HttpEntity<?>) this.requestEntity, JsonNode.class, stringObjectMap);

        return this.parseArray(nodeResponse);
    }

    protected ResponseEntity<T> post(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, (HttpEntity<?>) this.requestEntity, objectClass);
    }

    protected ResponseEntity<List<T>> post(String url, List<T> object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        ResponseEntity<JsonNode> nodeResponse = this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, (HttpEntity<?>) this.requestEntity, JsonNode.class);
        return this.parseArray(nodeResponse);
    }

    protected ResponseEntity<T> postFile(String url, File file) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Resource resource = new FileSystemResource(file);
        map.add("file", resource);
        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
                map, headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, (HttpEntity<?>) requestEntity, objectClass);
    }

    protected ResponseEntity<T> postXml(String url, File file) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Resource resource = new FileSystemResource(file);
        map.add("file", resource);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.POST, requestEntity, objectClass);
    }

    protected ResponseEntity<T> delete(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.DELETE, (HttpEntity<?>) this.requestEntity, objectClass);
    }

    protected ResponseEntity<List<T>> put(String url, List<T> object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        ResponseEntity<JsonNode> nodeResponse = this.restTemplate.exchange(this.url.concat(url), HttpMethod.PUT, (HttpEntity<?>) this.requestEntity, JsonNode.class);
        return this.parseArray(nodeResponse);
    }

    protected ResponseEntity<T> put(String url, Object object) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        this.headers = new HttpHeaders();
        this.headers.set("Accept", "application/json, text/plain, */*");
        this.headers.set("Accept-Encoding", "gzip, deflate");
        this.headers.set("Content-Type", "application/json;charset=utf-8");
        this.headers.set("gumgaToken", GumgaThreadScope.gumgaToken.get());
        this.requestEntity = new HttpEntity(object, this.headers);
        return this.restTemplate.exchange(this.url.concat(url), HttpMethod.PUT, (HttpEntity<?>) this.requestEntity, objectClass);
    }


    private ResponseEntity<List<T>> parseArray(ResponseEntity<JsonNode> nodeResponse){

        List<T> response = new ArrayList<>();


        nodeResponse.getBody().forEach(subNode -> {
            try {
                response.add(mapper.treeToValue(subNode, objectClass));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return new ResponseEntity<>(response, nodeResponse.getStatusCode());
    }

}
