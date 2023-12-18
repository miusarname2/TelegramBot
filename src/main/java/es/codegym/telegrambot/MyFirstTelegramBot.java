package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "catBot";
    public static final String TOKEN = "6632935496:AAFLw6GMKj1DWdciSgmXYtyXuod-Eb2JzJg";
    public int Id = 0;

    public TelegramBotContent msjs = new TelegramBotContent();

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        // TODO: escribiremos la funcionalidad principal del bot aquí
        // Nota: He estado mirando las funciones y asumiendo en base a ellas y creo que esta nos permite("getMessageText()"), saber que mensaje ha enviado el usuario
        if (getMessageText().equals("/start") && Id == 0){
            sendTextMessageAsync("Bienvenido al Chatbot del gato loco :)");
            sendTextMessageAsync("¡Hola, ¿cómo te llamas?");
            Id += 1;
        } else if (Id == 1) {
            String msj = String.format("Bienvenido %s, a esta aventura...",getMessageText());
            sendTextMessageAsync(msj);
            String msjOfClass = msjs.STEP_1_TEXT;
            sendTextMessageAsync(msjOfClass);
            Id += 1;
        }else{
            sendTextMessageAsync("Comando no reconodicido, prueba con algo como **/start**");
            Id = 0;
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}