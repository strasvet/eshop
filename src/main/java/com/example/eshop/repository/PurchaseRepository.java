package com.example.eshop.repository;

import com.example.eshop.model.Product;
import com.example.eshop.model.Purchase;
import com.example.eshop.model.User;
import com.example.eshop.model.dtoByRole.PurchaseForNoAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase ,Integer > {
    //Purchase findByIdAndBoughtBy(Integer id, User boughtBy);
    //Purchase findByIdAndBoughtByAndSoldBy(Integer id, User boughtBy, User soldBy);
    //Purchase findAllByProduct_idAndBoughtBy(Integer id, User boughtBy);

   //@Query(value = "select Purchase from Purchase p where p.product_id like p.boughtBy")
    //Purchase findByProductIdAndBoughtBy(Integer id, User boughtBy);

    //Purchase findByProduct_idAndBoughtBy(Product product, User boughtBy);
    Purchase findByBoughtByAndSoldBy(User bought, User solder);

    @Query("select new com.example.eshop.model.dtoByRole.PurchaseForNoAdmin(p.id,p.product_id.id,p.quantity,p.soldBy.firstName, p.soldBy.lastName, p.boughtBy.firstName, p.boughtBy.lastName, p.sellDate,p.endPrice) from Purchase p")
    List<PurchaseForNoAdmin> getPurchaseForNoAdmin();

}
