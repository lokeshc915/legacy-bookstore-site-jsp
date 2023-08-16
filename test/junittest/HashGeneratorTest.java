package junittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bookstore.util.EncryptUtil;
import com.bookstore.util.EncryptUtil.ALGORITHM;


public class HashGeneratorTest {

	@Test
	public void testGenerateMD5AndSHA1AndSHA256() {
		String password = "mysecret";
		
		String passwordForMd5 = EncryptUtil.md5(password);
		String passwordForSHA1 = EncryptUtil.digest(password, ALGORITHM.SHA1);
		String passwordForSHA256 = EncryptUtil.digest(password, ALGORITHM.SHA256);
		
		System.out.println("md5 hash:" + passwordForMd5);
		System.out.println("sha1 hash:" + passwordForSHA1);
        System.out.println("sha256 hash:" + passwordForSHA256);
        
        System.out.println("Kontrol (md5)" + EncryptUtil.matching(passwordForMd5,password, ALGORITHM.MD5));
        System.out.println("sha1 hash:" + EncryptUtil.matching(passwordForSHA1,password, ALGORITHM.SHA1));
        System.out.println("Kontrol (sha256)" + EncryptUtil.matching(passwordForSHA256,password, ALGORITHM.SHA256));
		
	}

}
