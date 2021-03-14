package github.spritecn.leanotJava.util

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class TimeUtilTest extends Specification {
    def "test getUtcTimeStr"() {
        given:
        def now = new Date();
        when:
        def str = TimeUtil.getUtcTimeStr(now)
        def date = TimeUtil.getUtcDate(str)
        then:
        now == date
    }
}
