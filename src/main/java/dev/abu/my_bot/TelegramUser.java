package dev.abu.my_bot;

public class TelegramUser {
    private String chatId;
    private String step;
    private String msg;
    private String fullName;
    private String firstName;
    private String selectedLang;
    private String startTest;

    public String getStartTest() {
        return startTest;
    }

    public void setStartTest(String startTest) {
        this.startTest = startTest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(String selectedLang) {
        this.selectedLang = selectedLang;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "TelegramUser{" +
                "chatId='" + chatId + '\'' +
                ", step='" + step + '\'' +
                ", msg='" + msg + '\'' +
                ", fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", selectedLang='" + selectedLang + '\'' +
                ", startTest='" + startTest + '\'' +
                '}';
    }
}
