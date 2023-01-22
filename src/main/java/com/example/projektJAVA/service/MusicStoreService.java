package com.example.projektJAVA.service;

import com.example.projektJAVA.repository.AlbumsRepository;
import com.example.projektJAVA.repository.SalesmenRepository;
import com.example.projektJAVA.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicStoreService {
    @Autowired
    private AlbumsRepository albumsRepository;

    @Autowired
    private SalesmenRepository salesmenRepository;

    @Autowired
    private UsersRepository usersRepository;
}
