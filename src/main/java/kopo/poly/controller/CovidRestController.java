package kopo.poly.controller;

import kopo.poly.service.ICovidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/covid")
public class CovidRestController {

    private final ICovidService convidService;


    @GetMapping("apiCovid")
    public String mian()throws Exception {
        log.info(this.getClass().getName() + "");

        String result = covidService.getCovidRes();

        return result;
//


    }
}
