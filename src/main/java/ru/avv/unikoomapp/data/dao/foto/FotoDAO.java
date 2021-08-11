package ru.avv.unikoomapp.data.dao.foto;

import ru.avv.unikoomapp.data.entity.Foto;

import java.util.List;

public interface FotoDAO {
    List<Foto> findAll(String id);
    Foto findOne(String file_hash);
    Foto addOne(Foto foto);
}
