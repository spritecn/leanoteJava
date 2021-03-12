package github.spritecn.leanotJava.dao.utils

import github.spritecn.leanotJava.model.UserModel
import groovy.util.logging.Slf4j
import org.junit.platform.commons.util.StringUtils
import spock.lang.*

@Slf4j
class SqlGeneratorTest extends Specification {
    @Ignore
    def "test genDefaultUpdateByIdSql"(){
        given:
        def user = new UserModel();
        user.setEmail("test@qq.com")
        user.setIsDeleted(true)
        user.setId(1L)
        when:
        def sql = SqlGenerator.genDefaultUpdateByIdSql(user,"leanote_user")
        log.info(sql);
        then:
        StringUtils.isNotBlank(sql)
    }

    def "test genDefaultInsertByModelSql"(){
        given:
        def user = new UserModel();
        user.setEmail("test@qq.com")
        user.setIsDeleted(true)
        user.setId(1L)
        user.setCreatedTime((System.currentTimeMillis()/1000L as BigDecimal).longValue())
        when:
        def sql = SqlGenerator.genDefaultInsertByModelSql(user,"leanote_user")
        log.info(sql);
        then:
        StringUtils.isNotBlank(sql)
    }

    def "test genDefaultListAllSql"(){
        when:
        def sql = SqlGenerator.genDefaultListAllSql(UserModel.class,"leanote_user")
        log.info(sql);
        then:
        StringUtils.isNotBlank(sql)
    }


}
