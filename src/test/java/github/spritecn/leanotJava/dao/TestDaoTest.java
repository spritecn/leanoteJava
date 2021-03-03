package github.spritecn.leanotJava.dao;

import github.spritecn.leanotJava.model.TestModel;
import github.spritecn.leanotJava.util.DbConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;


public class TestDaoTest {
    TestDao testDao = null;
    @Before
    public void init(){
        if(Objects.isNull(testDao)) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("dbPath", "jdbc:sqlite:D:/sqlite/leanote.db");
            DbConnectionFactory.init(configMap);
            testDao = new TestDao();
        }
    }

    @Test
    public void getById() {
        TestModel testModel = testDao.getById(1L);
        assertEquals(1L, (long) testModel.getId());
    }

    @Test
    public void listAll() {
        List<TestModel> testModelList = testDao.ListAll();
        assertTrue(testModelList.size()>0);
    }

    @Test
    public void updateById() {
        TestModel testModel = new TestModel();
        testModel.setId(1L);
        testModel.setName("2222");
        int result = testDao.updateById(testModel);
        assertEquals(1, result);
    }

    @Test
    public void insertAndReturnId() {
        TestModel testModel = new TestModel();
        testModel.setName("444");
        Long id = testDao.insertAndReturnId(testModel);
        assertTrue(id > 0);
    }
}