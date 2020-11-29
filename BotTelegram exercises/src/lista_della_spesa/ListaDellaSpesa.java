package lista_della_spesa;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.MessageToSend;
import com.botticelli.bot.request.methods.types.*;

import java.util.ArrayList;
import java.util.List;

public class ListaDellaSpesa extends Bot {
    ArrayList<Prodotto> listaSpesa;
    Ricezione ricezione = Ricezione.SPENTO;
    long id;
    MessageToSend mts;
    List<List<KeyboardButton>> keyboard = new ArrayList<>();
    List<KeyboardButton> line = new ArrayList<>();
    ReplyKeyboardMarkupWithButtons replyKeyboard;
    boolean flag2 = false;

    public ListaDellaSpesa(String token) {
        super(token);
        listaSpesa = new ArrayList<>();
    }

    @Override
    public void textMessage(Message message) {
        System.out.println(message.getText());
        System.out.println(ComandoSpesa.fromString(message.getText()));

        if(ricezione != Ricezione.SPENTO) {
            switch (ricezione) {
                case AGGIUNGI:
                    boolean flag = false;
                    int index = 0;
                    ComandoSpesa.fromString("acceso");
                    String[] arrayProdotto = message.getText().toLowerCase().split("_");
                    for (Prodotto prodotto : listaSpesa) {
                        if(prodotto.nome.equals(arrayProdotto[0])){
                            flag = true;
                            index = listaSpesa.indexOf(prodotto);
                            break;
                        }
                    }//controllo se il prodotto è già presente
                    if(flag){
                        if (arrayProdotto.length == 2)
                            listaSpesa.get(index).quantità += Integer.parseInt(arrayProdotto[1]);
                         else
                            listaSpesa.get(index).quantità++;
                    }else {
                        if (arrayProdotto.length == 2) {
                            Prodotto prodottoDaAggiungere = new Prodotto(arrayProdotto[0], Integer.parseInt(arrayProdotto[1]));
                            listaSpesa.add(prodottoDaAggiungere);
                        } else {
                            Prodotto prodottoDaAggiungere = new Prodotto(message.getText(), 1);
                            listaSpesa.add(prodottoDaAggiungere);
                        }
                    }
                    mts = new MessageToSend(message.getFrom().getId(), "Aggiunto!");
                    sendMessage(mts);
                    ricezione = Ricezione.SPENTO;
                    return;
                case RIMUOVI:
                    flag = false;
                    ComandoSpesa.fromString("acceso");
                    String[] arrayProdottoDaRimuovere = message.getText().split("_");
                    for (Prodotto prodotto : listaSpesa) {
                        if(prodotto.nome.equals(arrayProdottoDaRimuovere[0])){
                            if(arrayProdottoDaRimuovere.length == 1)
                                prodotto.quantità--;
                            else
                                prodotto.quantità -= Integer.parseInt(arrayProdottoDaRimuovere[1]);
                            if(prodotto.quantità <= 0)
                                listaSpesa.remove(prodotto);
                            flag = true;
                            break;
                        }
                    }
                    if(flag) {
                        mts = new MessageToSend(message.getFrom().getId(), "Rimosso!");
                        sendMessage(mts);
                        ricezione = Ricezione.SPENTO;
                        return;
                    }else{
                        mts = new MessageToSend(message.getFrom().getId(), "Prodotto non presente nella lista");
                        sendMessage(mts);
                        ricezione = Ricezione.SPENTO;
                        return;
                    }
                case SICURO:
                    if(message.getText().toLowerCase().equals("si")) {
                        listaSpesa.clear();
                        mts = new MessageToSend(message.getFrom().getId(), "Lista cancellata!");
                        ricezione = Ricezione.SPENTO;
                    }
                    else if(message.getText().toLowerCase().equals("no"))
                        ricezione = Ricezione.SPENTO;
                    else {
                        mts = new MessageToSend(message.getFrom().getId(), "Comando non valido");
                        ricezione = Ricezione.SICURO;
                    }
                    keyboard = new ArrayList<>();
                    line = new ArrayList<>();
                    line.add(new KeyboardButton("Lista della spesa"));
                    keyboard.add(line);
                    line = new ArrayList<>();
                    line.add(new KeyboardButton("Aggiungi"));
                    line.add(new KeyboardButton("Rimuovi"));
                    line.add(new KeyboardButton("Svuota lista"));
                    keyboard.add(line);
                    replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
                    replyKeyboard.setResizeKeyboard(true);
                    mts.setReplyMarkup(replyKeyboard);
                    sendMessage(mts);
                    return;
            }
        }else {
            switch (ComandoSpesa.fromString(message.getText())) {
                case START:
                    keyboard = new ArrayList<>();
                    line = new ArrayList<>();
                    id = message.getFrom().getId();
                    line.add(new KeyboardButton("Lista della spesa"));
                    keyboard.add(line);
                    line = new ArrayList<>();
                    line.add(new KeyboardButton("Aggiungi"));
                    line.add(new KeyboardButton("Rimuovi"));
                    line.add(new KeyboardButton("Svuota lista"));
                    keyboard.add(line);
                    replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
                    replyKeyboard.setResizeKeyboard(true);
                    if(!flag2) {
                        flag2 = true;
                        mts = new MessageToSend(message.getFrom().getId(), "Benvenuto!");
                    } else
                        mts = new MessageToSend(message.getFrom().getId(), "");
                    mts.setReplyMarkup(replyKeyboard);
                    sendMessage(mts);
                    return;
                case AGGIUNGI:
                    ricezione = Ricezione.AGGIUNGI;
                    mts = new MessageToSend(message.getFrom().getId(), "Inserisci NomeProdotto_quantità da inserire");
                    sendMessage(mts);
                    return;

                case RIMUOVI:
                    ricezione = Ricezione.RIMUOVI;
                    mts = new MessageToSend(message.getFrom().getId(), "Inserisci NomeProdotto_quantità da rimuovere");
                    sendMessage(mts);
                    return;

                case LISTA:
                    if (!listaSpesa.isEmpty()) {
                        for (Prodotto prodotto : listaSpesa) {
                            mts = new MessageToSend(message.getFrom().getId(), listaSpesa.indexOf(prodotto) + " " + prodotto.nome + " " + prodotto.quantità);
                            sendMessage(mts);
                        }
                    } else {
                        mts = new MessageToSend(message.getFrom().getId(), "Lista vuota!");
                        sendMessage(mts);

                    }
                    return;
                case SVUOTA:
                    ricezione = Ricezione.SICURO;
                    keyboard.clear();
                    line.clear();
                    line.add(new KeyboardButton("SI"));
                    line.add(new KeyboardButton("NO"));
                    keyboard.add(line);
                    replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
                    replyKeyboard.setResizeKeyboard(true);
                    mts = new MessageToSend(message.getFrom().getId(), "Sei sicuro?");
                    mts.setReplyMarkup(replyKeyboard);
                    sendMessage(mts);
                    return;
            }
        }
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
