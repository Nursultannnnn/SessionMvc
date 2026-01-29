package peaksoft.service;

import peaksoft.entity.Song;

import java.util.List;

public interface SongService {
    void saveSongWithPlaylist(Long id , Song song);
    List<Song> getAllSongsByPlaylist(Long id);
    Song getById(Long id);
    void updateSong(Long id,Song song);
    void deleteSong(Long id);
}