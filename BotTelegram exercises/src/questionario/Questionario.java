package questionario;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.MessageToSend;
import com.botticelli.bot.request.methods.StickerReferenceToSend;
import com.botticelli.bot.request.methods.types.*;

import java.util.ArrayList;
import java.util.List;

public class Questionario extends Bot {
    Domande domande = null;
    int score = 0;
    boolean flag = false;
    boolean zero = false;
    int numeroDomande = 0;
    String rispostaGiusta;
    MessageToSend mts;
    List<List<KeyboardButton>> keyboard = new ArrayList<>();
    List<KeyboardButton> line = new ArrayList<>();
    ReplyKeyboardMarkupWithButtons replyKeyboard;

    public Questionario(String token) {
        super(token);
    }

    @Override
    public void textMessage(Message message) {
        if(!flag) {
            inviaMessaggioBenvenuto(message.getFrom());
            flag = true;
            domande = Domande.DOMANDA1;
            return;
        }

        if(flag || message.getText().equals("Rigioca")){
            switch (domande){
                case DOMANDA1:
                    numeroDomande = 0;
                    score = 0;
                    zero = false;
                    domande = Domande.DOMANDA2;
                    String domanda = "Inserisci la lettera mancante:\nLe amazzoni vinsero battaglie grazie alla loro F_GA";
                    String risposta1 = "I";
                    String risposta2 = "O";
                    String risposta3 = "U";
                    domandaRisposte(message.getFrom(), message,domanda, risposta1, risposta2, risposta3);
                    rispostaGiusta = risposta1;
                    return;

                case DOMANDA2:
                    if(rispostaGiusta.equals(message.getText()))
                        score++;
                    domande = Domande.DOMANDA3;
                    domanda = "Dopo 4 birre e 2 black russian, come ti sentiresti?";
                    risposta1 = "Male";
                    risposta2 = "Malissimo";
                    risposta3 = "Dignitosamente brillo";
                    domandaRisposte(message.getFrom(), message,domanda, risposta1, risposta2, risposta3);
                    rispostaGiusta = risposta3;
                    return;

                case DOMANDA3:
                    if(rispostaGiusta.equals(message.getText()))
                        score++;
                    domande = Domande.DOMANDA4;
                    domanda = "Signora, ha una sigaretta?";
                    risposta1 = "Certo, tenga!";
                    risposta2 = "Non fumo";
                    risposta3 = "CENDODIGIOTTOO! CHIAMATE IL CENDODIGIOTTO!\nSOTTO IL PORTICATO DI TERAMO! PUTTANE E FROCI!";
                    domandaRisposte(message.getFrom(), message,domanda, risposta1, risposta2, risposta3);
                    rispostaGiusta = risposta3;
                    return;

                case DOMANDA4:
                    if(rispostaGiusta.equals(message.getText()))
                        score++;
                    domande = Domande.DOMANDA5;
                    domanda = "Quale futuro si augura per i bambini di oggi?";
                    risposta1 = "...";
                    risposta2 = "Exception in thread “presidente” java.io.FileNotFoundException";
                    risposta3 = "...Presidente?";
                    domandaRisposte(message.getFrom(), message,domanda, risposta1, risposta2, risposta3);
                    rispostaGiusta = risposta3;
                    return;

                case DOMANDA5:
                    if(rispostaGiusta.equals(message.getText()))
                        score++;
                    domande = Domande.FINITO;
                    domanda = "Quante volte si può ancora nominare la madre di Antonio Zechila?";
                    risposta1 = "1 volta";
                    risposta2 = "MAI PIÙÙ!!!!!!!";
                    risposta3 = "4 volte";
                    domandaRisposte(message.getFrom(), message,domanda, risposta1, risposta2, risposta3);
                    rispostaGiusta = risposta2;
                    return;

                case FINITO:
                    if(rispostaGiusta.equals(message.getText()))
                        score++;
                    if(score == numeroDomande)
                        mts = new MessageToSend(message.getFrom().getId(), "PERFECT SCORE! COMPLIMENTI!");
                    else if(score == 1)
                        mts = new MessageToSend(message.getFrom().getId(), "1 punto... Puoi fare di meglio, riprova!");
                    else if(score == 0) {
                        mts = new MessageToSend(message.getFrom().getId(), "0 punti... Datte all'ippica!!!");
                        zero = true;
                    }
                    else
                        mts = new MessageToSend(message.getFrom().getId(), "Hai totalizzato " + score + " punti!");
                    keyboard = new ArrayList<>();
                    line = new ArrayList<>();
                    line.add(new KeyboardButton("Rigioca"));
                    if(zero){
                        line = new ArrayList<>();
                        line.add(new KeyboardButton("DATTE ALL'IPPICA"));
                        sendStickerbyReference(new StickerReferenceToSend(message.getFrom().getId(), "CAACAgIAAxkBAAIDdV-kf5qas6IdDYN3XlkDbqOxzl7IAAJWAQACECECELb63Lh2g-F8HgQ"));
                        keyboard = new ArrayList<>();
                        line = new ArrayList<>();
                        line.add(new KeyboardButton("\uD83C\uDFC7 \uD83C\uDFC7 \uD83C\uDFC7 \uD83C\uDFC7 \uD83C\uDFC7"));
                        replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
                        replyKeyboard.setResizeKeyboard(true);
                        mts.setReplyMarkup(replyKeyboard);
                        keyboard.add(line);
                        sendMessage(mts);
                        domande = Domande.DOMANDA1;
                        return;
                    }
                    else {
                        domande = Domande.DOMANDA1;
                        replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
                        replyKeyboard.setResizeKeyboard(true);
                        mts.setReplyMarkup(replyKeyboard);
                        keyboard.add(line);
                        sendMessage(mts);
                        return;
                    }
            }

        }
    }

    private void inviaMessaggioBenvenuto (User user) {
        mts = new MessageToSend(user.getId(), "Benvenuto! Premi start per iniziare");
        keyboard = new ArrayList<>();
        line = new ArrayList<>();
        line.add(new KeyboardButton("START"));
        replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
        replyKeyboard.setResizeKeyboard(true);
        mts.setReplyMarkup(replyKeyboard);
        keyboard.add(line);
        sendMessage(mts);
    }

    private void domandaRisposte(User user, Message message, String domanda, String risposta1, String risposta2, String risposta3){
        mts = new MessageToSend(user.getId(), domanda);
        keyboard = new ArrayList<>();
        line = new ArrayList<>();
        line.add(new KeyboardButton(risposta1));
        keyboard.add(line);
        line = new ArrayList<>();
        line.add(new KeyboardButton(risposta2));
        keyboard.add(line);
        line = new ArrayList<>();
        line.add(new KeyboardButton(risposta3));
        keyboard.add(line);
        replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
        replyKeyboard.setResizeKeyboard(true);
        mts.setReplyMarkup(replyKeyboard);
        sendMessage(mts);
        numeroDomande++;
    }

    @Override
    public void audioMessage(Message message) {

    }

    @Override
    public void videoMessage(Message message) {

    }

    @Override
    public void voiceMessage(Message message) {

    }

    @Override
    public void stickerMessage(Message message) {

    }

    @Override
    public void documentMessage(Message message) {

    }

    @Override
    public void photoMessage(Message message) {

    }

    @Override
    public void contactMessage(Message message) {

    }

    @Override
    public void locationMessage(Message message) {

    }

    @Override
    public void venueMessage(Message message) {

    }

    @Override
    public void newChatMemberMessage(Message message) {

    }

    @Override
    public void newChatMembersMessage(Message message) {

    }

    @Override
    public void leftChatMemberMessage(Message message) {

    }

    @Override
    public void newChatTitleMessage(Message message) {

    }

    @Override
    public void newChatPhotoMessage(Message message) {

    }

    @Override
    public void groupChatPhotoDeleteMessage(Message message) {

    }

    @Override
    public void groupChatCreatedMessage(Message message) {

    }

    @Override
    public void inLineQuery(InlineQuery inlineQuery) {

    }

    @Override
    public void chose_inline_result(ChosenInlineResult chosenInlineResult) {

    }

    @Override
    public void callback_query(CallbackQuery callbackQuery) {

    }

    @Override
    public void gameMessage(Message message) {

    }

    @Override
    public void videoNoteMessage(Message message) {

    }

    @Override
    public void pinnedMessage(Message message) {

    }

    @Override
    public void preCheckOutQueryMessage(PreCheckoutQuery preCheckoutQuery) {

    }

    @Override
    public void shippingQueryMessage(ShippingQuery shippingQuery) {

    }

    @Override
    public void invoiceMessage(Message message) {

    }

    @Override
    public void successfulPaymentMessage(Message message) {

    }

    @Override
    public void routine() {

    }
}
