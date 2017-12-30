package twg2.logging;

import java.io.IOException;
import java.io.PrintStream;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.logging.Level;

/** A logging utility for logging message.
 * More controlled than System.out, I haven't quite gotten around to using {@link Logger}
 * @author TeamworkGuy2
 * @since 2013-8-10
 */
public final class LogServiceImpl implements LogService {
	static final DateTimeFormatter dateFormater = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneOffset.UTC);
	private boolean doPrintLog = true;
	private PrintStream output;
	private LogService.PrefixFormatter format;
	private PrintStream output2;
	private LogService.PrefixFormatter format2;
	private PrintStream output3;
	private LogService.PrefixFormatter format3;
	private Level level;
	private int levelValue;
	private ArrayList<Level> levels;
	private ArrayList<Class<?>> classes;
	private ArrayList<String> messages;
	private ArrayList<Throwable> errors;
	private ArrayList<Integer> paramStartIndex;
	private ArrayList<Object> params;


	/**
	 * @param level the {@link Level} of messages to log
	 * @param outputStream the output stream to print messages to
	 */
	public LogServiceImpl(Level level, PrintStream outputStream, PrefixFormatter format) {
		this(level, outputStream, format, null, null, null, null);
	}


	public LogServiceImpl(Level level, PrintStream outputStream, PrefixFormatter format, PrintStream outputStream2, PrefixFormatter format2) {
		this(level, outputStream, format, outputStream2, format2, null, null);
	}


	public LogServiceImpl(Level level, PrintStream outputStream, PrefixFormatter format, PrintStream outputStream2, PrefixFormatter format2, PrintStream outputStream3, PrefixFormatter format3) {
		this.level = level;
		this.output = outputStream;
		this.format = format;
		this.output2 = outputStream2;
		this.format2 = format2;
		this.output3 = outputStream3;
		this.format3 = format3;
		if(outputStream == null) {
			doPrintLog = false;
		}
		this.levelValue = level.intValue();
		levels = new ArrayList<Level>();
		classes = new ArrayList<Class<?>>();
		messages = new ArrayList<String>();
		errors = new ArrayList<Throwable>();
		paramStartIndex = new ArrayList<Integer>();
		params = new ArrayList<Object>();
	}


	@Override
	public Logger createLogger(Class<?> type) {
		return new LoggerImpl(this, type);
	}


	@Override
	public Level getLevel() {
		return level;
	}


	@Override
	public int getLevelValue() {
		return levelValue;
	}


	@Override
	public boolean wouldLog(Level level) {
		return level.intValue() >= this.levelValue;
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				paramStartIndex.add(params.size());
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Throwable thrown) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(thrown);
				paramStartIndex.add(params.size());
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, String str, Throwable thrown) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(thrown);
				paramStartIndex.add(params.size());
				params.add(str);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, String strA, String strB, Throwable thrown) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(thrown);
				paramStartIndex.add(params.size());
				params.add(strA);
				params.add(strB);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, String strA, String strB, String strC, Throwable thrown) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(thrown);
				paramStartIndex.add(params.size());
				params.add(strA);
				params.add(strB);
				params.add(strC);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, String str) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				paramStartIndex.add(params.size());
				params.add(str);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, String strA, String strB) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(strA);
				parameters.add(strB);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, String strA, String strB, String strC) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(strA);
				parameters.add(strB);
				parameters.add(strC);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Object param) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				paramStartIndex.add(params.size());
				params.add(param);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Object paramA, Object paramB) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(paramA);
				parameters.add(paramB);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Object paramA, Object paramB, Object paramC) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(paramA);
				parameters.add(paramB);
				parameters.add(paramC);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Object param, Throwable thrown) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(thrown);
				paramStartIndex.add(params.size());
				params.add(param);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, int a) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				paramStartIndex.add(params.size());
				params.add(a);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, int a, int b) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(a);
				parameters.add(b);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, int a, int b, int c) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(a);
				parameters.add(b);
				parameters.add(c);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, float a) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				paramStartIndex.add(params.size());
				params.add(a);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, float a, float b) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(a);
				parameters.add(b);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, float a, float b, float c) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				parameters.add(a);
				parameters.add(b);
				parameters.add(c);
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Object[] paramAry) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(null);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				for(int i = 0, size = paramAry.length; i < size; i++) {
					parameters.add(paramAry[i]);
				}
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, String msg, Object[] paramAry, Throwable thrown) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msg);
				errors.add(thrown);
				ArrayList<Object> parameters = params;
				paramStartIndex.add(parameters.size());
				for(int i = 0, size = paramAry.length; i < size; i++) {
					parameters.add(paramAry[i]);
				}
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, Supplier<String> msgSupplier) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msgSupplier.get());
				errors.add(null);
				paramStartIndex.add(params.size());
				output();
			}
		}
	}


	@Override
	public void log(Level level, Class<?> clazz, Throwable thrown, Supplier<String> msgSupplier) {
		if(level.intValue() >= this.levelValue) {
			synchronized(this) {
				levels.add(level);
				classes.add(clazz);
				messages.add(msgSupplier.get());
				errors.add(thrown);
				paramStartIndex.add(params.size());
				output();
			}
		}
	}

	@Override
	public void close() throws IOException {
		doPrintLog = false;
		output.close();
	}


	@Override
	public String toString() {
		return "{ level: " + this.getLevelValue() + ", out: " + output + (output2 != null ? ", output2: " + output2 : "") + (output3 != null ? ", output3: " + output3 : "") + " }";
	}


	private void output() {
		output(output, format);
		if(output2 != null) {
			output(output2, format2);
		}
		if(output3 != null) {
			output(output3, format3);
		}
	}


	private void output(PrintStream out, PrefixFormatter format) {
		if(!doPrintLog) {
			return;
		}

		int index = levels.size() - 1;

		if(format != null) {
			format.format(out, levels.get(index), classes.get(index));
		}

		int startIndex = paramStartIndex.get(index);
		int paramCount = (index == paramStartIndex.size()-1) ? params.size()-startIndex
				: paramStartIndex.get(index+1)-startIndex;
		Object[] args = new Object[paramCount];
		for(int i = startIndex, a = 0; i < startIndex+paramCount; i++, a++) {
			args[a] = params.get(i);
		}
		out.printf(messages.get(index), args);
		if(errors.get(index) != null) {
			out.printf(", error: ");
			errors.get(index).printStackTrace(out);
		}
		out.println();
	}

}
