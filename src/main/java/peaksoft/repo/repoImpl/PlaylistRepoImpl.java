package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Playlist;
import peaksoft.repo.PlaylistRepo;

import java.util.List;
@Repository // <--- Добавлена аннотация
@Transactional // Желательно добавить транзакционность здесь или в сервисе
@RequiredArgsConstructor
public class PlaylistRepoImpl implements PlaylistRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void savePlaylist(Playlist playlist) {
        entityManager.persist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return entityManager.createQuery("select p  from Playlist p", Playlist.class)
                .getResultList();
    }

    @Override
    public Playlist getById(Long id) {
        return entityManager.createQuery("select p from Playlist p left join fetch p.songs where p.id = :id", Playlist.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void updatePlaylist(Long id, Playlist newPlaylist) {
        Playlist playlist = entityManager.find(Playlist.class, id);
        playlist.setTitle(newPlaylist.getTitle());
        playlist.setDescription(newPlaylist.getDescription());
        playlist.setImageUrl(newPlaylist.getImageUrl());
    }

    @Override
    public void deletePlaylist(Long id) {
        Playlist playlist = entityManager.find(Playlist.class, id);
        entityManager.remove(playlist);
    }
}
