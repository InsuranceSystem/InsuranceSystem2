package aplus.insurancesystem.common.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MessageService {

    private final DefaultMessageService messageService;

    private String fromNumber;

    public MessageService(@Value("${coolsms.api.key}") String apiKey,
                          @Value("${coolsms.api.secret}") String apiSecretKey,
                          @Value("${coolsms.api.from-number}") String fromNumber) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
        this.fromNumber = fromNumber;
    }

    public SingleMessageSentResponse sendOne(String to, Esms esms) {
        Message message = new Message();
        // 발신번호(fromNumber) 및 수신번호(to)는 반드시 01012345678 형태로 입력되어야 함
        message.setFrom(fromNumber);
        message.setTo(to);
        message.setText("[A+보험사] " + esms.getMessage());

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        return response;
    }

}
