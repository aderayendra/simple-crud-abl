package id.aderayendra.simplecrudabl.repository;

import id.aderayendra.simplecrudabl.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdukRepository extends JpaRepository<Produk, String> {
}
