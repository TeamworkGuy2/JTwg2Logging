package twg2.logging.test;

import java.util.logging.Level;

import org.junit.Test;

import twg2.logging.LogWrapperImpl;
import twg2.logging.LogWrapperMulti;
import twg2.logging.Logging;
import twg2.logging.LoggingImpl;
import twg2.logging.LoggingMulti;

/**
 * @author TeamworkGuy2
 * @since 2015-10-3
 */
public class LogMultiTest {

	@Test
	public void loggingMultiTest() {
		String pre = "logging - ";
		LoggingImpl log1 = new LoggingImpl(Level.FINE, System.out, LoggingImpl.PrefixFormat.LEVEL_AND_CLASS);
		LoggingImpl log2 = new LoggingImpl(Level.INFO, System.out, LoggingImpl.PrefixFormat.LEVEL_AND_CLASS);

		@SuppressWarnings("resource")
		LoggingMulti logMulti = new LoggingMulti(new Logging[] { log1, log2 });

		logMulti.log(Level.FINER, LogMultiTest.class, "!! " + pre + "shouldn't see");
		logMulti.log(Level.CONFIG, LogMultiTest.class, pre + "should log once");
		logMulti.log(Level.INFO, LogMultiTest.class, pre + "should log twice");
	}


	@Test
	public void logWrapperMultiTest() {
		String pre = "wrapped - ";
		LoggingImpl logging1 = new LoggingImpl(Level.FINE, System.out, LoggingImpl.PrefixFormat.LEVEL_AND_CLASS);
		LoggingImpl logging2 = new LoggingImpl(Level.INFO, System.out, LoggingImpl.PrefixFormat.LEVEL_AND_CLASS);

		LogWrapperImpl log1 = new LogWrapperImpl(logging1, String.class);
		LogWrapperImpl log2 = new LogWrapperImpl(logging2, Number.class);

		@SuppressWarnings("resource")
		LogWrapperMulti logMulti = new LogWrapperMulti(new LogWrapperImpl[] { log1, log2 });

		logMulti.log(Level.FINER, "!! " + pre + "shouldn't see");
		logMulti.log(Level.CONFIG, pre + "should log once");
		logMulti.log(Level.INFO, pre + "should log twice");
	}


}
