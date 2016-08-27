package twg2.logging;

import java.io.PrintStream;
import java.time.Instant;
import java.util.logging.Level;

public enum LoggingPrefixFormat implements Logging.Formatter {
	NONE() {
		@Override public void format(PrintStream out, Level level, Class<?> cls) {
			// do nothing
		}
	},

	LEVEL() {
		@Override public void format(PrintStream out, Level level, Class<?> cls) {
			out.printf("%d, ", level.intValue());
		}
	},

	LEVEL_AND_CLASS() {
		@Override public void format(PrintStream out, Level level, Class<?> cls) {
			out.printf("%d, [%s] ", level.intValue(), cls.getCanonicalName());
		}
	},

	DATETIME_LEVEL_AND_CLASS() {
		@Override public void format(PrintStream out, Level level, Class<?> cls) {
			out.printf("[%s] %d, [%s] ", LoggingImpl.dateFormater.format(Instant.now()), level.intValue(), cls.getCanonicalName());
		}
	};

}

