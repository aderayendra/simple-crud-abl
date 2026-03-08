package id.aderayendra.simplecrudabl;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.aderayendra.simplecrudabl.model.Produk;
import id.aderayendra.simplecrudabl.repository.ProdukRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdukControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        produkRepository.deleteAll();
    }

    @Test
    void testCreateProduk() throws Exception {
        Produk produk = Produk.builder()
                .id("P001")
                .nama("Susu UHT")
                .kategori("Minuman")
                .harga(5000)
                .stok(10)
                .build();

        mockMvc.perform(post("/api/produk")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produk)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("P001"))
                .andExpect(jsonPath("$.nama").value("Susu UHT"));
    }

    @Test
    void testGetAllProduk() throws Exception {
        Produk produk = Produk.builder()
                .id("P001")
                .nama("Susu UHT")
                .kategori("Minuman")
                .harga(5000)
                .stok(10)
                .build();
        produkRepository.save(produk);

        mockMvc.perform(get("/api/produk"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nama").value("Susu UHT"));
    }

    @Test
    void testGetProdukById() throws Exception {
        Produk produk = Produk.builder()
                .id("P001")
                .nama("Susu UHT")
                .kategori("Minuman")
                .harga(5000)
                .stok(10)
                .build();
        produkRepository.save(produk);

        mockMvc.perform(get("/api/produk/P001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("P001"));
    }

    @Test
    void testUpdateProduk() throws Exception {
        Produk produk = Produk.builder()
                .id("P001")
                .nama("Susu UHT")
                .kategori("Minuman")
                .harga(5000)
                .stok(10)
                .build();
        produkRepository.save(produk);

        Produk updatedDetails = Produk.builder()
                .nama("Susu Cokelat")
                .kategori("Minuman")
                .harga(6000)
                .stok(5)
                .build();

        mockMvc.perform(put("/api/produk/P001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nama").value("Susu Cokelat"))
                .andExpect(jsonPath("$.harga").value(6000));
    }

    @Test
    void testDeleteProduk() throws Exception {
        Produk produk = Produk.builder()
                .id("P001")
                .nama("Susu UHT")
                .kategori("Minuman")
                .harga(5000)
                .stok(10)
                .build();
        produkRepository.save(produk);

        mockMvc.perform(delete("/api/produk/P001"))
                .andExpect(status().isNoContent());

        assertThat(produkRepository.findById("P001")).isEmpty();
    }
}
