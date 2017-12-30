# Change Log
All notable changes to this project will be documented in this file.
This project does its best to adhere to [Semantic Versioning](http://semver.org/).


--------
###[0.3.0](N/A) - 2017-12-30
#### Changed
* Upgrade to Java 9
* Upgrade to JUnit 5
__Renamed everything except `Twg2Logs`__
* `LogWrapper` -> `Logger`
* `LogWrapperImpl` -> `LoggerImpl`
* `LogWrapperMulti` -> `LoggerMulti`
* `Logging` -> `LogService`
* `LoggingImpl` -> `LogServiceImpl`
* `LoggingMulti` -> `LogServiceMulti`
* `LoggingPrefixFormat` -> `LogPrefixFormat`
* `Logging.Formatter` -> `LogService.PrefixFormatter`
* Moved `Logging.wouldLog(LogWrapper, Level)` -> `Logger.wouldLog(Logger, Level)`

#### Removed
* Removed `log(...)` overloads with more than 3 extra data parameters


--------
###[0.2.0](https://github.com/TeamworkGuy2/JTwg2Logging/commit/73213e7da2464e9bb1556e9fb6930539d9825d18) - 2016-2-28
#### Changed
* Moved LoggingImpl.PrefixFormat to separate LoggingPrefixFormat enum
* Changed compiled jar path
* Switched versions.md format to CHANGELOG.md, see http://keepachangelog.com/


--------
###[0.1.0](https://github.com/TeamworkGuy2/JTwg2Logging/commit/482de2490a1c2b4aec02c1d9e2c2975677be8a57) - 2016-2-24
#### Added
* Moved existing code from [JFileIo] (https://github.com/TeamworkGuy2/JFileIo) library into this library
* Removed and modified some method signatures to more closely match the java.util.logging API
