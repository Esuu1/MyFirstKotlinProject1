import mu.KotlinLogging

class Logger {
    companion object {
        val log = KotlinLogging.logger {}
        init {
            System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "DEBUG")
            System.setProperty("org.slf4j.simpleLogger.log.activity.Logger", "DEBUG")
        }

        fun info(message: String) {
            log.info(message)
        }

        fun error(message: String, throwable: Throwable? = null) {
            log.error(message, throwable)
        }

        fun warn(message: String) {
            log.warn(message)
        }
    }
}