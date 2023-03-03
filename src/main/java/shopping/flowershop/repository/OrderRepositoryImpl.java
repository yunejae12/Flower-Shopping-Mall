package shopping.flowershop.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shopping.flowershop.domain.Order;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final EntityManager em;

    @Override
    public void save(Order order){
        em.persist(order);
    }

    @Override
    public Order findOne(Long orderId){
        return em.find(Order.class,orderId);
    }


    @Override
    public List<Order> findAllByMemberByOrderByDateDesc(Long memberId){
        return em.createQuery("select o from Order o join o.member m where m.id = :memberId order by o.createdTime desc",Order.class)
                .setParameter("memberId",memberId)
                .getResultList();
    }

}
