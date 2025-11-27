package uz.pdp.todo;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

public class ParrotBot extends SpringWebhookBot {

    public ParrotBot(SetWebhook setWebhook) {
        super(setWebhook, "7757092297:AAEoLBgp2RQKZ6H8LY_nSja6nyWxdd00p6Q");
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .build();
    }

    @Override
    public String getBotPath() {
        return "https://magazinchi.uz/send";
    }

    @Override
    public String getBotUsername() {
        return "@pdp_matket_bot";
    }
}
