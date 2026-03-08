package id.aderayendra.simplecrudabl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produk")
public class Produk {

    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false, length = 100)
    private String nama;

    @Column(nullable = false, length = 20)
    private String kategori;

    @Column(nullable = false)
    private Integer harga;

    @Column(nullable = false)
    private Integer stok;
}
