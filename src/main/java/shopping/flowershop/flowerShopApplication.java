package shopping.flowershop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import shopping.flowershop.domain.Address;
import shopping.flowershop.domain.Member;
import shopping.flowershop.service.MemberService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class flowerShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(flowerShopApplication.class, args);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernateSetting");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try{
			Address address = new Address("1","2","00000");
			Member member = new Member("xd","abfs","aac",address,"xda@gmail.com","000-500-1111");
			tx.commit();
		}catch (Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();


	}

}
