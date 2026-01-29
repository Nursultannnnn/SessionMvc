package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Song;
import peaksoft.repo.SongRepo;
import peaksoft.service.SongService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepo songRepo;
    @Override
    public void saveSongWithPlaylist(Long id, Song song) {
        songRepo.saveSongWithPlaylist(id, song);
    }

    @Override
    public List<Song> getAllSongsByPlaylist(Long id) {
        return songRepo.getAllSongsByPlaylist(id);
    }

    @Override
    public Song getById(Long id) {
        return null;
    }

    @Override
    public void updateSong(Long id, Song song) {

    }

    @Override
    public void deleteSong(Long id) {

    }
}