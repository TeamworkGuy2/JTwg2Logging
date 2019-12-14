package twg2.logging;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.Level;

/** A {@link Logger} implementation which wraps a {@link LogService}.
 * @author TeamworkGuy2
 * @since 2014-12-6
 */
public class LoggerImpl implements Logger, Closeable {
	protected LogService log;
	protected Class<?> type;
	protected int level;
	protected Level levelObj;


	public LoggerImpl(LogService log, Class<?> type) {
		this(log, type, log.getLevel(), log.getLevelValue());
	}


	public LoggerImpl(LogService log, Class<?> type, Level level) {
		this(log, type, level, level.intValue());
	}


	/** Create a logger which filters logs based on 'level' and logs them to 'log' service with 'type' as the source class
	 * @param log the log service to log to
	 * @param type the class type which will be calling this logger
	 * @param level the default threshold log level
	 */
	public LoggerImpl(LogService log, Class<?> type, Level levelObj, int level) {
		this.log = log;
		this.type = type;
		this.level = level;
		this.levelObj = levelObj;
	}


	@Override
	public Level getLevel() {
		return levelObj;
	}


	@Override
	public int getLevelValue() {
		return level;
	}


	@Override
	public boolean wouldLog(Level level) {
		return level.intValue() >= this.level;
	}


	/**
	 * @return the class that this log wrapper is logging
	 */
	public Class<?> getLogForClass() {
		return this.type;
	}


	/**
	 * @return the underlying logger that this log wrapper logs to
	 */
	public LogService getWrappedLog() {
		return this.log;
	}


	@Override
	public void log(Level level, String msg) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg);
		}
	}


	@Override
	public void log(Level level, String msg, Throwable thrown) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, thrown);
		}
	}


	@Override
	public void log(Level level, String msg, String str, Throwable thrown) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, str, thrown);
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB, Throwable thrown) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, strA, strB, thrown);
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB, String strC, Throwable thrown) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, strA, strB, strC, thrown);
		}
	}


	@Override
	public void log(Level level, String msg, String str) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, str);
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, strA, strB);
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB, String strC) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, strA, strB, strC);
		}
	}


	@Override
	public void log(Level level, String msg, Object param) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, param);
		}
	}


	@Override
	public void log(Level level, String msg, Object paramA, Object paramB) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, paramA, paramB);
		}
	}


	@Override
	public void log(Level level, String msg, Object paramA, Object paramB, Object paramC) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, paramA, paramB, paramC);
		}
	}


	@Override
	public void log(Level level, String msg, Object param, Throwable thrown) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, param, thrown);
		}
	}


	@Override
	public void log(Level level, String msg, int a) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, a);
		}
	}


	@Override
	public void log(Level level, String msg, int a, int b) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, a, b);
		}
	}


	@Override
	public void log(Level level, String msg, int a, int b, int c) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, a, b, c);
		}
	}


	@Override
	public void log(Level level, String msg, float a) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, a);
		}
	}


	@Override
	public void log(Level level, String msg, float a, float b) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, a, b);
		}
	}


	@Override
	public void log(Level level, String msg, float a, float b, float c) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, a, b, c);
		}
	}


	@Override
	public void log(Level level, String msg, Object[] paramAry) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, paramAry);
		}
	}


	@Override
	public void log(Level level, String msg, Object[] paramAry, Throwable thrown) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msg, paramAry, thrown);
		}
	}


	@Override
	public void log(Level level, Supplier<String> msgSupplier) {
		if(level.intValue() >= this.level) {
			log.log(level, type, msgSupplier);
		}
	}


	@Override
	public void log(Level level, Throwable thrown, Supplier<String> msgSupplier) {
		if(level.intValue() >= this.level) {
			log.log(level, type, thrown, msgSupplier);
		}
	}


	/** Closing this log wrapper closes the underlying {@link LogService} instance
	 * which may also close other {@link Logger LogWrappers} associated with
	 * the underlying {@link LogService} instance.
	 */
	@Override
	public void close() throws IOException {
		log.close();
	}


	@Override
	public String toString() {
		return "{ level: " + this.getLevelValue() + ", logClass: " + type + ", logger: " + log + " }";
	}

}
