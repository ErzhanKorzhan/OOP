package pizzeria.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class LoggingComponent {
    public static final Logger logger = LogManager.getLogger();
}