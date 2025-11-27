package uz.pdp.todo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class BotConfig {

    @Bean
    public SetWebhook setWebhook() {
        SetWebhook setWebhook = new SetWebhook();
        setWebhook.setUrl("https://magazinchi.uz/send");
        return setWebhook;
    }

    @Bean
    public ParrotBot parrotBot(SetWebhook setWebhook) {
        return new ParrotBot(setWebhook);
    }
}


//
//