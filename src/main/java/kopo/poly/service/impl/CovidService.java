package kopo.poly.service.impl;

import kopo.poly.service.ICovidService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//import java.net.http.HttpHeaders;

import org.springframework.http.HttpHeaders;

@Slf4j
@RequiredArgsConstructor
@Service("CovidService")
public class CovidService implements ICovidService {

    private final RestTemplate restTemplate;

    @Value("${api.service.key}")
    private String apikey;

    @Override
    public String getCovidRes() throws Exception{
        log.info(this.getClass().getName() + "코로나 확진자 정보 가져오기 시작!");

        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("apis.data.go.kr")
                .path("/1790387/covid19currentStatusKorea/covid19CurrentStatusKoreaJason")
                .queryParam("serviceKey", apikey)
                .build().toString();

        log.info("생성된 ur 확인 :" + url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        log.info("api 조회 결과는? : " + response.getStatusCode());
        log.info("api 조회 결과 헤더에 담긴 정보는? : " + response.getHeaders());
        log.info("api 조회 실제 데이터는 : " + response.getBody());

        log.info(this.getClass().getName() + "코로나 확진자 정보 가져오기 종료!");
        return response.getBody();
    }
}
