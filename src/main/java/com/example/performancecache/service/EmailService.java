package com.example.performancecache.service;

import com.example.performancecache.dto.EmailDetails;
import com.example.performancecache.dto.Notice;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private NoticeService noticeService;

    /**
     * 이메일 전송 메소드
     *
     * @param emailDetails 발신자, 수신자, 제목 등이 저장되어 있는 클래스
     */
    public void sendEmail(EmailDetails emailDetails) {
        MimeMessage mimeMessage = createEmailInfo(emailDetails);
        javaMailSender.send(mimeMessage);
    }

    /**
     * 이메일 발신자, 수신자, 제목, 내용 등을 지정하는 메소드
     *
     * @param emailDetails 발신자, 수신자, 제목 등이 저장되어 있는 클래스
     * @return 메일 정보
     */
    public MimeMessage createEmailInfo(EmailDetails emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(emailDetails.getSenderEmail(), emailDetails.getSenderName());
            helper.setTo(emailDetails.getRecipientEmail());
            helper.setSubject(emailDetails.getTitle());
            helper.setText(emailDetails.getContent());
        } catch (MessagingException e) {
            System.out.println("[ERROR] 이메일 전송 중 오류가 발생했습니다.");
        } catch (UnsupportedEncodingException e) {
            System.out.println("[ERROR] 이메일 주소 확인바랍니다.");
        }
        return mimeMessage;
    }

    /**
     * 게시글 정보를 하나의 문자열로 만드는 메소드
     * 용도: 이메일 본문에 포함
     * 다른 종류의 게시글 내용를 메일 본문에 포함시켜 보내려면 다음 규칙에 지켜 메소드를 생성해야 한다.
     * [create게시글 종류 Content()메소드]
     *
     * @return 이메일 본문 내용
     */
    public String createNoticeContent() {
        List<Notice> notices = noticeService.sortNoticesByViewsAndCreateDate();
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < notices.size(); i++) {
            int num = i + 1;
            Notice notice = notices.get(i);
            content.append("\n" + num + ". HOT Notices [" + notice.getViews() + "] \n" + notice.getContent() + "\n");
        }
        return String.valueOf(content);
    }
}
