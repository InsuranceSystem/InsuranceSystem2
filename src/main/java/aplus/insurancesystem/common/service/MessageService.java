package aplus.insurancesystem.common.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.MessageListRequest;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MessageListResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

public class MessageService {
    final DefaultMessageService messageService;

    @Value("${coolsms.api.key}")
    private String apiKey;
    @Value("${coolsms.api.secret}")
    private String apiSecretKey;
    @Value("${coolsms.api.from-number}")
    private String fromNumber;

    public MessageService(DefaultMessageService messageService) {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
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
