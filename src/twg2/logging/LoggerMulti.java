package twg2.logging;

import java.io.Closeable;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.logging.Level;

/** A {@link Logger} which logs to multiple {@link LogService}s
 * @author TeamworkGuy2
 * @since 2014-12-6
 */
public class LoggerMulti implements Logger, Closeable {
	private LogService[] logs;
	private Class<?>[] types;
	private int[] levels;
	private Level[] levelObjs;
	private int leastLevel;
	private Level leastLevelObj;


	@SafeVarargs
	public LoggerMulti(LoggerImpl... logs) {
		this(toEntries(extractLogs(logs), extractLogClasses(logs)));
	}


	/**
	 * @param logsAry the set of logs create a logging wrapper for
	 */
	public LoggerMulti(Class<?>[] typesAry, LogService[] logsAry) {
		this(toEntries(logsAry, typesAry));
	}


	public LoggerMulti(Entry<LogService, Class<?>>[] logsAry) {
		Arrays.sort(logsAry, (l1, l2) -> {
			int v1 = l1.getKey().getLevelValue();
			int v2 = l2.getKey().getLevelValue();
			return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);
		});

		int logCount = logsAry.length;

		this.logs = new LogService[logCount];
		this.types = new Class[logCount];
		this.levels = new int[logCount];
		this.levelObjs = new Level[logCount];

		Level leastLevelObj = null;
		for(int i = 0; i < logCount; i++) {
			Entry<LogService, Class<?>> logI = logsAry[i];
			this.logs[i] = logI.getKey();
			this.types[i] = logI.getValue();
			this.levelObjs[i] = logI.getKey().getLevel();
			this.levels[i] = logI.getKey().getLevelValue();
			if(leastLevelObj == null || logI.getKey().getLevelValue() > leastLevelObj.intValue()) {
				leastLevelObj = logI.getKey().getLevel();
			}
		}

		this.leastLevel = levels[0];
		this.leastLevelObj = levelObjs[0];
	}


	@Override
	public Level getLevel() {
		return leastLevelObj;
	}


	@Override
	public int getLevelValue() {
		return leastLevel;
	}


	@Override
	public boolean wouldLog(Level level) {
		return level.intValue() >= this.leastLevel;
	}


	@Override
	public void log(Level level, String msg) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Throwable thrown) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, thrown);
			}
		}
	}


	@Override
	public void log(Level level, String msg, String str, Throwable thrown) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, str, thrown);
			}
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB, Throwable thrown) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, strA, strB, thrown);
			}
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB, String strC, Throwable thrown) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, strA, strB, strC, thrown);
			}
		}
	}


	@Override
	public void log(Level level, String msg, String str) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, str);
			}
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, strA, strB);
			}
		}
	}


	@Override
	public void log(Level level, String msg, String strA, String strB, String strC) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, strA, strB, strC);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Object param) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, param);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Object paramA, Object paramB) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, paramA, paramB);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Object paramA, Object paramB, Object paramC) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, paramA, paramB, paramC);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Object param, Throwable thrown) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, param, thrown);
			}
		}
	}


	@Override
	public void log(Level level, String msg, int a) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, a);
			}
		}
	}


	@Override
	public void log(Level level, String msg, int a, int b) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, a, b);
			}
		}
	}


	@Override
	public void log(Level level, String msg, int a, int b, int c) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, a, b, c);
			}
		}
	}


	@Override
	public void log(Level level, String msg, float a) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, a);
			}
		}
	}


	@Override
	public void log(Level level, String msg, float a, float b) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, a, b);
			}
		}
	}


	@Override
	public void log(Level level, String msg, float a, float b, float c) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, a, b, c);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Object[] paramAry) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, paramAry);
			}
		}
	}


	@Override
	public void log(Level level, String msg, Object[] paramAry, Throwable thrown) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msg, paramAry, thrown);
			}
		}
	}


	@Override
	public void log(Level level, Supplier<String> msgSupplier) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], msgSupplier);
			}
		}
	}


	@Override
	public void log(Level level, Throwable thrown, Supplier<String> msgSupplier) {
		int levelVal = level.intValue();
		if(levelVal >= this.leastLevel) {
			for(int i = 0, size = logs.length; i < size; i++) {
				if(levels[i] > levelVal) {
					break;
				}
				logs[i].log(level, types[i], thrown, msgSupplier);
			}
		}
	}


	/** Closing this log wrapper closes the underlying {@link LogService} instance
	 * which may also close other {@link Logger LogWrappers} associated with
	 * the underlying {@link LogService} instance.
	 */
	@Override
	public void close() throws IOException {
		for(int i = 0, size = logs.length; i < size; i++) {
			logs[i].close();
		}
	}


	@Override
	public String toString() {
		return "{ level: " + this.getLevelValue() + ", levels: " + Arrays.toString(levels) + ", logClasses: " + Arrays.toString(types) + ", loggers: " + Arrays.toString(logs) + " }";
	}


	@SafeVarargs
	static final Class<?>[] extractLogClasses(LoggerImpl... logs) {
		int logCount = logs.length;
		Class<?>[] logTypes = new Class[logCount];
		for(int i = 0; i < logCount; i++) {
			logTypes[i] = logs[i].getLogForClass();
		}
		return logTypes;
	}


	@SafeVarargs
	static final LogService[] extractLogs(LoggerImpl... logs) {
		int logCount = logs.length;
		LogService[] loggers = new LogService[logCount];
		for(int i = 0; i < logCount; i++) {
			loggers[i] = logs[i].getWrappedLog();
		}
		return loggers;
	}


	static final Entry<LogService, Class<?>>[] toEntries(LogService[] logsAry, Class<?>[] typesAry) {
		int logCount = logsAry.length;
		@SuppressWarnings("unchecked")
		Entry<LogService, Class<?>>[] entries = new Entry[logCount];
		for(int i = 0; i < logCount; i++) {
			entries[i] = new AbstractMap.SimpleImmutableEntry<>(logsAry[i], typesAry[i]);
		}
		return entries;
	}

}
