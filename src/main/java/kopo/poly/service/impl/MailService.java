package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService implements IMailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public int doSendMail(MailDTO pDTO) {

        //로그 찍기 (추후 로그 확인으로 이 함수에 접근했는지 확인하기 좋다)
        log.info(this.getClass().getName() + ".doSendMail Start!");

        //메일 발송 성공여부 (성공 : 1 , 실패 : 0)
        int res =1;

        //전달 받은 DTO로부터 데이터 가져오기(DTO객체가 메모리에 올리가지 않아 null이 발생할 수 있기 때문에여러방지 차원으로 if문을 사용
        if (pDTO == null) {
            pDTO = new MailDTO();

        }
    String toMail = CmmUtil.nvl(pDTO.getToMail()); // 받는 사람
    String title = CmmUtil.nvl(pDTO.getTitle()); // 메일 제목
    String contents = CmmUtil.nvl(pDTO.getContnets()); // 메일 제목
        log.info("toMail :" +toMail );
        log.info("title :" + title);
        log.info("contents :" + contents);

        // 메일 발송 메시지 구조(파일 첨부 가능)
        MimeMessage message = mailSender.createMimeMessage();

        // 메일 발송 메시지 구조를 쉽게 생성하게 도와주는 객체
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");

        try {

            messageHelper.setTO(toMail); // 받는 사람
            messageHelper.setFrom(fromMail); // 보내는 사람
            messageHelper.setSubject(title); // 메일 제목
            messageHelper.setText(contents); // 메일 내용

            mailSender.send(message);
        } catch (Exception e) {//모든 에러 다 잡기
            res = 0; //메일 발송시 실패했기 때문에 0으로 변경
            log.info("[ERROR]" + this.getClass().getName() + ".doSendMail : " + e);
        }

        //로그 찍기(추후 로그로 함수 호출이 끝났는지 확인함)
        log.info(this.getClass().getName() + ".doSendMail end!");

        return res;


    }

}
