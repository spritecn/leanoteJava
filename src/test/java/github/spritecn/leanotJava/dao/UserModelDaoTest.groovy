package github.spritecn.leanotJava.dao

import github.spritecn.leanotJava.util.IdGenerator

import github.spritecn.leanotJava.util.Md5Util
import github.spritecn.leanotJava.util.TimeUtil
import github.spritecn.leanotJava.model.UserModel
import github.spritecn.leanotJava.util.DbConnectionFactory
import spock.lang.*


@Stepwise
class UserModelDaoTest extends Specification {
     {
        DbConnectionFactory.init(["dbPath": "jdbc:sqlite:d:/sqlite/leanote.db?journal_mode=wal&synchronous=off"])
    }

    @Shared
    UserDao userDao = new UserDao()

    @Ignore
    def "GetById"() {
        when:
            def user = userDao.getById(1L)
        then:
            user.id == 1L
    }

    @Ignore
    def "ListAll"() {
        when:
            def userList = userDao.ListAll()
        then:
            userList.size() > 0
    }

    @Ignore
    def "UpdateById"() {
        given:
            def user = new UserModel();
            user.setEmail("test@qq.com")
            user.setIsDeleted(true)
            user.setId(1L)
        when:
            userDao.updateById(user)
        then:
            def userUpdated = userDao.getById(user.getId())
            userUpdated.email == user.email

    }

    @Ignore
    def "InsertAndReturnId"() {
        given:
            def user = new UserModel();
            user.setEmail("testInsertAndReturnId@qq.com")
            user.setId(1L)
            user.setPwd(Md5Util.md5("test"))
            user.setUserId(IdGenerator.genId())
            user.setCreatedTime(TimeUtil.genTimeStampSecond())
        when:
            def id = userDao.insertAndReturnId(user)
        then:
            id > 1
    }
}
