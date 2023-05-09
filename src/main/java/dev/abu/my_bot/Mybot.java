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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
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
            Contact contact = message.getContact();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals("/list")) {
                    System.out.println(users);
                }
                if (text.equals("/start")) {
                    if (user.getFullName()!=null){

                    }else{
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText("Assalomu alaykum! \n Botga xush kelibsiz");
                        sendMessage.setParseMode(ParseMode.MARKDOWN);
                        sendMessage.setChatId(message.getChatId());

                        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                        replyKeyboardMarkup.setResizeKeyboard(true);
                        List<KeyboardRow> keyboardRowList = new ArrayList<>();
                        KeyboardRow keyboardRow1 = new KeyboardRow();
                        KeyboardButton keyboardButton1 = new KeyboardButton();
                        keyboardButton1.setText("Share contact \uD83D\uDD04");
                        keyboardButton1.setRequestContact(true);
                        keyboardRow1.add(keyboardButton1);
                        keyboardRowList.add(keyboardRow1);
                        replyKeyboardMarkup.setKeyboard(keyboardRowList);
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        user.setStep(BotConstant.ENTER_NAME);
                    }
                } else if (user.getStep().equals(BotConstant.ENTER_NAME)) {

                }if (text != null && text.equals("/startni")) {
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setText("Iltimos tilni tanlang :");
                    sendMessage1.setParseMode(ParseMode.MARKDOWN);
                    sendMessage1.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Uz \uD83C\uDDFA\uD83C\uDDFF");
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    keyboardButton2.setText("Ru \uD83C\uDDF7\uD83C\uDDFA");
                    KeyboardButton keyboardButton3 = new KeyboardButton();
                    keyboardButton3.setText("Eng \uD83C\uDDFA\uD83C\uDDF8");
                    keyboardRow1.add(keyboardButton1);
                    keyboardRow1.add(keyboardButton2);
                    keyboardRow1.add(keyboardButton3);
                    keyboardRowList.add(keyboardRow1);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage1);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_FAN);
                } else if (text.equals("Uz \uD83C\uDDFA\uD83C\uDDFF")) {
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setText("Test ishlashga tayyormisiz ❓");
                    sendMessage1.setParseMode(ParseMode.MARKDOWN);
                    sendMessage1.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Ha tayyorman \uD83E\uDDE0");
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    keyboardButton2.setText("Yo'q  \uD83D\uDE34");
                    keyboardRow1.add(keyboardButton1);
                    keyboardRow1.add(keyboardButton2);
                    keyboardRowList.add(keyboardRow1);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage1);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_LANG);

                } else if (text.equals("Ru \uD83C\uDDF7\uD83C\uDDFA")) {
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setText("Готовы ли вы пройти тест ❓");
                    sendMessage1.setParseMode(ParseMode.MARKDOWN);
                    sendMessage1.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Да я готов \uD83D\uDE07");
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    keyboardButton2.setText("Нет \uD83D\uDE07");
                    keyboardRow1.add(keyboardButton1);
                    keyboardRow1.add(keyboardButton2);
                    keyboardRowList.add(keyboardRow1);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage1);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_LANG);
                }else if (text.equals("Eng \uD83C\uDDFA\uD83C\uDDF8")) {
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setText("Are you ready to take the Test ❓");
                    sendMessage1.setParseMode(ParseMode.MARKDOWN);
                    sendMessage1.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Yes ! I am ready \uD83D\uDE07");
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    keyboardButton2.setText("No \uD83D\uDE07");
                    keyboardRow1.add(keyboardButton1);
                    keyboardRow1.add(keyboardButton2);
                    keyboardRowList.add(keyboardRow1);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage1);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_LANG);
                } else if (text.equals("Yes ! I am ready \uD83D\uDE07")|| text.equals("Да я готов \uD83D\uDE07") || text.equals("Ha tayyorman \uD83E\uDDE0")) {
                    SendMessage sendMessage1 = new SendMessage();
                    sendMessage1.setText("Fanlardan birini tanlang: ");
                    sendMessage1.setParseMode(ParseMode.MARKDOWN);
                    sendMessage1.setChatId(message.getChatId());

                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    replyKeyboardMarkup.setResizeKeyboard(true);
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow1 = new KeyboardRow();
                    KeyboardRow keyboardRow2 = new KeyboardRow();
                    KeyboardRow keyboardRow3 = new KeyboardRow();
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Dasturlash \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB");
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    keyboardButton2.setText("Ingliz tili \uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F");
                    KeyboardButton keyboardButton3 = new KeyboardButton();
                    keyboardButton3.setText("IQ Test \uD83E\uDDE0");
                    keyboardRow1.add(keyboardButton1);
                    keyboardRow2.add(keyboardButton2);
                    keyboardRow3.add(keyboardButton3);
                    keyboardRowList.add(keyboardRow1);
                    keyboardRowList.add(keyboardRow2);
                    keyboardRowList.add(keyboardRow3);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    sendMessage1.setReplyMarkup(replyKeyboardMarkup);
                    try {
                        execute(sendMessage1);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    user.setStep(BotConstant.SELECT_FAN);
                } else if (text.equals("Yo'q  \uD83D\uDE34")||text.equals("No \uD83D\uDE07")||text.equals("Нет \uD83D\uDE07")) {
                    SendMessage sendMessage2 = new SendMessage();
                    Contact contact1 = message.getContact();
                    sendMessage2.setText("Botni qaytadan ishga tushirish uchun /start ni bosing!!!");
                    sendMessage2.setParseMode(ParseMode.MARKDOWN);
                    sendMessage2.setChatId(message.getChatId());
                    try {
                        execute(sendMessage2);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (text.equals("Dasturlash \uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBB")) {
                    try {
                        dasturTestUz1(chatId,user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } else if (text.equals("IQ Test \uD83E\uDDE0")) {
                    try {
                        boshlaTestUz(chatId,user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }else if (text.equals("Ingliz tili \uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F")) {
                    try {
                        englishTestUz(chatId,user);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else if (message.hasContact()) {
                String text = message.getText();
                Contact contact1 = message.getContact();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText(contact1.getFirstName()+" tayyormisiz ?" +
                        "\n Botdan foydalanish uchun /startni bosing");
                sendMessage.setChatId(message.getChatId());
                sendMessage.setParseMode(ParseMode.MARKDOWN);
                System.out.println(contact1.getFirstName());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.SELECT_FAN);
            } 
        }
        else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            TelegramUser user = saveUser(chatId);
            String data = callbackQuery.getData();
            if (user.getStep().equals(BotConstant.SELECT_LANG)){
                if (data.equals(BotQuery.UZ_SELECT)){
                    user.setStep(BotConstant.SELECT_LANG);
                } else if (data.equals(BotQuery.RU_SELECT)){
                    user.setStep(BotConstant.SELECT_LANG);
                } else if (data.equals(BotQuery.ENG_SELECT)){

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
                sendMessage.setText("Botni qaytadan ishga tushirish uchun /startni bos ");
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
            } else if (data.equals(BotQuery.ING_START1)) {
                sum++;
                try {
                    englishTestUz2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START);
            } else if (data.equals(BotQuery.ING_START)) {
                sum += 0;
                try {
                    englishTestUz2(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START);
            }else if (data.equals(BotQuery.ING_START2T)) {
                sum++;
                try {
                    englishTestUz3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START2);
            } else if (data.equals(BotQuery.ING_START2)) {
                sum += 0;
                try {
                    englishTestUz3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START2);
            }else if (data.equals(BotQuery.ING_START3T)) {
                sum++;
                try {
                    englishTestUz4(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START3);
            } else if (data.equals(BotQuery.ING_START3)) {
                sum += 0;
                try {
                    englishTestUz4(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START3);
            }else if (data.equals(BotQuery.ING_START4T)) {
                sum++;
                try {
                    englishTestUz5(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START4);
            } else if (data.equals(BotQuery.ING_START4)) {
                sum += 0;
                try {
                    englishTestUz5(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START4);
            }else if (data.equals(BotQuery.ING_START5T)) {
                sum++;
                try {
                    englishTestUz6(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START5);
            } else if (data.equals(BotQuery.ING_START5)) {
                sum += 0;
                try {
                    englishTestUz6(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START5);
            }else if (data.equals(BotQuery.ING_START6T)) {
                sum++;
                try {
                    englishTestUz7(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START6);
            } else if (data.equals(BotQuery.ING_START6)) {
                sum += 0;
                try {
                    englishTestUz7(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START6);
            }else if (data.equals(BotQuery.ING_START7T)) {
                sum++;
                try {
                    englishTestUz8(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START7);
            } else if (data.equals(BotQuery.ING_START7)) {
                sum += 0;
                try {
                    englishTestUz8(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START7);
            }else if (data.equals(BotQuery.ING_START8T)) {
                sum++;
                try {
                    englishTestUz9(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START8);
            } else if (data.equals(BotQuery.ING_START8)) {
                sum += 0;
                try {
                    englishTestUz9(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START8);
            }else if (data.equals(BotQuery.ING_START9T)) {
                sum++;
                try {
                    englishTestUz10(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START9);
            } else if (data.equals(BotQuery.ING_START9)) {
                sum += 0;
                try {
                    englishTestUz10(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START9);
            }else if (data.equals(BotQuery.ING_START10T)) {
                sum++;
                try {
                    last3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START10);
            } else if (data.equals(BotQuery.ING_START10)) {
                sum += 0;
                try {
                    last3(chatId, user);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                user.setStep(BotConstant.ING_START10);
            }
        }
    }
    private void last3(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        if (sum>8 && sum<=10){
            sendMessage.setText("Tabriklaymiz sizning ingliz tili darajangiz Advance C1 \uD83D\uDC4F \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum +
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (sum<=8 && sum>6) {
            sendMessage.setText("Sizning ingliz tili darajangiz Intermediate B2 \uD83D\uDC4D \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (sum<=6 && sum>4){
            sendMessage.setText("Sizning ingliz tili darajangiz Pre Intermediate B1 \uD83E\uDD28 \n" +
                    "Siz ingliz tilini o'rganishingiz zarur \uD83D\uDE09 \n"+
                    "Siz topgan to'g'ri javoblar soni : " + sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (sum<=4 && sum>0){
            sendMessage.setText("Sizning ingliz tili darajangiz Elementary A1 or A2 \uD83E\uDD28 \n" +
                    "Siz ingliz tilini o'rganishingiz zarur \uD83D\uDE09 \n"+
                    "Siz topgan to'g'ri javoblar soni : " + sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        user.setStep(BotConstant.ING_LAST);
    }
    private void englishTestUz10(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("If something is ........., then it is able to catch on fire. ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("underesitmate ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START10);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("fire ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START10);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("backstage ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START10);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("flammable ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START10T);

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
        user.setStep(BotConstant.ING_START10);
    }
    private void englishTestUz9(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("to invite someone to compete or fight ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("referee ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START9);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("challenge ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START9T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("interst ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START9);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("club ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START9);

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
        user.setStep(BotConstant.ING_START9);
    }
    private void englishTestUz8(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("To ....... an amount of something means to use up all of it ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("deplete ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START8T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("commerse ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START8);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("goods ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START8);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("mock ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START8);

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
        user.setStep(BotConstant.ING_START8);
    }
    private void englishTestUz7(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("iltimos qilmoq ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("scrape ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START7);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("bind ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START7);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("negative ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START7);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("plead ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START7T);

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
        user.setStep(BotConstant.ING_START7);
    }
    private void englishTestUz6(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("A ............ is permission from a doctor to get medicine ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("patient ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START6);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("intake ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START6);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("prescription ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START6T);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("oppress ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START6);

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
        user.setStep(BotConstant.ING_START6);
    }
    private void englishTestUz5(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("The moon and stars were ....... in the night sky. ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("visible ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START5T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("result ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START5);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("viewless ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START5);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("field ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START5);

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
        user.setStep(BotConstant.ING_START5);
    }
    private void englishTestUz4(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("It happened ... seven o'clock ... the evening ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("In/at ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START4);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("At/in  ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START4T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("At/last ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START4);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("When/- ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START4);

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
        user.setStep(BotConstant.ING_START4);
    }
    private void englishTestUz3(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("James and John saved and saved, and finally they ... buy the house of their dreams ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Managed to ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START3T);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Could  ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START3);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("Can ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START3);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Couldn't ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START3);

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
        user.setStep(BotConstant.ING_START3);
    }
    private void englishTestUz2(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("I took my car to the garage ... this morning ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("When ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START2);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("-  ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START2T);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("At ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START2);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("In ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START2);

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
        user.setStep(BotConstant.ING_START2);
    }
    private void englishTestUz(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("I had an accident ... last night ❓");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> tdm1 = new ArrayList<>();
        List<InlineKeyboardButton> tdm2 = new ArrayList<>();
        List<InlineKeyboardButton> tdm3 = new ArrayList<>();
        List<InlineKeyboardButton> tdm4 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("In ");
        inlineKeyboardButton1.setCallbackData(BotQuery.ING_START);

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("On  ");
        inlineKeyboardButton2.setCallbackData(BotQuery.ING_START);

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("At ");
        inlineKeyboardButton3.setCallbackData(BotQuery.ING_START);

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText(" - ");
        inlineKeyboardButton4.setCallbackData(BotQuery.ING_START1);

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
        user.setStep(BotConstant.ING_START);
    }
    private void last2(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        if (sum>8 && sum<=10){
            sendMessage.setText("Tabriklaymiz sizning dasturlashdagi darajangiz yuqori \uD83D\uDC4F \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum +
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (sum<=8 && sum>5) {
            sendMessage.setText("Sizning dasturlashdagi darajangiz o'rta darajada \uD83D\uDC4D \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (sum<=5 && sum>0){
            sendMessage.setText("Sizning darajangiz past darajada \uD83E\uDD28 \n" +
                    "Siz dasturlashni o'rganishingiz zarur \uD83D\uDE09 \n"+
                    "Siz topgan to'g'ri javoblar soni : " + sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        user.setStep(BotConstant.DAS_LAST);
    }
    private void dasturTestUz10(String chatId, TelegramUser user) throws TelegramApiException{
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(" Massiv noto’g’ri ta’rifini ko’rsating ❓");
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
                    "Siz topgan to'g'ri javoblar soni : " +  sum +
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        } else if (sum<=8 && sum>5) {
            sendMessage.setText("Sizning IQ darajangiz o'rta darajada \uD83D\uDC4D \n"+
                    "Siz topgan to'g'ri javoblar soni : " +  sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
            sendMessage.setChatId(chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }else if (sum<=5 && sum>0){
            sendMessage.setText("Sizning IQ darajangiz past darajada \uD83E\uDD28 \n" +
                    "Kayfiyatingizni ko'taring yoki psixolog maslahatiga boring \uD83D\uDE09 \n"+
                    "Siz topgan to'g'ri javoblar soni : " + sum+
                    "\n Boshqa fandan test ishlash uchun /startni bosing");
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
