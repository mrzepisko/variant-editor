package io.github.mrzepisko.varianteditor.dao;

import io.github.mrzepisko.varianteditor.model.User;
import io.github.mrzepisko.varianteditor.model.Variant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VariantRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VariantRepository repository;

    @Test(expected = PersistenceException.class)
    public void saveVariantDuplicatesTest() {
        Variant v1 = new Variant(1L, "a", "a", "a");
        Variant v2 = new Variant(1L, "a", "a", "a");

        entityManager.persist(v1);
        entityManager.persist(v2);
        entityManager.flush();
    }

    @Test
    public void findByUserIdentifierTest() {
        String user1Id = "asddfhj";
        String user2Id = "ghjfgh";
        String user3Id = "fgtyjdft";
        User user1 = new User(user1Id, "x");
        User user2 = new User(user2Id, "x");
        User user3 = new User(user3Id, "x");
        Variant v1 = new Variant(1L, "alt1", "chr1", "lorem ipsum");
        Variant v2 = new Variant(2L, "alt2", "chr2", "lorem ipsum2");
        Variant v3 = new Variant(3L, "alt3", "chr3", "lorem ipsum3");


        v1.setUser(user1);
        v2.setUser(user1);
        v3.setUser(user2);

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.persist(user3);
        entityManager.persist(v1);
        entityManager.persist(v2);
        entityManager.persist(v3);
        entityManager.flush();

        List<Variant> list1 = repository.findByUserIdentifier(user1Id);
        List<Variant> list2 = repository.findByUserIdentifier(user2Id);
        List<Variant> list3 = repository.findByUserIdentifier(user3Id);

        assertEquals(2, list1.size());
        assertEquals(1, list2.size());
        assertTrue(list3.isEmpty());
    }
}
