package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Song;
import peaksoft.enums.Genre;
import peaksoft.service.PlaylistService;
import peaksoft.service.SongService;

import java.util.List;
@Controller
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @GetMapping("playlist/{playlistId}")
    public String getAllSongsByPlaylist(@PathVariable("playlistId") Long playlistId, Model model) {
        List<Song> allSongsByPlaylist = songService.getAllSongsByPlaylist(playlistId);
        model.addAttribute("songs", allSongsByPlaylist);
        model.addAttribute("playlistId", playlistId);
        return "songList";
    }

    @GetMapping("/add/{playlistId}")
    public String addSongForm(@PathVariable("playlistId") Long playlistId, Model model) {
        model.addAttribute("newSong", new Song());
        model.addAttribute("playlistId", playlistId);
        model.addAttribute("genres", Genre.values());
        return "newSong";
    }

    @PostMapping("/save/{playlistId}")
    public String saveSong(@PathVariable("playlistId") Long playlistId,
                           @ModelAttribute("newSong") Song song) {
        songService.saveSongWithPlaylist(playlistId, song);
        return "redirect:/songs/playlist/" + playlistId;
    }

    // новшества
    // 1. Открыть страницу редактирования
    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable("songId") Long songId, Model model) {
        Song song = songService.getById(songId);
        // Передаем ID плейлиста, чтобы кнопка "Cancel" и редирект знали куда идти
        model.addAttribute("playlistId", song.getPlaylist().getId());
        model.addAttribute("song", song);
        model.addAttribute("genres", Genre.values());
        return "editSong";
    }

    // 2. Сохранить изменения
    @PostMapping("/update/{songId}")
    public String updateSong(@PathVariable("songId") Long songId,
                             @ModelAttribute("song") Song song,
                             @RequestParam("playlistId") Long playlistId) {
        songService.updateSong(songId, song);
        return "redirect:/playlists/" + playlistId; // Возвращаемся в плейлист
    }

    // 3. Удалить песню
    @PostMapping("/delete/{songId}/{playlistId}")
    public String deleteSong(@PathVariable("songId") Long songId,
                             @PathVariable("playlistId") Long playlistId) {
        songService.deleteSong(songId);
        return "redirect:/playlists/" + playlistId; // Возвращаемся в плейлист
    }

}
