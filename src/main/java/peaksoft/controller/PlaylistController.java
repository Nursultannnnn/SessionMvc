package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Playlist;
import peaksoft.service.PlaylistService;

import java.util.List;

@Controller
@RequestMapping("/playlists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    @GetMapping
    public String getAll(Model model) {
        List<Playlist> allplaylists = playlistService.getAllPlaylists();
        model.addAttribute("playlists", allplaylists);
        return "mainPage";
    }
    //новые добавленные
    @GetMapping("/new")
    public String createPlaylist(Model model){
        model.addAttribute("newPlaylist", new Playlist());
        return "newPlaylist";
    }

    @PostMapping("/save")
    public String savePlaylist(@ModelAttribute("playlist") Playlist playlist){
        playlistService.savePlaylist(playlist);
        return "redirect:/playlists";
    }


    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Playlist playlist = playlistService.getById(id);
        model.addAttribute("playlist", playlist);
        return "playlistById";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model){
        Playlist playlist = playlistService.getById(id);
        model.addAttribute("editPlaylist", playlist);
        return "editPlaylist";
    }

    @PostMapping("/update/{id}")
    public String updatePlaylist(@PathVariable("id") Long id, @ModelAttribute("editPlaylist") Playlist playlist){
        playlistService.updatePlaylist(id, playlist);
        return "redirect:/playlists";
    }

}
