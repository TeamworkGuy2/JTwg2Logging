package twg2.logging;

import java.util.function.Supplier;
import java.util.logging.Level;

/** A {@link LogService} wrapper for a specific class
 * @author TeamworkGuy2
 * @since 2015-2-7
 */
public interface Logger {

	/**
	 * @return the current logging level of this wrapper, values greater than this level
	 * are saved, values less than this level are ignored/dropped.
	 */
	public Level getLevel();

	/**
	 * @return the {@link Level} value associated with this log
	 */
	public int getLevelValue();

	/** Check if the specified value would be logged by this log wrapper
	 * @param level the level to compare to this object's log level
	 * @return true if the specified log level is greater than or equal to this
	 * object's log level, false if the specified log level is less than this object's log level
	 */
	public boolean wouldLog(Level level);


	public void log(Level level, String msg);

	public void log(Level level, String msg, Throwable thrown);

	public void log(Level level, String msg, String str, Throwable thrown);

	public void log(Level level, String msg, String strA, String strB, Throwable thrown);

	public void log(Level level, String msg, String strA, String strB, String strC, Throwable thrown);

	public void log(Level level, String msg, String str);

	public void log(Level level, String msg, String strA, String strB);

	public void log(Level level, String msg, String strA, String strB, String strC);

	public void log(Level level, String msg, Object param);

	public void log(Level level, String msg, Object paramA, Object paramB);

	public void log(Level level, String msg, Object paramA, Object paramB, Object paramC);

	public void log(Level level, String msg, Object param, Throwable thrown);

	public void log(Level level, String msg, int a);

	public void log(Level level, String msg, int a, int b);

	public void log(Level level, String msg, int a, int b, int c);

	public void log(Level level, String msg, float a);

	public void log(Level level, String msg, float a, float b);

	public void log(Level level, String msg, float a, float b, float c);

	public void log(Level level, String msg, Object[] paramAry);

	public void log(Level level, String msg, Object[] paramAry, Throwable thrown);

	public void log(Level level, Supplier<String> msgSupplier);

	public void log(Level level, Throwable thrown, Supplier<String> msgSupplier);


	/** Whether the specified {@code Logger} would log a message at the specified log level
	 */
	public static boolean wouldLog(Logger log, Level level) {
		return log != null && log.wouldLog(level);
	}

}