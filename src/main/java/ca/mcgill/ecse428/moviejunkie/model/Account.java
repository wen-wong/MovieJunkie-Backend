package ca.mcgill.ecse428.moviejunkie.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    private String username;
    private String password;
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_playlist",
            joinColumns = @JoinColumn(name = "account_username"),
            inverseJoinColumns = @JoinColumn(name= "playlist_id"))
    private Set<Playlist> playlists;

    public Account(){}

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.playlists = new HashSet<Playlist>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Playlist> getPlaylists() { return playlists; }
    public void setPlaylists(Set<Playlist> playlists) { this.playlists = playlists; }
    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }
    public void removePlaylist(int id) {
        Playlist playlist = null;
        for (Playlist plylst : this.playlists) {
            if (id == plylst.getId()) {
                playlist = plylst;
            }
        }
        if (playlist != null) {
            this.playlists.remove(playlist);
            playlist.setAccount(null);
        }
    }
}
