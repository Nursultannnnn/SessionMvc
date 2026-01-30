package peaksoft.repo.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.entity.Playlist;
import peaksoft.entity.Song;
import peaksoft.repo.SongRepo;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class SongRepoImpl implements SongRepo {

    @PersistenceContext
    private final EntityManager entityManager;


    @Override
    public void saveSongWithPlaylist(Long id, Song song) {
        Playlist playlist = entityManager.find(Playlist.class, id);
        song.setPlaylist(playlist);
        entityManager.persist(song);
        playlist.getSongs().add(song);
    }

    @Override
    public List<Song> getAllSongsByPlaylist(Long id) {
        return entityManager.createQuery("select s from Song s join fetch s.playlist p where s.playlist.id =:id",Song.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public Song getById(Long id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    public void updateSong(Long id, Song newSong) {
        Song song = entityManager.find(Song.class, id);
        song.setName(newSong.getName());
        song.setDuration(newSong.getDuration());
        song.setGenre(newSong.getGenre());
        entityManager.merge(song);
    }

    @Override
    public void deleteSong(Long id) {
        Song song = entityManager.find(Song.class, id);
        // Важно: перед удалением нужно убрать связь с плейлистом, если это не делается автоматически
        song.setPlaylist(null);
        entityManager.remove(song);
    }
}