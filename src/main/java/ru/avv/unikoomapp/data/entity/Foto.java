package ru.avv.unikoomapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "foto")
public class Foto {
    @Id
    @SequenceGenerator(name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "id")
    private long id;

    @Column(name = "file_hash")
    private String fileHash;

    @Column(name = "original_file_name")
    private String fileName;

    @Column(name = "person_id")
    private Long personId;

    public Foto() {

    }

    public Foto(String fileHash, String fileName, Long personId) {
        this.fileHash = fileHash;
        this.fileName = fileName;
        this.personId = personId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foto)) return false;

        Foto foto = (Foto) o;

        return getId() == foto.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "Foto{" +
                "id=" + id +
                ", fileHash='" + fileHash + '\'' +
                ", fileName='" + fileName + '\'' +
                ", personId='" + personId + '\'' +
                '}';
    }
}
