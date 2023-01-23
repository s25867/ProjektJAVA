package com.example.demo.service;

import com.example.demo.repository.AlbumsRepository;
import com.example.demo.repository.SalesmenRepository;
import com.example.demo.repository.UsersRepository;
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
