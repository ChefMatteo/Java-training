package randomMedia;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.AudioReferenceToSend;
import com.botticelli.bot.request.methods.PhotoReferenceToSend;
import com.botticelli.bot.request.methods.VoiceReferenceToSend;
import com.botticelli.bot.request.methods.types.*;

import java.util.ArrayList;
import java.util.Random;

public class RandomMedia extends Bot {
    Random r = new Random();
    ArrayList<String> listaFoto;
    ArrayList<String> listaMusica;
    ArrayList<String> listaAudio;

    public RandomMedia(String token) {
        super(token);
        listaAudio = new ArrayList<>();
        listaFoto = new ArrayList<>();
        listaMusica = new ArrayList<>();
    }

    @Override
    public void textMessage(Message message) {

    }

    @Override
    public void audioMessage(Message message) {

    }

    @Override
    public void videoMessage(Message message) {

    }

    @Override
    public void voiceMessage(Message message) {
        listaAudio.add(message.getVoice().getFileID());
        int n = r.nextInt(listaAudio.size());
        sendVoicebyReference(new VoiceReferenceToSend(message.getChat().getId(), listaAudio.get(n)));

    }

    @Override
    public void stickerMessage(Message message) {

    }

    @Override
    public void documentMessage(Message message) {

    }

    @Override
    public void photoMessage(Message message) {
        listaFoto.add(message.getPhoto().get(0).getFileID());
        int n = r.nextInt(listaFoto.size());
        sendPhotobyReference(new PhotoReferenceToSend(message.getChat().getId(), listaFoto.get(n)));
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
