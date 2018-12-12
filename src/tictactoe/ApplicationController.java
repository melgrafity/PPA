package tictactoe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tictactoe.event.TicTacToeEvent;
import tictactoe.event.TicTacToeEventHandler;

/**
 * Created by pwilkin on 22-Nov-18.
 */
public class ApplicationController {
//do niego się jedynie zgłasza chęć obsługi zdarzenia lub że wystąpiło jakieś zdarzenie
    //tu się informujemy co się dzieje. kto będzie chciał,to to w odpowiedni sposób obsłuży.


    protected Map<Class<? extends TicTacToeEvent>, List<TicTacToeEventHandler<TicTacToeEvent>>> handlers;

    public ApplicationController() {
        handlers = new HashMap<>();
    }

    public <T extends TicTacToeEvent> void registerHandler(Class<T> eventClass, TicTacToeEventHandler<T> handler) {
        if (!handlers.containsKey(eventClass)) {
            handlers.put(eventClass, new ArrayList<>());
        }
        handlers.get(eventClass).add((TicTacToeEventHandler<TicTacToeEvent>) handler);
    }

    public <T extends TicTacToeEvent> void unregisterHandler(Class<T> eventClass, TicTacToeEventHandler<T> handler) {
        if (handlers.containsKey(eventClass)) {
            handlers.get(eventClass).remove(handler);
        }
    }

    public <T extends TicTacToeEvent> void handleEvent(T event) {
        if (handlers.containsKey(event.getClass())) {//czy ktoś nam nie zarejestrował handlera do takiego zdarzenia?
            handlers.get(event.getClass()).forEach(x -> x.handleEvent(event));
        }
    }

}
