import com.botticelli.bot.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.botticelli.messagereceiver.MessageReceiver;
import lista_della_spesa.ListaDellaSpesa;
import primoBot.PrimoBot;
import questionario.Questionario;
import randomMedia.RandomMedia;

public class Main {

    public static String filePath;

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException, UnknownHostException, SocketException
    {
        filePath = new File("").getAbsolutePath() + System.getProperty("file.separator");
        File tokenFile = new File(filePath + "token.txt");
        String token = "";
        try (Scanner s = new Scanner(tokenFile))
        {
            while (s.hasNext())
            {
                token = s.nextLine();
            }
        }

        //Bot bot = new PrimoBot(token);
        //Bot bot = new RandomMedia(token);
        //Bot bot = new ListaDellaSpesa(token);
        Bot bot = new Questionario(token);
        MessageReceiver mr = new MessageReceiver(bot, 500, 1);
        mr.ignoreEditedMessages();
        mr.start();

    }
}
