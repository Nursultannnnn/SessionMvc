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

}
