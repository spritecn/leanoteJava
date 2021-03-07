package github.spritecn.leanotJava.util

import spock.lang.Specification
import spock.lang.Unroll

class Md5UtilTest extends Specification {
    @Unroll
    def "Md5"() {
        when:
            def result = Md5Util.md5(s)
        then:
            result == s1
        where:
            s|s1
            "test"|"098f6bcd4621d373cade4e832627b4f6"
            "中国"|"c13dceabcb143acd6c9298265d618a9f"
            "3"|"eccbc87e4b5ce2fe28308fd9f2a7baf3"
            "-----fd，，，，"|"a66f25c730e8da024482e80387548aa8"
    }
}
