import component.WordSimilarity;
import dao.CodeMapper;
import dao.InsuProMapper;
import dao.UserMapper;
import entity.Code;
import entity.InsuPro;
import entity.User;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.SignService;

import java.util.ArrayList;
import java.util.Random;

/** 
* SignController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 31, 2017</pre> 
* @version 1.0 
*/


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
public class SignControllerTest { 

    @Autowired
    SignService service;
    @Autowired
    WordSimilarity similarity;
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: signIn(@RequestBody SignInParam param) 
* 
*/ 
@Test
public void testSignIn() throws Exception {
    similarity.readCiLin();
}

/** 
* 
* Method: signUp(@RequestBody SignUpParam param) 
* 
*/ 
@Test
public void testSignUp() throws Exception {
} 

/** 
* 
* Method: findPsw(@RequestBody FindPswParam param) 
* 
*/ 
@Test
public void testFindPsw() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getVerCode() 
* 
*/

@Test
public void testGetVerCode() throws Exception { 
//TODO: Test goes here...
} 


} 
