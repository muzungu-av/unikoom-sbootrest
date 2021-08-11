package ru.avv.unikoomapp.data.dao.foto;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.avv.unikoomapp.data.entity.Foto;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FotoDAOImpl implements FotoDAO {

    private EntityManager entityManager;

    @Autowired
    public FotoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Foto> findAll(String id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Foto> query = session.createQuery("from Foto where person_id = :id", Foto.class);
        query.setParameter("id", Long.decode(id));
        List<Foto> fotos = query.getResultList();
        return fotos;
    }

    @Transactional
    public Foto findOne(String file_hash) {
        Session session = entityManager.unwrap(Session.class);
        Query<Foto> query = session.createQuery("from Foto where file_hash = :file_hash", Foto.class);
        query.setParameter("file_hash", file_hash);
        Foto foto = query.getSingleResult();
        return foto;
    }

    @Transactional
    public Foto addOne(Foto foto) {
        Session session = entityManager.unwrap(Session.class);
        session.save(foto);
        return foto;
    }
}
