package github.spritecn.leanotJava.dao.utils

import github.spritecn.leanotJava.util.IdGenerator
import groovy.util.logging.Slf4j
import spock.lang.Specification

import java.util.stream.LongStream

@Slf4j
class IdGeneratorTest extends Specification {
    def "GenId"() {
        given:
            Set<String> set = new TreeSet<>()
            int count = 200_0000
            long  startTime =   System.currentTimeMillis()
        when:
            LongStream.range(0,count).forEach(x-> {
                String id = IdGenerator.genId();
                log.info(id);
                if(!set.add(id)) log.info("{}---repeat---",x)
            })
        then:
            log.info("time:{}ms",System.currentTimeMillis() - startTime)
            set.size() == count

    }
}
