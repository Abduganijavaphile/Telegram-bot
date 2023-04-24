package dev.abu.my_bot;


import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Mybot extends TelegramLongPollingBot {
    List<TelegramUser> users = new ArrayList<>();
    int sum = 0;
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            TelegramUser user = saveUser(chatId);
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/list")) {
                    System.out.println(users);
                }
                if (text.equals("/start")) {
                    if (user.getFullName()!=null){
                        try {
                            setLang(chatId,user);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }else{
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText("Assalomu alaykum! \n Botga xush kelibsiz\n" +
                                "✍\uFE0F, ❗\uFE0F Iltimos Ism va Familiyangizni kiriting:\n" );
                        sendMessage.setChatId(chatId);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        user.setStep(BotConstant.ENTER_NAME);
                    }
                } else if (user.getStep().equals(BotConstant.ENTER_NAME)) {
                    try {
                        user.setFullName(text);
                        setLang(chatId,user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            TelegramUser user = saveUser(chatId);
            String data = callbackQuery.getData();
            if (user.getStep().equals(BotConstant.SELECT_LANG)){
                if (data.equals(BotQuery.UZ_SELECT)){
                    try {
                        startTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_LANG);
                } else if (data.equals(BotQuery.RU_SELECT)){
                    try {
                        startTestRu(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_LANG);
                } else if (data.equals(BotQuery.ENG_SELECT)){
                    try {
                        startTestEng(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_LANG);
                }else if (data.equals(BotQuery.START_TEST)) {
                    try {
                        boshlaTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.STARTED_TEST);
                }else if (data.equals(BotQuery.START_TEST1)) {
                    try {
                        dasturTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.STARTED_TEST);
                } else if (data.equals(BotQuery.RU_SELECT1)) {
                    try {
                        boshlaTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.START_RU);
                }else if (data.equals(BotQuery.RU_SELECT2)) {
                    try {
                        dasturTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.START_RU);
                }else if (data.equals(BotQuery.ENG_SELECT1)) {
                    try {
                        boshlaTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.START_ENG);
                }else if (data.equals(BotQuery.ENG_SELECT2)) {
                    try {
                        dasturTestUz(chatId, user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.START_ENG);
                }

            }else if (data.equals(BotQuery.IQ_START1)) {
                sum++;
                try {
                    boshlaTestUz2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START);
            }else if (data.equals(BotQuery.IQ_START)) {
                sum+=0;
                try {
                    boshlaTestUz2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START);
            }else if (data.equals(BotQuery.IQ_START2T)) {
                sum++;
                try {
                    boshlaTestUz3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START2);
            }else if (data.equals(BotQuery.IQ_START2)) {
                sum+=0;
                try {
                    boshlaTestUz3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START2);
            }else if (data.equals(BotQuery.IQ_START3T)) {
                sum++;
                try {
                    boshlaTestUz4(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START3);
            }else if (data.equals(BotQuery.IQ_START3)) {
                sum+=0;
                try {
                    boshlaTestUz4(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START3);
            }else if (data.equals(BotQuery.IQ_START4T)) {
                sum++;
                try {
                    boshlaTestUz5(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START4);
            }else if (data.equals(BotQuery.IQ_START4)) {
                sum+=0;
                try {
                    boshlaTestUz5(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START4);
            }else if (data.equals(BotQuery.IQ_START5T)) {
                sum++;
                try {
                    boshlaTestUz6(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START5);
            }else if (data.equals(BotQuery.IQ_START5)) {
                sum+=0;
                try {
                    boshlaTestUz6(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START5);
            }else if (data.equals(BotQuery.IQ_START6T)) {
                sum++;
                try {
                    boshlaTestUz7(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START6);
            }else if (data.equals(BotQuery.IQ_START6)) {
                sum+=0;
                try {
                    boshlaTestUz7(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START6);
            }else if (data.equals(BotQuery.IQ_START7T)) {
                sum++;
                try {
                    boshlaTestUz8(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START7);
            }else if (data.equals(BotQuery.IQ_START7)) {
                sum+=0;
                try {
                    boshlaTestUz8(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START7);
            }else if (data.equals(BotQuery.IQ_START8T)) {
                sum++;
                try {
                    boshlaTestUz9(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START8);
            }else if (data.equals(BotQuery.IQ_START8)) {
                sum+=0;
                try {
                    boshlaTestUz9(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START8);
            }else if (data.equals(BotQuery.IQ_START9T)) {
                sum++;
                try {
                    boshlaTestUz10(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START9);
            }else if (data.equals(BotQuery.IQ_START9)) {
                sum+=0;
                try {
                    boshlaTestUz10(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START9);
            }else if (data.equals(BotQuery.IQ_START10T)) {
                sum++;
                try {
                    last(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START10);
            } else if (data.equals(BotQuery.IQ_START10)) {
                sum+=0;
                try {
                    last(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.IQ_START10);
            }else if (data.equals(BotQuery.DAS_START1)) {
                try {
                    dasturTestUz1(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DAS_START);
            } else if (data.equals(BotQuery.DAS_START2)) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("Botni qaytadan ishga tushirish uchun startni bos ");
                sendMessage.setChatId(chatId);
                user.setStep(BotConstant.DAS_START);
            }else if (data.equals(BotQuery.TEST_DASTUR1T)) {
                sum++;
                try {
                    dasturTestUz2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START1);
            } else if (data.equals(BotQuery.TEST_DASTUR1)) {
                sum+=0;
                try {
                    dasturTestUz2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START1);
            }else if (data.equals(BotQuery.TEST_DASTUR2T)) {
                sum++;
                try {
                    dasturTestUz3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START2);
            } else if (data.equals(BotQuery.TEST_DASTUR2)) {
                sum+=0;
                try {
                    dasturTestUz3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START2);
            }else if (data.equals(BotQuery.TEST_DASTUR3T)) {
                sum++;
                try {
                    dasturTestUz4(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START3);
            } else if (data.equals(BotQuery.TEST_DASTUR3)) {
                sum+=0;
                try {
                    dasturTestUz4(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START3);
            }else if (data.equals(BotQuery.TEST_DASTUR4T)) {
                sum++;
                try {
                    dasturTestUz5(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START4);
            } else if (data.equals(BotQuery.TEST_DASTUR4)) {
                sum+=0;
                try {
                    dasturTestUz5(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START4);
            }else if (data.equals(BotQuery.TEST_DASTUR5T)) {
                sum++;
                try {
                    dasturTestUz6(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START5);
            } else if (data.equals(BotQuery.TEST_DASTUR5)) {
                sum+=0;
                try {
                    dasturTestUz6(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START5);
            }else if (data.equals(BotQuery.TEST_DASTUR6T)) {
                sum++;
                try {
                    dasturTestUz7(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START6);
            } else if (data.equals(BotQuery.TEST_DASTUR6)) {
                sum+=0;
                try {
                    dasturTestUz7(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START6);
            }else if (data.equals(BotQuery.TEST_DASTUR7T)) {
                sum++;
                try {
                    dasturTestUz8(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START7);
            } else if (data.equals(BotQuery.TEST_DASTUR7)) {
                sum+=0;
                try {
                    dasturTestUz8(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START7);
            }else if (data.equals(BotQuery.TEST_DASTUR8T)) {
                sum++;
                try {
                    dasturTestUz9(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START8);
            } else if (data.equals(BotQuery.TEST_DASTUR8)) {
                sum+=0;
                try {
                    dasturTestUz9(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START8);
            }else if (data.equals(BotQuery.TEST_DASTUR9T)) {
                sum++;
                try {
                    dasturTestUz10(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START9);
            } else if (data.equals(BotQuery.TEST_DASTUR9)) {
                sum+=0;
                try {
                    dasturTestUz10(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START9);
            }else if (data.equals(BotQuery.TEST_DASTUR10T)) {
                sum++;
                try {
                    last2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START10);
            } else if (data.equals(BotQuery.TEST_DASTUR10)) {
                sum+=0;
                try {
                    last2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.DASTUR_START10);
            }
        }
    }
    private void last2(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        if (sum>8 && sum<=10){
            sendMessage.setText("Tabriklaymiz sizning dasturlashdagi darajangiz yuqori \uD83D\uDC4F \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum );
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (sum<=8 && sum>5) {
            sendMessage.setText("Sizning dasturlashdagi darajangiz o'rta darajada \uD83D\uDC4D \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum);
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (sum<=5 && sum>0){
            sendMessage.setText("Sizning darajangiz past darajada \uD83E\uDD28 \n" +
                    "Siz dasturlashni o'rganishingiz zarur \uD83D\uDE09 \n"+
                    "Siz topgan to'g'ri javoblar soni : " + sum);
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        user.setStep(BotConstant.IQ_LAST);
    }
    private void dasturTestUz10(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Massiv noto’g’ri ta’rifini ko’rsating. ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("int a[20];");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR10);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("int a[2] = {1,2,3,4};");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR10T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("int a[] = {1,2,3,4};");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR10);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("int a[4] = {1,2,3,4};");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR10T);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START10);
    }
    private void dasturTestUz9(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Satrdan boshlang’ich va oxirgi probellarni o‘chirish funksiyasi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Timr; \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR9);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("TrimLeft; \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR9);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("TrimRight; \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR9);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Trim; \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR9T);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START9);
    }
    private void dasturTestUz8(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Satrni kichik harflarga o‘tkazish funksiyasi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("AnsiLowerCase(const S: string): String;");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR8T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("AnsiUpperCase(const S: string): String;");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR8);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("AnsiCompareText(const S1, S2: string): Int;");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR8);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("AnsiCompareStr(const S1, S2: string): Int;");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR8);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START8);
    }
    private void dasturTestUz7(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Qaysi funksiya yordamida darchani tozalash mumkin ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Clean() \uD83E\uDDF9");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR7);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Clear() \uD83C\uDD91");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR7T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Delete() \uD83D\uDDD1");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR7);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Clear ❌");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR7);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START7);
    }
    private void dasturTestUz6(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Forma chegarasi qalinligini o’zgartirish qaysi parametr yordamida amalga oshiriladi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("BorderHeight ▶\uFE0F");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR6);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("BorderSize ▶\uFE0F");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR6);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("BorderWidth ▶\uFE0F");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR6T);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Border ▶\uFE0F");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR6);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START6);
    }
    private void dasturTestUz5(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" DateTimePicker ning asosiy xossalari ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Date, Time \uD83D\uDCC5");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR5T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Text, Caption \uD83D\uDCC5");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR5);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Timer \uD83D\uDD54");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR5);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Data, Timer \uD83D\uDD54");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR5);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START5);
    }
    private void dasturTestUz4(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Edit matn axborotini kiritish va aks ettirish komponenti asosiy xossasi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Caption \uD83D\uDCAD");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR4);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Lines \uD83E\uDD14");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR4);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Text \uD83D\uDCDD");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR4T);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Items \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR4);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START4);
    }
    private void dasturTestUz3(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Aylanani chizuvchi funksiya… ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("line(x1,y1,x2,y2);");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR3);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("circle(x,y);");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR3T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("putpixel(x,y,color);");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR3);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("getpixel(x,y);");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR3);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START3);
    }
    private void dasturTestUz2(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Cheksiz takrorlash operatoridan qaysi operator yordamida chiqib ketish mumkin ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("break \uD83E\uDD14");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR2T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("continue \uD83E\uDD14");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR2);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("return \uD83E\uDD14");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR2);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Switch  \uD83E\uDD14");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR2);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START2);
    }
    private void dasturTestUz1(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Boolean qanaqa tipli o'zgaruvchi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Butun tip \uD83E\uDDE0");
        inlineKeyboardButton1.setCallbackData(BotQuery.TEST_DASTUR1);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Satr tip \uD83E\uDDE0");
        inlineKeyboardButton2.setCallbackData(BotQuery.TEST_DASTUR1);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Butun bo'lmagan tip \uD83E\uDDE0");
        inlineKeyboardButton3.setCallbackData(BotQuery.TEST_DASTUR1);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Mantiqiy tip  \uD83E\uDDE0");
        inlineKeyboardButton4.setCallbackData(BotQuery.TEST_DASTUR1T);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DASTUR_START1);
    }
    private void last(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        if (sum>8 && sum<=10){
            sendMessage.setText("Tabriklaymiz sizning IQ darajangiz yuqori \uD83D\uDC4F \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum );
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (sum<=8 && sum>5) {
            sendMessage.setText("Sizning IQ darajangiz o'rta darajada \uD83D\uDC4D \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum);
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (sum<=5 && sum>0){
            sendMessage.setText("Sizning IQ darajangiz past darajada \uD83E\uDD28 \n" +
                    "Kayfiyatingizni ko'taring yoki psixolog maslahatiga boring \uD83D\uDE09 \n"+
                    "Siz topgan to'g'ri javoblar soni : " + sum);
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        user.setStep(BotConstant.IQ_LAST);
    }
    private void boshlaTestUz10(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Kichkina kulrangina, filga o'xshaydi.Bu nima ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Qo'ng'ir ayiq \uD83D\uDC28");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START10);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Kengru \uD83E\uDD98");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START10);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Mamont \uD83E\uDDA3");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START10);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Filning bolasi  \uD83D\uDC18");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START10T);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START10);
    }
    private void boshlaTestUz9(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Bu harf deyarli dunyoning barcha alfavitlarida bir xil yozilishi bilan rekord o'rnatgan.Bu qaysi harf ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("O \uD83D\uDE2E");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START9T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("A \uD83C\uDD70\uFE0F");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START9);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("R \uD83E\uDDD0");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START9);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("B  \uD83C\uDD71\uFE0F");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START9);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START9);
    }
    private void boshlaTestUz8(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Yerdan osonlikcha ko'tarish mumkin, ammo uni uzoqqa otib bo'lmaydi,Bu nima ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Tosh \uD83E\uDD4C");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START8);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Yog'och \uD83E\uDEB5");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START8);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Qush pati \uD83E\uDEB6");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START8T);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Ip  \uD83E\uDDF5");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START8);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START8);
    }
    private void boshlaTestUz7(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Avstraliyaning poytaxti nima ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Sydney \uD83C\uDDE6\uD83C\uDDFA");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START7);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Melbourne \uD83C\uDDE6\uD83C\uDDFA");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START7);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Gold Coast \uD83C\uDDE6\uD83C\uDDFA");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START7);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Kanberra  \uD83C\uDDE6\uD83C\uDDFA");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START7T);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START7);
    }
    private void boshlaTestUz6(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Quyidagi so‘zlardan qaysi biri g‘alati: avtomobil, poyezd, avtobus, velosiped, samolyot? ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("avtomabil \uD83D\uDE93");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START6);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("velosiped  \uD83D\uDEB4");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START6T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("poyezd \uD83D\uDE89");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START6);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("samalyot  ✈\uFE0F");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START6);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START6);
    }
    private void boshlaTestUz5(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Ketma-ketlikda qaysi raqam keladi: 1, 1, 2, 3, 5, 8, 13, __ ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("21 \uD83E\uDD14");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START5T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("14  \uD83E\uDD14");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START5);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("19 \uD83E\uDD14");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START5);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("23  \uD83E\uDD14");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START5);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START5);
    }
    private void boshlaTestUz4(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Harflarni to'g'ri joylashtirib ortiqcha so'zni toping ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("ANIOVY \uD83E\uDD14");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START4);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("ZO'NBEKSON  \uD83E\uDD14");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START4T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("MARSAQNAD \uD83E\uDD14");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START4);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("KOSHETNT  \uD83E\uDD14");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START4);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START4);
    }
    private void boshlaTestUz3(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Anvarning tug'ilgan kunida uning otasi Anvar nechi yosh bo'lsa " +
                "shuncha tangani idishga solib qo'yadi agar idishda 21 ta tanga bo'lsa Anvarning " +
                "yoshi nechida " +
                " ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdu1 = new ArrayList<>();
        List<InlineKeyboardButton> tdu2 = new ArrayList<>();
        List<InlineKeyboardButton> tdu3 = new ArrayList<>();
        List<InlineKeyboardButton> tdu4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("15 \uD83D\uDCAD");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START3);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("45  \uD83D\uDCAD");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START3);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("21 \uD83D\uDCAD");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START3);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("6  \uD83D\uDCAD");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START3T);

        tdu1.add(inlineKeyboardButton1);
        tdu2.add(inlineKeyboardButton2);
        tdu3.add(inlineKeyboardButton3);
        tdu4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdu1);
        trm.add(tdu2);
        trm.add(tdu3);
        trm.add(tdu4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START3);
    }
    private void boshlaTestUz2(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Bu yerda qaysi so'z ortiqcha ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdi1 = new ArrayList<>();
        List<InlineKeyboardButton> tdi2 = new ArrayList<>();
        List<InlineKeyboardButton> tdi3 = new ArrayList<>();
        List<InlineKeyboardButton> tdi4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Yomg'ir \uD83C\uDF27");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START2);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Do'l  \uD83C\uDF28");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START2);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Qor ❄\uFE0F");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START2);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Chaqmoq  ⛈");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START2T);

        tdi1.add(inlineKeyboardButton1);
        tdi2.add(inlineKeyboardButton2);
        tdi3.add(inlineKeyboardButton3);
        tdi4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdi1);
        trm.add(tdi2);
        trm.add(tdi3);
        trm.add(tdi4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START2);
    }
    private void boshlaTestUz(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Kim o'tirgan holda yuradi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Taksist \uD83D\uDE95");
        inlineKeyboardButton1.setCallbackData(BotQuery.IQ_START);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Xayolparast kishi  \uD83E\uDDD8\uD83C\uDFFB");
        inlineKeyboardButton2.setCallbackData(BotQuery.IQ_START);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Shaxmatchi ♟");
        inlineKeyboardButton3.setCallbackData(BotQuery.IQ_START1);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Uchuvchi  ✈\uFE0F");
        inlineKeyboardButton4.setCallbackData(BotQuery.IQ_START);

        tdm1.add(inlineKeyboardButton1);
        tdm2.add(inlineKeyboardButton2);
        tdm3.add(inlineKeyboardButton3);
        tdm4.add(inlineKeyboardButton4);
        List<List<InlineKeyboardButton>> trm  = new ArrayList<>();
        trm.add(tdm1);
        trm.add(tdm2);
        trm.add(tdm3);
        trm.add(tdm4);

        inlineKeyboardMarkup.setKeyboard(trm);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.IQ_START);
    }
    private void dasturTestUz(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Dasturlash bo'yicha bilimingizni sinab ko'rishni xoxlaysizmi ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdd = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonDas = new InlineKeyboardButton();
        inlineKeyboardButtonDas.setText("Ha xoxlayman \uD83E\uDDE0");
        inlineKeyboardButtonDas.setCallbackData(BotQuery.DAS_START1);

        InlineKeyboardButton inlineKeyboardButtonDas2 = new InlineKeyboardButton();
        inlineKeyboardButtonDas2.setText("Yo'q  \uD83D\uDE34");
        inlineKeyboardButtonDas2.setCallbackData(BotQuery.DAS_START2);

        tdd.add(inlineKeyboardButtonDas);
        tdd.add(inlineKeyboardButtonDas2);

        List<List<InlineKeyboardButton>> trd  = new ArrayList<>();
        trd.add(tdd);

        inlineKeyboardMarkup.setKeyboard(trd);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.DAS_START);
    }
    private void startTestUz(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(user.getFullName() + " IQ Testni Ishlashga tayyormisiz ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdt = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonUzb = new InlineKeyboardButton();
        inlineKeyboardButtonUzb.setText("Ha tayyorman \uD83E\uDDE0");
        inlineKeyboardButtonUzb.setCallbackData(BotQuery.START_TEST);

        InlineKeyboardButton inlineKeyboardButtonDas = new InlineKeyboardButton();
        inlineKeyboardButtonDas.setText("Yo'q  \uD83D\uDE34 ");
        inlineKeyboardButtonDas.setCallbackData(BotQuery.START_TEST1);

        tdt.add(inlineKeyboardButtonUzb);
        tdt.add(inlineKeyboardButtonDas);

        List<List<InlineKeyboardButton>> trt = new ArrayList<>();
        trt.add(tdt);

        inlineKeyboardMarkup.setKeyboard(trt);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);

        user.setStep(BotConstant.STARTED_TEST);
    }
    private void startTestRu(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(user.getFullName() + " Готовы ли вы пройти IQ-тест ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> td = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonRu = new InlineKeyboardButton();
        inlineKeyboardButtonRu.setText("Да я готов \uD83D\uDE07");
        inlineKeyboardButtonRu.setCallbackData(BotQuery.RU_SELECT1);

        InlineKeyboardButton inlineKeyboardButtonN = new InlineKeyboardButton();
        inlineKeyboardButtonN.setText("Нет \uD83D\uDE07");
        inlineKeyboardButtonN.setCallbackData(BotQuery.RU_SELECT2);

        td.add(inlineKeyboardButtonRu);
        td.add(inlineKeyboardButtonN);
        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        tr.add(td);
        inlineKeyboardMarkup.setKeyboard(tr);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setSelectedLang(BotConstant.START_RU);
    }
    private void startTestEng(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(user.getFullName() + " Are you ready to take the IQ Test ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> td = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonEng = new InlineKeyboardButton();
        inlineKeyboardButtonEng.setText("Yes ! I am ready \uD83D\uDE07");
        inlineKeyboardButtonEng.setCallbackData(BotQuery.ENG_SELECT1);

        InlineKeyboardButton inlineKeyboardButtonNo = new InlineKeyboardButton();
        inlineKeyboardButtonNo.setText("No \uD83D\uDE07");
        inlineKeyboardButtonNo.setCallbackData(BotQuery.ENG_SELECT2);

        td.add(inlineKeyboardButtonEng);
        td.add(inlineKeyboardButtonNo);
        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        tr.add(td);
        inlineKeyboardMarkup.setKeyboard(tr);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setSelectedLang(BotConstant.START_ENG);
    }
    private void setLang(String chatId,TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(user.getFullName() + " Iltimos tilni tanlang :");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> td = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButtonUz = new InlineKeyboardButton();
        inlineKeyboardButtonUz.setText("Uz \uD83C\uDDFA\uD83C\uDDFF");
        inlineKeyboardButtonUz.setCallbackData(BotQuery.UZ_SELECT);

        InlineKeyboardButton inlineKeyboardButtonRu = new InlineKeyboardButton();
        inlineKeyboardButtonRu.setText("Ru \uD83C\uDDF7\uD83C\uDDFA");
        inlineKeyboardButtonRu.setCallbackData(BotQuery.RU_SELECT);

        InlineKeyboardButton inlineKeyboardButtonEng = new InlineKeyboardButton();
        inlineKeyboardButtonEng.setText("Eng \uD83C\uDDFA\uD83C\uDDF8");
        inlineKeyboardButtonEng.setCallbackData(BotQuery.ENG_SELECT);

        td.add(inlineKeyboardButtonUz);
        td.add(inlineKeyboardButtonRu);
        td.add(inlineKeyboardButtonEng);

        List<List<InlineKeyboardButton>> tr = new ArrayList<>();
        tr.add(td);

        inlineKeyboardMarkup.setKeyboard(tr);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);

        user.setStep(BotConstant.SELECT_LANG);
    }
    private TelegramUser saveUser(String chatId) {
        for (TelegramUser user : users) {
            if (user.getChatId().equals(chatId)){
                return user;
            }
        }
        TelegramUser user = new TelegramUser();
        user.setChatId(chatId);
        users.add(user);
        return user;
    }

    @Override
    public String getBotUsername() {
        return "javaphile_09_bot";
    }

    @Override
    public String getBotToken() {
        return "6162184969:AAEDw1uUkfMV5fodrfkrjg9YljgjTPzx3U0";
    }
    private void saveFileToFolder(String fileId, String fileName) throws Exception{
        GetFile getFile = new GetFile(fileId);
        File tgFile = execute(getFile);
        String fileUrl = tgFile.getFileUrl(getBotToken());

        URL url = new URL(fileUrl);
        InputStream inputStream = url.openStream();

        FileUtils.copyInputStreamToFile(inputStream, new java.io.File(fileName));

    }
    private void sendText(String chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
