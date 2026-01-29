package peaksoft.repo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Playlist;

import java.util.List;
@Repository
@Transactional
public interface PlaylistRepo {
    void savePlaylist(Playlist playlist);
    List<Playlist> getAllPlaylists();
    Playlist getById(Long id);
    void updatePlaylist(Long id,Playlist newPlaylist);
    void deletePlaylist(Long id);
}
