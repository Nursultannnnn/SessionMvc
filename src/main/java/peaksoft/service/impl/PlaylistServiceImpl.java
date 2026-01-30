package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import peaksoft.entity.Playlist;
import peaksoft.repo.PlaylistRepo;
import peaksoft.service.PlaylistService;

import java.util.List;
@Service//add this annotation
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    @Qualifier("playlistRepo")// добавили эту анотацию почему . чтобы не выходило ошиибки насчет плейлист репо .
    private final PlaylistRepo playListRepo;

    @Override
    public void savePlaylist(Playlist playlist) {
        playListRepo.savePlaylist(playlist);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playListRepo.getAllPlaylists();
    }

    @Override
    public Playlist getById(Long id) {
        return playListRepo.getById(id);
    }

    @Override
    public void updatePlaylist(Long id, Playlist newPlaylist) {
        playListRepo.updatePlaylist(id,newPlaylist);
    }

    @Override
    public void deletePlaylist(Long id) {
        playListRepo.deletePlaylist(id);
    }
}
