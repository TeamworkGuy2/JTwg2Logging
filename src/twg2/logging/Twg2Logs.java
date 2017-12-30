package twg2.logging;

import java.io.PrintStream;
import java.util.logging.Level;

import twg2.logging.LogService.PrefixFormatter;

/**
 * @author TeamworkGuy2
 * @since 2016-1-23
 */
public final class Twg2Logs {
	private static volatile Twg2Logs defaultInst;
	private static final Object lock = new Object();

	@SuppressWarnings("unused")
	private final Level level;
	@SuppressWarnings("unused")
	private final PrintStream outputStream;
	@SuppressWarnings("unused")
	private final LogService.PrefixFormatter format;
	private final LogServiceImpl inst;


	public Twg2Logs(Level level, PrintStream outputStream, LogService.PrefixFormatter format) {
		this.level = level;
		this.outputStream = outputStream;
		this.format = format;
		this.inst = new LogServiceImpl(level, outputStream, format);
	}


	public LoggerImpl createLog(Class<?> cls) {
		return new LoggerImpl(this.inst, cls);
	}


	public static final boolean tryToInitialize(Level level, PrintStream outputStream, LogService.PrefixFormatter format) {
		synchronized (lock) {
			if(defaultInst != null) {
				return false;
			}
			Twg2Logs inst = new Twg2Logs(level, outputStream, format);
			defaultInst = inst;
			return true;
		}
	}


	public static final Twg2Logs initialize(Level level, PrintStream outputStream, PrefixFormatter format) {
		synchronized (lock) {
			if(defaultInst != null) {
				throw new IllegalStateException("cannot create default instance, initialize() has already been called");
			}
			Twg2Logs inst = new Twg2Logs(level, outputStream, format);
			defaultInst = inst;
			return inst;
		}
	}


	public static final boolean isInitialized() {
		synchronized (lock) {
			return defaultInst != null;
		}
	}


	public static final Twg2Logs defaultInst() {
		synchronized (lock) {
			if(defaultInst == null) {
				throw new IllegalStateException("cannot access default instance, initialize() has not been called");
			}
			return defaultInst;
		}
	}

}
