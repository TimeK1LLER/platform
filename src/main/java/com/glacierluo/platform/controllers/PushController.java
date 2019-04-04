package com.glacierluo.platform.controllers;

import com.glacierluo.platform.classes.Json;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(path="/")
public class PushController {
    @GetMapping("/pushMessage")
    public @ResponseBody Json pushMessage() throws IOException, ParseException {
        Constants.useOfficial();
        Sender sender = new Sender("SxY9udfMqmWAC5YSiEjiHw==");
        String messagePayload = "This is a test message!";
        String title = "ffg牛逼！";
        String description = "非凡哥牛逼！";
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(messagePayload)
                .restrictedPackageName("com.ncutea")
                .notifyType(1)
                .build();
//        Result result = sender.send(message, regId, 3);
        Result result = sender.sendToAlias(message, "洛冰河", 3);
        System.out.println("Server response: "+ "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason());
        return new Json("Server response: "+ "MessageId: " + result.getMessageId()
                + " ErrorCode: " + result.getErrorCode().toString()
                + " Reason: " + result.getReason(), 200);
    }
}
