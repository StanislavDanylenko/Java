package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.List;

@RestController
public class SimpleBytestController {

    private static final int KEY = 1215516513;
    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping("/sendMessage")
    public Greeting getMessage(@RequestBody int[] message) throws IOException {
        String str = processBytes(message/*.getMessage()*/, KEY);
        System.out.println(str);
        template.convertAndSend("/topic/greetings", new Greeting(str));
        return null;
    }

    private String processBytes(int[] cryptBytes, int key) throws IOException {
        BASE64Decoder dec = new BASE64Decoder();
        byte[] bytes = new byte[cryptBytes.length];
        for (int i = 0; i < cryptBytes.length; i++) {
            bytes[i] = (byte) (cryptBytes[i] ^ key);
        }
        byte[] string = dec.decodeBuffer(new String(bytes));
        return new String(string);
    }

}
