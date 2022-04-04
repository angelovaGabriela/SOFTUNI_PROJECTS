package impl.factories;

import impl.MessageLogger;
import interfaces.Appender;
import interfaces.Factory;
import interfaces.Logger;

public class LoggerFactory implements Factory <Logger> {
    AppenderFactory appenderFactory;

    public LoggerFactory(){
        this.appenderFactory = new AppenderFactory();
    }

    @Override
    public Logger produce(String input) {
        String[] tokens = input.split(System.lineSeparator());
        Appender[] appenders = new Appender[tokens.length];

        for (int i = 0; i < appenders.length; i++) {
           appenders[i] = this.appenderFactory.produce(tokens[i]);

        }
        return new MessageLogger(appenders);
    }
}
