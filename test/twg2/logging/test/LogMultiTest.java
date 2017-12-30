package twg2.logging.test;

import java.util.logging.Level;

import org.junit.Test;

import twg2.logging.LoggerImpl;
import twg2.logging.LoggerMulti;
import twg2.logging.LogService;
import twg2.logging.LogServiceImpl;
import twg2.logging.LogServiceMulti;
import twg2.logging.LogPrefixFormat;

/**
 * @author TeamworkGuy2
 * @since 2015-10-3
 */
public class LogMultiTest {

	@Test
	public void loggingMultiTest() {
		String pre = "logging - ";
		LogServiceImpl log1 = new LogServiceImpl(Level.FINE, System.out, LogPrefixFormat.LEVEL_AND_CLASS);
		LogServiceImpl log2 = new LogServiceImpl(Level.INFO, System.out, LogPrefixFormat.LEVEL_AND_CLASS);

		@SuppressWarnings("resource")
		LogServiceMulti logMulti = new LogServiceMulti(new LogService[] { log1, log2 });

		logMulti.log(Level.FINER, LogMultiTest.class, "!! " + pre + "shouldn't see");
		logMulti.log(Level.CONFIG, LogMultiTest.class, pre + "should log once");
		logMulti.log(Level.INFO, LogMultiTest.class, pre + "should log twice");
	}


	@Test
	public void logWrapperMultiTest() {
		String pre = "wrapped - ";
		LogServiceImpl logging1 = new LogServiceImpl(Level.FINE, System.out, LogPrefixFormat.LEVEL_AND_CLASS);
		LogServiceImpl logging2 = new LogServiceImpl(Level.INFO, System.out, LogPrefixFormat.LEVEL_AND_CLASS);

		LoggerImpl log1 = new LoggerImpl(logging1, String.class);
		LoggerImpl log2 = new LoggerImpl(logging2, Number.class);

		@SuppressWarnings("resource")
		LoggerMulti logMulti = new LoggerMulti(new LoggerImpl[] { log1, log2 });

		logMulti.log(Level.FINER, "!! " + pre + "shouldn't see");
		logMulti.log(Level.CONFIG, pre + "should log once");
		logMulti.log(Level.INFO, pre + "should log twice");
	}


}
