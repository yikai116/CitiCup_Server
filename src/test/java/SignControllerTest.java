import dao.CodeMapper;
import entity.Code;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/** 
* SignController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 31, 2017</pre> 
* @version 1.0 
*/


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-mybaits.xml"})
public class SignControllerTest { 

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
//TODO: Test goes here... 
} 

/** 
* 
* Method: signUp(@RequestBody SignUpParam param) 
* 
*/ 
@Test
public void testSignUp() throws Exception { 
//TODO: Test goes here... 
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

@Autowired
private CodeMapper codeMapper;
@Test
public void testGetVerCode() throws Exception { 
//TODO: Test goes here...
    Code code = codeMapper.findByPhone("15196673448");
    System.out.println(code.getDate());
} 


} 
