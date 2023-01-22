package com.example.projektJAVA.web;

import com.example.projektJAVA.exception.ResourceNotFoundException;
import com.example.projektJAVA.model.Album;
import com.example.projektJAVA.repository.AlbumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AlbumsController {
    @Autowired
    private AlbumsRepository albumsRepository;

    @GetMapping("/albums")
    public String getAllAlbums(Model model){
        if(!this.albumsRepository.findAll().isEmpty()){
            model.addAttribute("listAlbums", this.albumsRepository.findAll());
            return "showAlbums";
        }
        else {
            model.addAttribute("message", "List of albums is empty!");
            return "error";
        }
    }

    @GetMapping("/albums/find/{id}")
    public String getAlbumById(@PathVariable(value = "id") Long albumId, Model model){
        if(this.albumsRepository.findById(albumId).isPresent()){
            Album album = this.albumsRepository.findById(albumId).orElseThrow();

            model.addAttribute("album", album);
            model.addAttribute("header", "Found album:");
            return "showAlbum";
        }
        else{
            model.addAttribute("message", "Album with id: " + albumId + " not found!");
            return "error";
        }

    }

    @GetMapping("/albums/add")
    public String createAlbum(Model model){
        model.addAttribute("album", new Album());
        return "createAlbum";
    }

    @PostMapping("/albums/add")
    public String addAlbum(@ModelAttribute Album album, Model model){
        this.albumsRepository.save(album);

        model.addAttribute("album", album);
        model.addAttribute("header", "Created album:");
        return "showAlbum";
    }

    @GetMapping("/albums/update/{id}")
    public String recreateAlbum(@PathVariable ("id") Long albumId, Model model){
        if(this.albumsRepository.findById(albumId).isPresent()){
            Album album = this.albumsRepository.findById(albumId).orElseThrow();

            model.addAttribute("existingAlbum", album);
            model.addAttribute("updatedAlbum", new Album());
            return "updateAlbum";
        }
        else{
            model.addAttribute("message", "Album with id: " + albumId + " not found");
            return "error";
        }
    }

    @PostMapping("/albums/update/{id}")
    public String updateAlbum(@PathVariable ("id") Long albumId, @ModelAttribute Album album,Model model){
        if(this.albumsRepository.findById(albumId).isPresent()){
            Album existingAlbum = this.albumsRepository.findById(albumId).orElseThrow();

            existingAlbum.setAuthor(album.getAuthor());
            existingAlbum.setTitle(album.getTitle());
            this.albumsRepository.save(existingAlbum);

            model.addAttribute("album", existingAlbum);
            model.addAttribute("header", "Updated album:");
            return "showAlbum";
        }
        else{
            model.addAttribute("message", "Album with id: " + albumId + " not found");
            return "error";
        }


    }

    @DeleteMapping("/albums/delete/{id}")
    public String deleteAlbum(@PathVariable ("id") Long albumId, Model model){
        if(this.albumsRepository.findById(albumId).isPresent()){
            Album existingAlbum = this.albumsRepository.findById(albumId).orElseThrow();
            this.albumsRepository.delete(existingAlbum);

            model.addAttribute("message", "Album with id: " + albumId + " has been deleted.");
        }
        else{
            model.addAttribute("message", "Album with id: " + albumId + " not found");
        }

        return "error";

    }
}
